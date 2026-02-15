package org.example;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ArctgSeriesTest {
    private static final int HEAVY_MAX_TERMS = 3_000_000;


    @Test
    void shouldReturnZeroForZeroArgument() {
        double actual = ArctgSeries.arctg(0.0, 1e-12, 10);
        assertEquals(0.0, actual, 1e-15);
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/arctg-accuracy-cases.csv", numLinesToSkip = 1)
    void shouldApproximateMathAtanInsideConvergenceInterval(double x, double eps, double tolerance) {
        double actual = ArctgSeries.arctg(x, eps, HEAVY_MAX_TERMS);
        double expected = Math.atan(x);
        assertEquals(expected, actual, tolerance);
    }

    @Test
    void shouldApproximateAtBoundaryXEqualsOne() {
        double actual = ArctgSeries.arctg(1.0, 1e-6, HEAVY_MAX_TERMS);
        assertEquals(Math.PI / 4.0, actual, 1e-4);
    }

    @Test
    void shouldApproximateAtBoundaryXEqualsMinusOne() {
        double actual = ArctgSeries.arctg(-1.0, 1e-6, HEAVY_MAX_TERMS);
        assertEquals(-Math.PI / 4.0, actual, 1e-4);
    }

    @Test
    void shouldReturnFirstTermWhenMaxTermsEqualsOne() {
        double actual = ArctgSeries.arctg(0.5, 1e-12, 1);
        assertEquals(0.5, actual, 1e-15);
    }

    @ParameterizedTest
    @CsvSource({"0.2", "0.7", "0.95"})
    void shouldBeOddFunction(double x) {
        double positive = ArctgSeries.arctg(x, 1e-10, HEAVY_MAX_TERMS);
        double negative = ArctgSeries.arctg(-x, 1e-10, HEAVY_MAX_TERMS);
        assertEquals(-positive, negative, 1e-9);
    }

    @Test
    void shouldHandleNaNInputLikeMathAtan() {
        double actual = ArctgSeries.arctg(Double.NaN, 1e-10, 100);
        assertTrue(Double.isNaN(actual));
    }

    @Test
    void shouldThrowForPositiveInfinity() {
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(Double.POSITIVE_INFINITY, 1e-10, 100));
    }

    @Test
    void shouldThrowForNegativeInfinity() {
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(Double.NEGATIVE_INFINITY, 1e-10, 100));
    }

    @Test
    void shouldPreserveNegativeZeroSignBit() {
        double actual = ArctgSeries.arctg(-0.0, 1e-10, 100);
        assertEquals(Double.doubleToRawLongBits(-0.0), Double.doubleToRawLongBits(actual));
    }

    @Test
    void shouldReturnTinyPositiveValueForDoubleMinValue() {
        double actual = ArctgSeries.arctg(Double.MIN_VALUE, 1e-10, 100);
        assertEquals(Double.MIN_VALUE, actual);
    }

    @Test
    void shouldMatchMathAtanOnGridFromMinusOneToOneWithStepPointOne() {
        for (int i = -10; i <= 10; i++) {
            double x = i / 10.0;
            double expected = Math.atan(x);
            double tolerance = Math.abs(x) == 1.0 ? 1e-4 : 1e-8;
            double eps = Math.abs(x) == 1.0 ? 1e-6 : 1e-10;
            double actual = ArctgSeries.arctg(x, eps, HEAVY_MAX_TERMS);
            assertEquals(expected, actual, tolerance, "x=" + x);
        }
    }

    @Test
    void shouldMatchMathAtanInFuzzyRange() {
        int runs = Integer.getInteger("arctg.fuzz.runs", 100_000);
        Random random = new Random(409555L);

        for (int i = 0; i < runs; i++) {
            double x = -0.999 + random.nextDouble() * 1.998;
            double actual = ArctgSeries.arctg(x, 1e-10, 10_000);
            double expected = Math.atan(x);
            assertEquals(expected, actual, 1e-6, "x=" + x);
        }
    }

    @Test
    void shouldThrowForAbsoluteXGreaterThanOne() {
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(1.5, 1e-10, 1000));
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(-2.0, 1e-10, 1000));
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(1.0000001, 1e-10, 1000));
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(-1.0000001, 1e-10, 1000));
    }

    @Test
    void shouldThrowForInvalidEps() {
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(0.5, 0.0, 100));
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(0.5, -1e-6, 100));
    }

    @Test
    void shouldThrowForInvalidMaxTerms() {
        assertThrows(IllegalArgumentException.class, () -> ArctgSeries.arctg(0.5, 1e-6, 0));
    }
}

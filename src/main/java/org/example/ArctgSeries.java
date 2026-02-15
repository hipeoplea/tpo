package org.example;

public final class ArctgSeries {
    private ArctgSeries() {
    }

    public static double arctg(double x, double eps, int maxTerms) {
        if (x == 0.0) {
            return x;
        }
        if (Math.abs(x) > 1.0) {
            throw new IllegalArgumentException("Series converges for |x| <= 1");
        }
        if (eps <= 0.0) {
            throw new IllegalArgumentException("eps must be > 0");
        }
        if (maxTerms <= 0) {
            throw new IllegalArgumentException("maxTerms must be > 0");
        }

        double sum = x;
        double termPower = x;

        for (int n = 1; n < maxTerms; n++) {
            termPower *= -x * x;
            double add = termPower / (2 * n + 1);
            sum += add;
            if (Math.abs(add) < eps) {
                break;
            }
        }

        return sum;
    }
}

package com.github.fiecher.triangle_rasterisation.math;

public class Barycentric {

    private final double lambda1;
    private final double lambda2;
    private final double lambda3;

    private final boolean inside;

    public Barycentric(final double lambda1, final double lambda2, final double lambda3) {

        final double sum = lambda1 + lambda2 + lambda3;
        if (!Utils.equals(sum, 1)) {
            throw new IllegalArgumentException("Coordinates are not normalized");
        }

        this.lambda1 = lambda1;
        this.lambda2 = lambda2;
        this.lambda3 = lambda3;

        this.inside = computeInside();
    }


    public double getLambda1() {
        return lambda1;
    }


    public double getLambda2() {
        return lambda2;
    }


    public double getLambda3() {
        return lambda3;
    }

    private boolean computeInside() {
        final boolean f1 = Utils.moreThan(lambda1, 0);
        final boolean f2 = Utils.moreThan(lambda2, 0);
        final boolean f3 = Utils.moreThan(lambda3, 0);

        return f1 && f2 && f3;
    }


    public boolean isInside() {
        return inside;
    }
}

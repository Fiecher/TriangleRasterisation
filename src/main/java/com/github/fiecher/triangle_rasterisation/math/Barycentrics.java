package com.github.fiecher.triangle_rasterisation.math;

public class Barycentrics {

    private final double l1;
    private final double l2;
    private final double l3;

    private final boolean inside;

    public Barycentrics(final double l1, final double l2, final double l3) {

        final double sum = l1 + l2 + l3;
        if (!Instruments.equals(sum, 1)) {
            throw new IllegalArgumentException("Coordinates are not normalized");
        }

        this.l1 = l1;
        this.l2 = l2;
        this.l3 = l3;

        this.inside = computeInside();
    }


    public double lambda1() {
        return l1;
    }


    public double lambda2() {
        return l2;
    }


    public double lambda3() {
        return l3;
    }

    private boolean computeInside() {
        final boolean b1 = Instruments.moreThan(l1, 0);
        final boolean b2 = Instruments.moreThan(l2, 0);
        final boolean b3 = Instruments.moreThan(l3, 0);

        return b1 && b2 && b3;
    }


    public boolean inside() {
        return inside;
    }
}

package com.github.fiecher.triangle_rasterisation.math;

import javafx.geometry.Point2D;

import java.util.Objects;

public class Triangle {

    private final Point2D v1;
    private final Point2D v2;
    private final Point2D v3;

    public Triangle(final Point2D v1, final Point2D v2, final Point2D v3) {

        Objects.requireNonNull(v1);
        Objects.requireNonNull(v2);
        Objects.requireNonNull(v3);

        this.v1 = v1;
        this.v2 = v2;
        this.v3 = v3;
    }


    public Point2D v1() {
        return v1;
    }


    public double x1() {
        return v1.getX();
    }


    public double y1() {
        return v1.getY();
    }


    public Point2D v2() {
        return v2;
    }


    public double x2() {
        return v2.getX();
    }


    public double y2() {
        return v2.getY();
    }


    public Point2D v3() {
        return v3;
    }


    public double x3() {
        return v3.getX();
    }


    public double y3() {
        return v3.getY();
    }


    public Barycentrics barycentrics(final javafx.geometry.Point2D p) {

        final double x = p.getX();
        final double y = p.getY();

        final double x1 = x1();
        final double y1 = y1();

        final double x2 = x2();
        final double y2 = y2();

        final double x3 = x3();
        final double y3 = y3();

        // n stands for numerator
        final double n1 = (y2 - y3) * (x - x3) + (x3 - x2) * (y - y3);
        final double n2 = (y3 - y1) * (x - x3) + (x1 - x3) * (y - y3);
        final double n3 = (y1 - y2) * (x - x1) + (x2 - x1) * (y - y1);

        // d = 1 / denominator
        final double d = 1 / ((y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3));

        // lambdas
        final double l1 = n1 * d;
        final double l2 = n2 * d;
        final double l3 = n3 * d;

        return new Barycentrics(l1, l2, l3);
    }
    
}

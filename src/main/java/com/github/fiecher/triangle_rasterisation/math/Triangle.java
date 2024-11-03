package com.github.fiecher.triangle_rasterisation.math;

import com.github.fiecher.triangle_rasterisation.color.Texture;
import javafx.geometry.Point2D;

import java.util.Objects;

public class Triangle {

    private Point2D point1;
    private Point2D point2;
    private Point2D point3;

    private Texture texture;

    public Triangle(Point2D point1, Point2D point2, Point2D point3, Texture texture) {

        Objects.requireNonNull(point1);
        Objects.requireNonNull(point2);
        Objects.requireNonNull(point3);
        Objects.requireNonNull(texture);

        this.point1 = point1;
        this.point2 = point2;
        this.point3 = point3;
        this.texture = texture;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public Point2D getPoint1() {
        return point1;
    }

    public Point2D getPoint2() {
        return point2;
    }

    public Point2D getPoint3() {
        return point3;
    }


    public double x3() {
        return point3.getX();
    }


    public double y3() {
        return point3.getY();
    }


    public Barycentric barycentrics(final Point2D p) {

        final double x = p.getX();
        final double y = p.getY();

        final double x1 = getPoint1().getX();
        final double y1 = getPoint1().getY();

        final double x2 = getPoint2().getX();
        final double y2 = getPoint2().getY();

        final double x3 = getPoint3().getX();
        final double y3 = getPoint3().getY();

        final double n1 = (y2 - y3) * (x - x3) + (x3 - x2) * (y - y3);
        final double n2 = (y3 - y1) * (x - x3) + (x1 - x3) * (y - y3);
        final double n3 = (y1 - y2) * (x - x1) + (x2 - x1) * (y - y1);

        final double d = 1 / ((y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3));

        final double l1 = n1 * d;
        final double l2 = n2 * d;
        final double l3 = n3 * d;

        return new Barycentric(l1, l2, l3);
    }

}

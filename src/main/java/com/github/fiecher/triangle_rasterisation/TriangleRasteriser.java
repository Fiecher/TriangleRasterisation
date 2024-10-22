package com.github.fiecher.triangle_rasterisation;

import javafx.geometry.Point2D;

public interface TriangleRasteriser extends BaseRasteriser {

    public TriangleColor getColor();

    public void setColor(final TriangleColor color);

    public void draw(final Point2D p1, final Point2D p2, final Point2D p3);
}

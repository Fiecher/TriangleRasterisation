package com.github.fiecher.triangle_rasterisation.rasterisers;

import com.github.fiecher.triangle_rasterisation.color.Colorer;
import com.github.fiecher.triangle_rasterisation.math.Barycentrics;
import com.github.fiecher.triangle_rasterisation.math.Instruments;
import com.github.fiecher.triangle_rasterisation.math.Triangle;
import javafx.geometry.Point2D;
import javafx.scene.image.PixelWriter;

import java.util.*;

public class TriangleRasterisator {

    private PixelWriter pixelWriter;
    private Colorer colorer;

    public PixelWriter getPixelWriter() {
        return pixelWriter;
    }

    public Colorer getColorer() {
        return colorer;
    }

    public void setPixelWriter(PixelWriter pixelWriter) {
        this.pixelWriter = pixelWriter;
    }

    public void setColorer(Colorer colorer) {
        this.colorer = colorer;
    }

    public TriangleRasterisator(PixelWriter pixelWriter, Colorer colorer) {
        this.pixelWriter = pixelWriter;
        this.colorer = colorer;
    }


    private List<Point2D> sortedVertices(final Triangle t) {
        final List<Point2D> vertices = new ArrayList<>();
        vertices.add(t.v1());
        vertices.add(t.v2());
        vertices.add(t.v3());

        vertices.sort(Comparator.comparing(Point2D::getY).thenComparing(Point2D::getX));

        return vertices;
    }

    private void drawFlat(final Triangle t,
                          final Point2D lone,
                          final Point2D flat1,
                          final Point2D flat2) {

        final double lx = lone.getX();
        final double ly = lone.getY();

        // "delta flat x1"
        final double dfx1 = flat1.getX() - lx;
        final double dfy1 = flat1.getY() - ly;

        final double dfx2 = flat2.getX() - lx;
        final double dfy2 = flat2.getY() - ly;

        double dx1 = dfx1 / dfy1;
        double dx2 = dfx2 / dfy2;

        final double fy = flat1.getY();
        if (Instruments.moreThan(ly, fy)) {
            drawBottom(t, lone, fy, dx1, dx2);
        } else {
            drawTop(t, lone, fy, dx1, dx2);
        }
    }

    private void drawTop(final Triangle t,
                         final Point2D v,
                         final double maxY,
                         final double dx1,
                         final double dx2) {

        double x1 = v.getX();
        double x2 = x1;

        for (int y = (int) v.getY(); y <= maxY; y++) {
            // round doubles instead of floor?
            drawHLine(t, (int) x1, (int) x2, y);

            x1 += dx1;
            x2 += dx2;
        }
    }

    private void drawBottom(final Triangle t,
                            final Point2D v,
                            final double minY,
                            final double dx1,
                            final double dx2) {

        double x1 = v.getX();
        double x2 = x1;

        for (int y = (int) v.getY(); y > minY; y--) {
            // round doubles instead of floor?
            drawHLine(t, (int) x1, (int) x2, y);

            x1 -= dx1;
            x2 -= dx2;
        }
    }

    private void drawHLine(final Triangle t,
                           final int x1,
                           final int x2,
                           final int y) {

        for (int x = (int) x1; x <= x2; x++) {
            final Barycentrics b;
            try {
                b = t.barycentrics(new Point2D(x, y));
            } catch (Exception e) {
                continue;
            }

            if (!b.inside()) {
                continue;
            }

            pixelWriter.setColor(x, y, colorer.get(b).jfxColor());
        }
    }

    public void draw(final Point2D p1, final Point2D p2, final Point2D p3) {
        Objects.requireNonNull(p1);
        Objects.requireNonNull(p2);
        Objects.requireNonNull(p3);
        final Triangle t = new Triangle(p1, p2, p3);

        // docs:
        // https://www.sunshine2k.de/coding/java/TriangleRasterization/TriangleRasterization.html

        final List<Point2D> vertices = sortedVertices(t);

        final Point2D v1 = vertices.get(0);
        final Point2D v2 = vertices.get(1);
        final Point2D v3 = vertices.get(2);

        final double x1 = v1.getX();
        final double y1 = v1.getY();

        final double x2 = v2.getX();
        final double y2 = v2.getY();

        final double x3 = v3.getX();
        final double y3 = v3.getY();

        if (Instruments.equals(y2, y3)) {
            drawFlat(t, v1, v2, v3);
            return;
        }

        if (Instruments.equals(y1, y2)) {
            drawFlat(t, v3, v1, v2);
            return;
        }

        final double x4 = x1 + ((y2 - y1) / (y3 - y1)) * (x3 - x1);
        final Point2D v4 = new Point2D(x4, v2.getY());

        // non strict equality?
        if (Instruments.moreThan(x4, x2)) {
            drawFlat(t, v1, v2, v4);
            drawFlat(t, v3, v2, v4);
        } else {
            drawFlat(t, v1, v4, v2);
            drawFlat(t, v3, v4, v2);
        }
    }
}

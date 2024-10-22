package com.github.fiecher.triangle_rasterisation.rasterisers;

import com.github.fiecher.triangle_rasterisation.TriangleColor;
import com.github.fiecher.triangle_rasterisation.TriangleRasteriser;
import javafx.geometry.Point2D;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.PixelWriter;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Arrays;
import java.util.Comparator;

public class TriangleRasterisator implements TriangleRasteriser {

    private GraphicsContext ctx;
    private TriangleColor color;

    @Override
    public GraphicsContext getCtx() {
        return ctx;
    }

    @Override
    public void setCtx(final GraphicsContext ctx) {
        if (ctx == null) {
            // exception?
            return;
        }

        this.ctx = ctx;
    }

    @Override
    public TriangleColor getColor() {
        return color;
    }

    @Override
    public void setColor(final TriangleColor color) {
        if (color == null) {
            // exception?
            return;
        }

        this.color = color;
    }

    @Override
    public void draw(Point2D p1, Point2D p2, Point2D p3) {

    }

    class Triangle {

        private final PixelWriter w;
        private final TriangleColor color;

        private final Point2D p1;
        private final Point2D p2;
        private final Point2D p3;

        // denominator for barycentric coordinates
        // d is "1 / denominator" for faster calculations
        private final double d;
        private Triangle(
                final PixelWriter w,
                final TriangleColor color,
                final Point2D p1,
                final Point2D p2,
                final Point2D p3) {
            this.w = w;
            this.color = color;

            // points should be valid
            this.p1 = p1;
            this.p2 = p2;
            this.p3 = p3;

            // call after assigning points
            this.d = 1 / denominator();
        }
        private double denominator() {
            final double x1 = p1.getX();
            final double y1 = p1.getY();

            final double x2 = p2.getX();
            final double y2 = p2.getY();

            final double x3 = p3.getX();
            final double y3 = p3.getY();

            return (y2 - y3) * (x1 - x3) + (x3 - x2) * (y1 - y3);
        }
    }

    static Point2D[] vertices = {
            new Point2D(100, 300),
            new Point2D(200, 100),
            new Point2D(400, 200)
    };

    static Canvas canvas = new Canvas(400, 400);


    public static void sortVerticesAscendingByY(Point2D[] vertices) {
        Arrays.sort(vertices, Comparator.comparingDouble(Point2D::getY));
    }

    public static void draw(GraphicsContext gc, Point2D v1, Point2D v2, Point2D v3) {
        sortVerticesAscendingByY(vertices);
        if (v2.getY() == v3.getY()) {
            fillBottomTriangle(gc, v1, v2, v3);
        } else if (v1.getY() == v2.getY()) {
            fillTopTriangle(gc, v1, v2, v3);
        } else {
            Point2D v4 = new Point2D(
                    (int) (v1.getX() + ((double) (v2.getY() - v1.getY()) / (double) (v3.getY() - v1.getY())) * (v3.getX() - v1.getX())),
                    v2.getY());
            fillBottomTriangle(gc, v1, v2, v4);
            fillTopTriangle(gc, v2, v4, v3);
        }


    }

    private static void drawLine(GraphicsContext gc, int x1, int y1, int x2, int y2) {
        gc.strokeLine(x1, y1, x2, y2);
    }

    private static void fillBottomTriangle(GraphicsContext gc, Point2D v1, Point2D v2, Point2D v3) {

        double invslope1 = (v2.getX() - v1.getX()) / (v2.getY() - v1.getY());

        double invslope2 = (v3.getX() - v1.getX()) / (v3.getY() - v1.getY());

        double deltaL = v1.getX();

        double deltaR = v1.getX();


        for (int scanlineY = (int) v1.getY(); scanlineY <= v2.getY(); scanlineY++) {
            drawLine(gc, (int) deltaL, scanlineY, (int) deltaR, scanlineY);
            deltaL += invslope1;
            deltaR += invslope2;
        }
    }

    private static void fillTopTriangle(GraphicsContext gc, Point2D v1, Point2D v2, Point2D v3) {
        double invslope1 = (v3.getX() - v1.getX()) / (v3.getY() - v1.getY());

        double invslope2 = (v3.getX() - v2.getX()) / (v3.getY() - v2.getY());

        double curx1 = v3.getX();

        double curv2 = v3.getX();


        for (int scanlineY = (int) v3.getY(); scanlineY > v1.getY(); scanlineY--) {
            drawLine(gc, (int) curx1, scanlineY, (int) curv2, scanlineY);
            curx1 -= invslope1;
            curv2 -= invslope2;
        }
    }

    public void start(Stage primaryStage) {
        // Создаем Canvas, на котором будем рисовать
        Canvas canvas = new Canvas(1000, 1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Рисуем линию
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(10);
        draw(gc, vertices[0], vertices[1], vertices[2]);

        // Создаем панель и добавляем туда Canvas
        Pane root = new Pane();
        root.getChildren().add(canvas);

        // Создаем сцену и добавляем на нее панель
        Scene scene = new Scene(root, 500, 500);

        // Устанавливаем сцену
        primaryStage.setScene(scene);

        // Устанавливаем заголовок окна и отображаем его
        primaryStage.setTitle("Drawing a Line Example");
        primaryStage.show();
    }

}

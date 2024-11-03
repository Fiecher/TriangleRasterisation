package com.github.fiecher.triangle_rasterisation;

import com.github.fiecher.triangle_rasterisation.color.GradientColorer;
import com.github.fiecher.triangle_rasterisation.color.MonotoneColorer;
import com.github.fiecher.triangle_rasterisation.rasterisers.TriangleRasterisator;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Main extends Application {

    public static void main(String args[]) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Test");
        Group root = new Group();
        Canvas canvas = new Canvas(1000,1000);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        TriangleRasterisator tr1 = new TriangleRasterisator(gc.getPixelWriter(), new MonotoneColorer(Color.BLACK));
        TriangleRasterisator tr2 = new TriangleRasterisator(gc.getPixelWriter(), new GradientColorer(Color.RED, Color.GREEN, Color.BLUE));

        tr1.draw(new Point2D(200,200), new Point2D(300,300), new Point2D(450,230));
        tr2.draw(new Point2D(0,0), new Point2D(100,100), new Point2D(150,30));

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }
}

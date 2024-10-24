package com.github.fiecher.triangle_rasterisation;

import com.github.fiecher.triangle_rasterisation.colors.SingleColor;
import com.github.fiecher.triangle_rasterisation.rasterisers.BasedTriangleRasterisator;
import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
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
        BasedTriangleRasterisator tr = new BasedTriangleRasterisator();

        SingleColor color = new SingleColor(Color.BLACK);
        tr.setColor(color);
        tr.setCtx(gc);
        tr.draw(new Point2D(1000,50), new Point2D(300,200), new Point2D(300,300));

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();


    }
}

module com.github.fiecher.triangle_rasterisation.trianglerasterisation {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.fiecher.triangle_rasterisation.trianglerasterisation to javafx.fxml;
    exports com.github.fiecher.triangle_rasterisation.trianglerasterisation;
}
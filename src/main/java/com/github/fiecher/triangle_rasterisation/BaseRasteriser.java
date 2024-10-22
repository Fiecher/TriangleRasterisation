package com.github.fiecher.triangle_rasterisation;
import javafx.scene.canvas.GraphicsContext;
/**
 * <code>BaseRasterizer</code> represents a blank rasterizer with cached
 * <code>GraphicsContext</code>.
 */
public interface BaseRasteriser {

    public GraphicsContext getCtx();

    public void setCtx(final GraphicsContext ctx);
}
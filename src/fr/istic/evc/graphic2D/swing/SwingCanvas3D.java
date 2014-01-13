package fr.istic.evc.graphic2D.swing;

import java.awt.GraphicsConfiguration;

import javax.media.j3d.Canvas3D;
import javax.media.j3d.J3DGraphics2D;

public class SwingCanvas3D extends Canvas3D {

	private static final long serialVersionUID = 1L;

	public SwingCanvas3D(GraphicsConfiguration arg0) {
		super(arg0);
	}

	@Override
	public void postRender() {
		super.postRender();
		
		J3DGraphics2D graphics = getGraphics2D();
		graphics.flush(true);
	}
}

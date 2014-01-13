package fr.istic.evc.utils.position;

public class PixelPosition implements Position {

	private int pixel;
	
	public PixelPosition(int pixel) {
		this.pixel = pixel;
	}

	@Override
	public int getRealPosition() {
		return pixel;
	}
	
}
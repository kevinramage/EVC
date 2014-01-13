package fr.istic.evc.utils.size;

public class PixelSize extends Size {

	protected int value;
	
	public PixelSize(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value + "px";
	}

	@Override
	public int reduceAllocableSize(int size) {
		return size - value;
	}

	@Override
	public int getExactSize(int totalSize) {
		return value;
	}

	@Override
	public int getExactSize() {
		return value;
	}
}

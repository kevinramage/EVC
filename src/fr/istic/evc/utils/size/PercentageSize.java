package fr.istic.evc.utils.size;

public class PercentageSize extends Size {

	private int value;
	private int exactValue;
	
	public PercentageSize(int value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value + "%";
	}

	@Override
	public int reduceAllocableSize(int size) {
		return size;
	}

	@Override
	public int getExactSize(int totalSize) {
		exactValue = value * totalSize / 100;
		return exactValue;
	}

	@Override
	public int getExactSize() {
		return exactValue;
	}
}

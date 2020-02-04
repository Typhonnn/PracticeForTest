package files;

import java.io.Serializable;

public class Product implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	private int price;

	public Product(String name, int price) {
		this.name = name;
		this.price = price;
	}

	@Override
	public String toString() {
		return name + ", " + price;
	}

	public String getName() {
		return name;
	}

	public int getPrice() {
		return price;
	}

}

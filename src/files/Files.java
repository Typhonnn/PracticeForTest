package files;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;

// saving and reading array of products. The products are being read from the user
public class Files {
	public static Scanner s = new Scanner(System.in);
	public static final String FNAME = "Products.txt";

	public static void main(String[] args) {
		System.out.println("Please enter number of products:");
		int numOfProducts = s.nextInt();
		ArrayList<Product> pArr = null;
		ArrayList<Product> pArr2 = null;
		pArr = getProductsFromUser(numOfProducts);
		if (saveProductsToFile(pArr)) {
			pArr2 = loadProductsFromFile();
		}
		if (pArr2 != null) {
			System.out.println(pArr2.toString());
		}
	}
	

	private static ArrayList<Product> loadProductsFromFile() {
		ArrayList<Product> pArr;
		try (ObjectInputStream oIn = new ObjectInputStream(new BufferedInputStream(new FileInputStream(FNAME)))) {
			int num = oIn.readInt();
			pArr = new ArrayList<Product>(num);
			for (int i = 0; i < num; i++) {
				pArr.add((Product) oIn.readObject());
			}
		} catch (Exception e) {
			System.out.println("Failed to read objects!");
			return null;
		}
		return pArr;
	}

	private static boolean saveProductsToFile(ArrayList<Product> pArr) {
		try (ObjectOutputStream oOut = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(FNAME)))) {
			oOut.writeInt(pArr.size());
			for (int i = 0; i < pArr.size(); i++) {
				oOut.writeObject(pArr.get(i));
			}
		} catch (FileNotFoundException e) {
			System.out.println("Save Exception: File contacts.obj Not Found!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
			System.out.println("Failed to save objects!");
			return false;
		}
		return true;
	}

	private static ArrayList<Product> getProductsFromUser(int numOfProducts) {
		ArrayList<Product> pArr = new ArrayList<Product>(numOfProducts);
		for (int i = 0; i < numOfProducts; i++) {
			System.out.println("Enter name:");
			String name = s.next();
			System.out.println("Enter price:");
			int price = s.nextInt();
			pArr.add(new Product(name, price));
		}
		return pArr;
	}
}

package com.veggie411.veggie411;

import android.util.Log;

public class HelperMethods {
	//json to product
	//product to json
	protected static void addProducts() {
		Product a = new Product("4001686301555");
		a.setBrand("HARIBO");
		a.setName("GOLDBAREN");
		a.addIngredient("GLUCOSE SYRUP");
		a.addIngredient("SUGAR");
		a.addIngredient("GELATIN");
		a.addIngredient("DEXTROSE");
		a.addIngredient("CITRIC ACID");
		a.addIngredient("ARONIA");
		a.addIngredient("NETTLE CONCENTRATE");
		a.addIngredient("SPINACH CONCENTRATE");
		a.addIngredient("KIWI CONCENTRATE");
		a.addIngredient("ORANGE CONCENTRATE");
		a.addIngredient("ELDERBERRY CONCENTRATE");
		a.addIngredient("LEMON CONCENTRATE");
		a.addIngredient("MANGO CONCENTRATE");
		a.addIngredient("PASSION FRUIT CONCENTRATE");
		a.addIngredient("BLACKCURRENT CONCENTRATE");
		a.addIngredient("APPLE JUICE FROM CONCENTRATE");
		a.addIngredient("STRAWBERRY JUICE FROM CONCENTRATE");
		a.addIngredient("RASPBERRY JUICE FROM CONCENTRATE");
		a.addIngredient("ORANGE JUICE FROM CONCENTRATE");
		a.addIngredient("LEMON JUICE FROM CONCENTRATE");
		a.addIngredient("PINEAPPLE JUICE FROM CONCENTRATE");
		a.addIngredient("FLAVOR");
		a.addIngredient("WHITE YELLOW BEESWAX");
		a.addIngredient("CARNAUBA WAX");
		a.addIngredient("ELDERBERRY EXTRACT");
		a.addIngredient("FRUIT EXTRACT");
		a.addIngredient("FRUIT EXTRACT FROM CAROB");
		a.addIngredient("INVERT SUGAR SYRUP");
		
		MainActivity.tempProductDatabase.put("4001686301555", a);
		
//		for (Product p:MainActivity.tempProductDatabase.values()) {
//			Log.i("PRODUCT", p.getBarcode());
//			Log.i("Brand", p.getBrand());
//			Log.i("Name", p.getName());
//			String s = "";
//			for (String i:p.getIngredientsArray()) {
//				s+=","+i;
//			}
//			Log.i("Ingredients", s);
//		}
		
		//add ingredient
	}

	public static void addIngredients() {
		Ingredient a = new Ingredient("GELATIN");
		
		MainActivity.tempIngredientDatabase.put(a.getName(), a);
		//MainActivity.tempIngredientDatabase.put(b.getName(), b);
	}

	public static void addUserBlacklist() {
		//MainActivity.tempBlacklistDatabase.add("chicken");
		MainActivity.tempBlacklistDatabase.add("GELATIN");
	}
	
}

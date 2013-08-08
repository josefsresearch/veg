package com.veggie411.veggie411;

import java.util.ArrayList;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class ViewProductActivity extends Activity {

	private Product newProduct;

	//get all fields input
	private Runnable viewParts;
	private IngredientsArrayAdapter adapter;
	private ArrayList<String> ingredients;
	
	private String barcode;
	
	private TextView productBarcode;
	private TextView productBrandName;
	private ListView listView;
	private ImageButton done;
	private Product product;
	private ImageView productImage;
	private ImageView resultImage;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(Constants.ACTIVITY_VIEW_PRODUCT, "CREATED");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_product);
		barcode = getIntent().getExtras().getString(Constants.BARCODE);
		Log.i("in view", barcode);
		product = MainActivity.tempProductDatabase.get(barcode);
		productImage = (ImageView) findViewById(R.id.view_product_image);
		//productImage.setBackground(product.getImage()); TODO set image
		resultImage = (ImageView) findViewById(R.id.view_product_result_image);
		ingredients = product.getIngredients();
		boolean clean = true;
		for (String ing:ingredients) {
			if (MainActivity.tempBlacklistDatabase.contains(ing)) {
				clean = false;
			}
		}
		if (clean) {
			//resultImage.setImageDrawable(R.drawable.fail);
		} else {
			//resultImage.setImageDrawable(R.drawable.success);
		}
		productBarcode = (TextView) findViewById(R.id.view_product_barcode);
		productBarcode.setText("#"+barcode);
		productBrandName = (TextView) findViewById(R.id.view_product_name);
		productBrandName.setText(product.getBrand() +" "+product.getName());
		listView = (ListView) findViewById(R.id.view_product_ingredients_list);
		View header = (View)getLayoutInflater().inflate(R.layout.list_ingredients_header, null);
		listView.addHeaderView(header);
		done = (ImageButton) findViewById(R.id.view_product_done);
		
		
		adapter = new IngredientsArrayAdapter(this, android.R.layout.simple_list_item_1, ingredients);
		listView.setAdapter(adapter);
		listView.setItemsCanFocus(true);
		done.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					done.setBackgroundResource(R.drawable.done_pressed);
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					done.setBackgroundResource(R.drawable.done);
				}
				return false;
			}
		});
		done.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				finish();
			}
		});
	}
}
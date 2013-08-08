package com.veggie411.veggie411;

import java.util.HashMap;
import java.util.HashSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends Activity {

	private ImageButton scanButton;
	private ImageButton productsButton;
	private ImageButton ingredientsButton;
	private ImageButton moreButton;
	private Intent viewProductActivity;
	private Intent productListActivity;
	private Intent ingredientsActivity;

	final IntentIntegrator integrator = new IntentIntegrator(this);	

	protected static HashMap<String, Product> tempProductDatabase = new HashMap<String, Product>();
	protected static HashMap<String, Ingredient> tempIngredientDatabase = new HashMap<String, Ingredient>();
	protected static HashSet<String> tempBlacklistDatabase = new HashSet<String>();

	Toast temp;
	private Builder alertDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(Constants.ACTIVITY_MAIN, Constants.CREATED);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		HelperMethods.addProducts();
		HelperMethods.addIngredients();
		HelperMethods.addUserBlacklist();
		alertDialog = new AlertDialog.Builder(this);
		scanButton = (ImageButton) findViewById(R.id.main_scan);
		productsButton = (ImageButton) findViewById(R.id.main_products);
		ingredientsButton = (ImageButton) findViewById(R.id.main_ingredients);
		moreButton = (ImageButton) findViewById(R.id.main_more);
		temp = Toast.makeText(this, "Hello!",Toast.LENGTH_LONG);
		viewProductActivity = new Intent(this, ViewProductActivity.class);
		productListActivity = new Intent(this, ProductListActivity.class);
		ingredientsActivity = new Intent(this, IngredientsActivity.class);
		
//		if () {
//			launchSignInPage();
//		}
		
		scanButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					scanButton.setBackgroundResource(R.drawable.scan_pressed);
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					scanButton.setBackgroundResource(R.drawable.scan);
				}
				return false;
			}
		});
		scanButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {		
				scanButton.setBackgroundResource(R.drawable.scan);
				Log.i("INTEGRATOR APP", "CALLED");
				integrator.initiateScan();
			}
		});
		productsButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					//productsButton.getBackground().setColorFilter(0x84b976, PorterDuff.Mode.MULTIPLY);
					productsButton.setBackgroundResource(R.drawable.product_list_pressed);

				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					//productsButton.getBackground().clearColorFilter();
					productsButton.setBackgroundResource(R.drawable.product_list);
				}
				return false;
			}
		});
		productsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {		
				startActivity(productListActivity);
			}
		});
		ingredientsButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					ingredientsButton.setBackgroundResource(R.drawable.ingredients_pressed);
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					ingredientsButton.setBackgroundResource(R.drawable.ingredients);
				}
				return false;
			}
		});
		ingredientsButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				startActivity(ingredientsActivity);
			}
		});
		moreButton.setOnTouchListener(new OnTouchListener() {
			@Override
			public boolean onTouch(View arg0, MotionEvent arg1) {
				if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
					moreButton.setBackgroundResource(R.drawable.more_pressed);
				}
				if (arg1.getAction() == MotionEvent.ACTION_UP) {
					moreButton.setBackgroundResource(R.drawable.more);
				}
				return false;
			}
		});
		moreButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {	
				Intent addProductActivity = new Intent(MainActivity.this, MoreActivity.class);
				startActivity(addProductActivity);
			}
		});
	}

	private void launchSignInPage() {
		Intent signInActivity = new Intent(this, SignInActivity.class);
		startActivityForResult(signInActivity, Constants.RESULT_SIGN_IN);
	}

	protected void onResume() {
		Log.i(Constants.ACTIVITY_MAIN, Constants.ON_RESUME);
		super.onResume();
	}

	protected void onPause() {
		Log.i(Constants.ACTIVITY_MAIN, Constants.ON_PAUSE);

		super.onPause();
	}

	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		Log.i("REQUEST CODE IS", String.valueOf(requestCode));
		Log.i("COMPARE is ", String.valueOf(Constants.RESULT_SCAN));
		if (requestCode == RESULT_CANCELED){
			//TODO
		} else if (requestCode == Constants.RESULT_SCAN) {
			IntentResult scanResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, intent);
			if (scanResult != null && scanResult.getContents() != null) {
				String code = scanResult.getContents();
				Log.i("Result is ", code);
				Product getProduct = goGet(code);
				if (getProduct != null) {
					Intent viewProductActivity = new Intent(this, ViewProductActivity.class);
					viewProductActivity.putExtra(Constants.BARCODE, code);
					startActivity(viewProductActivity);
				} else {
					Intent addProductActivity = new Intent(this, AddProductActivity.class);
					addProductActivity.putExtra(Constants.BARCODE, code);
					startActivity(addProductActivity);
				}
			} else {
				//error
			}
		} else {

		}
	}

	private Product goGet(String code) {
		if (tempProductDatabase.containsKey(code)) {
			return tempProductDatabase.get(code);
		}
		return null;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

}

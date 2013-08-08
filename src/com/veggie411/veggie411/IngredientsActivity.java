package com.veggie411.veggie411;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class IngredientsActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(Constants.ACTIVITY_INGREDIENTS, "CREATED");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ingredients);
		
	}
	
}
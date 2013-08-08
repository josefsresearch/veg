package com.veggie411.veggie411;

import java.util.ArrayList;
import java.util.HashSet;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RadioButton;

public class EditProfileActivity extends Activity {
	
	private ImageButton imageButton;
	private RadioButton lacto_ovo;
	private RadioButton pescatarian;
	private RadioButton vegetarian;
	private RadioButton vegan;
	private RadioButton can_eat;
	private RadioButton cant_eat;
	private EditText addIngredient;
	private ImageButton addButton;
	private ListView ingredientsList;
	private ImageButton saveButton;
	
	private ArrayList<String> specialIngredients;
	private IngredientsArrayAdapter adapter;
	//private HashSet<String> tempBlacklist;
	private HashSet<String> oldBlacklist;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(Constants.ACTIVITY_EDIT_PROFILE, Constants.CREATED);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_edit_profile);
		imageButton = (ImageButton) findViewById(R.id.edit_profile_image);
		lacto_ovo = (RadioButton) findViewById(R.id.edit_profile_lacto_ovo);
		pescatarian = (RadioButton) findViewById(R.id.edit_profile_pescatarian);
		vegetarian = (RadioButton) findViewById(R.id.edit_profile_vegetarian);
		vegan = (RadioButton) findViewById(R.id.edit_profile_vegan);
		can_eat = (RadioButton) findViewById(R.id.edit_profile_can_eat);
		cant_eat = (RadioButton) findViewById(R.id.edit_profile_cant_eat);
		addIngredient = (EditText) findViewById(R.id.edit_profile_add_ingredient);
		addButton = (ImageButton) findViewById(R.id.edit_profile_add_button);
		ingredientsList = (ListView) findViewById(R.id.edit_profile_special_ingredients_list);
		saveButton = (ImageButton) findViewById(R.id.edit_profile_save_button);
		
		specialIngredients = new ArrayList<String>();
		//tempBlacklist = new HashSet<String>();
		oldBlacklist = MainActivity.tempBlacklistDatabase;
		MainActivity.tempBlacklistDatabase = new HashSet<String>();
		adapter = new IngredientsArrayAdapter(this, android.R.layout.simple_list_item_1, new ArrayList<String>());
		ingredientsList.setAdapter(adapter);
		
		addButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				//if doesnt exist???TODO
				String ingred = addIngredient.getText().toString();
				if (cant_eat.isChecked()) {
					//tempBlacklist.add(ingred);
					MainActivity.tempBlacklistDatabase.add(ingred);
				}
				adapter.add(ingred);
				adapter.notifyDataSetChanged();
			}
		});

		
		saveButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent resultIntent = new Intent();
				String status = "VEGETARIAN";
				if (pescatarian.isChecked()) {
					status = "PESCATARIAN";
				} else if (lacto_ovo.isChecked()) {
					status = "LACTO_OVO";
				} else if (vegan.isChecked()) {
					status = "VEGAN";
				}
				resultIntent.putExtra("STATUS", status);
				resultIntent.putExtra("SPECIAL_INGREDIENTS", adapter.objects);
				//MainActivity.tempBlacklistDatabase = tempBlacklist;
				setResult(Activity.RESULT_OK, resultIntent);
				finish();
			}
		});
	}
	
	@Override
	public void onBackPressed() {
		MainActivity.tempBlacklistDatabase = oldBlacklist;
	}
}

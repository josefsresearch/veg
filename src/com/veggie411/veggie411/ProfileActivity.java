package com.veggie411.veggie411;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity {
	
	private ImageView image;
	private TextView name;
	private TextView ageCity;
	private TextView points;
	private ImageView statusImage;
	private TextView status;
	private ListView specialIngredients;
	private ImageButton editButton;
	private ArrayList<String> ingredients;
	private IngredientsArrayAdapter adapter;

	
	private Intent editProfileActivity;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.i(Constants.ACTIVITY_PROFILE, Constants.CREATED);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);
		image = (ImageView) findViewById(R.id.profile_image);
		name = (TextView) findViewById(R.id.profile_name);
		ageCity = (TextView) findViewById(R.id.profile_age_location);
		points = (TextView) findViewById(R.id.profile_points);
		statusImage = (ImageView) findViewById(R.id.profile_status_image);
		status = (TextView) findViewById(R.id.profile_status);
		specialIngredients = (ListView) findViewById(R.id.profile_additional_ingredients_list);
		editButton = (ImageButton) findViewById(R.id.profile_edit_button);
		
		editProfileActivity = new Intent(this, EditProfileActivity.class);
		ingredients = new ArrayList<String>();
		adapter = new IngredientsArrayAdapter(this, android.R.layout.simple_list_item_1, ingredients);
		specialIngredients.setAdapter(adapter);
		
		editButton.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				startActivityForResult(editProfileActivity, Constants.RESULT_EDIT_PROFILE);
			}
		});
	}
	
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		if (requestCode == Constants.RESULT_EDIT_PROFILE) {
			Bundle b = intent.getExtras();
			//get image?
			String tempStatus = (String) b.get("STATUS");
			if (tempStatus != null) {
				status.setText(tempStatus);
			} else {
				Log.e("PROFILE", "DIDNT GET STATUS");
			}
			ArrayList<String> tempSpecialIngredients = (ArrayList<String>) b.getStringArrayList("SPECIAL_INGREDIENTS");
			if (tempSpecialIngredients != null) {
				//adapter set ingredients
				Toast.makeText(this, tempSpecialIngredients.toString(), Toast.LENGTH_SHORT).show();
				for (String i:tempSpecialIngredients) {
					adapter.add(i);
				}
				adapter.notifyDataSetChanged();
			} else {
				
			}
		}
	}
	
}

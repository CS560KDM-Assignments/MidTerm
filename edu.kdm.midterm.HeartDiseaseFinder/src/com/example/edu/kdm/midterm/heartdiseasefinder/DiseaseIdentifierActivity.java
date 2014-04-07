package com.example.edu.kdm.midterm.heartdiseasefinder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

@SuppressLint("NewApi")
public class DiseaseIdentifierActivity extends Activity {
	

	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_disease_identifier);
		String op= "" ;
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy); 

		TextView yesNo = (TextView)findViewById(R.id.textViewYesNo);
		ImageView ig = (ImageView)findViewById(R.id.imageViewYesNo);
		TextView disease = (TextView) findViewById(R.id.textViewDesc);
		final Spinner problem = (Spinner) findViewById(R.id.spinnerHeart);
		final Spinner treatment = (Spinner) findViewById(R.id.spinnerTreat);
		Button prevent = (Button)findViewById(R.id.buttonPrevent);
		final WebView web = (WebView)findViewById(R.id.webView1);

		try {
			//URL solr = new URL("file:///C:/Users/Malathy/Desktop/Masters/KDM/exam-midterm/response.html";)
			URL solr = new URL("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=id%3Amkdn9&wt=json&indent=true");

			BufferedReader in = new BufferedReader(new InputStreamReader(solr.openStream()));
			String inputLine; 


			while ((inputLine = in.readLine()) != null) {
				// Process each line.
				if(inputLine.contains("yes"))
				{
					op = "yes";
					System.out.println(op);
				}else if(inputLine.contains("no"))
				{
					op = "no";
					System.out.println(op);
				}
			}
			in.close(); 

		} catch (MalformedURLException me) {
			System.out.println(me); 

		} catch (IOException ioe) {
			System.out.println(ioe);
		}
		if (op.equals("yes"))
		{
			yesNo.setText("Causes for heart disease is more");
			ig.setImageResource(R.drawable.heart_badpng);
			disease.setText("Oops!Hurry and Find the possible diseases below");
		}
		else if (op.equals("no"))
		{
			yesNo.setText("You have a good heart!! Keep going");
			ig.setImageResource(R.drawable.heart_goodpng);
			disease.setText("Protect yourself from diseases below");
		}
	//	if(problem.isSelected())
		//	System.out.println("value os spinner"+problem.getSelectedItem().toString());
		problem.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				if (String.valueOf(problem.getSelectedItem()).equalsIgnoreCase("1: Heart Attack")){
					System.out.println("value os spinner"+problem.getSelectedItem().toString());
					String[] list = getResources().getStringArray(R.array.heart_att);
					ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
					dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					treatment.setAdapter(dataAdapter);
				}				
			else if (String.valueOf(problem.getSelectedItem()).equalsIgnoreCase("2: Heart Failure")){
				System.out.println("value os spinner"+problem.getSelectedItem().toString());
				String[] list = getResources().getStringArray(R.array.heart_fail);
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				treatment.setAdapter(dataAdapter);

			}
			else if (String.valueOf(problem.getSelectedItem()).equalsIgnoreCase("3: Chest Pain")){
				System.out.println("value os spinner"+problem.getSelectedItem().toString());
				String[] list = getResources().getStringArray(R.array.heart_chestpains);
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				treatment.setAdapter(dataAdapter);
			}
			else if (String.valueOf(problem.getSelectedItem()).equalsIgnoreCase("4: Abnormal HeartBeat")){
				System.out.println("value os spinner"+problem.getSelectedItem().toString());
				String[] list = getResources().getStringArray(R.array.heart_ab);
				ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
				dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
				treatment.setAdapter(dataAdapter);
			} 
			
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				if (String.valueOf(problem.getSelectedItem()).equalsIgnoreCase("1: Heart Attack")){
					System.out.println("value os spinner"+problem.getSelectedItem().toString());
					String[] list = getResources().getStringArray(R.array.heart_att);
					ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(),android.R.layout.simple_spinner_item, list);
					dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
					treatment.setAdapter(dataAdapter);
				}						
			}
			
		});
		treatment.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				String op1="";
				String final_place="";
				try {
					//URL solr = new URL("file:///C:/Users/Malathy/Desktop/Masters/KDM/exam-midterm/response.html";)
					URL solr1 = new URL("http://134.193.136.127:8983/solr/collection1_shard1_replica1/select?q=id%3Amkdn10&wt=json&indent=true");

					BufferedReader in = new BufferedReader(new InputStreamReader(solr1.openStream()));
					String inputLine; 
					
					
					int count=0;

					while ((inputLine = in.readLine()) != null) {
						// Process each line.
						if(count ==1 ){
							System.out.println("nect line"+inputLine);
							op1 =inputLine.split("]")[0];
							int len = op1.length();//replaceAll("\"", "");
							final_place = op1.substring(1,len);
							count=0;
							System.out.println(final_place);
						}
						if(inputLine.contains("title")){
							System.out.println("lineeee"+inputLine);
						count =1;	
						}
						
						//https://www.google.com/maps/search/UNIVERSITY+OF+KANSAS+HOSPITAL/@39.0344109,-94.5720491,14z
					}
					in.close(); 

				} catch (MalformedURLException me) {
					System.out.println(me); 

				} catch (IOException ioe) {
					System.out.println(ioe);
				}
				web.getSettings().setJavaScriptEnabled(true);
				System.out.println("formateedddd"+op1);
				//web.loadUrl("https://www.google.com/maps/place/University+of+Kansas+Medical+Center/@39.056244,-94.61074,17z/data=!3m1!4b1!4m2!3m1!1s0x87c0ee2e9019bfed:0xbce5061c05c1ad50");
				web.loadUrl("https://www.google.com/maps/search/"+final_place);
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	prevent.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View arg0) {
			System.out.println("button click");
			web.getSettings().setJavaScriptEnabled(true);
			web.loadUrl("http://www.mayoclinic.org/diseases-conditions/heart-disease/in-depth/heart-disease-prevention/art-20046502");
		}
	});	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.disease_identifier, menu);
		return true;
	}

}

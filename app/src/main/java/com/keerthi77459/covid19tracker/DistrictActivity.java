package com.keerthi77459.covid19tracker;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

public class DistrictActivity extends AppCompatActivity {
    
    RecyclerView districtView;
    stateName district;
    SharedPreferences sharedPreferences;
    districtAdapter DA;
    ArrayList<stateName> list1 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_district);
        
        districtView = findViewById(R.id.districtView);
        
        districtView.setLayoutManager(new LinearLayoutManager(this));

        fetchDistrict();
        
    }

    private void fetchDistrict() {

        sharedPreferences = getSharedPreferences("STATEDATA", MODE_PRIVATE);


        final String URL = "https://data.covid19india.org/state_district_wise.json";

        String State = sharedPreferences.getString("StateName",null);

        StringRequest request = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject stateObject = object.getJSONObject(State);
                    JSONObject districtObject = stateObject.getJSONObject("districtData");
                    Iterator<String> districtKeys = districtObject.keys();

                    while(districtKeys.hasNext()){
                        String districtKey = districtKeys.next();
                        district = new stateName(districtKey);
                        list1.add(district);
                    }
                    DA = new districtAdapter(DistrictActivity.this,list1);
                    districtView.setAdapter(DA);

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}
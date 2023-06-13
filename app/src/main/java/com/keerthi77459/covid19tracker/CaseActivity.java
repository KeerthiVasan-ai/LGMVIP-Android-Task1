package com.keerthi77459.covid19tracker;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;

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

public class CaseActivity extends AppCompatActivity {

    RecyclerView caseView;
    caseName Case;
    SharedPreferences sharedPreferences;
    caseAdapter CA;
    ArrayList<caseName> list2 = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);

        caseView = findViewById(R.id.caseView);

        caseView.setLayoutManager(new LinearLayoutManager(this));

        fetchCase();
    }

    private void fetchCase() {

        sharedPreferences = getSharedPreferences("STATEDATA", MODE_PRIVATE);

        final String URL = "https://data.covid19india.org/state_district_wise.json";

        String State = sharedPreferences.getString("StateName",null);
        String District = sharedPreferences.getString("DistrictName",null);

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                try {
                    JSONObject object = new JSONObject(response);
                    JSONObject stateObject = object.getJSONObject(State);
                    JSONObject object2 = stateObject.getJSONObject("districtData");
                    JSONObject districtObject = object2.getJSONObject(District);
                    String active = districtObject.getString("active");
                    String confirmed = districtObject.getString("confirmed");
                    String deceased = districtObject.getString("deceased");
                    String recovered = districtObject.getString("recovered");

                    Case = new caseName(active,confirmed,deceased,recovered);
                    list2.add(Case);

                    CA = new caseAdapter(CaseActivity.this,list2);
                    caseView.setAdapter(CA);

                } catch(JSONException e){
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(CaseActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }
}
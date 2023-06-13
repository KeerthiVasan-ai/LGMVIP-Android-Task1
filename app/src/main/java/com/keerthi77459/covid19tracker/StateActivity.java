package com.keerthi77459.covid19tracker;

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
import java.util.Iterator;


public class StateActivity extends AppCompatActivity {

    stateName state;
    stateAdapter SA;
    RecyclerView stateView;
    ArrayList<stateName> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_state);

        stateView = findViewById(R.id.stateView);

        stateView.setLayoutManager(new LinearLayoutManager(this));

        fetchState();
    }
    private void fetchState() {
        final String URL = "https://data.covid19india.org/state_district_wise.json";

        StringRequest stringRequest = new StringRequest(Request.Method.GET, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject object = new JSONObject(response);
                    Iterator<String> stateKeys = object.keys();

                    while(stateKeys.hasNext()){
                        String stateName = stateKeys.next();
                        state = new stateName(stateName);
                        list.add(state);
                    }
                    SA = new stateAdapter(StateActivity.this,list);
                    stateView.setAdapter(SA);


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(StateActivity.this,error.getMessage(),Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
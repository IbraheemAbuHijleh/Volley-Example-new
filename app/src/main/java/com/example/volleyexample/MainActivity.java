package com.example.volleyexample;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RequestQueue queue;
    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue= Volley.newRequestQueue(this);
        list=findViewById(R.id.list);
    }

    public void btmclick(View view) {


        String URL = "https://jsonplaceholder.org/posts";  // Corrected the URL
        JsonArrayRequest request = new JsonArrayRequest(
                Request.Method.GET,
                URL,
                null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        ArrayList<String> list1= new ArrayList<>();
                        for (int i = 0; i < 20; i++) {
                            try {
                              JSONObject o=response.getJSONObject(i);
                              list1.add(o.getString("title"));
                            } catch (JSONException e) {
                                Log.d("Error 1",e.toString());
                            }

                        }
                        ArrayAdapter<String> adapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,list1);
                        list.setAdapter(adapter);
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Log.e("Volley", "Error: " + error.toString());
                    }

                }

        );

// Add the request to the RequestQueue

        queue.add(request);

    }
}
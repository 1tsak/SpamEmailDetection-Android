package com.a2v10.spamemaildetection;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class MailDetailsActivity extends AppCompatActivity {

    TextView subject, body;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mail_details);
        subject = findViewById(R.id.subject);
        body = findViewById(R.id.body);
        subject.setText(getIntent().getStringExtra("subject"));
        body.setText(getIntent().getStringExtra("body"));
        checkSpam(getIntent().getStringExtra("body"));

    }


     void checkSpam(String body) {

        // Set up the Volley request queue
        RequestQueue queue = Volley.newRequestQueue(this);

        // Set the API endpoint URL
        String url = "http://192.168.29.238:5000/predict";

        // Create the request body as a JSON object
        JSONObject requestBody = new JSONObject();
        try {
            requestBody.put("email", "email text here");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // Create a new POST request using Volley
        JsonObjectRequest request = new JsonObjectRequest(
                Request.Method.POST,
                url,
                requestBody,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // Handle the response
                        String prediction = response.toString();
                        Toast.makeText(MailDetailsActivity.this, prediction, Toast.LENGTH_LONG).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Handle the error
                        error.printStackTrace();
                    }
                }
        ) {
            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String, String> headers = new HashMap<>();
                headers.put("Content-Type", "application/json");
                return headers;
            }
        };

        // Add the request to the request queue
        queue.add(request);
    }


}
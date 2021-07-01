package com.companyName.zypher_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private String title,imageUrl,success_url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Context ct = this;

        //Requesting Data from URL using Volley library;
        RequestQueue queue = Volley.newRequestQueue(this);
        String url ="https://backend-test-zypher.herokuapp.com/testData";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest
                (Request.Method.POST, url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        //Closing the progress bar view
                        findViewById(R.id.loadingPB).setVisibility(View.GONE);

                        try {

                            title = response.getString("title");
                            imageUrl = response.getString("imageURL");
                            success_url = response.getString("success_url");
                        }catch (Exception e)
                        {
                            Log.v("JsonRead","Exception thrown trying to read json.");
                        }finally {
                            if(title!=null && imageUrl!=null && success_url!=null)
                            {
                                DialogBox.getInstance().showDialog(ct,title,imageUrl,success_url);
                            }
                        }

                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Log.e("Response",error.getMessage());

                    }
                });

        queue.add(jsonObjectRequest);
    }
}

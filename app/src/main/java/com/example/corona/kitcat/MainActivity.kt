package com.example.corona.kitcat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import org.json.JSONArray

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getCatImage()
        nextPhoto.setOnClickListener {
            val toast = Toast.makeText(this,"Wait for another photo to load !",Toast.LENGTH_LONG)
            toast.setGravity(
                Gravity.CENTER_VERTICAL, 0, 0)
            toast.show()
            getCatImage()
        }
    }

    private fun getCatImage(){

        // Instantiate the RequestQueue.
        val queue = Volley.newRequestQueue(this)
        val url = "https://api.thecatapi.com/v1/images/search"

        // Request a string response from the provided URL.
        val stringRequest = StringRequest(
            Request.Method.GET, url,
            { response ->
                val url = JSONArray(response).getJSONObject(0).getString("url")
                Picasso.get()
                    .load(url)
                    .fit()
                    .centerCrop()
                    .placeholder( R.drawable.progress_animation )
                    .into(mainImage)

            },
            {
                Log.w("Error","We can't get the url or load the file!")
            })

        // Add the request to the RequestQueue.
        queue.add(stringRequest)
    }
}
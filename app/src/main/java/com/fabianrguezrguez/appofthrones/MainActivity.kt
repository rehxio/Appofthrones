package com.fabianrguezrguez.appofthrones

import android.content.Intent
import android.net.Uri
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.*
import java.io.IOException


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        googleBtn.setOnClickListener {
            val client = OkHttpClient()

            val request = Request.Builder()
                .url("https://www.leanmind.es")
                .build()


            client.newCall(request).enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    runOnUiThread {
                        padre.text = "Failure !"
                    }
                }

                override fun onResponse(call: Call, response: Response) {
                    val body = response.body()?.string()
                    runOnUiThread {
                        try {
                            padre.text = body
                        } catch (ioe: IOException) {
                            padre.text = "Error during get body"
                        }
                    }
                }
            })

        }
    }
}

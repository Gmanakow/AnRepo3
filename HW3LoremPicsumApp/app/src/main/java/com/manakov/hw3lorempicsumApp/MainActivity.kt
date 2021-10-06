package com.manakov.hw3lorempicsumApp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var imageView: ImageView
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextView)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.readyButton)

        button.setOnClickListener {
            getImage()
        }
    }

    private fun getImage() {
        Thread{
            try {
                val url = URL(editText.text.toString())
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

                connection.doInput = true
                connection.connectTimeout = 5000
                connection.connect()

                var inStream = connection.inputStream
                var bitmap: Bitmap = BitmapFactory.decodeStream(inStream)

                runOnUiThread{
                    imageView.setImageBitmap(bitmap)
                }

            } catch (e : java.lang.Exception) {
                Log.e("error", e.toString())
            }
        }.start()

    }
}

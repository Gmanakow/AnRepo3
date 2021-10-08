package com.manakov.hw3lorempicsumApp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.squareup.picasso.Picasso
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity() {

    lateinit var editText: EditText
    lateinit var imageView: ImageView
    lateinit var button: Button
    lateinit var picassoButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        editText = findViewById(R.id.editTextView)
        imageView = findViewById(R.id.imageView)
        button = findViewById(R.id.readyButton)
        picassoButton = findViewById(R.id.picassoButton)

        button.setOnClickListener {
            getImage()
        }

        picassoButton.setOnClickListener {
            try {
                Picasso
                    .get()
                    .load(
                        editText.text.toString()
                    )
                    .into(
                        imageView
                    )
            } catch (e : Exception){
                Toast.makeText(this, "err", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun getImage() {
        Thread {
            try {
                val url = URL(editText.text.toString())
                val connection: HttpURLConnection = url.openConnection() as HttpURLConnection

                connection.doInput = true
                connection.connectTimeout = 5000
                connection.connect()

                var inStream = connection.inputStream
                var bitmap: Bitmap = BitmapFactory.decodeStream(inStream)

                runOnUiThread {
                    imageView.setImageBitmap(bitmap)
                }

            } catch (e: java.lang.Exception) {
                runOnUiThread {
                    Toast.makeText(this, "err", Toast.LENGTH_SHORT).show()
                }
            }
        }.start()

    }
}

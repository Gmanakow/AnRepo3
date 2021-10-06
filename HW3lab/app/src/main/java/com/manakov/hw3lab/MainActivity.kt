package com.manakov.hw3lab

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import androidx.core.view.children

//flag task

class MainActivity : AppCompatActivity() {

    lateinit var list : ArrayList<View>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var viewGroup : ViewGroup = findViewById(R.id.mainView)

        list = ArrayList(viewGroup.childCount)
        list.addAll(viewGroup.children)

        Log.d("checking", viewGroup.childCount.toString() )

        list[
                (0 until viewGroup.childCount)
                    .random()
        ].visibility = View.VISIBLE
    }
}
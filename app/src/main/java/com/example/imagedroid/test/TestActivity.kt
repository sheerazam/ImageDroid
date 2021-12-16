package com.example.imagedroid.test

import android.os.Bundle
import android.widget.TextView
import androidx.activity.ComponentActivity
import com.example.imagedroid.R

class TestActivity : ComponentActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
        val txtValue = findViewById<TextView>(R.id.txtValue)
    }
}
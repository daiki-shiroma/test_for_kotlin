package com.example.test_for_kotolin

import android.content.Intent
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val textView: TextView = findViewById(R.id.textView)
        textView.setOnClickListener {
            val intent = Intent(this, DirectionalSwipeActivity::class.java)
            startActivity(intent)
        }
    }
}
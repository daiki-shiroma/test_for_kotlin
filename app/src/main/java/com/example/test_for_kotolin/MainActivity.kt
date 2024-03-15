package com.example.test_for_kotolin

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnThrowBall: Button = findViewById(R.id.button_throw_ball)
        val btnFeed: Button = findViewById(R.id.button_feed)

        btnThrowBall.setOnClickListener {
            // 正しいクラス参照方法: ファイル拡張子は不要
            val intent = Intent(this, ThrowBallActivity::class.java)
            startActivity(intent)
        }

        // btnFeed.setOnClickListener に関するコードがここに続く...
    }
}

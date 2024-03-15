package com.example.test_for_kotolin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.GestureDetector
import android.view.MotionEvent
import androidx.core.view.GestureDetectorCompat

abstract class ThrowBallActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    private lateinit var gestureDetector: GestureDetectorCompat

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_throw_ball)
        gestureDetector = GestureDetectorCompat(this, this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onDown(event: MotionEvent): Boolean {
        return true
    }

    fun onFling(event1: MotionEvent, event2: MotionEvent, velocityX: Float, velocityY: Float): Boolean {
        // フリック動作でボールを投げるロジックをここに実装
        return true
    }

    // 他のGestureDetector.OnGestureListenerメソッド...
}

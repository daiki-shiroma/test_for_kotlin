package com.example.test_for_kotolin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.MotionEvent
import android.widget.ImageView
import android.view.ScaleGestureDetector
import android.view.View
import android.widget.Button

class DragBallActivity : AppCompatActivity(), View.OnTouchListener {

    private var lastTouchX: Float = 0.0f
    private var lastTouchY: Float = 0.0f

    private lateinit var ballImage: ImageView
    private lateinit var backButton: Button
    private lateinit var scaleGestureDetector: ScaleGestureDetector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drag_ball)

        ballImage = findViewById(R.id.ball_image)
        ballImage.setOnTouchListener(this)

        scaleGestureDetector = ScaleGestureDetector(this, ScaleListener())

        backButton = findViewById(R.id.back_button)
        backButton.setOnClickListener {
            finish()
        }
    }

    override fun onTouch(v: View, event: MotionEvent): Boolean {
        scaleGestureDetector.onTouchEvent(event)

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastTouchX = event.rawX
                lastTouchY = event.rawY
            }
            MotionEvent.ACTION_MOVE -> {
                val (newX, newY) = dragBall(event, lastTouchX, lastTouchY)
                lastTouchX = newX
                lastTouchY = newY
            }
        }
        return true
    }

    private inner class ScaleListener : ScaleGestureDetector.SimpleOnScaleGestureListener() {
        override fun onScale(detector: ScaleGestureDetector): Boolean {
            val scaleFactor = detector.scaleFactor

            ballImage.scaleX *= scaleFactor
            ballImage.scaleY *= scaleFactor

            // スケールを適用する最大値、最小値を設定する場合
            ballImage.scaleX = 0.1f.coerceAtLeast(ballImage.scaleX.coerceAtMost(5.0f))
            ballImage.scaleY = 0.1f.coerceAtLeast(ballImage.scaleY.coerceAtMost(5.0f))

            return true
        }
    }

    private fun dragBall(event: MotionEvent, lastTouchX: Float, lastTouchY: Float): Pair<Float, Float>{
        val dx = event.rawX - lastTouchX
        val dy = event.rawY - lastTouchY

        // ビュー（ボール）の位置を更新
        ballImage.x += dx
        ballImage.y += dy

        return Pair(event.rawX, event.rawY)
    }
}
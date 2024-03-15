package com.example.test_for_kotolin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import androidx.core.view.GestureDetectorCompat
import android.animation.ObjectAnimator
import android.animation.Animator
import android.animation.Animator.*

class ThrowBallActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var ballImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_throw_ball)

        ballImage = findViewById(R.id.ball_image)

        ballImage.setOnClickListener {
            throwBall()
        }

        gestureDetector = GestureDetectorCompat(this, this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onShowPress(e: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent): Boolean {
        TODO("Not yet implemented")
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        TODO("Not yet implemented")
    }

    override fun onLongPress(e: MotionEvent) {
        TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        this.throwBall()
        return true
    }

    private fun throwBall() {
        val displayMetrics = resources.displayMetrics
        val screenHeight = displayMetrics.heightPixels

        // ボールのサイズとステータスバーの高さを考慮した移動距離
        val translateYDistance = -screenHeight.toFloat() + ballImage.height


        val moveUp = ObjectAnimator.ofFloat(ballImage, "translationY", 0f, translateYDistance).apply {
            duration = 1000 // ミリ秒単位でアニメーションの期間を設定
        }
        val fadeOut = ObjectAnimator.ofFloat(ballImage, "alpha", 1f, 0f).apply {
            duration = 1000 // ミリ秒単位でフェードアウトの期間を設定
        }
        val resetPosition = ObjectAnimator.ofFloat(ballImage, "translationY", ballImage.translationY, 0f).apply {
            duration = 0 // 位置をリセットするためのアニメーションなので、即時に戻す
        }
        val resetAlpha = ObjectAnimator.ofFloat(ballImage, "alpha", ballImage.alpha, 1f).apply {
            duration = 0 // アルファ値をリセットするためのアニメーションなので、即時に戻す
        }

        moveUp.addListener(object : AnimatorListener {
            override fun onAnimationStart(animation: Animator) {
                fadeOut.start()
            }

            override fun onAnimationEnd(animation: Animator) {
                resetPosition.start()
                resetAlpha.start()
            }

            override fun onAnimationCancel(animation: Animator) {
                TODO("Not yet implemented")
            }

            override fun onAnimationRepeat(animation: Animator) {
                TODO("Not yet implemented")
            }
        })
        moveUp.start()
    }
}
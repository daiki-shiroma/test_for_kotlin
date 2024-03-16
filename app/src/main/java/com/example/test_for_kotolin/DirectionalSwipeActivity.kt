package com.example.test_for_kotolin

import android.animation.Animator
import android.animation.ObjectAnimator
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class DirectionalSwipeActivity : AppCompatActivity(),View.OnTouchListener {
    private var startX: Float = 0.0f
    private var startY: Float = 0.0f

    private lateinit var ballImage: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_throw_ball)

        ballImage = findViewById(R.id.ball_image)
        ballImage.setOnTouchListener(this)
    }

    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = event.x
                startY = event.y
            }
            MotionEvent.ACTION_UP -> {
                val endX = event.x
                val endY = event.y
                val distanceX = endX - startX
                val distanceY = endY - startY

                when {
                    distanceX > 100 -> onSwipeRight()
                    distanceX < -100 -> onSwipeLeft()
                    distanceY > 100 -> onSwipeDown()
                    distanceY < -100 -> onSwipeUp()
                }
            }
        }
        return true
    }

    private fun onSwipeRight() { /* 右へのスワイプ処理 */ }
    private fun onSwipeLeft() { /* 左へのスワイプ処理 */ }
    private fun onSwipeUp() {
        /* 上へのスワイプ処理 */
        throwBall()
    }
    private fun onSwipeDown() { /* 下へのスワイプ処理 */ }
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

        moveUp.addListener(object : Animator.AnimatorListener {
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
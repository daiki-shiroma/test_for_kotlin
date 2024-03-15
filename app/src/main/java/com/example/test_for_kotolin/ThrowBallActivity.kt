package com.example.test_for_kotolin

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.GestureDetector
import android.view.MotionEvent
import android.widget.ImageView
import androidx.core.view.GestureDetectorCompat
import android.animation.AnimatorSet
import android.animation.ObjectAnimator

class ThrowBallActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    private lateinit var gestureDetector: GestureDetectorCompat
    private lateinit var ballImage: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_throw_ball)

        ballImage = findViewById(R.id.ball_image)
        gestureDetector = GestureDetectorCompat(this, this)
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        gestureDetector.onTouchEvent(event)
        return super.onTouchEvent(event)
    }

    override fun onDown(e: MotionEvent): Boolean {
        // 必ず true を返すことで、他のジェスチャイベントが発生することを可能にします
        return true
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
        val moveUp = ObjectAnimator.ofFloat(ballImage, "translationY", 0f, -1000f).apply {
            duration = 1000 // ミリ秒単位でアニメーションの期間を設定
        }
        val fadeOut = ObjectAnimator.ofFloat(ballImage, "alpha", 1f, 0f).apply {
            duration = 1000 // ミリ秒単位でフェードアウトの期間を設定
        }
        AnimatorSet().apply {
            playTogether(moveUp, fadeOut) // アニメーションを同時に実行
            start()
        }
    }
}

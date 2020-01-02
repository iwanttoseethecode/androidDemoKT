package com.example.androiddemokt.ui.propertyAnimation

import android.animation.*
import android.animation.Animator.AnimatorListener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.core.animation.addListener
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_coin.*

class CoinActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_coin)



    }

    fun coinSelfRotationClick(view: View) {
        var animator = ObjectAnimator.ofFloat(coinTV,"rotationY",0f,360f)

        animator.setDuration(800)
        animator.repeatCount = 1
        animator.repeatMode = ValueAnimator.RESTART

        animator.addUpdateListener {
            var fraction = it.animatedFraction

            if (fraction >= 0.25f && fraction < 0.75f){
                coinTV.setBackgroundResource(R.drawable.coin_green_shape)
                coinTV.setText("反面")
            }else{
                coinTV.setBackgroundResource(R.drawable.coin_red_shape)
                coinTV.setText("正面")
            }
        }

        animator.start()

    }

    fun coinThrowClick(view: View) {
        var animator1 = ObjectAnimator.ofFloat(coinTV,"rotationX",0f,360f)
        animator1.setDuration(800)
        animator1.repeatCount = 4
        animator1.repeatMode = ValueAnimator.RESTART
        animator1.addUpdateListener{
            var fraction = it.animatedFraction

            if (fraction >= 0.25f && fraction < 0.75f){
                coinTV.setBackgroundResource(R.drawable.coin_green_shape)
                coinTV.setText("反面")
            }else{
                coinTV.setBackgroundResource(R.drawable.coin_red_shape)
                coinTV.setText("正面")
            }

        }

        var animator2 = ObjectAnimator.ofFloat(coinTV,"translationY",-200f,600f)
        animator2.setDuration(2000)
        animator2.repeatCount = 1
        animator2.repeatMode = ValueAnimator.REVERSE

        var animatorSet = AnimatorSet()
        animatorSet.playTogether(animator1,animator2)
        animatorSet.start()

    }
}

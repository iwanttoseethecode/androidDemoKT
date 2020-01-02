package com.example.androiddemokt.ui.newAnimation

import android.animation.AnimatorInflater
import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_animation.*

class AnimationActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_animation)
    }

    fun clickBtn1(view: View) {
        var objectAnimator = ObjectAnimator.ofFloat(btn1,"translationX",0f,300f)
        objectAnimator.setDuration(2000)
        objectAnimator.start()
    }
    fun clickBtn2(view: View) {
        var objectAnimator = ObjectAnimator.ofFloat(btn2,"translationX",0f,300f,0f)
        objectAnimator.setDuration(2000)
        objectAnimator.start()
    }
    fun clickBtn3(view: View) {
        var objectAnimator = ObjectAnimator.ofInt(btn3,"backgroundColor",resources.getColor(R.color.colorAccent,null),resources.getColor(R.color.colorPrimary,null))
        objectAnimator.repeatCount = ValueAnimator.INFINITE
        objectAnimator.repeatMode = ValueAnimator.REVERSE
        objectAnimator.setDuration(2000)
        objectAnimator.start()
    }
    fun clickBtn4(view: View) {
        var objectAnimator = ObjectAnimator.ofFloat(btn4,"alpha",0f,1f)
        objectAnimator.setDuration(2000)
        objectAnimator.start()
    }
    fun clickBtn5(view: View) {
        var animatorSet = AnimatorSet()
        animatorSet.playTogether(
            ObjectAnimator.ofFloat(btn5,"alpha",0f,1f,0.5f,1f),
            ObjectAnimator.ofFloat(btn5,"rotation",0f,360f,0f),
            ObjectAnimator.ofFloat(btn5,"scaleX",0f,1f,1.5f,1f),
            ObjectAnimator.ofFloat(btn5,"scaleY",0f,1f,1.5f,1f),
            ObjectAnimator.ofFloat(btn5,"translationX",0f,200f,0f),
            ObjectAnimator.ofFloat(btn5,"translationY",0f,100f,0f)
        )
        animatorSet.setDuration(5000)
        animatorSet.start()
    }
    fun clickBtn6(view: View) {
        var valueAnimator = ValueAnimator.ofFloat(0f,1f)
        valueAnimator.setDuration(5000)
        valueAnimator.addUpdateListener{
            var value = it.animatedValue as Float
            btn6.alpha = value
            btn6.scaleX = value
            btn6.scaleY = value
        }
        valueAnimator.start()
    }
    fun clickBtn7(view: View) {
        var objectAnimator1 = ObjectAnimator.ofFloat(btn7,"translationX",-200f,0f)
        var objectAnimator2 = ObjectAnimator.ofFloat(btn7,"rotation",0f,360f)
        var objectAnimator3 = ObjectAnimator.ofFloat(btn7,"alpha",1f,0f,1f)

        var animatorSet1 = AnimatorSet()
        animatorSet1.play(objectAnimator2).with(objectAnimator3).after(objectAnimator1)
        animatorSet1.setDuration(4000)
        animatorSet1.start()
    }
    fun clickBtn8(view: View) {
        var animatorSet = AnimatorInflater.loadAnimator(this,R.anim.btn_anim)
        animatorSet.setTarget(btn8)
        animatorSet.start()
    }
    fun clickBtn9(view: View) {
        var intent = Intent(this,ValueAnimatorActivity::class.java)
        startActivity(intent)
    }
}

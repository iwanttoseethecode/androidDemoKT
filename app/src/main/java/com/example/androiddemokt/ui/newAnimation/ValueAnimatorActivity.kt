package com.example.androiddemokt.ui.newAnimation

import android.animation.ObjectAnimator
import android.animation.TimeInterpolator
import android.animation.ValueAnimator
import android.graphics.PointF
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.*
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_value_animator.*

/*属性动画之TimeInterpolator和TypeEvaluator
  转载地址：http://blog.csdn.net/javazejian/article/details/52334098
  每个TimeInterpolator和TypeEvaluator的衍生类都是代表着一个数学中的函数公式，而每一个ValueAnimator也不过是一个TimeInterpolator和一个TypeEvaluator的结合体。
  从数学的函数角度来说，ValueAnimator就是由TimeInterpolator和TypeEvaluator这两个简单函数组合而成的一个复合函数，
  ValueAnimator而就是通过TimeInterpolator和TypeEvaluator相结合的方式来对一个数值作运动的。

  继承关系 TimeInterpolator <—— Interpolator <—— BaseInterpolator <——其中常用的有LinearInterpolator（线性插值器：匀速动画，刚分析过），
  AccelerateDecelerateInterpolator（加速减速器：动画开头和结束慢，中间快，默认插值器），DecelerateInterpolator（减速插值器：动画越来越慢）、
  BounceInterpolator （弹跳插值器）、AnticipateInterpolator （回荡秋千插值器）、CycleInterpolator （正弦周期变化插值器）等等

*/

class ValueAnimatorActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_value_animator)
    }

    fun resetBtn(view: View) {
        var resetAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",0f)
        resetAnimator.setDuration(800)
        resetAnimator.interpolator = LinearInterpolator()
        resetAnimator.start()
    }

    fun clickBtn1(view: View) {//线性插值器
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",0f,1600f)
        objectAnimator.setDuration(5000)
        objectAnimator.interpolator = LinearInterpolator()
        objectAnimator.start()
    }

    fun clickBtn2(view: View) {//加速减速器
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",0f,1600f)
        objectAnimator.setDuration(5000)
        objectAnimator.interpolator = AccelerateDecelerateInterpolator()
        objectAnimator.start()
    }
    fun clickBtn3(view: View) {//加速器插值器
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",0f,1600f)
        objectAnimator.setDuration(5000)
        objectAnimator.interpolator = AccelerateInterpolator()
        objectAnimator.start()
    }
    fun clickBtn4(view: View) {//减速插值器
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",0f,1600f)
        objectAnimator.duration = 5000
        objectAnimator.interpolator = DecelerateInterpolator()
        objectAnimator.start()
    }
    fun clickBtn5(view: View) {//回荡插值器，特点慢速反向运动然后加速会往回落
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",200f,1600f)
        objectAnimator.duration = 5000
        objectAnimator.interpolator = AnticipateInterpolator()
        objectAnimator.start()
    }
    fun clickBtn6(view: View) {//正弦周期插值器，特点以指定的周期重复动画，变化率曲线为正弦
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",400f,1000f)
        objectAnimator.duration = 5000
        objectAnimator.interpolator = CycleInterpolator(2f)
        objectAnimator.start()
    }
    fun clickBtn7(view: View) {//弹跳插值器，特点是动画结尾，呈弹跳状态。
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",200f,1200f)
        objectAnimator.duration = 5000
        objectAnimator.interpolator = BounceInterpolator()
        objectAnimator.start()
    }
    fun clickBtn8(view: View) {//特点开始向上推，然后向下荡，荡过最低线。然后再回到最低线。
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",200f,1600f)
        objectAnimator.duration = 5000
        objectAnimator.interpolator = AnticipateOvershootInterpolator()
        objectAnimator.start()
    }
    fun clickBtn9(view: View) {//自定义Interpolator
        var objectAnimator = ObjectAnimator.ofFloat(runningboll,"translationY",400f,800f)
        objectAnimator.duration = 5000
        objectAnimator.interpolator = MyInterpolator(3f)
        objectAnimator.start()
    }
    fun clickBtn10(view: View) {//自定义抛物线运动,PointF数值为float,Point数值为int,我们这里选择PointF
        var valueAnimator = ValueAnimator()
        valueAnimator.setObjectValues(PointF(0f,0f))
        valueAnimator.interpolator = LinearInterpolator()
        valueAnimator.setEvaluator(MyEvalutor())
        valueAnimator.duration = 5000
        valueAnimator.addUpdateListener {
            var pointf:PointF = valueAnimator.animatedValue as PointF
            runningboll.x = pointf.x
            runningboll.y = pointf.y
        }
        valueAnimator.start()
    }
}

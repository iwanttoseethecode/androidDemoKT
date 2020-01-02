package com.example.androiddemokt.ui.drawble

import android.graphics.BitmapFactory
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_drawble.*

class DrawbleActivity : AppCompatActivity() {

    var mImgIds = intArrayOf(R.mipmap.avft,
        R.mipmap.box_stack,
        R.mipmap.bubble_frame,
        R.mipmap.bubbles,
        R.mipmap.bullseye,
        R.mipmap.circle_filled,
        R.mipmap.circle_outline,
        R.mipmap.avft,
        R.mipmap.box_stack,
        R.mipmap.bubble_frame,
        R.mipmap.bubbles,
        R.mipmap.bullseye,
        R.mipmap.circle_filled,
        R.mipmap.circle_outline)

    var mImgIds_active = intArrayOf(
        R.mipmap.avft_active,
        R.mipmap.box_stack_active,
        R.mipmap.bubble_frame_active,
        R.mipmap.bubbles_active,
        R.mipmap.bullseye_active,
        R.mipmap.circle_filled_active,
        R.mipmap.circle_outline_active,
        R.mipmap.avft_active,
        R.mipmap.box_stack_active,
        R.mipmap.bubble_frame_active,
        R.mipmap.bubbles_active,
        R.mipmap.bullseye_active,
        R.mipmap.circle_filled_active,
        R.mipmap.circle_outline_active
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_drawble)
        initReveal()
        initRoundImage()
        initCircleImage()
        initSearchImage()
    }

    fun initSearchImage(){
        searchImgView.setImageDrawable(SearchDrawable())
    }

    fun initCircleImage(){
        var bitmap = BitmapFactory.decodeResource(resources,R.mipmap.p4)
        circleImageView.setImageDrawable(CircleImageDrawable(bitmap))
    }

    fun initRoundImage(){
        var bitmap = BitmapFactory.decodeResource(resources,R.mipmap.p4)
        roundImageView.setImageDrawable(RoundImageDrawable(bitmap,40f))
    }

    fun initReveal(){
        var revealDrawables = arrayOfNulls<Drawable>(mImgIds.size)
        for (i in 0 until mImgIds.size){
            var rd = RevealDrawble(resources.getDrawable(mImgIds[i],null),resources.getDrawable(mImgIds_active[i],null),RevealDrawble.HORIZONTAL)
            revealDrawables[i] = rd
        }
        gallaryHorizonalScrollView.addImageViews(revealDrawables)
    }

}

package com.example.androiddemokt.ui.mainPage.dataSource

import com.example.androiddemokt.ui.Graphics2D.BoomActivity
import com.example.androiddemokt.ui.Graphics2D.ClipActivity
import com.example.androiddemokt.ui.Graphics2D.WatchActivity
import com.example.androiddemokt.ui.circleProgressBar.CircleProgressBarActivity
import com.example.androiddemokt.ui.colorFilter.ColorFilterActivity
import com.example.androiddemokt.ui.coordinateChange.CoordinateActivity
import com.example.androiddemokt.ui.customViewPager.CustomViewPagerActivity
import com.example.androiddemokt.ui.doubleCache.DrawRectActivity
import com.example.androiddemokt.ui.doubleCache.TabletActivity
import com.example.androiddemokt.ui.drawble.DrawbleActivity
import com.example.androiddemokt.ui.flowLayout.FlowLayoutActivity
import com.example.androiddemokt.ui.headerFooter.HeaderFooterRecyclerActivity
import com.example.androiddemokt.ui.newAnimation.AnimationActivity
import com.example.androiddemokt.ui.paintPathCanvas.FirstPaintCanvasActivity
import com.example.androiddemokt.ui.paintPathCanvas.PaintDetailActivity
import com.example.androiddemokt.ui.paintPathCanvas.PathActivity
import com.example.androiddemokt.ui.pathMeasure.PathMeasureActivity
import com.example.androiddemokt.ui.propertyAnimation.CoinActivity
import com.example.androiddemokt.ui.shadow.ShadowActivity
import com.example.androiddemokt.ui.targetCricle.TargetCircleActivity
import com.example.androiddemokt.ui.waterfallLayout.WaterfallActivity
import com.example.androiddemokt.ui.xformode.XformodeActivity
import com.example.androiddemokt.ui.xformode.XformodeDemo2Activity
import com.example.androiddemokt.ui.xformode.XformodeDemoActivity

/**
 * Created by luoling on 2019/11/21.
 * description:
 */

private var BasicNoteTitles = listOf<TitleModel>(
    TitleModel("硬币动画视图",CoinActivity::class.java),
    TitleModel("动画视图",AnimationActivity::class.java),
    TitleModel("Canvas、Paint使用",FirstPaintCanvasActivity::class.java),
    TitleModel("canvas坐标变换",CoordinateActivity::class.java),
    TitleModel("Paint的详细应用",PaintDetailActivity::class.java),
    TitleModel("Path使用",PathActivity::class.java),
    TitleModel("Xformode效果展示",XformodeActivity::class.java),
    TitleModel("Xformode案例",XformodeDemoActivity::class.java),
    TitleModel("Xformode案例2", XformodeDemo2Activity::class.java),
    TitleModel("ColorFilter效果",ColorFilterActivity::class.java),
    TitleModel("PathMeasure使用",PathMeasureActivity::class.java),
    TitleModel("写字板",TabletActivity::class.java),
    TitleModel("画矩形",DrawRectActivity::class.java),
    TitleModel("图片剪裁",ClipActivity::class.java),
    TitleModel("阴影和渐变",ShadowActivity::class.java),
    TitleModel("爆炸效果动画", BoomActivity::class.java),
    TitleModel("自定义时钟",WatchActivity::class.java)
)

private var CustomNoteTitles = listOf<TitleModel>(
    TitleModel("自定义drawable",DrawbleActivity::class.java),
    TitleModel("自定义靶心视图", TargetCircleActivity::class.java),
    TitleModel("自定义环形进度条",CircleProgressBarActivity::class.java),
    TitleModel("自定义流式布局",FlowLayoutActivity::class.java),
    TitleModel("瀑布流布局",WaterfallActivity::class.java),
    TitleModel("自定义ViewPager",CustomViewPagerActivity::class.java),
    TitleModel("RecyclerView添加头部和尾部",HeaderFooterRecyclerActivity::class.java)
)

fun generateBasicNoteData(startIndex:Int,endIndex:Int):List<TitleModel>?{

    if (startIndex>endIndex || startIndex > BasicNoteTitles.size){
        return null
    }

    var realEndIndex = endIndex
    if (endIndex >= BasicNoteTitles.size){
        realEndIndex = BasicNoteTitles.size - 1
    }

    return BasicNoteTitles.slice(startIndex .. realEndIndex)
}

fun generateCustomNoteData(startIndex: Int,endIndex: Int):List<TitleModel>?{

    if (startIndex>endIndex || startIndex > CustomNoteTitles.size){
        return null
    }

    var realEndIndex = endIndex
    if (endIndex >= CustomNoteTitles.size){
        realEndIndex = CustomNoteTitles.size - 1
    }

    return CustomNoteTitles.slice(startIndex .. realEndIndex)
}
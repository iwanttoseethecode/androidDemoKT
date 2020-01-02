package com.example.androiddemokt.ui.circleProgressBar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.androiddemokt.R
import kotlinx.android.synthetic.main.activity_circle_progress_bar.*
import java.lang.Thread.sleep

class CircleProgressBarActivity : AppCompatActivity() {

    internal var progress:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_circle_progress_bar)

        circleProgressBar.setOnClickListener(View.OnClickListener {
            Thread(Runnable {
                progress = 0
                while (progress <= 100) {
                    progress += 2
                    circleProgressBar.setProgress(progress)
                    try {
                        sleep(100)
                    } catch (e: InterruptedException) {
                        e.printStackTrace()
                    }

                }
            }).start()
        })

    }

}

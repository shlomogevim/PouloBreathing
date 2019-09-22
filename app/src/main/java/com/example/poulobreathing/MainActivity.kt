package com.example.poulobreathing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import com.example.poulobreathing.util.Prefs
import com.github.florent37.viewanimator.ViewAnimator
import kotlinx.android.synthetic.main.activity_main.*
import java.text.MessageFormat

class MainActivity : AppCompatActivity() {

    lateinit var prefs: Prefs

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = Prefs(this)

        breathTakenTxt.text = MessageFormat.format("{0} min today", prefs.sessions)
        todayMinusText.text = MessageFormat.format("{0} breaths", prefs.breaths)
        lastBreathTxt.text = prefs.date

        startIntroAnimation()

        startButton.setOnClickListener {

            startAnimation()
        }
    }

    private fun startAnimation() {
        ViewAnimator
            .animate(loutosImageID)
            .alpha(0f, 1f)
            .onStart {
                guidText.text = "Inhale.. Exhale"
            }
            .decelerate()
            .duration(1000)
            .thenAnimate(loutosImageID)
            .scale(0.02f, 1.5f, 0.02f)
            .rotation(360f)
            .repeatCount(5)
            .accelerate()
            .duration(5000)
            .onStop {
                guidText.text = "Good Job"
                loutosImageID.scaleX = 1.0f
                loutosImageID.scaleY = 1.0f

                prefs.sessions = prefs.sessions + 1
                prefs.breaths = prefs.breaths + 1
                prefs.setDate(System.currentTimeMillis())

                var handler = Handler()
                var countDownTimer = object : Runnable {
                    override fun run() {
                        startActivity(Intent(this@MainActivity, MainActivity::class.java))
                        finish()
                    }
                }
                handler.postDelayed(countDownTimer, 100)
            }.start()
    }

    private fun startIntroAnimation() {   //https://github.com/florent37/ViewAnimator
        ViewAnimator
            .animate(guidText)
            .scale(0f, 2f)
            .duration(1500)
            .onStart {
                guidText.text = "Breathe"
            }
            .start()
    }

}

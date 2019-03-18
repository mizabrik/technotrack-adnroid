package name.mizabrik.technotrackhomework

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

private val STATE_TIMER = "name.mizabrik.technotrackhomework.SPLASHSCREEN_TIMER"

class SplashScreenActivity : AppCompatActivity() {
    private lateinit var timer: PausableTimer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_splashscreen)

        if (savedInstanceState == null) {
            timer = PausableTimer(2000)
        } else {
            timer = savedInstanceState.getParcelable(STATE_TIMER)!!
        }

        timer.onFinish = {
            val intent = Intent(this, CounterActivity::class.java).apply {
                setFlags(Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_TASK_ON_HOME)
            }
            startActivity(intent)
            finish()
        }
    }

    override fun onStart() {
        super.onStart()
        timer.start()
    }

    override fun onStop() {
        timer.pause()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putParcelable(STATE_TIMER, timer)
        super.onSaveInstanceState(outState)
    }
}
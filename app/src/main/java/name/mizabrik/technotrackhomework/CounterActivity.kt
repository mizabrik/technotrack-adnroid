package name.mizabrik.technotrackhomework

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_counter.*

private val STATE_TIMER = "name.mizabrik.technotrackhomework.COUNTER_TIMER"
private val STATE_COUNTING = "name.mizabrik.technotrackhomework.COUNTER_COUNTING"
private val STATE_TEXT = "name.mizabrik.technotrackhomework.COUNTER_TEXT"

class CounterActivity : AppCompatActivity() {
    private lateinit var timer: PausableTimer
    private var counting = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_counter)

        if (savedInstanceState == null) {
            timer = PausableTimer(1_000_000)
        } else {
            timer = savedInstanceState.getParcelable(STATE_TIMER)!!
            counting = savedInstanceState.getBoolean(STATE_COUNTING)
            textView.text = savedInstanceState.getString(STATE_TEXT)!!

            if (counting) button.setText(R.string.button_stop)
        }

        timer.onFinish = {
            counting = false
            textView.text = 1000.toRussian()
            button.setText(R.string.button_start)
        }
        timer.onSecond = {
            textView.text = it.toInt().toRussian()
        }
    }

    override fun onStart() {
        super.onStart()
        if (counting) timer.start()
    }

    fun onButtonClick(view: View) {
        if (counting) {
            button.setText(R.string.button_start)
            timer.cancel()
        } else {
            textView.text = ""
            button.setText(R.string.button_stop)
            timer.start()
        }

        counting = !counting
    }

    override fun onStop() {
        if (counting) timer.pause()
        super.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        outState?.putString(STATE_TEXT, textView.text.toString())
        outState?.putBoolean(STATE_COUNTING, counting)
        outState?.putParcelable(STATE_TIMER, timer)
        super.onSaveInstanceState(outState)
    }
}
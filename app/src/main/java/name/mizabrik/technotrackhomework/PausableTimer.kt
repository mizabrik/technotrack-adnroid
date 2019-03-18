package name.mizabrik.technotrackhomework

import android.os.CountDownTimer
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

@Parcelize
class PausableTimer(
    private val millisInFuture: Long,
    private var millisElapsed: Long = 0
) : Parcelable {
    private inner class Timer(millisInFuture: Long) : CountDownTimer(millisInFuture, 10) {
        override fun onFinish() {
            this@PausableTimer.onFinish?.invoke()
        }

        override fun onTick(millisUntilFinished: Long) {
            val oldSecond = millisElapsed / 1000
            millisElapsed = millisInFuture - millisUntilFinished
            val newSecond = millisElapsed / 1000

            if (newSecond > oldSecond) {
                onSecond?.invoke(newSecond)
            }
        }

    }

    @IgnoredOnParcel
    private var timer: Timer? = null

    fun start() = synchronized(this) {
        if (timer != null) {
            throw RuntimeException("Timer is already started")
        }

        timer = Timer(millisInFuture - millisElapsed)
        timer!!.start()
    }

    fun pause() = synchronized(this) {
        if (timer == null) {
            throw RuntimeException("Timer is not started")
        }

        timer!!.cancel()
        timer = null
    }

    fun cancel() = synchronized(this) {
        if (timer == null) {
            throw RuntimeException("Timer is not started")
        }

        timer!!.cancel()
        timer = null
        millisElapsed = 0
    }

    @IgnoredOnParcel
    var onFinish: (() -> Unit)? = null

    @IgnoredOnParcel
    var onSecond: ((Long) -> Unit)? = null
}
import androidx.lifecycle.LiveData
import androidx.test.internal.runner.junit4.statement.UiThreadStatement.runOnUiThread
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit

fun <T> LiveData<T>.getOrAwaitValue(
    time: Long = 60,
    timeUnit: TimeUnit = TimeUnit.SECONDS
): T? {
    var data: T? = null
    val latch = CountDownLatch(1)
    val observer = object : androidx.lifecycle.Observer<T> {
        override fun onChanged(o: T?) {
            data = o
            latch.countDown()
            runOnUiThread {
                this@getOrAwaitValue.removeObserver(this)
            }
        }
    }

    this.observeForever(observer)

    if (!latch.await(time, timeUnit)) {
        return null
    }

    @Suppress("UNCHECKED_CAST")
    return data as T
}
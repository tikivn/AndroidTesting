package androidx.core.os

import android.os.Handler
import android.os.Message

/**
 * Version of [Handler.postDelayed] which adds the ability to specify [token], enabling the use
 * of [Handler.removeCallbacksAndMessages].
 */
@PublishedApi
internal fun Handler.postDelayedWithToken(runnable: Runnable, token: Any?, delayInMillis: Long) {
  // Note: this method signature ordering is designed to be an overload to the existing
  // postDelayed methods on Handler and matches the existing overloads for postAtTime.
  // TODO delete and replace with HandlerCompat.postDelayed once available.

  val message = Message.obtain(this, runnable)
  message.obj = token
  sendMessageDelayed(message, delayInMillis)
}

inline fun Handler.repeat(
  delayInMillis: Long,
  token: Any? = null,
  crossinline action: () -> Unit
) {
  postDelayedWithToken(object : Runnable {
    override fun run() {
      action()
      postDelayedWithToken(this, token, delayInMillis)
    }
  }, token, delayInMillis)
}

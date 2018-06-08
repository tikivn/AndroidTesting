package vn.tiki.android.androidtesting.testing

import vn.tiki.android.androidtesting.data.AppExecutors
import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
  companion object {
    private val instant = Executor { it.run() }
  }
}

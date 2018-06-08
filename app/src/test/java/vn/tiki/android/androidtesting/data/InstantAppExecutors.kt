package vn.tiki.android.androidtesting.data

import java.util.concurrent.Executor

class InstantAppExecutors : AppExecutors(instant, instant, instant) {
  companion object {
    private val instant = Executor { it.run() }
  }
}

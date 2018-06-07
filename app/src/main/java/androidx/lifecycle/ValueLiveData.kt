package androidx.lifecycle

import android.arch.lifecycle.LiveData

class ValueLiveData<T : Any?> private constructor(value: T?) : LiveData<T>() {

  init {
    // use post instead of set since this can be created on any thread
    postValue(value)
  }

  companion object {
    fun <T> create(value: T? = null): LiveData<T> {
      return ValueLiveData(value)
    }
  }
}

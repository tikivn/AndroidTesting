package androidx.lifecycle

import android.arch.lifecycle.LifecycleOwner
import android.arch.lifecycle.MediatorLiveData

class NonNullLiveData<T> : MediatorLiveData<T>()

fun <T> NonNullLiveData<T>.observe(owner: LifecycleOwner, observer: (t: T) -> Unit) {
  this.observe(owner, android.arch.lifecycle.Observer {
    it?.let(observer)
  })
}

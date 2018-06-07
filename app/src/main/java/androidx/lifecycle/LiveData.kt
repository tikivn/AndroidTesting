package androidx.lifecycle

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MediatorLiveData
import android.arch.lifecycle.Transformations
import android.support.annotation.MainThread

@MainThread
fun <T> LiveData<T>.nonNull(): NonNullLiveData<T> {
  val mediator: NonNullLiveData<T> = NonNullLiveData()
  mediator.addSource(this, { it?.let { mediator.value = it } })
  return mediator
}

@MainThread
fun <X, Y> LiveData<X>.map(func: (X?) -> Y?): LiveData<Y> = Transformations.map(this, func)

@MainThread
fun <X> LiveData<X>.filter(predicate: (X?) -> Boolean): LiveData<X> {
  val result = MediatorLiveData<X>()
  result.addSource(this, { value ->
    if (predicate(value)) {
      result.value = value
    }
  })
  return result
}

@MainThread
fun <X, Y> LiveData<X>.switchMap(func: (X?) -> LiveData<Y>): LiveData<Y> = Transformations.switchMap(this, func)

object LiveDataX {

  fun <T> just(value: T?): LiveData<T> {
    return ValueLiveData.create(value)
  }
}

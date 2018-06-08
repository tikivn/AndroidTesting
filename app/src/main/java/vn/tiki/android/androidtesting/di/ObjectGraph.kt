package vn.tiki.android.androidtesting.di

import android.app.Activity
import android.content.Context
import android.support.v4.app.Fragment
import vn.tiki.android.androidtesting.MainApplication
import kotlin.LazyThreadSafetyMode.NONE

class ObjectGraph {

  private val factories = mutableMapOf<Class<*>, Factory<*>>()

  fun <T> put(clazz: Class<T>, factory: Factory<T>) {
    factories[clazz] = factory
  }

  @Suppress("UNCHECKED_CAST")
  fun <T> get(clazz: Class<T>): Lazy<T> = lazy(NONE) {
    factories[clazz]?.invoke() as T ?: throw IllegalStateException("no Factory for $clazz")
  }
}

inline fun <reified T> ObjectGraph.get(): T = get(T::class.java).value

inline fun <reified T> ObjectGraph.add(noinline factory: Factory<T>) = put(T::class.java, factory)

fun Context.objectGraph() = (applicationContext as MainApplication).objectGraph

inline fun <reified T> Fragment.inject(): Lazy<T> = lazy(NONE) {
  val context: Context = context ?: throw IllegalStateException("Fragment.getContext() is null")
  context.objectGraph().get<T>()
}

inline fun <reified T> Activity.inject(): Lazy<T> = lazy(NONE) {
  objectGraph().get<T>()
}

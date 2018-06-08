package vn.tiki.android.androidtesting

import android.app.Application
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import android.content.SharedPreferences
import vn.tiki.android.androidtesting.data.AppExecutors
import vn.tiki.android.androidtesting.data.local.LocalStorage
import vn.tiki.android.androidtesting.data.local.LocalStorageImpl
import vn.tiki.android.androidtesting.data.remote.ApiServices
import vn.tiki.android.androidtesting.data.repository.UserRepository
import vn.tiki.android.androidtesting.di.ObjectGraph
import vn.tiki.android.androidtesting.di.add
import vn.tiki.android.androidtesting.di.get
import vn.tiki.android.androidtesting.ui.login.LoginViewModel

open class MainApplication : Application() {

  lateinit var objectGraph: ObjectGraph

  override fun onCreate() {
    super.onCreate()
    objectGraph = ObjectGraph()
    initDependencies(objectGraph)
  }

  protected open fun initDependencies(objectGraph: ObjectGraph) {
    val context: Context = this
    objectGraph.apply {
      add { context }
      add { AppExecutors() }
      add {
        context.getSharedPreferences("local_storage", Context.MODE_PRIVATE) as SharedPreferences
      }
      add {
        val appExecutors = get<AppExecutors>()
        val sharedPreferences = get<SharedPreferences>()
        LocalStorageImpl(appExecutors, sharedPreferences) as LocalStorage
      }
      add {
        val appExecutors = get<AppExecutors>()
        val apiService = get<ApiServices>()
        val localStorage = get<LocalStorage>()
        UserRepository(appExecutors, apiService, localStorage)
      }
      add {
        object : ViewModelProvider.Factory {
          @Suppress("UNCHECKED_CAST")
          override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            val viewModel: ViewModel = when {
              modelClass.isAssignableFrom(LoginViewModel::class.java) -> LoginViewModel(context, get())
              modelClass.isAssignableFrom(MainViewModel::class.java) -> MainViewModel(get())
              else -> throw IllegalArgumentException("unknown model class $modelClass")
            }
            return viewModel as T
          }
        } as ViewModelProvider.Factory
      }
    }
  }
}

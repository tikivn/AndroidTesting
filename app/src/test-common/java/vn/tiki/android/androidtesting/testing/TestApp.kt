package vn.tiki.android.androidtesting.testing

import vn.tiki.android.androidtesting.MainApplication
import vn.tiki.android.androidtesting.data.AppExecutors
import vn.tiki.android.androidtesting.data.local.LocalStorage
import vn.tiki.android.androidtesting.data.remote.ApiServices
import vn.tiki.android.androidtesting.di.ObjectGraph
import vn.tiki.android.androidtesting.di.add

class TestApp : MainApplication() {

  override fun initDependencies(objectGraph: ObjectGraph) {
    super.initDependencies(objectGraph)

    val mockedApiServices = mock<ApiServices>()
    val mockedLocalStorage = mock<LocalStorage>()

    objectGraph.add {
      mockedApiServices
    }
    objectGraph.add {
      mockedLocalStorage
    }
    objectGraph.add {
      InstantAppExecutors() as AppExecutors
    }
  }
}

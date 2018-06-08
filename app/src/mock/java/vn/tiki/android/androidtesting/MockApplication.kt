package vn.tiki.android.androidtesting

import vn.tiki.android.androidtesting.data.AppExecutors
import vn.tiki.android.androidtesting.data.remote.ApiServices
import vn.tiki.android.androidtesting.data.remote.MockApiServices
import vn.tiki.android.androidtesting.di.ObjectGraph
import vn.tiki.android.androidtesting.di.add
import vn.tiki.android.androidtesting.di.get

class MockApplication : MainApplication() {

  override fun initDependencies(objectGraph: ObjectGraph) {
    super.initDependencies(objectGraph)
    objectGraph.add {
      val appExecutors = objectGraph.get<AppExecutors>()
      MockApiServices(appExecutors) as ApiServices
    }
  }
}

package vn.tiki.android.androidtesting.data.remote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.SystemClock
import retrofit2.Response
import vn.tiki.android.androidtesting.data.AppExecutors

class MockApiServices(private val appExecutors: AppExecutors) : ApiServices {

  override fun getUser(username: String, accessToken: String) {

  }

  override fun login(username: String, password: String): LiveData<ApiResponse<String>> {
    val result = MutableLiveData<ApiResponse<String>>()
    appExecutors.networkIO()
        .execute {
          // Mock request time
          SystemClock.sleep(300)
          if ("foo@gmail.com" == username && "bar" == password) {
            result.postValue(ApiResponse.create(Response.success("mock-access-token")))
          } else {
            result.postValue(ApiResponse.create(RuntimeException("email and password are not matched")))
          }
        }
    return result
  }
}

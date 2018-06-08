package vn.tiki.android.androidtesting.data.remote

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.os.SystemClock
import retrofit2.Response
import vn.tiki.android.androidtesting.data.AppExecutors
import vn.tiki.android.androidtesting.data.model.User

class MockApiServices(private val appExecutors: AppExecutors) : ApiServices {
  companion object {
    const val MOCK_TOKEN = "mock-access-token"
  }

  override fun getUser(accessToken: String?): LiveData<ApiResponse<User>> {
    return mockResponse {
      if (MOCK_TOKEN == accessToken) {
        User(
          "foo",
          "foo@gmail.com",
          "started learning to code because of passion. Then I wrote code for a living. Now I'm writing code to create value for people. Later on, I will teach coding",
          "https://avatars0.githubusercontent.com/u/1658142?v=4"
        )
      } else {
        throw RuntimeException("You are unauthorized")
      }
    }
  }

  override fun login(username: String, password: String): LiveData<ApiResponse<String>> {
    return mockResponse {
      if ("foo@gmail.com" == username && "bar" == password) {
        MOCK_TOKEN
      } else {
        throw RuntimeException("Email and password are not matched")
      }
    }
  }

  private fun <T> mockResponse(func: () -> T): LiveData<ApiResponse<T>> {
    val result = MutableLiveData<ApiResponse<T>>()
    appExecutors.networkIO()
      .execute {
        // Mock request time
        SystemClock.sleep(200)

        val response = try {
          ApiResponse.create(Response.success(func()))
        } catch (e: Exception) {
          ApiResponse.create<T>(e)
        }
        result.postValue(response)
      }
    return result
  }
}

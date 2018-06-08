package vn.tiki.android.androidtesting.data.remote

import android.arch.lifecycle.LiveData
import vn.tiki.android.androidtesting.data.model.User

interface ApiServices {

  fun login(username: String, password: String): LiveData<ApiResponse<String>>

  fun getUser(accessToken: String?): LiveData<ApiResponse<User>>
}

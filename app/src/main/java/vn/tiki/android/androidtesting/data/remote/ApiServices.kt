package vn.tiki.android.androidtesting.data.remote

import android.arch.lifecycle.LiveData

interface ApiServices {

  fun login(username: String, password: String): LiveData<ApiResponse<String>>

  fun getUser(username: String, accessToken: String)
}

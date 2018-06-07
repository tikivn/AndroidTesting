package vn.tiki.android.androidtesting.data.repository

import android.arch.lifecycle.LiveData
import androidx.lifecycle.LiveDataX
import vn.tiki.android.androidtesting.data.model.Resource
import vn.tiki.android.androidtesting.data.model.User

class UserRepository() {
  fun login(username: String, password: String): LiveData<Resource<User>> {
    return LiveDataX.just(null)
  }
}
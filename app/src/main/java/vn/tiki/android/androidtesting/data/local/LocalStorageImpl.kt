package vn.tiki.android.androidtesting.data.local

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.SharedPreferences
import androidx.core.content.edit
import vn.tiki.android.androidtesting.data.AppExecutors

class LocalStorageImpl(
  private val appExecutors: AppExecutors,
  private val sharedPreferences: SharedPreferences
) : LocalStorage {

  companion object {
    const val KEY_ACCESS_TOKEN = "access_token"
  }

  override fun setAccessToken(accessToken: String) {
    sharedPreferences.edit {
      putString(KEY_ACCESS_TOKEN, accessToken)
    }
  }

  override fun getAccessToken(): LiveData<String> {
    val result = MutableLiveData<String>()
    appExecutors.diskIO().execute {
      val accessToken = sharedPreferences.getString(KEY_ACCESS_TOKEN, null)
      result.postValue(accessToken)
    }
    return result
  }
}

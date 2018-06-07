package vn.tiki.android.androidtesting.data.local

import android.arch.lifecycle.LiveData
import android.support.annotation.WorkerThread

interface LocalStorage {
  @WorkerThread
  fun setAccessToken(accessToken: String)

  fun getAccessToken(): LiveData<String>
}
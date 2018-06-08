package vn.tiki.android.androidtesting

import android.arch.lifecycle.ViewModel
import vn.tiki.android.androidtesting.data.repository.UserRepository

class MainViewModel(userRepository: UserRepository) : ViewModel() {
  val accessToken = userRepository.getAccessToken()
}

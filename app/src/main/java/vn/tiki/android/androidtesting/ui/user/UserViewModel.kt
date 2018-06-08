package vn.tiki.android.androidtesting.ui.user

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import vn.tiki.android.androidtesting.data.model.Resource
import vn.tiki.android.androidtesting.data.model.User
import vn.tiki.android.androidtesting.data.repository.UserRepository

class UserViewModel(userRepository: UserRepository) : ViewModel() {
  val user: LiveData<Resource<User>> = userRepository.getUser()
}

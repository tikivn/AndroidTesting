package vn.tiki.android.androidtesting.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context
import androidx.lifecycle.map
import androidx.lifecycle.switchMap
import vn.tiki.android.androidtesting.R
import vn.tiki.android.androidtesting.data.model.Resource
import vn.tiki.android.androidtesting.data.model.Status.ERROR
import vn.tiki.android.androidtesting.data.model.Status.LOADING
import vn.tiki.android.androidtesting.data.model.Status.SUCCESS
import vn.tiki.android.androidtesting.data.repository.UserRepository
import vn.tiki.android.androidtesting.util.isEmailAddress

class LoginViewModel(
    context: Context,
    userRepository: UserRepository
) : ViewModel() {

  private val messageInvalidEmail by lazy { context.getString(R.string.email_is_invalid) }
  private val _username = MutableLiveData<String>()
  private val _password = MutableLiveData<String>()
  private val _submit = MutableLiveData<Any>()
  private val _result: LiveData<Resource<String>> = _submit.switchMap {
    userRepository.login(_username.value!!, _password.value!!)
  }

  val usernameError: LiveData<String> = _username.map { username ->
    if (isEmailAddress(username)) {
      null
    } else {
      messageInvalidEmail
    }
  }

  val submitButtonEnabled: LiveData<Boolean> = usernameError.map { it == null }

  val loginError: LiveData<String> = _result.map {
    if (it?.status == ERROR) {
      it.message
    } else {
      null
    }
  }

  val submitting: LiveData<Boolean> = _result.map { it?.status == LOADING }

  val loginSucceed: LiveData<Boolean> = _result.map { it?.status == SUCCESS }

  internal fun setUsername(username: String) {
    _username.value = username
  }

  internal fun setPassword(password: String) {
    _password.value = password
  }

  internal fun submit() {
    _submit.value = null
  }
}

package vn.tiki.android.androidtesting.ui.login

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import android.content.Context

class LoginViewModel(context: Context) : ViewModel() {

  private val _invalidEmailError = MutableLiveData<String>()
  val invalidEmailError: LiveData<String>
    get() = _invalidEmailError

  fun setEmail(email: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun setPassword(password: String) {
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

  fun submit() {
    // verify email
    // show loading
    // onSuccess => notify success
    // onError => show error
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }

}

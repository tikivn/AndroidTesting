package vn.tiki.android.androidtesting.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doAfterTextChanged
import androidx.lifecycle.filter
import androidx.lifecycle.nonNull
import androidx.lifecycle.observe
import kotlinx.android.synthetic.main.login_fragment.*
import vn.tiki.android.androidtesting.R.layout
import vn.tiki.android.androidtesting.di.inject

class LoginFragment : Fragment() {

  interface OnLoginSuccessListener {
    fun onLoginSuccess()
  }

  companion object {
    fun newInstance() = LoginFragment()
  }

  private val viewModelFactory: ViewModelProvider.Factory by inject()

  private lateinit var viewModel: LoginViewModel
  private var listener: OnLoginSuccessListener? = null

  override fun onAttach(context: Context?) {
    super.onAttach(context)
    if (context is OnLoginSuccessListener) {
      listener = context
    }
  }

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return inflater.inflate(layout.login_fragment, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(LoginViewModel::class.java)

    bindInputs()

    bindOutputs()
  }

  private fun bindInputs() {
    etUsername.doAfterTextChanged { viewModel.setUsername(it.toString()) }
    etPassword.doAfterTextChanged { viewModel.setPassword(it.toString()) }
    btSubmit.setOnClickListener { viewModel.submit() }
  }

  private fun bindOutputs() {
    viewModel.usernameError
      .observe(this, Observer { tilUsername.error = it })

    viewModel.submitButtonEnabled
      .nonNull()
      .observe(this, { isEnabled -> btSubmit.isEnabled = isEnabled })

    viewModel.loginError
      .observe(this, Observer { error ->
        tilPassword.error = error
      })

    viewModel.submitting
      .nonNull()
      .observe(this, { submitting ->
        if (submitting) {
          etUsername.isEnabled = false
          etPassword.isEnabled = false
          btSubmit.isProgressEnabled = true
        } else {
          etUsername.isEnabled = true
          etPassword.isEnabled = true
          btSubmit.isProgressEnabled = false
          btSubmit.isEnabled = viewModel.submitButtonEnabled.value ?: false
        }
      })

    viewModel.loginSucceed
      .filter { it ?: false }
      .nonNull()
      .observe(this) {
        listener?.onLoginSuccess()
      }
  }
}

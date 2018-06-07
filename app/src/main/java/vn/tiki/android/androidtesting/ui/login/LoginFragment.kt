package vn.tiki.android.androidtesting.ui.login

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.design.widget.TextInputLayout
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.view.doAfterTextChanged
import vn.tiki.android.androidtesting.R

class LoginFragment : Fragment() {

  companion object {
    fun newInstance() = LoginFragment()
  }

  private lateinit var viewModel: LoginViewModel
  private lateinit var tilEmail: TextInputLayout
  private lateinit var etEmail: EditText
  private lateinit var tilPassword: TextInputLayout
  private lateinit var etPassword: EditText
  private lateinit var btLogin: Button

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    val view = inflater.inflate(R.layout.login_fragment, container, false)
    tilEmail = view.findViewById(R.id.tilEmail)
    etEmail = view.findViewById(R.id.etEmail)
    tilPassword = view.findViewById(R.id.tilPassword)
    etPassword = view.findViewById(R.id.etPassword)
    btLogin = view.findViewById(R.id.btLogin)
    return view
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

    bindInputs()

    bindOutputs()
  }

  private fun bindInputs() {
    etEmail.doAfterTextChanged { viewModel.setEmail(it.toString()) }
    etPassword.doAfterTextChanged { viewModel.setPassword(it.toString()) }
    btLogin.setOnClickListener { viewModel.submit() }
  }

  private fun bindOutputs() {
    viewModel.invalidEmailError
        .observe(this, Observer { tilEmail.error = it })
    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
  }
}

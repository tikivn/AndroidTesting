package vn.tiki.android.androidtesting.ui.user

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.nonNull
import androidx.lifecycle.observe
import vn.tiki.android.androidtesting.R
import vn.tiki.android.androidtesting.data.model.Status.ERROR
import vn.tiki.android.androidtesting.data.model.Status.LOADING
import vn.tiki.android.androidtesting.data.model.Status.SUCCESS
import vn.tiki.android.androidtesting.di.inject

class UserFragment : Fragment() {

  companion object {
    fun newInstance() = UserFragment()
  }

  private val viewModelFactory: ViewModelProvider.Factory by inject()

  private lateinit var viewModel: UserViewModel

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
    return inflater.inflate(R.layout.fragment_user, container, false)
  }

  override fun onActivityCreated(savedInstanceState: Bundle?) {
    super.onActivityCreated(savedInstanceState)
    viewModel = ViewModelProviders.of(this, viewModelFactory).get(UserViewModel::class.java)

    bindOutputs()
  }

  private fun bindOutputs() {
    viewModel.user
      .nonNull()
      .observe(this) { resources ->
        when (resources.status) {
          LOADING -> TODO("show loading")
          ERROR -> TODO("show error")
          SUCCESS -> {
            resources.data?.let { user ->
              TODO("bind user data")
            }
          }
        }
      }
  }
}

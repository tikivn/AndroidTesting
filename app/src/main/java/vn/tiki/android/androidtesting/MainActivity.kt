package vn.tiki.android.androidtesting

import android.arch.lifecycle.ViewModelProvider
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import androidx.lifecycle.map
import androidx.lifecycle.nonNull
import androidx.lifecycle.observe
import vn.tiki.android.androidtesting.R.id
import vn.tiki.android.androidtesting.di.inject
import vn.tiki.android.androidtesting.ui.login.LoginFragment
import vn.tiki.android.androidtesting.ui.user.UserFragment

class MainActivity : AppCompatActivity() {

  private val viewModelFactory: ViewModelProvider.Factory by inject()

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    val viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)

    if (savedInstanceState == null) {
      viewModel.accessToken
        .map { token ->
          if (token == null) {
            LoginFragment.newInstance()
          } else {
            UserFragment.newInstance()
          }
        }
        .nonNull()
        .observe(this) { fragment ->
          showFragment(fragment)
        }
    }
  }

  private fun showFragment(fragment: Fragment) {
    supportFragmentManager.beginTransaction()
      .replace(id.container, fragment)
      .commitNow()
  }
}

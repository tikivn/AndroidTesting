package vn.tiki.android.androidtesting

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import vn.tiki.android.androidtesting.ui.login.LoginFragment

class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.main_activity)
    if (savedInstanceState == null) {
      supportFragmentManager.beginTransaction()
          .replace(R.id.container, LoginFragment.newInstance())
          .commitNow()
    }
  }
}

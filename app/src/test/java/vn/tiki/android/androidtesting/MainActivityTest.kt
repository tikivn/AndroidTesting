package vn.tiki.android.androidtesting

import com.google.common.truth.Truth.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.Robolectric
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config
import vn.tiki.android.androidtesting.ui.login.LoginFragment

@RunWith(RobolectricTestRunner::class)
@Config(constants = BuildConfig::class)
class MainActivityTest {

  @Test
  fun showLoginFragment() {
    val activity = Robolectric.setupActivity(MainActivity::class.java)

    val showingFragment = activity.supportFragmentManager.findFragmentById(R.id.container)

    assertThat(showingFragment).isInstanceOf(LoginFragment::class.java)
  }
}

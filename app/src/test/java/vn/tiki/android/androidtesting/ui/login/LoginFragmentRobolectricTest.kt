package vn.tiki.android.androidtesting.ui.login

import android.support.design.widget.TextInputLayout
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.LiveDataX
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito
import org.mockito.Mockito.verify
import org.robolectric.RobolectricTestRunner
import org.robolectric.RuntimeEnvironment
import org.robolectric.annotation.Config
import org.robolectric.shadows.support.v4.SupportFragmentTestUtil
import vn.tiki.android.androidtesting.R
import vn.tiki.android.androidtesting.data.remote.ApiResponse
import vn.tiki.android.androidtesting.data.remote.ApiServices
import vn.tiki.android.androidtesting.di.get
import vn.tiki.android.androidtesting.testing.TestApp

@RunWith(RobolectricTestRunner::class)
@Config(application = TestApp::class)
class LoginFragmentRobolectricTest {

  private lateinit var tested: LoginFragment
  private lateinit var etUsername: EditText
  private lateinit var tilUsername: TextInputLayout
  private lateinit var tilPassword: TextInputLayout
  private lateinit var btSubmit: Button
  private lateinit var mockedApiServices: ApiServices

  @Before
  fun setUp() {
    mockedApiServices = (RuntimeEnvironment.application as TestApp).objectGraph.get()

    tested = LoginFragment.newInstance()
    SupportFragmentTestUtil.startFragment(tested)

    val view = tested.view!!
    etUsername = view.findViewById(R.id.etUsername)
    tilUsername = view.findViewById(R.id.tilUsername)
    tilPassword = view.findViewById(R.id.tilPassword)
    btSubmit = view.findViewById(R.id.btSubmit)
  }

  @Test
  fun invalidEmail() {
    etUsername.setText("giang")

    assertThat(tilUsername.error).isEqualTo(tested.getString(R.string.email_is_invalid))

    assertThat(btSubmit.isEnabled).isFalse()
  }

  @Test
  fun validEmail() {
    etUsername.setText("giang@tiki.vn")

    assertThat(tilUsername.error).isNull()

    assertThat(btSubmit.isEnabled).isTrue()
  }

  @Test
  fun loginError() {
    Mockito.`when`(mockedApiServices.login(anyString(), anyString()))
      .thenReturn(LiveDataX.just(ApiResponse.create(RuntimeException("Email and password are not matched"))))

    etUsername.setText("giang@tiki.vn")

    btSubmit.performClick()
    verify(mockedApiServices).login("giang@tiki.vn", "")

    assertThat(tilPassword.error).isEqualTo("Email and password are not matched")
  }
}

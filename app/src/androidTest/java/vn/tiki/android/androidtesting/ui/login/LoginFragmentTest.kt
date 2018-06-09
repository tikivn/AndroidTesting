package vn.tiki.android.androidtesting.ui.login

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import androidx.lifecycle.LiveDataX
import org.hamcrest.Matchers
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import vn.tiki.android.androidtesting.R
import vn.tiki.android.androidtesting.data.remote.ApiResponse
import vn.tiki.android.androidtesting.data.remote.ApiServices
import vn.tiki.android.androidtesting.di.get
import vn.tiki.android.androidtesting.di.objectGraph
import vn.tiki.android.androidtesting.hasError
import vn.tiki.android.androidtesting.testing.SingleFragmentActivity

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {

  @Rule
  @JvmField
  val activityTestRule = ActivityTestRule(SingleFragmentActivity::class.java)

  private lateinit var mockedApiServices: ApiServices

  @Before
  fun setUp() {
    activityTestRule.activity.setFragment(LoginFragment.newInstance())
    mockedApiServices = activityTestRule.activity.objectGraph().get()
  }

  @Test
  fun invalidEmail() {
    onView(withId(R.id.etUsername))
        .perform(replaceText("giang"), closeSoftKeyboard())

    onView(withId(R.id.tilUsername))
      .check(matches(hasError(activityTestRule.activity.getString(R.string.email_is_invalid))))

    onView(withId(R.id.btSubmit))
        .check(matches(Matchers.not(isEnabled())))
  }

  @Test
  fun validEmail() {
    onView(withId(R.id.etUsername))
        .perform(replaceText("giang@tiki.vn"), closeSoftKeyboard())

    onView(withId(R.id.tilUsername))
        .check(matches(hasError(null)))

    onView(withId(R.id.btSubmit))
        .check(matches(isEnabled()))
  }

  @Test
  fun loginError() {
    `when`(mockedApiServices.login(anyString(), anyString()))
        .thenReturn(LiveDataX.just(ApiResponse.create(RuntimeException("Email and password are not matched"))))
    onView(withId(R.id.etUsername))
        .perform(replaceText("foo@tiki.vn"))
    onView(withId(R.id.etPassword))
        .perform(replaceText("bar"))
    onView(withId(R.id.btSubmit))
        .perform(click())

    onView(withId(R.id.tilPassword))
        .check(matches(hasError("Email and password are not matched")))
  }
}

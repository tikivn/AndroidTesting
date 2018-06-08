package vn.tiki.android.androidtesting

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.closeSoftKeyboard
import android.support.test.espresso.action.ViewActions.replaceText
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isEnabled
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.filters.LargeTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import org.hamcrest.Matchers.not
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import vn.tiki.android.androidtesting.testing.SingleFragmentActivity
import vn.tiki.android.androidtesting.ui.login.LoginFragment

@LargeTest
@RunWith(AndroidJUnit4::class)
class LoginTest {

  @Rule
  @JvmField
  var activityTestRule = ActivityTestRule(SingleFragmentActivity::class.java)

  @Before
  fun setUp() {
    activityTestRule.activity.setFragment(LoginFragment.newInstance())
  }

  @Test
  fun invalidEmail() {

    onView(withId(R.id.etUsername))
        .perform(replaceText("giang"), closeSoftKeyboard())

    onView(withId(R.id.tilUsername))
        .check(matches(hasErrorText(activityTestRule.activity.getString(R.string.email_is_invalid))))

    onView(withId(R.id.btSubmit))
        .check(matches(not(isEnabled())))
  }

  @Test
  fun validEmail() {
    onView(withId(R.id.etUsername))
        .perform(replaceText("giang@tiki.vn"), closeSoftKeyboard())

    onView(withId(R.id.tilUsername))
        .check(matches(hasErrorText(null)))

    onView(withId(R.id.btSubmit))
        .check(matches(isEnabled()))
  }
}

package vn.tiki.android.androidtesting

import android.support.annotation.VisibleForTesting
import android.support.design.widget.TextInputLayout
import android.support.test.espresso.matcher.BoundedMatcher
import android.support.test.espresso.remote.annotation.RemoteMsgConstructor
import android.support.test.espresso.remote.annotation.RemoteMsgField
import android.view.View
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers

@VisibleForTesting
internal class HasErrorTextMatcher @RemoteMsgConstructor internal constructor(
  @field:RemoteMsgField(order = 0) private val stringMatcher: Matcher<String>
) : BoundedMatcher<View, TextInputLayout>(TextInputLayout::class.java) {

  override fun describeTo(description: Description) {
    description.appendText("with error: ")
    stringMatcher.describeTo(description)
  }

  override fun matchesSafely(view: TextInputLayout): Boolean {
    return stringMatcher.matches(view.error)
  }
}


/**
 * Returns a matcher that matches [TextInputLayout] based on edit text error string value.
 *
 *
 * **Note:** View's error property can be `null`, to match against it use `
 * hasErrorText(nullValue(String.class)`
 */
fun hasErrorText(stringMatcher: Matcher<String>): Matcher<View> {
  return HasErrorTextMatcher(checkNotNull(stringMatcher))
}

/**
 * Returns a matcher that matches [TextInputLayout] based on edit text error string value.
 *
 *
 * **Note:** View's error property can be `null`, to match against it use `
 * hasErrorText(nullValue(String.class)`
 */
fun hasErrorText(text: String?): Matcher<View> {
  return HasErrorTextMatcher(Matchers.`is`(text))
}

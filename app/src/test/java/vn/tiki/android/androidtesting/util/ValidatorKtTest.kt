package vn.tiki.android.androidtesting.util

import com.google.common.truth.Truth.assertThat
import org.junit.Test

class ValidatorKtTest {

  @Test
  fun emailAddress() {
    assertThat(isEmailAddress("foo@tiki.vn")).isTrue()
  }

  @Test
  fun notEmailAddress() {
    assertThat(isEmailAddress("foo@tiki")).isFalse()
  }
}

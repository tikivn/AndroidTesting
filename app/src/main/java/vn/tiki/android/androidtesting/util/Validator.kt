package vn.tiki.android.androidtesting.util

import android.util.Patterns.EMAIL_ADDRESS

/**
 * Check if text is email address
 * @return true if text is email address or false otherwise.
 */
fun isEmailAddress(text: String?): Boolean {
  return EMAIL_ADDRESS.matcher(text).matches()
}

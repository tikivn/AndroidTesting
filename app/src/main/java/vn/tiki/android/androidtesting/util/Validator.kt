package vn.tiki.android.androidtesting.util

import java.util.regex.Pattern

val EMAIL_ADDRESS: Pattern = Pattern.compile(
  "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
    "\\@" +
    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
    "(" +
    "\\." +
    "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
    ")+"
)

/**
 * Check if text is email address
 * @return true if text is email address or false otherwise.
 */
fun isEmailAddress(text: String?): Boolean {
  return EMAIL_ADDRESS.matcher(text).matches()
}

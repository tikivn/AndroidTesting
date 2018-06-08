package vn.tiki.android.androidtesting.data.repository

import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.arch.lifecycle.Observer
import androidx.lifecycle.LiveDataX.just
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import retrofit2.Response
import vn.tiki.android.androidtesting.data.local.LocalStorage
import vn.tiki.android.androidtesting.data.remote.ApiResponse
import vn.tiki.android.androidtesting.data.remote.ApiServices
import vn.tiki.android.androidtesting.testing.InstantAppExecutors
import vn.tiki.android.androidtesting.testing.mock

@RunWith(JUnit4::class)
class UserRepositoryTest {

  private val mockedApiServices = mock(ApiServices::class.java)
  private val mockedLocalStorage = mock(LocalStorage::class.java)
  private val tested = UserRepository(InstantAppExecutors(), mockedApiServices, mockedLocalStorage)

  @Rule
  @JvmField
  val instantExecutorRule = InstantTaskExecutorRule()

  @Test
  fun `get access-token from LocalStorage`() {
    tested.getAccessToken()
    verify(mockedLocalStorage).getAccessToken()

    // given
    val token = "access-token"
    `when`(mockedLocalStorage.getAccessToken()).thenReturn(just(token))

    // when
    val observer = mock<Observer<String>>()
    tested.getAccessToken().observeForever(observer)

    // then
    verify(observer).onChanged(token)
  }

  @Test
  fun `login success then save token to LocalStorage`() {
    val token = "token"
    `when`(mockedApiServices.login(anyString(), anyString()))
        .thenReturn(just(ApiResponse.create(Response.success(token))))

    tested.login("foo@tiki.vn", "bar")
        .observeForever(mock())

    verify(mockedLocalStorage).setAccessToken(token)
  }
}

import android.app.Application
import android.arch.core.executor.testing.InstantTaskExecutorRule
import android.util.Log
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.mobiledevandroide.data.model.LoginResponseModel
import com.example.mobiledevandroide.network.ApiService
import com.example.mobiledevandroide.store.JwtManager
import com.example.mobiledevandroide.store.SharedPreferencesManager
import com.example.mobiledevandroide.viewModels.LoginResult
import com.example.mobiledevandroide.viewModels.LoginViewModel
import io.mockk.every
import io.mockk.mockkStatic
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.*
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.junit.MockitoJUnit
import org.mockito.junit.MockitoRule
import retrofit2.Response


@ExperimentalCoroutinesApi

@RunWith(AndroidJUnit4::class)
class LoginViewModelTest {

    private val testDispatcher = UnconfinedTestDispatcher()

    @JvmField
    @Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mockitoRule: MockitoRule = MockitoJUnit.rule()

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var apiService: ApiService

    @Mock
    private lateinit var jwtManager: JwtManager

    @Mock
    private lateinit var sharedPreferencesManager: SharedPreferencesManager

    private lateinit var loginViewModel: LoginViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        mockkStatic(Log::class)
        every { Log.v(any(), any()) } returns 0
        every { Log.d(any(), any()) } returns 0
        every { Log.i(any(), any()) } returns 0
        every { Log.e(any(), any()) } returns 0
        jwtManager = mock(JwtManager::class.java)
        sharedPreferencesManager = mock(SharedPreferencesManager::class.java)

        loginViewModel = LoginViewModel(mock(Application::class.java), apiService, jwtManager, sharedPreferencesManager)
    }



    @Test
    fun `loginSuccess should emit success result`() = runTest {
        val response = Response.success(LoginResponseModel("fakeToken", "tk"))
        `when`(apiService.login(anyString(), anyString())).thenReturn(response)

        loginViewModel.login("username", "password") { /* empty callback */ }

        val result = loginViewModel.loginResult.value
        assert(result is LoginResult.Success)
        assert((result as LoginResult.Success).message == "Success")
    }

    @Test
    fun `loginFailure should emit error result`() = runTest {
        val response = Response.error<LoginResponseModel>(400, mock())
        `when`(apiService.login(anyString(), anyString())).thenReturn(response)

        loginViewModel.login("username", "password") { /* empty callback */ }

        val result = loginViewModel.loginResult.value
        assert(result is LoginResult.Error)
        assert((result as LoginResult.Error).message == "Error")
    }

}

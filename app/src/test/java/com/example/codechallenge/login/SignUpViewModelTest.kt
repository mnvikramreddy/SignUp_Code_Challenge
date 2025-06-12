package com.example.codechallenge.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.codechallenge.LiveDataUtil
import com.example.codechallenge.model.UserDetails
import com.example.codechallenge.util.Event
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class SignUpViewModelTest {

    private lateinit var signUpViewModel: SignUpViewModel

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setup() {
        signUpViewModel = SignUpViewModel()
    }

    @Test
    fun when_name_is_entered_clear_error() {
        signUpViewModel.onFirstNameChanged("FirstName")
        assert(!LiveDataUtil.getValue(signUpViewModel.firstNameError))

    }

    @Test
    fun when_email_is_entered_clear_error() {
        signUpViewModel.onEmailChanged("Email@email.com")
        assert(!LiveDataUtil.getValue(signUpViewModel.emailError))

    }

    @Test
    fun when_password_is_entered_clear_error() {
        signUpViewModel.onPasswordChanged("Password1")
        assert(!LiveDataUtil.getValue(signUpViewModel.passwordError))

    }

    @Test
    fun when_website_is_entered_clear_error() {
        signUpViewModel.onWebsiteChanged("website.com")
        assert(!LiveDataUtil.getValue(signUpViewModel.websiteError))
    }

    @Test
    fun when_cardView_Click_checkCamera_permission_livedata_check() {
        signUpViewModel.onCardViewClick()
        val value: Event<Boolean> = LiveDataUtil.getValue(signUpViewModel.checkCameraPermission)
        assert(value.getContentIfNotHandled() == true)
    }

    @Test
    fun `when submit button clicked with empty fields make sure validate and set error`() {
        signUpViewModel.onSubmit()
        assert(LiveDataUtil.getValue(signUpViewModel.firstNameError))
        assert(LiveDataUtil.getValue(signUpViewModel.emailError))
        assert(LiveDataUtil.getValue(signUpViewModel.passwordError))
        assert(LiveDataUtil.getValue(signUpViewModel.websiteError))

        assert(LiveDataUtil.getValue(signUpViewModel.navigateToConfirmation) != userDetails)
    }

    @Test
    fun when_user_enter_all_fields_correctly_and_clicked_submit_success() {
        signUpViewModel.run {
            onFirstNameChanged("FirstName")
            onEmailChanged("Email@email.com")
            onPasswordChanged("Password1")
            onWebsiteChanged("website.com")
            onSubmit()

            assert(!LiveDataUtil.getValue(firstNameError))
            assert(!LiveDataUtil.getValue(emailError))
            assert(!LiveDataUtil.getValue(passwordError))
            assert(!LiveDataUtil.getValue(websiteError))

            assert(LiveDataUtil.getValue(signUpViewModel.navigateToConfirmation) == userDetails)
        }
    }

    @Test
    fun when_user_enter_all_fields_but_not_according_validation_and_clicked_submit_success_gives_error() {
        signUpViewModel.run {
            onFirstNameChanged("name")
            onEmailChanged("Email@email")
            onPasswordChanged("Password")
            onWebsiteChanged("website")
            onSubmit()

            assert(!LiveDataUtil.getValue(firstNameError))
            assert(LiveDataUtil.getValue(emailError))
            assert(LiveDataUtil.getValue(passwordError))
            assert(LiveDataUtil.getValue(websiteError))

            assert(LiveDataUtil.getValue(signUpViewModel.navigateToConfirmation) != userDetails)
        }
    }

    companion object {
        private val userDetails = UserDetails(
            firstName = "FirstName",
            email = "Email@email.com",
            password = "Password1",
            website = "website.com"
        )
    }
}
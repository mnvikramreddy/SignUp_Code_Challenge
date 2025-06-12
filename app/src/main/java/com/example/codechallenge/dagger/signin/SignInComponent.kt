package com.example.codechallenge.dagger.signin

import com.example.codechallenge.MainActivity
import com.example.codechallenge.login.ConfirmationFragment
import com.example.codechallenge.login.SignUpFragment
import dagger.Subcomponent

@Subcomponent
interface SignInComponent {

    @Subcomponent.Factory
    interface Factory{
        fun create(): SignInComponent
    }

    fun inject(mainActivity: MainActivity)
    fun inject(signUpFragment: SignUpFragment)
    fun inject(confirmationFragment: ConfirmationFragment)
}
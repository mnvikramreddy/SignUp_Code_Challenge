package com.example.codechallenge.login

import android.graphics.Bitmap
import androidx.core.util.PatternsCompat
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.model.UserDetails
import com.example.codechallenge.util.Event
import java.util.regex.Pattern
import javax.inject.Inject

class SignUpViewModel @Inject constructor() : ViewModel() {

    private val _avatarBitmap = MutableLiveData<Bitmap>()
    val avatarBitmap: LiveData<Bitmap> = _avatarBitmap

    private val _avatarText = MutableLiveData<Boolean>()
    val avatarText: LiveData<Boolean> = _avatarText

    private val _firstNameError = MutableLiveData<Boolean>()
    val firstNameError: LiveData<Boolean> = _firstNameError

    private val _emailError = MutableLiveData<Boolean>()
    val emailError: LiveData<Boolean> = _emailError

    private val _passwordError = MutableLiveData<Boolean>()
    val passwordError: LiveData<Boolean> = _passwordError

    private val _websiteError = MutableLiveData<Boolean>()
    val websiteError: LiveData<Boolean> = _websiteError

    private val _navigateToConfirmation = MutableLiveData<UserDetails>()
    val navigateToConfirmation: LiveData<UserDetails> = _navigateToConfirmation

    private val _checkCameraPermission = MutableLiveData<Event<Boolean>>()
    val checkCameraPermission: LiveData<Event<Boolean>> = _checkCameraPermission

    private val userDetailsObject by lazy { UserDetails() }


    fun onFirstNameChanged(name: String) {
        _firstNameError.value = false
        userDetailsObject.firstName = name
    }

    fun onEmailChanged(email: String) {
        _emailError.value = false
        userDetailsObject.email = email
    }

    fun onPasswordChanged(password: String) {
        _passwordError.value = false
        userDetailsObject.password = password
    }

    fun onWebsiteChanged(website: String) {
        _websiteError.value = false
        userDetailsObject.website = website
    }

    fun setAvatar(avatar: Bitmap) {
        _avatarBitmap.value = avatar
        _avatarText.value = true
        userDetailsObject.profile = avatar
    }

    fun onSubmit() {
        userDetailsObject.run {
            if (firstName.isBlank()) _firstNameError.value = true
            if (email.isBlank() || !PatternsCompat.EMAIL_ADDRESS.matcher(email)
                    .matches()
            ) _emailError.value = true
            if (password.isBlank() || !isValidPassword(password)) _passwordError.value = true
            if (website.isBlank() || !PatternsCompat.WEB_URL.matcher(website)
                    .matches()
            ) _websiteError.value = true
        }
        if (_firstNameError.value == false && _emailError.value == false && _passwordError.value == false && _websiteError.value == false)
            _navigateToConfirmation.value = userDetailsObject
    }

    private fun isValidPassword(password: String): Boolean {
        if (password.isBlank()) {
            return false
        }
        val regex = ("^(?=.*[0-9])"
                + "(?=.*[a-z])(?=.*[A-Z])"
                + "(?=\\S+$).{8,20}$")

        return Pattern.compile(regex).matcher(password).matches()
    }

    fun onCardViewClick(){
        _checkCameraPermission.value = Event(true)
    }
}
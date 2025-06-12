package com.example.codechallenge

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.codechallenge.model.UserDetails
import javax.inject.Inject

class MainViewModel @Inject constructor():ViewModel() {

    private val _userDetails = MutableLiveData<UserDetails>()
    val userDetails: LiveData<UserDetails> = _userDetails

    fun setUserDetails(userDetails: UserDetails){
        _userDetails.value = userDetails
    }
}
package com.example.simpleuserprofilefetcher

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserViewModel : ViewModel() {

    private val userRepository = UserRepository()
    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun fetchUserProfile() {
        userRepository.getUserProfile().enqueue(object : Callback<User> {
            override fun onResponse(call: Call<User>, response: Response<User>) {
                if (response.isSuccessful) {
                    _user.value = response.body()
                } else {
                    _error.value = "Failed to load user data"
                }
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                _error.value = t.message
            }
        })
    }
}
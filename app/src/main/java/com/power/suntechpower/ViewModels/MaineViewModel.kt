package com.power.suntechpower.ViewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.power.suntechpower.Models.User
import com.power.suntechpower.Util.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MaineViewModel @Inject constructor(private val apiService: ApiService) : ViewModel() {

    private val _userList = MutableLiveData<List<User>>()
    val userList: LiveData<List<User>> get() = _userList
    val progressBarStatus = MutableLiveData<Boolean>()
    private val _error = MutableLiveData<String>()
    val error: LiveData<String> get() = _error

    fun loginuser(token:String , postid : String, driverid : String) {

        Log.d("logindata",token+"\n"+postid+"\n"+driverid)

        progressBarStatus.value = true
        viewModelScope.launch {
            val response = apiService.Loginuser("","","")
            if (response.isSuccessful) {
                Log.d("logindata",response.message())
                progressBarStatus.value = false
            } else {
                Log.d("logindata",response.message())
                progressBarStatus.value = false
            }
        }
    }

}

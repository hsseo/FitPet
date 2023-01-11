package com.example.kotlinproject.app_base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

abstract class BaseViewModel : ViewModel() {

    private val showErrorMessageLiveData: MutableLiveData<String> = MutableLiveData()
    private val showLoadingLiveData: MutableLiveData<String> = MutableLiveData()
    private val hideLoadingLiveData: MutableLiveData<Void> = MutableLiveData()

    fun showErrorMessageLiveData() : MutableLiveData<String>{
        return showErrorMessageLiveData
    }
    fun showLoadingLiveData() : MutableLiveData<String>{
        return showLoadingLiveData
    }
    fun hideLoadingLiveData() : MutableLiveData<Void>{
        return hideLoadingLiveData
    }

    fun showLoading(msg : String = "") {
        showLoadingLiveData.postValue(null)
    }
    fun hideLoading() {
        hideLoadingLiveData.postValue(null)
    }
}
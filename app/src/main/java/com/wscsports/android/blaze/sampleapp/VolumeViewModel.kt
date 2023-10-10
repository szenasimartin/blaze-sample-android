package com.wscsports.android.blaze.sampleapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class VolumeViewModel : ViewModel() {

    val volume = MutableLiveData(false)

    fun onVolumeChanged() {
        volume.postValue(true)
    }

}
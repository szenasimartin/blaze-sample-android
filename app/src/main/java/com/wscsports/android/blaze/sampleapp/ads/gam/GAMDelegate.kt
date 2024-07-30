package com.wscsports.android.blaze.sampleapp.ads.gam

import android.util.Log
import com.blaze.blazesdk.ads.custom_native.BlazeGoogleCustomNativeAdsHandler
import com.blaze.gam.BlazeCustomAdData
import com.blaze.gam.BlazeGAMDelegate

object GAMDelegate : BlazeGAMDelegate {

    override val customGAMTargetingProperties: Map<String, String> = emptyMap()

    override fun onGAMAdError(errMsg: String) {
        Log.d("onGAMAdError", "Error on ad - $errMsg")
    }

    override fun onGAMAdEvent(
        eventType: BlazeGoogleCustomNativeAdsHandler.EventType,
        adData: BlazeCustomAdData
    ) {
        Log.d("onGAMAdEvent", "Received Ad event of type:  $eventType, for adData: $adData")
    }

}
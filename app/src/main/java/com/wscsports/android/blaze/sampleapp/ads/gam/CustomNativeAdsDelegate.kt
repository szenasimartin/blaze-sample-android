package com.wscsports.android.blaze.sampleapp.ads.gam

import android.util.Log
import com.blaze.blazesdk.ads.custom_native.BlazeGoogleCustomNativeAdsHandler
import com.blaze.gam.custom_native.BlazeCustomNativeAdData
import com.blaze.gam.custom_native.BlazeGAMCustomNativeAdsDelegate

object CustomNativeAdsDelegate : BlazeGAMCustomNativeAdsDelegate {

    override val customGAMTargetingProperties: Map<String, String> = emptyMap()

    override fun onGAMCustomNativeAdError(errMsg: String) {
        Log.d("CustomNativeAdsDelegate", "onGAMCustomNativeAdError: Error on ad - $errMsg")
    }

    override fun onGAMCustomNativeAdEvent(
        eventType: BlazeGoogleCustomNativeAdsHandler.EventType,
        adData: BlazeCustomNativeAdData
    ) {
        Log.d("CustomNativeAdsDelegate", "onGAMCustomNativeAdError: Received Ad event of type:  $eventType, for adData: $adData")
    }

}
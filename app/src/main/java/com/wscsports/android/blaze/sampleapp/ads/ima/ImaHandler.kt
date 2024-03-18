package com.wscsports.android.blaze.sampleapp.ads.ima

import android.util.Log
import com.blaze.blazesdk.features.ads.ima.BlazeIMAHandlerEventType
import com.blaze.blazesdk.features.ads.ima.BlazeImaHandler
import com.blaze.blazesdk.features.ads.ima.models.BlazeImaAdInfo
import com.google.ads.interactivemedia.v3.api.ImaSdkFactory
import com.google.ads.interactivemedia.v3.api.ImaSdkSettings


class ImaHandler : BlazeImaHandler {

    override fun onAdEvent(ad: BlazeImaAdInfo, eventType: BlazeIMAHandlerEventType) {
        Log.d("onAdEvent", "eventType - ${eventType.name}, ad - $ad")
    }

    override fun provideAdExtraParams(): Map<String, String> {
        // Optional implementation. You can use this to add consents macros (or any other query params) on top of the SDK's IMA requests.
        return emptyMap()
    }

    override fun provideCustomImaSdkSettings(): ImaSdkSettings? {
        // Optional implementation. You can use this to customize ImaSdkSettings.
        return ImaSdkFactory.getInstance().createImaSdkSettings().apply {

        }
    }

}
package com.wscsports.android.blaze.sampleapp.ads.ima

import android.util.Log
import com.blaze.blazesdk.features.ads.ima.BlazeIMAHandlerEventType
import com.blaze.blazesdk.features.ads.ima.BlazeImaHandler
import com.blaze.blazesdk.features.ads.ima.models.BlazeImaAdInfo

class ImaHandler : BlazeImaHandler {

    override fun onAdEvent(ad: BlazeImaAdInfo, eventType: BlazeIMAHandlerEventType) {
        Log.d("onAdEvent", "eventType - ${eventType.name}, ad - $ad")
    }

}
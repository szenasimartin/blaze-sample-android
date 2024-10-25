package com.wscsports.android.blaze.sampleapp.ads.gam

import android.util.Log
import com.blaze.blazesdk.ads.banners.BlazeGAMBannerHandlerEventType
import com.blaze.gam.banner.BlazeGAMBannerAdsAdData
import com.blaze.gam.banner.BlazeGAMBannerAdsDelegate

object BannerAdsDelegate: BlazeGAMBannerAdsDelegate {
    override fun onGAMBannerAdsAdError(errorMsg: String, adData: BlazeGAMBannerAdsAdData) {
        Log.e("BannerAdsDelegate", "onGAMBannerAdsAdError: $errorMsg")
    }

    override fun onGAMBannerAdsAdEvent(
        eventType: BlazeGAMBannerHandlerEventType,
        adData: BlazeGAMBannerAdsAdData
    ) {
        Log.d("BannerAdsDelegate", "onGAMBannerAdsAdEvent: eventType: $eventType, adData: $adData")
    }

}
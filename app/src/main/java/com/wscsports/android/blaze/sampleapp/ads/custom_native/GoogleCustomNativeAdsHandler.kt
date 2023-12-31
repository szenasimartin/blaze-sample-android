package com.wscsports.android.blaze.sampleapp.ads.custom_native

import android.content.Context
import android.util.Log
import com.blaze.blazesdk.features.ads.custom_native.BlazeGoogleCustomNativeAdsHandler
import com.blaze.blazesdk.features.ads.custom_native.models.BlazeAdRequestData
import com.blaze.blazesdk.features.ads.custom_native.models.BlazeGoogleCustomNativeAdModel

class GoogleCustomNativeAdsHandler(
    appContext: Context
) : BlazeGoogleCustomNativeAdsHandler {

    private val adsProvider = AdsProvider(appContext)

    override fun onAdEvent(eventType: BlazeGoogleCustomNativeAdsHandler.EventType, adModel: BlazeGoogleCustomNativeAdModel) {
        when (eventType) {
            BlazeGoogleCustomNativeAdsHandler.EventType.OPENED_AD -> {
                // Report the ad impression to the ad provider.
                adModel.reportAdImpression()
            }

            BlazeGoogleCustomNativeAdsHandler.EventType.CTA_CLICKED -> {
                // Report the ad click to the ad provider.
                adModel.reportCTAClicked()
            }

            else ->
                Log.d("AdsHandler", "Received Ad event of type:  $eventType, for adModel: $adModel")
        }
    }

    override suspend fun provideAd(adRequestData: BlazeAdRequestData): BlazeGoogleCustomNativeAdModel? {
        return adsProvider.generateAd(adRequestData)
    }

}
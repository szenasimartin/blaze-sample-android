package com.wscsports.android.blaze.sampleapp.ads

import android.content.Context
import android.util.Log
import com.blaze.blazesdk.features.ads.BlazeAdsHandler
import com.blaze.blazesdk.features.ads.models.BlazeAdModel
import com.blaze.blazesdk.features.ads.models.BlazeAdRequestData

class AdsHandler(
    appContext: Context
) : BlazeAdsHandler {

    private val adsProvider = AdsProvider(appContext)

    override fun onAdEvent(eventType: BlazeAdsHandler.EventType, adModel: BlazeAdModel) {
        when (eventType) {
            BlazeAdsHandler.EventType.OPENED_AD -> {
                // Report the ad impression to the ad provider.
                adModel.reportAdImpression()
            }

            BlazeAdsHandler.EventType.CTA_CLICKED -> {
                // Report the ad click to the ad provider.
                adModel.reportCTAClicked()
            }

            else ->
                Log.d("AdsHandler", "Received Ad event of type:  $eventType, for adModel: $adModel")
        }
    }

    override suspend fun provideAd(adRequestData: BlazeAdRequestData): BlazeAdModel? {
        return adsProvider.generateAd(adRequestData)
    }

}
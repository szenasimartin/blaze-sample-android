package com.wscsports.android.blaze.sampleapp.ads

import android.content.Context
import com.blaze.blazesdk.features.ads.models.BlazeAdModel
import com.blaze.blazesdk.features.ads.models.BlazeAdRequestData
import com.google.android.gms.ads.MobileAds
import kotlinx.coroutines.CompletableDeferred

class AdsProvider(context: Context) {

    companion object {
        const val adUnit = "SOME_DEFAULT_AD_UNIT_ID"
        const val formatId = "SOME_DEFAULT_FORMAT_ID"
    }

    private var appContext: Context = context

    init {
        MobileAds.initialize(context)
    }

    suspend fun generateAd(adRequestData: BlazeAdRequestData): BlazeAdModel? {
        var resultAd: BlazeAdModel? = null
        val nativeAdsManager = NativeAdsManager(context = appContext)

        // Load a single ad from Google and wait for it's result to return.
        val deferred = CompletableDeferred<Unit>()
        nativeAdsManager.requestAd(
            adUnit = adRequestData.adInfo?.adUnitId ?: adUnit,
            formatId = adRequestData.adInfo?.formatId ?: formatId,
            contextMap = adRequestData.adInfo?.context ?: emptyMap(),
            onAdsDataFailed = {
                deferred.complete(Unit)
            },
            onAdsDataLoaded = { ad ->
                resultAd = ad.toAdModel()
                deferred.complete(Unit)
            })
        deferred.await()

        return resultAd
    }

}
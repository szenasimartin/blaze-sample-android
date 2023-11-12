package com.wscsports.android.blaze.sampleapp.ads

import android.content.Context
import com.google.android.gms.ads.AdListener
import com.google.android.gms.ads.AdLoader
import com.google.android.gms.ads.LoadAdError
import com.google.android.gms.ads.admanager.AdManagerAdRequest
import com.google.android.gms.ads.nativead.NativeCustomFormatAd

class NativeAdsManager(private val context: Context) {

    private lateinit var adLoader: AdLoader

    fun requestAd(
        adUnit: String,
        formatId: String,
        contextMap: Map<String, String>,
        onAdsDataLoaded: (ads: NativeCustomFormatAd) -> Unit,
        onAdsDataFailed: (error: String) -> Unit
    ) {
        adLoader = AdLoader.Builder(context, adUnit)
            .forCustomFormatAd(
                formatId,
                { nativeCustomFormatAd ->
                    onAdsDataLoaded(nativeCustomFormatAd)
                })
            { _, _ ->
                // We handle clicks through the ads handler.
            }
            .withAdListener(object : AdListener() {
                override fun onAdFailedToLoad(loadAdError: LoadAdError) {
                    onAdsDataFailed(loadAdError.toString())
                }
            })
            .build()

        val adRequest = AdManagerAdRequest.Builder()
            .apply {
                // Adding custom extra values
                contextMap.forEach { item ->
                    addCustomTargeting(item.key, item.value)
                }
            }
            .build()
        adLoader.loadAd(adRequest)
    }

}


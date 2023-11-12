package com.wscsports.android.blaze.sampleapp.ads

import android.util.Log
import com.blaze.blazesdk.features.ads.models.BlazeAdModel
import com.google.android.gms.ads.nativead.NativeCustomFormatAd

/**
 * Extension for retrieving back the original native ad we set into [BlazeAdModel.customAdditionalData].
 */
fun BlazeAdModel.toNativeAd(): NativeCustomFormatAd? {
    return (customAdditionalData as? CustomAdData)?.nativeAd
}

/**
 * Extension to conveniently report an ad impression.
 */
fun BlazeAdModel.reportAdImpression() {
    toNativeAd()?.recordImpression()
    Log.d("AdsHandler", "Ad impression for ad with title: $title")
}

/**
 * Extension to conveniently report an ad click.
 */
fun BlazeAdModel.reportCTAClicked() {
    val key = when (content) {
        is BlazeAdModel.Content.Image -> AdConstants.DISPLAY
        is BlazeAdModel.Content.Video -> AdConstants.VIDEO
    }

    toNativeAd()?.performClick(key)
    Log.d("AdsHandler", "Ad CTA clicked for ad with title: $title, with key: $key")
}
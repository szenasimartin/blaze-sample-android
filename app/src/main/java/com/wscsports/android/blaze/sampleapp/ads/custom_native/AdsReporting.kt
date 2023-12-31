package com.wscsports.android.blaze.sampleapp.ads.custom_native

import android.util.Log
import com.blaze.blazesdk.features.ads.custom_native.models.BlazeGoogleCustomNativeAdModel
import com.google.android.gms.ads.nativead.NativeCustomFormatAd

/**
 * Extension for retrieving back the original native ad we set into [BlazeGoogleCustomNativeAdModel.customAdditionalData].
 */
fun BlazeGoogleCustomNativeAdModel.toNativeAd(): NativeCustomFormatAd? {
    return (customAdditionalData as? CustomAdData)?.nativeAd
}

/**
 * Extension to conveniently report an ad impression.
 */
fun BlazeGoogleCustomNativeAdModel.reportAdImpression() {
    toNativeAd()?.recordImpression()
    Log.d("AdsHandler", "Ad impression for ad with title: $title")
}

/**
 * Extension to conveniently report an ad click.
 */
fun BlazeGoogleCustomNativeAdModel.reportCTAClicked() {
    val key = when (content) {
        is BlazeGoogleCustomNativeAdModel.Content.Image -> AdConstants.DISPLAY
        is BlazeGoogleCustomNativeAdModel.Content.Video -> AdConstants.VIDEO
    }

    toNativeAd()?.performClick(key)
    Log.d("AdsHandler", "Ad CTA clicked for ad with title: $title, with key: $key")
}
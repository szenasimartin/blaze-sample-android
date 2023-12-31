package com.wscsports.android.blaze.sampleapp.ads.custom_native

import com.google.android.gms.ads.nativead.NativeCustomFormatAd

/**
 * You can here create additional data you want to be passed along with all the sdk events.
 */
data class CustomAdData(
    val nativeAd: NativeCustomFormatAd
)
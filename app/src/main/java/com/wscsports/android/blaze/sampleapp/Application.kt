package com.wscsports.android.blaze.sampleapp

import android.app.Application
import com.blaze.blazesdk.BlazeSDK
import com.blaze.blazesdk.core.managers.CachingLevel
import com.wscsports.android.blaze.sampleapp.ads.custom_native.GoogleCustomNativeAdsHandler
import com.wscsports.android.blaze.sampleapp.ads.ima.ImaHandler
import com.wscsports.android.blaze.sampleapp.core.Delegates

/** Use the [Application] class to initialize the BlazeSDK.
 * Note - you won't be able to use BlazeSDK before calling BlazeSDK.init
 * */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        BlazeSDK.init(
            // Please provide valid API-KEY here
            apiKey = "[API_KEY]",
            cachingLevel = CachingLevel.DEFAULT,
            cachingSize = 512,
            globalDelegate = Delegates.globalDelegate,
            playerEntryPointDelegate = Delegates.playerEntryPointDelegate,
            completionBlock = {
                logd("BlazeSDK.init completionBlock")
                BlazeSDK.setGoogleCustomNativeAdsHandler(googleCustomNativeAdsHandler = GoogleCustomNativeAdsHandler(appContext = this))
                BlazeSDK.setImaHandler(imaHandler = ImaHandler())
            },
            errorBlock = { error ->
                logd("BlazeSDK.init errorBlock -> , Init Error = $error")
            }
        )
    }

}

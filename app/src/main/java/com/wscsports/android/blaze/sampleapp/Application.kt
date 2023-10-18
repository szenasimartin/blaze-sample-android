package com.wscsports.android.blaze.sampleapp

import android.app.Application
import com.blaze.blazesdk.BlazeSDK
import com.blaze.blazesdk.core.managers.CachingLevel

/** Use the [Application] class to initialize the BlazeSDK.
 * Note - you won't be able to use BlazeSDK before calling BlazeSDK.init
 * */
class Application : Application() {

    override fun onCreate() {
        super.onCreate()

        BlazeSDK.init(
            // Please provide valid API-KEY here
            apiKey = "[API_KEY]",

            application = this,
            cachingLevel = CachingLevel.DEFAULT,
            cachingSize = 512,
            completionBlock = {
                logd("BlazeSDK.init completionBlock")
            },
            errorBlock = { error ->
                logd("BlazeSDK.init errorBlock -> , Init Error = $error")
            }
        )
    }

}

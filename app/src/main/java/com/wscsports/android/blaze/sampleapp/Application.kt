package com.wscsports.android.blaze.sampleapp

import android.app.Application
import com.blaze.blazesdk.BlazeSDK
import com.blaze.blazesdk.core.managers.CachingLevel

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        /*
           The SDK requires initialization by using a valid API key
            */
        BlazeSDK.init(
            // Please provide valid API-KEY here
            apiKey = "[API-KEY]",

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

package com.wscsports.android.blaze.sampleapp.core

import android.util.Log
import com.blaze.blazesdk.core.analytics.models.BlazeAnalyticsEvent
import com.blaze.blazesdk.core.delegates.BlazePlayerEntryPointDelegate
import com.blaze.blazesdk.core.delegates.BlazePlayerType
import com.blaze.blazesdk.core.delegates.BlazeWidgetDelegate
import com.blaze.blazesdk.core.models.BlazeResult
import com.blaze.blazesdk.features.player.BlazePlayerInContainerDelegate
import com.blaze.blazesdk.features.stories.models.ui.BlazeLinkActionHandleType
import com.blaze.blazesdk.features.widgets.shared.BlazeGlobalDelegate

/**
 * [Delegates] shows you how you can override and react to events throughout the SDK.
 * Note - all functions implementations are optional. It's the application developers choice which function to implement.
 * For more information, please refer to:
 *
 * Global Delegates: https://dash.readme.com/project/wsc-blaze/v1.0/docs/android-global-delegate-methods
 * Widget Delegates: https://dev.wsc-sports.com/docs/android-widgets#widget-delegate-methods
 * Entry Point Delegates: https://dev.wsc-sports.com/docs/android-blaze-player-entry-point-delegate
 * Container Delegates: https://dev.wsc-sports.com/docs/android-player-container#blazeplayerincontainerdelegate
 *
 * */
object Delegates {

    private const val TAG = "BlazeSampleAppDelegatesTAG"

    // Global Delegates sample implementation
    val globalDelegate = object : BlazeGlobalDelegate {

        override fun onErrorThrown(error: BlazeResult.Error) {
            Log.d(TAG, "onErrorThrown - $error")
        }

        override fun onEventTriggered(eventData: BlazeAnalyticsEvent) {
            Log.d(TAG, "onEventTriggered - $eventData")
        }

    }

    // Entry Points Delegates sample implementation
    val playerEntryPointDelegate = object : BlazePlayerEntryPointDelegate {

        override fun onDataLoadStarted(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onDataLoadStarted - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onDataLoadComplete(
            playerType: BlazePlayerType,
            sourceId: String?,
            itemsCount: Int,
            result: BlazeResult<Unit>
        ) {
            Log.d(TAG, "onDataLoadComplete - playerType - $playerType, sourceId - $sourceId, itemsCount - $itemsCount, result - $result")
        }

        override fun onPlayerDidAppear(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onPlayerDidAppear - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onPlayerDidDismiss(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onPlayerDidDismiss - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onTriggerCTA(
            playerType: BlazePlayerType,
            sourceId: String?,
            actionType: String,
            actionParam: String
        ): Boolean {
            Log.d(TAG, "onTriggerCTA - playerType - $playerType, sourceId - $sourceId, actionType - $actionType, actionParam - $actionParam")
            return false
        }

        override fun onTriggerPlayerBodyTextLink(
            playerType: BlazePlayerType,
            sourceId: String?,
            actionParam: String
        ): BlazeLinkActionHandleType {
            Log.d(TAG, "onTriggerPlayerBodyTextLink - playerType - $playerType, sourceId - $sourceId, actionParam - $actionParam")
            return BlazeLinkActionHandleType.DEEPLINK
        }

    }

    // Widget Delegates sample implementation
    val widgetDelegate = object : BlazeWidgetDelegate {

        override fun onDataLoadStarted(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onDataLoadStarted - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onDataLoadComplete(
            playerType: BlazePlayerType,
            sourceId: String?,
            itemsCount: Int,
            result: BlazeResult<Unit>
        ) {
            Log.d(TAG, "onDataLoadComplete - playerType - $playerType, sourceId - $sourceId, itemsCount - $itemsCount, result - $result")
        }

        override fun onPlayerDidAppear(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onPlayerDidAppear - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onPlayerDidDismiss(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onPlayerDidDismiss - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onTriggerCTA(
            playerType: BlazePlayerType,
            sourceId: String?,
            actionType: String,
            actionParam: String
        ): Boolean {
            Log.d(TAG, "onTriggerCTA - playerType - $playerType, sourceId - $sourceId, actionType - $actionType, actionParam - $actionParam")
            return false
        }

        override fun onTriggerPlayerBodyTextLink(
            playerType: BlazePlayerType,
            sourceId: String?,
            actionParam: String
        ): BlazeLinkActionHandleType {
            Log.d(TAG, "onTriggerPlayerBodyTextLink - playerType - $playerType, sourceId - $sourceId, actionParam - $actionParam")
            return BlazeLinkActionHandleType.DEEPLINK
        }

        override fun onItemClicked(
            sourceId: String?,
            itemId: String,
            itemTitle: String) {
            Log.d(TAG, "sourceId - $sourceId, itemId - $itemId, itemTitle - $itemTitle")
        }

    }

    // Container Delegates sample implementation
    val playerInContainerDelegate = object : BlazePlayerInContainerDelegate {

        override fun onDataLoadStarted(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onDataLoadStarted - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onDataLoadComplete(
            playerType: BlazePlayerType,
            sourceId: String?,
            itemsCount: Int,
            result: BlazeResult<Unit>
        ) {
            Log.d(TAG, "onDataLoadComplete - playerType - $playerType, sourceId - $sourceId, itemsCount - $itemsCount, result - $result")
        }

        override fun onPlayerDidAppear(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onPlayerDidAppear - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onPlayerDidDismiss(playerType: BlazePlayerType, sourceId: String?) {
            Log.d(TAG, "onPlayerDidDismiss - playerType - $playerType, sourceId - $sourceId")
        }

        override fun onTriggerCTA(
            playerType: BlazePlayerType,
            sourceId: String?,
            actionType: String,
            actionParam: String
        ): Boolean {
            Log.d(TAG, "onTriggerCTA - playerType - $playerType, sourceId - $sourceId, actionType - $actionType, actionParam - $actionParam")
            return false
        }

        override fun onTriggerPlayerBodyTextLink(
            playerType: BlazePlayerType,
            sourceId: String?,
            actionParam: String
        ): BlazeLinkActionHandleType {
            Log.d(TAG, "onTriggerPlayerBodyTextLink - playerType - $playerType, sourceId - $sourceId, actionParam - $actionParam")
            return BlazeLinkActionHandleType.DEEPLINK
        }

    }

}
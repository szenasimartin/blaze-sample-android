package com.wscsports.android.blaze.sampleapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.WindowCompat
import com.blaze.blazesdk.core.analytics.models.BlazeAnalyticsEvent
import com.blaze.blazesdk.core.managers.CachingLevel
import com.blaze.blazesdk.core.models.BlazeResult
import com.blaze.blazesdk.features.stories.models.shared.OrderType
import com.blaze.blazesdk.features.stories.models.ui.CtaTypeModel
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.features.widgets.shared.GlobalDelegates
import com.blaze.blazesdk.presets.BlazeStoriesTilesPresets
import com.wscsports.android.blaze.sampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        // Handle the splash screen transition.
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        WindowCompat.setDecorFitsSystemWindows(window, false)
        initListeners()
        initRowWidget()
        initGridWidget()
        initGlobalDelegates()
    }

    private fun initGlobalDelegates() {
        GlobalDelegates.onStoryPlayerDidAppear = ::onStoryPlayerDidAppear
        GlobalDelegates.onStoryPlayerDismissed = ::onStoryPlayerDismissed
        GlobalDelegates.onStoryLoadStarted = ::onStoryLoadStarted
        GlobalDelegates.onStoryLoadCompleted = ::onStoryLoadCompleted
        GlobalDelegates.onEventTriggered = ::onEventTriggered
    }

    /**
     * set and update the pullToRefresh layout Listener
     */
    private fun initListeners() {
        binding.pullToRefresh.setOnRefreshListener {
            updateLabels()
            binding.pullToRefresh.isRefreshing = false
        }
    }

    /**
     * Used to showcase options to change labels and or refresh data for current labelExpression.
     */
    private fun updateLabels() {
        binding.storyRowWidget.reloadData()
        binding.storyGridWidget.reloadData()//isSilentRefresh = false)

//        binding.storyRowWidget.updateLabel(BlazeWidgetLabel.singleLabel("live-stories"))
//        binding.storyGridWidget.updateLabel(BlazeWidgetLabel.singleLabel("top-stories"))
    }

    private fun initRowWidget() {
        val presetCircleWidget = BlazeStoriesTilesPresets.ROW_WIDGET_CIRCLE
        binding.storyRowWidget.initWidget(
            blazeStoryTheme = presetCircleWidget,
            labelExpression = BlazeWidgetLabel.singleLabel("live-stories"),
            widgetId = "live-stories",
//            maxItemsFromAPI = null,
//            orderType = null,
//            cachingLevel = CachingLevel.DEFAULT,
            onItemClicked = { item ->
                logd("onItemClicked - item.id => ${item.id}")
            },
            onWidgetDataLoadStarted = ::onWidgetDataLoadStarted,
            onWidgetDataLoadCompleted = ::onWidgetDataLoadCompleted,
            onWidgetPlayerDismissed = ::onWidgetPlayerDismissed,
            onTriggerCTA = ::onTriggerCTA
        )
    }

    private fun initGridWidget() {
        val presetGridWidget = BlazeStoriesTilesPresets.GRID_WIDGET_RECTANGLE_2_COL

        // We can limit the amount of items shown on the widget level
        presetGridWidget.widgetLayout.maxDisplayItemsCount = 4

        binding.storyGridWidget.initWidget(
            blazeStoryTheme = presetGridWidget,
            labelExpression = BlazeWidgetLabel.singleLabel("top-stories"),
            widgetId = "top-stories",
//            maxItemsFromAPI = null,
//            orderType = null,
//            cachingLevel = CachingLevel.DEFAULT,
            onItemClicked = { item ->
                logd("onItemClicked - item.id => ${item.id}")
            },
            onWidgetDataLoadStarted = ::onWidgetDataLoadStarted,
            onWidgetDataLoadCompleted = ::onWidgetDataLoadCompleted,
            onWidgetPlayerDismissed = ::onWidgetPlayerDismissed,
            onTriggerCTA = ::onTriggerCTA
        )
    }


    private fun onWidgetDataLoadStarted(widgetId: String) {
        binding.pullToRefresh.isRefreshing = true
        logd("onWidgetDataLoadStarted - widgetId => $widgetId")
    }

    private fun onWidgetDataLoadCompleted(
        widgetId: String,
        itemsCount: Int,
        error: BlazeResult<Any>?
    ) {
        logd("onWidgetDataLoadCompleted - widgetId => $widgetId, itemsCount => $itemsCount, error => $error")
        binding.pullToRefresh.isRefreshing = false
    }

    private fun onWidgetPlayerDismissed(widgetId: String) {
        logd("onWidgetPlayerDismissed - widgetId => $widgetId")
    }

    private fun onTriggerCTA(widgetId: String, actionType: String, actionParam: String): Boolean {
        logd("onTriggerCTA - widgetId => $widgetId, actionType => $actionType, actionParam => $actionParam")

        return when (CtaTypeModel.typeFromString(actionType)) {
            CtaTypeModel.DEEPLINK -> {
                //return true as if this was handled by App and not SDK
                true
            }

            CtaTypeModel.WEB -> {
                //return true as if this was not handled by App and should be handled by SDK
                false
            }

            null -> {
                //Handle in case needed
                false
            }
        }
    }

    private fun onEventTriggered(eventType: String, eventData: BlazeAnalyticsEvent) {
        logd("onEventTriggered - eventType => $eventType, eventData => $eventData")
    }

    private fun onStoryPlayerDidAppear() {
        logd("onStoryPlayerDidAppear")
    }

    private fun onStoryPlayerDismissed() {
        logd("onStoryPlayerDismissed")
    }

    private fun onStoryLoadStarted(storyId: String?) {
        logd("onStoryLoadStarted - storyId => $storyId")
    }

    private fun onStoryLoadCompleted(storyId: String?, error: BlazeResult<Any>?) {
        logd("onStoryLoadCompleted - storyId => $storyId, error => $error")
    }


}
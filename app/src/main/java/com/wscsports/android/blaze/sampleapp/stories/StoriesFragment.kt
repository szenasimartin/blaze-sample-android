package com.wscsports.android.blaze.sampleapp.stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blaze.blazesdk.core.analytics.models.BlazeAnalyticsEvent
import com.blaze.blazesdk.core.models.BlazeResult
import com.blaze.blazesdk.features.ads.models.BlazeStoriesAdsConfigType
import com.blaze.blazesdk.features.stories.models.ui.CtaTypeModel
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.features.widgets.shared.GlobalDelegates
import com.blaze.blazesdk.presets.BlazeStoriesPresetThemes
import com.wscsports.android.blaze.sampleapp.R
import com.wscsports.android.blaze.sampleapp.databinding.FragmentStoriesBinding
import com.wscsports.android.blaze.sampleapp.logd

class StoriesFragment : Fragment(R.layout.fragment_stories) {

    private var binding: FragmentStoriesBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentStoriesBinding.inflate(inflater, container, false)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initRowWidget()
        initGridWidget()
        initGlobalDelegates()
    }

    /**
     * Global delegates
     * */
    private fun initGlobalDelegates() {
        GlobalDelegates.onStoryPlayerDidAppear = ::onStoryPlayerDidAppear
        GlobalDelegates.onStoryPlayerDismissed = ::onStoryPlayerDismissed
        GlobalDelegates.onEventTriggered = ::onEventTriggered
    }

    /**
     * set and update the pullToRefresh layout Listener
     */
    private fun initListeners() {
        binding?.apply {
            storiesPullToRefresh.setOnRefreshListener {
                updateLabels()
                storiesPullToRefresh.isRefreshing = false
            }
        }
    }

    /**
     * Used to showcase options to change labels and or refresh data for current labelExpression.
     */
    private fun updateLabels() {
        binding?.apply {
//        binding.storyRowWidget.updateLabel(BlazeWidgetLabel.singleLabel("live-stories"))
//        binding.storyGridWidget.updateLabel(BlazeWidgetLabel.singleLabel("top-stories"))
            storyRowWidget.reloadData()
            storyGridWidget.reloadData()
        }
    }


    private fun initRowWidget() {
        // Using default Preset
        val storiesRowPreset = BlazeStoriesPresetThemes.ROW_WIDGET_CIRCLE

        //You can modify onboarding experience by setting firstTimeSlide in playerTheme
        //storiesRowPreset.playerTheme.firstTimeSlide.mainTitle.text ="Moments First Time Slide Title"

        //You can modify player buttons experience by setting buttons scaleType in playerTheme
        //storiesRowPreset.playerTheme.buttons.exitButton.scaleType = BlazeScaleType.FIT_XY

        // Example of customizing ads configuration
        binding?.storyRowWidget?.updateAdsConfigType(
            BlazeStoriesAdsConfigType.EVERY_X_STORIES
        )

        binding?.storyRowWidget?.initWidget(
            blazeStoryTheme = storiesRowPreset,
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("live-stories")),
            widgetId = "live-stories-row",
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
        // Using default Preset
        val storiesGridPreset = BlazeStoriesPresetThemes.GRID_WIDGET_RECTANGLE_2_COL

        //You can modify onboarding experience by setting firstTimeSlide in playerTheme
        //storiesGridPreset.playerTheme.firstTimeSlide.mainTitle.text ="Moments First Time Slide Title"

        //You can modify player buttons experience by setting buttons scaleType in playerTheme
        //storiesGridPreset.playerTheme.buttons.exitButton.scaleType = BlazeScaleType.FIT_XY

        // We can modify the given presets. i.e.,
        // Limit the amount of items shown on the widget level
        storiesGridPreset.widgetLayout.maxDisplayItemsCount = 4

        // Example of customizing ads configuration
        binding?.storyRowWidget?.updateAdsConfigType(
            BlazeStoriesAdsConfigType.FIXED_PAGES_INDEX
        )

        binding?.storyGridWidget?.initWidget(
            blazeStoryTheme = storiesGridPreset,
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("top-stories")),
            widgetId = "top-stories-grid",
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
        binding?.storiesPullToRefresh?.isRefreshing = true
        logd("onWidgetDataLoadStarted - widgetId => $widgetId")
    }

    private fun onWidgetDataLoadCompleted(
        widgetId: String,
        itemsCount: Int,
        error: BlazeResult<Any>?
    ) {
        logd("onWidgetDataLoadCompleted - widgetId => $widgetId, itemsCount => $itemsCount, error => $error")
        binding?.storiesPullToRefresh?.isRefreshing = false
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

}
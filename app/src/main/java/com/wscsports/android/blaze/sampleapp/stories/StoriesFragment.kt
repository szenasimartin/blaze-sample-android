package com.wscsports.android.blaze.sampleapp.stories

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blaze.blazesdk.features.ads.custom_native.models.BlazeStoriesAdsConfigType
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeStoriesPresetThemes
import com.wscsports.android.blaze.sampleapp.R
import com.wscsports.android.blaze.sampleapp.core.Delegates
import com.wscsports.android.blaze.sampleapp.databinding.FragmentStoriesBinding

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
    }

    override fun onResume() {
        super.onResume()
        binding?.storiesPullToRefresh?.isRefreshing = false
    }

    /**
     * set and update the pullToRefresh layout Listener
     */
    private fun initListeners() {
        binding?.apply {
            storiesPullToRefresh.setOnRefreshListener {
                updateDataSource()
                storiesPullToRefresh.isRefreshing = false
            }
        }
    }

    /**
     * Used to showcase options to change data source type and or refresh data for current data source.
     */
    private fun updateDataSource() {
        binding?.apply {

            storyRowWidget.updateDataSource(
                dataSourceType = BlazeDataSourceType.Labels(
                    BlazeWidgetLabel.singleLabel("live-stories")
                )
            )

            storyGridWidget.updateDataSource(
                dataSourceType = BlazeDataSourceType.Labels(
                    BlazeWidgetLabel.singleLabel("top-stories")
                )
            )

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

        // You can modify the format of the last update text.
        //storiesRowPreset.playerTheme.lastUpdate.textCase = PlayerTextCase.UPPERCASE

        // Example of customizing ads configuration
        binding?.storyRowWidget?.updateAdsConfigType(
            BlazeStoriesAdsConfigType.EVERY_X_STORIES
        )

        binding?.storyRowWidget?.initWidget(
            blazeStoryTheme = storiesRowPreset,
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("live-stories")),
            widgetId = "live-stories-row",
            widgetDelegate = Delegates.widgetDelegate,
            shouldOrderWidgetByReadStatus = true
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
            widgetDelegate = Delegates.widgetDelegate,
            shouldOrderWidgetByReadStatus = true
        )
    }

}
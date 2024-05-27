package com.wscsports.android.blaze.sampleapp.moments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeMomentPresetThemes
import com.wscsports.android.blaze.sampleapp.R
import com.wscsports.android.blaze.sampleapp.core.Delegates
import com.wscsports.android.blaze.sampleapp.databinding.FragmentMomentsBinding

class MomentsFragment : Fragment(R.layout.fragment_moments) {

    private var binding: FragmentMomentsBinding? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentMomentsBinding.inflate(inflater, container, false)
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
        binding?.momentsPullToRefresh?.isRefreshing = false
    }

    /**
     * set and update the pullToRefresh layout Listener
     */
    private fun initListeners() {
        binding?.apply {
            momentsPullToRefresh.setOnRefreshListener {
                updateDataSource()
                momentsPullToRefresh.isRefreshing = false
            }
        }
    }

    /**
     * Used to showcase options to change data source type and or refresh data for current data source.
     */
    private fun updateDataSource() {
        binding?.apply {

            momentsRowWidget.updateDataSource(
                dataSourceType = BlazeDataSourceType.Labels(
                    BlazeWidgetLabel.singleLabel("moments")
                )
            )

            momentsGridWidget.updateDataSource(
                dataSourceType = BlazeDataSourceType.Labels(
                    BlazeWidgetLabel.singleLabel("moments")
                )
            )

            momentsRowWidget.reloadData()
            momentsGridWidget.reloadData()
        }
    }


    private fun initRowWidget() {
        // Using default Preset
        val momentsRowPreset = BlazeMomentPresetThemes.ROW_WIDGET_RECTANGLE
        //You can modify onboarding experience by setting firstTimeSlide in playerTheme
        //momentsRowPreset.playerTheme.firstTimeSlide.mainTitle.text ="Moments First Time Slide Title"

        //You can modify player buttons experience by setting buttons scaleType in playerTheme
        // momentsRowPreset.playerTheme.buttons.exitButton.scaleType = BlazeScaleType.FIT_XY

        binding?.momentsRowWidget?.initWidget(
            blazeMomentTheme = momentsRowPreset,
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("moments")),
            widgetId = "moments-row",
            widgetDelegate = Delegates.widgetDelegate,
            shouldOrderWidgetByReadStatus = true
        )
    }

    private fun initGridWidget() {
        // Using default Preset
        val momentsGridPreset = BlazeMomentPresetThemes.ROW_WIDGET_RECTANGLE

        //You can modify onboarding experience by setting firstTimeSlide in playerTheme
        //momentsGridPreset.playerTheme.firstTimeSlide.mainTitle.text ="Moments First Time Slide Title"

        //You can modify player buttons experience by setting buttons scaleType in playerTheme
        // momentsGridPreset.playerTheme.buttons.exitButton.scaleType = BlazeScaleType.FIT_XY

        //You can modify the end positioning of the bottom gradient.
        //momentsGridPreset.playerTheme.playerFooterGradient.endPositioning = PlayerFooterGradientPositioning.BOTTOM_TO_CONTAINER

        // We can modify the given presets. i.e.,
        // Limit the amount of items shown on the widget level
        momentsGridPreset.widgetLayout.maxDisplayItemsCount = 4

        binding?.momentsGridWidget?.initWidget(
            blazeMomentTheme = momentsGridPreset,
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("moments")),
            widgetId = "moments-grid",
            widgetDelegate = Delegates.widgetDelegate,
            shouldOrderWidgetByReadStatus = true
        )
    }

}
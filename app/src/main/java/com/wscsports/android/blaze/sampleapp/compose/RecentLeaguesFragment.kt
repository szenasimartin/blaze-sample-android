package com.wscsports.android.blaze.sampleapp.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.blaze.blazesdk.core.models.BlazeResult
import com.blaze.blazesdk.features.compose.StoriesWidgetsRow
import com.blaze.blazesdk.features.stories.models.ui.CtaTypeModel
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeStoriesPresetThemes
import com.wscsports.android.blaze.sampleapp.R
import com.wscsports.android.blaze.sampleapp.logd

class RecentLeaguesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposeFragmentLayout()
            }
        }
    }

    private fun onWidgetDataLoadStarted(widgetId: String) {
        logd("onWidgetDataLoadStarted - widgetId => $widgetId")
    }

    private fun onWidgetDataLoadCompleted(
        widgetId: String,
        itemsCount: Int,
        error: BlazeResult<Any>?
    ) {
        logd("onWidgetDataLoadCompleted - widgetId => $widgetId, itemsCount => $itemsCount, error => $error")
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

    @Preview(showBackground = true)
    @Composable
    fun MainActivityPreview() {
        ComposeFragmentLayout()
    }

    @Composable
    fun ComposeFragmentLayout() {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState()),

                ) {

                Spacer(modifier = Modifier.height(16.dp))

                BlazeTitle(
                    title = resources.getString(R.string.recent_stories)
                )

                val screenWidth = LocalConfiguration.current.screenWidthDp // Device screen width
                val rowHeight = screenWidth * (9f / 16f) // Calculating height (using 16:9 ratio).
                val horizontalMargin = 8 // In dp
                val calculatedRowHeight = rowHeight - horizontalMargin

                StoriesWidgetsRow(
                    modifier = Modifier
                        .height(calculatedRowHeight.dp)
                        .fillMaxWidth(),
                    blazeStoryTheme = BlazeStoriesPresetThemes.WIDGET_HORIZONTAL_RECTANGLE_SINGLE_ITEM,
                    dataSourceType =  BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("live-stories")),
                    widgetId = "live-stories-widget-row-compose",
                    onItemClicked = { },
                    onWidgetDataLoadStarted = ::onWidgetDataLoadStarted,
                    onWidgetDataLoadCompleted = ::onWidgetDataLoadCompleted,
                    onWidgetPlayerDismissed = ::onWidgetPlayerDismissed,
                    onTriggerCTA = ::onTriggerCTA
                )

                Spacer(modifier = Modifier.height(16.dp))

                BlazeTitle(
                    title = resources.getString(R.string.top_stories)
                )

                StoriesWidgetsRow(
                    modifier = Modifier
                        .height(calculatedRowHeight.dp)
                        .fillMaxWidth(),
                    blazeStoryTheme = BlazeStoriesPresetThemes.WIDGET_HORIZONTAL_RECTANGLE_SINGLE_ITEM,
                    dataSourceType =  BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("top-stories")),
                    widgetId = "top-stories-widget-row-compose",
                    onItemClicked = { },
                    onWidgetDataLoadStarted = ::onWidgetDataLoadStarted,
                    onWidgetDataLoadCompleted = ::onWidgetDataLoadCompleted,
                    onWidgetPlayerDismissed = ::onWidgetPlayerDismissed,
                    onTriggerCTA = ::onTriggerCTA
                )
            }
        }
    }
}

@Composable
fun BlazeTitle(title: String) {

    Text(
        text = title,
        color = Color(0xFF232935),
        fontSize = 20.sp,
        fontWeight = FontWeight.Bold,
        modifier = Modifier
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 0.dp)
            .fillMaxWidth()
            .padding(12.dp)
            .wrapContentSize(Alignment.CenterStart)
    )
}
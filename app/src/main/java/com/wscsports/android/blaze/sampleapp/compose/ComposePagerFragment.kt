package com.wscsports.android.blaze.sampleapp.compose

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.blaze.blazesdk.features.compose.StoriesWidgetsRow
import com.blaze.blazesdk.features.compose.WidgetStoriesStateHandler
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeStoriesPresetThemes
import com.wscsports.android.blaze.sampleapp.core.Delegates
import com.wscsports.android.blaze.sampleapp.viewpager.ViewPagerActivity

class ComposePagerFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                ComposePagerLayout()
            }
        }
    }

    @OptIn(ExperimentalFoundationApi::class)
    @Composable
    fun ComposePagerLayout() {
        val pagerState = rememberPagerState(pageCount = {
            5
        })
        HorizontalPager(state = pagerState) {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .background(
                        if (it == 0) {
                            Color.Blue
                        } else {
                            Color.Red
                        }
                    )
            ) {

                Text(text = "Pager: $it", modifier = Modifier.padding(20.dp), color = Color.White)

                StoriesWidgetsRow(
                    modifier = Modifier.height(120.dp).fillMaxWidth().horizontalScroll(state = rememberScrollState()),
                    widgetStoriesStateHandler = WidgetStoriesStateHandler(
                        widgetId = "top-stories",
                        blazeStoryTheme = BlazeStoriesPresetThemes.ROW_WIDGET_CIRCLE,
                        dataSourceType = BlazeDataSourceType.Labels(BlazeWidgetLabel.atLeastOneOf("g220551", "mg220551")),
                        widgetDelegate = Delegates.widgetDelegate
                    )
                )

                Button(onClick = { startActivity(Intent(requireContext(), ViewPagerActivity::class.java)) }) {
                    Text(text = "Next")
                }

            }
        }
    }
}

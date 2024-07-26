package com.wscsports.android.blaze.sampleapp.viewpager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.rememberScrollState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import com.blaze.blazesdk.features.compose.StoriesWidgetsRow
import com.blaze.blazesdk.features.compose.WidgetStoriesStateHandler
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeStoriesPresetThemes
import com.wscsports.android.blaze.sampleapp.core.Delegates
import com.wscsports.android.blaze.sampleapp.databinding.FragmentInViewPagerBinding
import java.util.UUID

class FragmentInViewPager : Fragment() {
    private lateinit var binding: FragmentInViewPagerBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = FragmentInViewPagerBinding.inflate(inflater).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.root.setContent {
            Box(modifier = Modifier.height(120.dp)) {
                StoriesWidgetsRow(
                    modifier = Modifier.height(120.dp).horizontalScroll(state = rememberScrollState()),
                    widgetStoriesStateHandler = WidgetStoriesStateHandler(
                        widgetId = "top-stories",
                        blazeStoryTheme = BlazeStoriesPresetThemes.ROW_WIDGET_CIRCLE,
                        dataSourceType = BlazeDataSourceType.Labels(BlazeWidgetLabel.atLeastOneOf("g220551", "mg220551")),
                        widgetDelegate = Delegates.widgetDelegate
                    )
                )
            }
        }
    }
}

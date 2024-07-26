package com.wscsports.android.blaze.sampleapp.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.lifecycleScope
import com.blaze.blazesdk.core.models.BlazeResult
import com.blaze.blazesdk.features.compose.MomentsWidgetsGrid
import com.blaze.blazesdk.features.compose.WidgetMomentsStateHandler
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeMomentPresetThemes
import com.wscsports.android.blaze.sampleapp.core.Delegates
import com.wscsports.android.blaze.sampleapp.logd
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class RecentLeaguesFragment : Fragment() {

    private val viewModelViewState = MutableStateFlow(ViewState(listOf(Items.Moments("1"))))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            CoroutineScope(Dispatchers.IO).launch {
                delay(2000)
                viewModelViewState.update { currentState ->
                    val newList: MutableList<Items> = currentState.items.toMutableList()
                    newList.add(0, Items.Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas malesuada auctor eros in iaculis. Quisque sit amet hendrerit risus. Nulla ultrices nisl quis orci blandit, in sodales massa co"))
                    newList.add(0, Items.Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas malesuada auctor eros in iaculis. Quisque sit amet hendrerit risus. Nulla ultrices nisl quis orci blandit, in sodales massa co"))
                    newList.add(0, Items.Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas malesuada auctor eros in iaculis. Quisque sit amet hendrerit risus. Nulla ultrices nisl quis orci blandit, in sodales massa co"))
                    newList.add(0, Items.Text("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Maecenas malesuada auctor eros in iaculis. Quisque sit amet hendrerit risus. Nulla ultrices nisl quis orci blandit, in sodales massa co"))
                    currentState.copy(items = newList)
                }
            }
        }
    }

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

        return when (actionType) {

            "Deeplink" -> {
                //return true as if this was handled by App and not SDK
                false
            }

            "Web" -> {
                //return true as if this was not handled by App and should be handled by SDK
                true
            }

            else -> {
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
        val viewState by viewModelViewState.collectAsStateWithLifecycle()
        LazyColumn {
            items(viewState.items) { item ->
                when (item) {
                    is Items.Moments -> MomentsWidgetsGrid(
                        modifier = Modifier,
                        widgetMomentsStateHandler = WidgetMomentsStateHandler(
                            widgetId = item.id,
                            blazeMomentTheme = BlazeMomentPresetThemes.GRID_WIDGET_RECTANGLE_3_COL.apply {
                                widgetLayout.maxDisplayItemsCount = 9
                            },
                            dataSourceType = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("th_home_moments")),
                            widgetDelegate = Delegates.widgetDelegate,
                        ),
                    )

                    is Items.Text -> Text(text = item.text)
                }
            }
        }

    }
}

data class ViewState(val items: List<Items>)

sealed class Items {
    data class Text(val text: String) : Items()
    data class Moments(val id: String) : Items()
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
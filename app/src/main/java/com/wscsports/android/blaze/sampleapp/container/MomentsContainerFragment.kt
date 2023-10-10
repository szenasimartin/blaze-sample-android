package com.wscsports.android.blaze.sampleapp.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.blaze.blazesdk.core.models.BlazeResult
import com.blaze.blazesdk.features.moments.container.BlazeMomentsPlayerContainer
import com.blaze.blazesdk.features.player.BlazePlayerInContainerDelegate
import com.blaze.blazesdk.features.stories.models.ui.CtaTypeModel
import com.blaze.blazesdk.features.widgets.labels.BlazeDataSourceType
import com.blaze.blazesdk.features.widgets.labels.BlazeWidgetLabel
import com.blaze.blazesdk.presets.BlazeMomentPresetThemes
import com.wscsports.android.blaze.sampleapp.R
import com.wscsports.android.blaze.sampleapp.VolumeViewModel
import com.wscsports.android.blaze.sampleapp.databinding.FragmentContainerMomentsBinding
import com.wscsports.android.blaze.sampleapp.logd

class MomentsContainerFragment: Fragment(R.layout.fragment_container_moments) {

    private var _binding: FragmentContainerMomentsBinding? = null
    private val volumeViewModel: VolumeViewModel by activityViewModels()
    private var momentsPlayerContainer: BlazeMomentsPlayerContainer? = null

    // This property is only valid between onCreateView and onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentContainerMomentsBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        try {
            initObservers()
            playMomentsInContainer()
        } catch (err: Throwable) {
            err.printStackTrace()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initObservers() {
        volumeViewModel.volume.observe(viewLifecycleOwner) { isVolumeChanged ->
            if (isVolumeChanged) {
                momentsPlayerContainer?.onVolumeChanged()
            }
        }
    }

    private fun playMomentsInContainer() {
        // Customizing player
        val momentsPlayerTheme = BlazeMomentPresetThemes.MOMENTS_PLAYER_THEME
        momentsPlayerTheme.buttons.muteButton.isVisible = false
        momentsPlayerTheme.buttons.exitButton.isVisible = false
        momentsPlayerTheme.playerSeekBar.isVisible = false

        // Delegates implementation
        val playerInContainerDelegate = object : BlazePlayerInContainerDelegate {
            override fun onContainedPlayerDismissed(containerId: String) {
                logd("onContainedPlayerDismissed - containerId => $containerId")
            }

            override fun onDataLoadCompleted(containerId: String, itemsCount: Int, result: BlazeResult<Any>?) {
                logd("onDataLoadCompleted - containerId => $containerId, itemsCount => $itemsCount, result => $result")
            }

            override fun onDataLoadStarted(containerId: String) {
                logd("onDataLoadStarted - containerId => $containerId")
            }

            override fun onTriggerCTA(containerId: String, actionType: String, actionParam: String): Boolean {
                logd("onTriggerCTA - containerId => $containerId, actionType => $actionType, actionParam => $actionParam")
                return when (CtaTypeModel.typeFromString(actionType)) {
                    CtaTypeModel.DEEPLINK -> {
                        true
                    }

                    CtaTypeModel.WEB -> {
                        false
                    }

                    null -> {
                        //Handle in case needed
                        false
                    }
                }
            }
        }

        // Initializing the BlazeMomentsPlayerContainer instance
        momentsPlayerContainer = BlazeMomentsPlayerContainer(
            dataSource = BlazeDataSourceType.Labels(BlazeWidgetLabel.singleLabel("<SOME_LABEL>")),
            shouldPrepareContent = false,
            containerId = "<UNIQUE_CONTAINER_ID",
            momentsPlayerTheme = momentsPlayerTheme,
            playerInContainerDelegate = playerInContainerDelegate
        )

        // Starting to play
        momentsPlayerContainer?.startPlaying(
            childFragmentManager,
            binding.primeMomentsTabContainer,
        )

    }

}
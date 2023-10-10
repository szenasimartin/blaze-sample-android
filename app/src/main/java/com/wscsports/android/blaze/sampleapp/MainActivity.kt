package com.wscsports.android.blaze.sampleapp

import android.os.Bundle
import android.view.KeyEvent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.wscsports.android.blaze.sampleapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val navController by lazy {
        (supportFragmentManager.findFragmentById(R.id.mainActivityNavigation) as? NavHostFragment)?.navController
    }
    private val volumeViewModel: VolumeViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initUi()
    }

    private fun initUi() {
        binding.apply {
            navController?.let {
                mainActivityBottomNavigationView.setupWithNavController(it)
            }
        }
    }

    // Observing user increasing/decreasing volume, and updating the SDK
    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        return if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN || keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
            volumeViewModel.onVolumeChanged()
            true
        } else {
            super.onKeyUp(keyCode, event)
        }
    }

}
package com.wscsports.android.blaze.sampleapp.viewpager

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wscsports.android.blaze.sampleapp.databinding.ActivityViewPagerBinding

private const val NUM_PAGES = 5

class ViewPagerActivity : FragmentActivity() {

    private lateinit var binding: ActivityViewPagerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityViewPagerBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.root.adapter = PagerAdapter(this)
    }

    private inner class PagerAdapter(fa: FragmentActivity) : FragmentStateAdapter(fa) {
        override fun getItemCount(): Int = NUM_PAGES

        override fun createFragment(position: Int) = FragmentInViewPager()
    }
}

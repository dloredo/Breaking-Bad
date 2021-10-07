package com.dloredo.breakingbad

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.dloredo.breakingbad.databinding.ActivityMainBinding
import com.dloredo.breakingbad.ui.main.ViewPagerAdapter
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.pager.adapter = ViewPagerAdapter(this)
        TabLayoutMediator(binding.tabs, binding.pager){tab, position ->
            when (position) {
                0 -> {
                    tab.text = "Characters"
                }
                1 -> {
                    tab.text = "Phrases"
                }
                else -> {
                    tab.text = "Settings"
                }
            }
        }.attach()
    }
}
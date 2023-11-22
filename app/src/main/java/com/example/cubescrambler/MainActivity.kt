package com.example.cubescrambler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import com.example.cubescrambler.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.tvScramble.setOnTouchListener { v, event ->
            val action = event.action
            when(action) {
                MotionEvent.ACTION_UP -> {
                    //createScramble()
                }
            }
            true
        }
    }
}
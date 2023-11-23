package com.example.cubescrambler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import com.example.cubescrambler.databinding.ActivityMainBinding
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val Face = arrayOf("U", "F", "R", "L", "B", "D")
    private val Rotation = arrayOf("", "'", "2")
    private val random = Random()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = getString(R.string.toolbar_title)

        binding.tvScramble.setOnTouchListener { v, event ->
            val action = event.action
            when(action) {
                MotionEvent.ACTION_UP -> {
                    binding.tvScramble.text = createScramble()
                }
            }
            true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId) {
            R.id.three -> {}
            R.id.two -> {}
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createScramble(): String {
        val scramble = StringBuilder()
        val length = 20

        var face: Int
        var before = -1 // 이전에 나왔던 면을 저장
        var rotation: Int

        for (i in 0 until length) {
            do {
                face = random.nextInt(6)
            } while (face == before || face + before == 5) // 같은 면이거나 반대 면일 경우 다시
            before = face
            scramble.append(Face[face])

            rotation = random.nextInt(3)
            scramble.append(Rotation[rotation]).append(" ")
        }
        return scramble.toString()
    }
}
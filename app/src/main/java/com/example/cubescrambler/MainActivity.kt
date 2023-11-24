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

    private val FACE = arrayOf("U", "F", "R", "L", "B", "D")
    private val ROTATION = arrayOf("", "'", "2")
    private val random = Random()

    private var cube = 0 // default 3x3x3
    private val THREE = 0
    private val TWO = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        setSupportActionBar(binding.toolbar)
        binding.toolbar.title = getString(R.string.toolbar)

        binding.tvScramble.setOnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_UP -> {
                    if (cube == THREE) {
                        binding.tvScramble.text = createScramble(THREE)
                    } else if (cube == TWO) {
                        binding.tvScramble.text = createScramble(TWO)
                    }
                }
            }
            true
        }
    }

    override fun onResume() {
        super.onResume()

        if (cube == THREE) {
            binding.tvScramble.text = createScramble(THREE)
        } else if (cube == TWO) {
            binding.tvScramble.text = createScramble(TWO)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.option_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.three -> {
                cube = THREE
                binding.tvEvent.text = "3x3x3"
                binding.tvScramble.text = createScramble(THREE)
            }

            R.id.two -> {
                cube = TWO
                binding.tvEvent.text = "2x2x2"
                binding.tvScramble.text = createScramble(TWO)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun createScramble(cube: Int): String {
        val scramble = StringBuilder()

        var length = 0
        if (cube == THREE) {
            length = 20
        } else if (cube == TWO) {
            length = 9
        }

            var face: Int
            var before = -1 // 이전에 나왔던 면을 저장
            var rotation: Int

            for (i in 0 until length) {
                do {
                    if (cube == THREE) {
                        face = random.nextInt(6)
                    } else {
                        face = random.nextInt(3)
                    }
                } while (face == before || face + before == 5) // 같은 면이거나 반대 면일 경우 다시
                before = face
                scramble.append(FACE[face])

                rotation = random.nextInt(3)
                scramble.append(ROTATION[rotation]).append(" ")
            }
            return scramble.toString()
        }
    }
package com.example.cubescrambler

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import com.example.cubescrambler.databinding.ActivityMainBinding
import com.google.android.gms.oss.licenses.OssLicensesMenuActivity
import java.util.Random

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    private val FACE = arrayOf("U", "F", "R", "L", "B", "D")
    private val ROTATION = arrayOf("", "'", "2")
    private val random = Random()

    private var cube = 0 // default 3x3x3
    private val THREE = 0
    private val TWO = 1
    private val FOUR = 2
    private val FIVE = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
    }

    private fun initView() {
        binding.tvScramble.setOnTouchListener { v, event ->
            val action = event.action
            when (action) {
                MotionEvent.ACTION_UP -> {
                    if (cube == THREE) {
                        binding.tvScramble.text = createScramble(THREE)
                    } else if (cube == TWO) {
                        binding.tvScramble.text = createScramble(TWO)
                    } else if (cube == FOUR) {
                        binding.tvScramble.text = createScramble(FOUR)
                    } else if (cube == FIVE) {
                        binding.tvScramble.text = createScramble(FIVE)
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
        } else if (cube == FOUR) {
            binding.tvScramble.text = createScramble(FOUR)
        } else if (cube == FIVE) {
            binding.tvScramble.text = createScramble(FIVE)
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
            R.id.four -> {
                cube = FOUR
                binding.tvEvent.text = "4x4x4"
                binding.tvScramble.text = createScramble(FOUR)
            }
            R.id.five -> {
                cube = FIVE
                binding.tvEvent.text = "5x5x5"
                binding.tvScramble.text = createScramble(FIVE)
            }
            R.id.license -> {
                startActivity(Intent(this, OssLicensesMenuActivity::class.java))
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
            length = 10
        } else if (cube == FOUR) {
            length = 45
        } else if (cube == FIVE) {
            length = 60
        }

        var face: Int
        var before = -1 // 이전에 나왔던 면을 저장
        var rotation: Int
        var isW = false

        for (i in 0 until length) {
            do {
                if (cube == TWO) {
                    face = random.nextInt(3)
                } else {
                    face = random.nextInt(6)
                }
            } while (face == before || face + before == 5) // 같은 면이거나 반대 면일 경우 다시
            before = face
            scramble.append(FACE[face])

            if(cube == FOUR || cube == FIVE) {
                isW = random.nextBoolean()
                if(isW) {
                    if(cube == FOUR) {
                        if(face == 0 || face == 1 || face == 2) {
                            scramble.append("w")
                        }
                    } else {
                        scramble.append("w")
                    }
                }
            }
            rotation = random.nextInt(3)
            scramble.append(ROTATION[rotation]).append(" ")
        }
        return scramble.toString()
    }
}
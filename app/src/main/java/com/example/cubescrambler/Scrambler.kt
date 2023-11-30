package com.example.cubescrambler

import java.util.Random

object Scrambler {
    private val FACE = arrayOf("U", "F", "R", "L", "B", "D")
    private val ROTATION = arrayOf("", "'", "2")
    private val random = Random()

    private var cube = 0
    private val THREE = 0 // default
    private val TWO = 1
    private val FOUR = 2
    private val FIVE = 3

    private fun createScramble(): String {
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

            // 4x4x4 또는 5x5x5일 경우에만 w 기호 필요
            if(cube == FOUR || cube == FIVE) {
                isW = random.nextBoolean()
                if(isW) {
                    if(cube == FOUR) { // 4x4x4일 경우에는 U, F, R 기호만 필요
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
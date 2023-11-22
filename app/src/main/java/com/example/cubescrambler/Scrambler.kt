package com.example.cubescrambler

import java.util.Random

object Scrambler {
    private val Face = arrayOf("U", "F", "R", "L", "B", "D")
    private val Rotation = arrayOf("", "'", "2")
    private val random = Random()

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
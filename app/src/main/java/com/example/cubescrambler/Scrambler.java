package com.example.cubescrambler;

import java.util.Random;

public class Scrambler {
    private final static char[] Face = {'U', 'F', 'R', 'L', 'B', 'D'};
    private final static char[] Rotation = {' ', '\'', '2'};
    private final static Random random = new Random();

    private static String createScramble() {
        StringBuilder scramble = new StringBuilder();
        int length = 20;

        int face;
        int before = -1; // 이전에 나왔던 면을 저장
        int rotation;

        for(int i = 0; i < length; i++) {
            do {
                face = random.nextInt(6);
            } while(face == before || face + before == 5); // 같은 면이거나 반대 면일 경우 다시
            before = face;
            scramble.append(Face[face]);

            rotation = random.nextInt(3);
            scramble.append(Rotation[rotation]).append(" ");
        }
        return scramble.toString();
    }
}

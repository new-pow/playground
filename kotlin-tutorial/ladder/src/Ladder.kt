package src

import kotlin.random.Random

data class Ladder(val layer: Int, val playerNumber: Int) {

    // 2차원 Char 배열
    // 1. 행의 개수는 layer
    // 2. 열의 개수는 playerNumber
    val ladder = Array(layer) { CharArray(playerNumber*2 + 1) }

    // ladder 초기화
    init {
        for (i in 0 until layer) { // until은 마지막을 포함하지 않는다.
            // TODO : depth 1로 줄여보기
            for (j in 0 until playerNumber*2 + 1) {
                if (j % 2 == 0) {
                    ladder[i][j] = '|'
                } else {
                    ladder[i][j] = generateStep()
                }
            }
        }
    }

    fun generateStep() : Char {
        val random = Random.nextBoolean()
        if (random) {
            return '-'
        }
        return ' '
    }

    fun printLadder() {
        //TODO : depth 1로 줄여보기
        for (i in 0 until layer) {
            for (j in 0 until playerNumber*2 + 1) {
                print(ladder[i][j])
            }
            println()
        }
    }

}

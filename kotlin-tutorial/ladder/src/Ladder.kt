package src

import java.util.ArrayList
import kotlin.random.Random

data class Ladder(val layer: Int, val players: Players) {

    // 2차원 Char 배열
    // 1. 행의 개수는 layer
    // 2. 열의 개수는 playerNumber
    val width = 2
    val ladder = ArrayList<String>()

    val BRIDGE = "-------"
    val SPACE = "\t"
    val WALL = "|"

    // ladder 초기화
    init {
        for (i in 0 until layer) { // until은 마지막을 포함하지 않는다.
            ladder.add(generateLayer())
        }
    }

    private fun generateLayer() : String {
        var layer = "\t"
        var temp = ""
        for (i in 0 until players.getSize()*width -1) {
            if (i % width == 0) {
                layer += WALL
                continue
            }
            val generatePath = generatePath(temp)
            layer += generatePath
            temp = generatePath
        }
        return layer
    }

    private fun generatePath(temp : String) : String {
        if (temp == BRIDGE) {
            return SPACE
        }
        return generateStep()
    }


    private fun generateStep() : String {
        if (Random.nextBoolean()) {
            return BRIDGE
        }
        return SPACE
    }

    fun printLadder() {
        // print player name
        players.printPlayers()
        for (i in 0 until layer) {
            printLayer(i)
        }
    }

    private fun printLayer(i : Int) {
        print(ladder.get(i))
        println()
    }

}

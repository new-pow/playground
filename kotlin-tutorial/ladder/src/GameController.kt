package src

import src.*

fun main() {
    // 1단계 : 유저 입력 받기
    val ui = UserInterface()
    val players = ui.getPlayers()
    val ladderLayer = ui.getLadderLayerNumber()

    // 2단계 : 사다리 초기화
    val ladder = Ladder(ladderLayer, players)
    ladder.printLadder()
}

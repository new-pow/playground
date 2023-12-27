import src.*

fun main() {
    val ui = UserInterface()

    val playerNumber = ui.getPlayerNumber()
    val ladderLayer = ui.getLadderLayerNumber()

    val ladder = Ladder(ladderLayer, playerNumber)
    ladder.printLadder()
}

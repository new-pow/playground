package src.domain

data class Players(val players: List<Player>) {

    init {
        require(players.size >= 2) { "플레이어는 2명 이상이어야 합니다." }
    }

    fun printPlayers() {
        print("\t")
        players.forEach { print("${it.name}\t") }
        println()
    }

    fun getSize() : Int {
        return players.size
    }
}

data class Player(val name: String) {
    init {
        require(name.length >= 1) { "이름은 1글자 이상이어야 합니다." }
        require(name.length <= 5) { "이름은 5글자 이하여야 합니다." }
        require(name.isNotBlank()) { "이름은 공백이 아니어야 합니다." }
    }
}

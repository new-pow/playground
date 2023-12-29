package src

class UserInterface {

    fun getPlayers() : Players {
        println("참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)")
        val input = readLine()!! // !! : null이 아님을 보장. null이면 NPE 발생
        val names = input.split(",").map { it.trim() }
        return Players(names.map { Player(it) })
    }

    fun getLadderLayerNumber() : Int {
        println("최대 사다리 높이는 몇 개인가요?")
        return readLine()!!.toInt()
    }

}

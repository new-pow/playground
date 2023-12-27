package src

class UserInterface {

    fun getPlayerNumber() : Int {
        println("참여할 사람은 몇 명인가요?")
        return readLine()!!.toInt() // !! : null이 아님을 보장. null이면 NPE 발생
    }

    fun getLadderLayerNumber() : Int {
        println("최대 사다리 높이는 몇 개인가요?")
        return readLine()!!.toInt()
    }

}

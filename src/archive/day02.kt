fun day02_1(): Int {
    var horiz = 0
    var depth = 0
    linesInFile("resources/day02.txt").forEach {
        val dir = it.split(' ')[0]
        val size = it.split(' ')[1].toInt()
        println("$dir < $size")
        when (dir) {
            "forward" -> horiz += size
            "down" -> depth += size
            "up" -> depth -= size
            else -> {
                print("$dir  !!!!!!!!!!!!!!")
            }
        }
    }
    return horiz * depth
}

fun day02_2(): Int {
    var horiz = 0; var depth = 0; var aim = 0
    linesInFile("resources/day02.txt").forEach {
        val dir = it.split(' ')[0]
        val size = it.split(' ')[1].toInt()
        println("$dir $size")
        when (dir) {
            "forward" -> {
                horiz += size
                depth += aim * size
            }
            "down" -> aim += size
            "up" -> aim -= size
            else -> {
                print("$dir  !!!!!!!!!!!!!!")
            }
        }
    }
    return horiz * depth
}

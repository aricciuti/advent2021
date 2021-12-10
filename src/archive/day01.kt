
fun day01_1(): Int {
    var result = 0;
    var prev = 0
    linesInFile("resources/day01.txt").forEach {
        println("$it & $prev")
        val crt = it.toInt()
        if (prev in 1 until crt) {
            result++
        }
        prev = crt
    }
    return result
}

fun day01_2(): Int {
    var result = 0
    var v0 = 0; var v1 = 0; var sumPrev = 0
    linesInFile("resources/day01.txt").forEach {
        val crt = it.toInt()
        val sumCrt = crt + v1 + v0
        if (v0 > 0 && v1 > 0 && sumPrev > 0 && sumPrev < sumCrt) {
            result++
            println("$it $v1 $v0 => $sumPrev < $sumCrt                      +1")
        } else {
            println("$it $v1 $v0 => $sumPrev > $sumCrt")
        }
        if (v0 > 0 && v1 > 0) sumPrev = sumCrt
        v0 = v1
        v1 = crt
    }
    return result
}

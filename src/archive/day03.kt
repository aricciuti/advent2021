
fun day03_1(): Int {
    val gammaRate0 = Array(12){0}; val gammaRate1 =  Array(12){0}
    val epsilonRate0 = Array(12){0}; val epsilonRate1 =  Array(12){0}
    val size = 5
    linesInFile("resources/test.txt").forEach {line ->
        line.toCharArray().forEachIndexed { index, c ->
            gammaRate0[index] += (if (c == '0') 1 else 0)
            gammaRate1[index] += (if (c == '1') 1 else 0)
            epsilonRate0[index] += (if (c == '0') 0 else 1)
            epsilonRate1[index] += (if (c == '1') 0 else 1)
        }
        println("$line : ${intArrayToString(gammaRate0)} / ${intArrayToString(gammaRate1)} - ${intArrayToString(epsilonRate0)} / ${intArrayToString(epsilonRate1)}")
    }

    var gammaRate = ""; var epsilonRate = ""
    for (i in 0 until size) {
        gammaRate += if (gammaRate0[i] > gammaRate1 [i]) '0' else '1'
        epsilonRate += if (epsilonRate0[i] > epsilonRate1 [i]) '0' else '1'
        println("$gammaRate - $epsilonRate")
    }

    val gammaRateD = convertBinaryToDecimal(gammaRate)
    val epsilonRateD = convertBinaryToDecimal(epsilonRate)
    println("$epsilonRateD * $epsilonRateD")


    return gammaRateD * epsilonRateD
}

fun day03_2(): Int {
    val ratingSize = 12
    var lines = linesInFile("resources/day03.txt")
    for (index in 0 until ratingSize) {
        var gammaRate0 = 0; var gammaRate1 =  0
        lines.forEach {line ->
            gammaRate0 += (if (line[index] == '0') 1 else 0)
            gammaRate1 += (if (line[index] == '1') 1 else 0)
        }
        lines = lines.filter { it[index] == (if (gammaRate0 > gammaRate1) '0' else '1') }
        println("new Lines : ${lines}")
        if (lines.size == 1) break
    }
    val oxygenRate = convertBinaryToDecimal(lines[0])
    println("oxygenRate = $oxygenRate")


    lines = linesInFile("resources/day03.txt")
    for (index in 0 until ratingSize) {
        var gammaRate0 = 0; var gammaRate1 =  0
        lines.forEach {line ->
            gammaRate0 += (if (line[index] == '0') 1 else 0)
            gammaRate1 += (if (line[index] == '1') 1 else 0)
        }
        lines = lines.filter { it[index] == (if (gammaRate0 > gammaRate1) '1' else '0') }
        println("new Lines : ${lines}")
        if (lines.size == 1) break
    }
    val co2Rate = convertBinaryToDecimal(lines[0])
    println("co2Rate = $co2Rate")

    return oxygenRate * co2Rate
}

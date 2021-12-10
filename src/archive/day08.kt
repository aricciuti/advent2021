
fun day08_1(): Int {
    val outSignals = mutableListOf<List<String>>()

    linesInFile("resources/day08.txt").forEach { line ->
        outSignals.add(line.split(" | ")[1].split(" "))
    }
    return outSignals.sumOf { it.filter { it.length == 2 ||  it.length == 3 ||  it.length == 4 ||  it.length == 7}.count() }
}

fun day08_2(): Int {
    val inSignals = mutableListOf<List<String>>()
    val outSignals = mutableListOf<List<String>>()

    linesInFile("resources/day08.txt").forEach { line ->
        inSignals.add(line.split(" | ")[0].split(" ").map {String(it.toCharArray().sortedArray())})
        outSignals.add(line.split(" | ")[1].split(" ").map {String(it.toCharArray().sortedArray())})
    }
    val letterPatterns = mapOf(
        0 to "abcefg",  //        6
        1 to "cf",      // 2
        2 to "acdeg",   //    5
        3 to "acdfg",   //    5
        4 to "bcdf",    // 4
        5 to "abdfg",   //    5
        6 to "abdefg",  //       6
        7 to "acf",     // 3
        8 to "abcdefg", // 7
        9 to "abcdfg"   //       6
    )

    var output = 0

    (0 until inSignals.size).forEach { pos ->
        val inDigits = inSignals[pos]
        var the0s = inDigits.filter { it.length == 6 }
        val the1 = inDigits.firstOrNull { it.length == 2 }
        var the2s = inDigits.filter { it.length == 5 }
        var the3s = inDigits.filter { it.length == 5 }
        val the4 = inDigits.firstOrNull { it.length == 4 }
        var the5s = inDigits.filter { it.length == 5 }
        var the6s = inDigits.filter { it.length == 6 }
        val the7 = inDigits.firstOrNull { it.length == 3 }
        val the8 = inDigits.firstOrNull { it.length == 7 }
        var the9s = inDigits.filter { it.length == 6 }

        // include 1
        the0s = the0s.filter { the1 == null || containsAllChars(it, the1) }
        the3s = the3s.filter { the1 == null || containsAllChars(it, the1) }
        the9s = the9s.filter { the1 == null || containsAllChars(it, the1) }
        // not include 1
        the2s = the2s.filter { the1 == null || !containsAllChars(it, the1) }
        the5s = the5s.filter { the1 == null || !containsAllChars(it, the1)}
        the6s = the6s.filter { the1 == null || !containsAllChars(it, the1) }

        // include 4
        the9s = the9s.filter { the4 == null || containsAllChars(it, the4) }
        // not include 4
        the0s = the0s.filter { the4 == null || !containsAllChars(it, the4) }
        the2s = the2s.filter { the4 == null || !containsAllChars(it, the4) }
        the3s = the3s.filter { the4 == null || !containsAllChars(it, the4) }
        the5s = the5s.filter { the4 == null || !containsAllChars(it, the4) }
        the6s = the6s.filter { the4 == null || !containsAllChars(it, the4) }

        // include 7
        the0s = the0s.filter { the7 == null || containsAllChars(it, the7) }
        the3s = the3s.filter { the7 == null || containsAllChars(it, the7) }
        the9s = the9s.filter { the7 == null || containsAllChars(it, the7) }
        // not include 7
        the2s = the2s.filter { the7 == null || !containsAllChars(it, the7) }
        the5s = the5s.filter { the7 == null || !containsAllChars(it, the7) }
        the6s = the6s.filter { the7 == null || !containsAllChars(it, the7) }

        // 6 include 5
        the5s = the5s.filter { the6s.any { t6 -> containsAllChars(t6, it) } }
        // 6 not include 2
        the2s = the2s.filter { the6s.any { t6 -> !containsAllChars(t6, it) } }

        // filter length 5: 2, 3, 5
        if (the2s.size == 1) {
            the3s = the3s.filter { it != the2s[0] }; the5s = the5s.filter { it != the2s[0] }
        }
        if (the3s.size == 1) {
            the2s = the2s.filter { it != the3s[0] }; the5s = the5s.filter { it != the3s[0] }
        }
        if (the5s.size == 1) {
            the2s = the2s.filter { it != the5s[0] }; the3s = the3s.filter { it != the5s[0] }
        }

        // filter length 6: 0, 6, 9
        if (the0s.size == 1) {
            the6s = the6s.filter { it != the0s[0] }; the9s = the9s.filter { it != the0s[0] }
        }
        if (the6s.size == 1) {
            the0s = the0s.filter { it != the6s[0] }; the9s = the9s.filter { it != the6s[0] }
        }
        if (the9s.size == 1) {
            the0s = the0s.filter { it != the9s[0] }; the6s = the6s.filter { it != the9s[0] }
        }

        if (the0s.size > 1 ) println("$pos -> 0: $the0s  ")
        if (the2s.size > 1 ) println("$pos -> 2: $the2s  ")
        if (the3s.size > 1 ) println("$pos -> 3: $the3s  ")
        if (the5s.size > 1 ) println("$pos -> 5: $the5s  ")
        if (the6s.size > 1 ) println("$pos -> 6: $the6s  ")
        if (the9s.size > 1 ) println("$pos -> 9: $the9s  ")

        val res =  outSignals[pos].map {
            if( the0s.contains(it) ) "0"
            else if ( the1 == it) "1"
            else if ( the2s.contains(it)) "2"
            else if ( the3s.contains(it)) "3"
            else if ( the4 == it) "4"
            else if ( the5s.contains(it)) "5"
            else if ( the6s.contains(it)) "6"
            else if ( the7 == it) "7"
            else if ( the8 == it) "8"
            else if ( the9s.contains(it)) "9"
            else {
                println("$pos !!!!!!!!!!!!!!!!!!!! $it")
                "x"
            }
        }.joinToString { it }.replace(", ", "")

        output += res.toInt()

        println("$pos, $output  ")
    }
    return output
}

private fun containsAllChars(source: String, the1: String) : Boolean {
    var containsAll = true
    (0 until the1.length).forEach {
        if (!source.contains(the1[it])) containsAll = false
    }
    return containsAll
}



import java.math.BigDecimal

data class Fish(var timer: Int)

fun day06_1(): Int {
    val fishes = linesInFile("resources/day06.txt")[0].split(",").map { Fish(it.toInt()) }.toMutableList()
    var day = 0
    while (day < 80) {
        val fishesIterator = fishes.listIterator()
        day ++
        while (fishesIterator.hasNext()) {
            val fish = fishesIterator.next()
            if (fish.timer == 0) {
                fish.timer = 6
                fishesIterator.add(Fish(8))
            }
            else {
                if (fish.timer > 0) fish.timer --
            }

        }
        println("$day : ${fishes.map { it.toString() }}")
    }
    return fishes.size
}

fun oneFish_128(aFish: String): String {
    var fishes = aFish
    var day = 0
    while (day < 128) {
        val fishesIterator = fishes.iterator()
        day ++
        var newFishes = ""
        while (fishesIterator.hasNext()) {
            val fish = fishesIterator.next()
            if (fish.equals('0')) {
                newFishes += "68"
            }
            else {
                val fistTimer = fish.toString().toInt()
                if (fistTimer > 0) {
                    newFishes += (fistTimer - 1).toString()
                }
            }
        }
        fishes = newFishes
    }
    return fishes
}

fun oneFish_256(timer: Int, oneFishMap_128: Map<Int, String> ): BigDecimal {
    var fishCount = BigDecimal.ZERO
    oneFishMap_128.get(timer)?.forEach { fish ->
        fishCount = fishCount.add(oneFishMap_128.get(fish.toString().toInt())!!.length.toBigDecimal())
    }
    return fishCount
}

fun day06_2(): BigDecimal {
    val oneFishMap_128 = mapOf(
        0 to oneFish_128("0"),
        1 to oneFish_128("1"),
        2 to oneFish_128("2"),
        3 to oneFish_128("3"),
        4 to oneFish_128("4"),
        5 to oneFish_128("5"),
        6 to oneFish_128("6"),
        7 to oneFish_128("7"),
        8 to oneFish_128("8")
    )
    val oneFishMap_256 = mapOf(
        0 to oneFish_256(0, oneFishMap_128),
        1 to oneFish_256(1, oneFishMap_128),
        2 to oneFish_256(2, oneFishMap_128),
        3 to oneFish_256(3, oneFishMap_128),
        4 to oneFish_256(4, oneFishMap_128),
        5 to oneFish_256(5, oneFishMap_128),
        6 to oneFish_256(6, oneFishMap_128),
        7 to oneFish_256(7, oneFishMap_128),
        8 to oneFish_256(8, oneFishMap_128)
    )

    var fishCount = BigDecimal.ZERO
    linesInFile("resources/day06.txt")[0].split(",").map { it.toInt() }
        .forEach { fishCount = fishCount.add(oneFishMap_256.get(it)) }
    return fishCount
}

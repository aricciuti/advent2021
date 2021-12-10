
data class Cell(val x: Int, val y: Int, val value: Int, var marked: Boolean = false )

fun day09_1(): Int {
    val cells = mutableListOf<Cell>()
    var sizeX = 0
    var sizeY = 0
    linesInFile("resources/day09.txt").forEachIndexed{ x, line ->
        line.forEachIndexed { y, i ->
            cells.add(Cell(x, y, i.toString().toInt()))
            if (sizeX < x) sizeX = x
            if (sizeY < y) sizeY = y
        }
    }
    val bassins = mutableListOf<Int>()
    while (true) {
        var found = false
        cells.forEach { cell ->
            if (! cell.marked && getNeigbours(cells, sizeX, sizeY, cell).filter { cell.value >= it.value }.size == 0) {
                val bassin = markBassin(cells, sizeX, sizeY,cell)
                println("*************************************** cell $cell -> bassin: ${bassin}")
                bassins.add(bassin)
                found = true
            }
        }
        if (!found) break
    }

    return bassins.sortedDescending().filterIndexed { index, i -> index < 3 }.reduce { acc, i ->  acc * i }
}

fun markBassin(cells: List<Cell>, sizeX: Int, sizeY: Int, clusterCell: Cell): Int {
    if (clusterCell.marked ) {
        return 0
    }
    clusterCell.marked = true
    println(" $clusterCell")
    return 1 + getNeigbours(cells, sizeX, sizeY, clusterCell)
        .filter { !it.marked && it.value < 9 }.sumOf { markBassin(cells, sizeX, sizeY, it) }
}

fun getNeigbours(cells: List<Cell>, sizeX: Int, sizeY: Int, aCell: Cell): List<Cell> {
    val neighbours = mutableListOf<Cell>()
    if (aCell.x > 0) neighbours.add(cells.filter { it.x == aCell.x - 1 && it.y == aCell.y }.first())
    if (aCell.x < sizeX) neighbours.add(cells.filter { it.x == aCell.x + 1 && it.y == aCell.y }.first())
    if (aCell.y > 0) neighbours.add(cells.filter { it.x == aCell.x && it.y == aCell.y - 1 }.first())
    if (aCell.y < sizeY) neighbours.add(cells.filter { it.x == aCell.x && it.y == aCell.y + 1 }.first())
    return neighbours
}


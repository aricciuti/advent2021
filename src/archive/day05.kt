
data class Coord(var x: Int, var y: Int)
data class GridCoord(val coord: Coord, var nbOverlapp: Int)
data class Line(val start: Coord, val end: Coord)

fun day05_1(): Int {
    var gridXSize = 0
    var gridYSize = 0
    val lines = linesInFile("resources/day05.txt").map {line ->
        val coordsStr = line.split(" -> ")
        val line = Line(Coord(coordsStr[0].split(",")[0].toInt(), coordsStr[0].split(",")[1].toInt()),
            Coord(coordsStr[1].split(",")[0].toInt(), coordsStr[1].split(",")[1].toInt()))
        if (gridXSize < line.start.x) gridXSize = line.start.x
        if (gridXSize < line.end.x) gridXSize = line.end.x
        if (gridYSize < line.start.y) gridYSize = line.start.y
        if (gridYSize < line.end.y) gridYSize = line.end.y
        line
    }

    val grid = Array(gridXSize + 1) {Array(gridYSize + 1){ GridCoord(Coord(0, 0), 0) }}
    (0 .. gridXSize).forEach { x ->
        (0 .. gridYSize).forEach { y ->
            grid[x][y].coord.x = x
            grid[x][y].coord.y = y
        }
    }

    lines.forEach { line ->
        if (line.start.x == line.end.x) {
            var start : Int = line.start.y; var end : Int = line.end.y
            if (start > end) {val z = start ; start = end ; end = z}
            for (pos in start until end + 1) {
                grid[line.start.x][pos].nbOverlapp ++
            }
        } else if (line.start.y == line.end.y) {
            var start : Int = line.start.x; var end : Int = line.end.x
            if (start > end) {val z = start ; start = end ; end = z}
            for (pos in start until end + 1) {
                grid[pos][line.start.y].nbOverlapp ++
            }
        } else {
            // 02
            var start : Coord = line.start; var end : Coord = line.end
            if (line.start.x > line.end.x) {
                val z = start; start = end; end = z
            }
            val coefY = if (start.y > end.y) -1 else 1
            var y = start.y
            for (x in start.x until end.x + 1) {
                grid[x][y].nbOverlapp ++
                y += coefY
                if (y == end.y + coefY) break
            }
        }
    }
    var count = 0
    (0 .. gridXSize).forEach { x ->
        (0 .. gridYSize).forEach { y ->
            if (grid[x][y].nbOverlapp > 1) count++
        }
    }

    return count
}

import java.io.File
import java.util.*
import javax.imageio.ImageIO
import kotlin.math.pow

private fun Int.toRGB() = intArrayOf(this ushr 16 and 0xff, this ushr 8 and 0xff, this and 0xff)

data class PixelEnergy(
    var pixelIn: Pair<Int, Int>, // energy Table
    val pixelOut: Pair<Int, Int>, // bitmap
    var pixelParent: Pair<Int, Int>? = null, // Djikstra path
    var e: Double? = null, // energy
    var sum: Double? = null, // Djikstra path
)

class SeamCarving(inputFileName: String?, outputFileName: String?, var seamWidth: Int?, var seamHeight: Int?) {
    var bufferedImage = ImageIO.read(File(inputFileName!!))
    var width = bufferedImage.width
    var height = bufferedImage.height
    var energy = MutableList(width) { x -> MutableList(height) { y -> PixelEnergy(x to y, x to y) } }

    init {
        initEnergy()
        repeat(2) {
            repeat(width - (seamWidth ?: width)) {
                path().run {
                    forEach { for (x in it.first until width - 1) energy[x][it.second] = energy[x + 1][it.second] }
                    for (y in 0 until height)
                        for (x in (this[0].first - y).coerceAtLeast(0)..(this[0].first + y).coerceAtMost(width - 1)) {
                            energy[x][y].pixelIn = x to y
                            energy[x][y].sum = null
                        }
                    width--
                }
            }
            energy = MutableList(height) { x ->
                MutableList(width) { y ->
                    PixelEnergy(x to y, energy[y][x].pixelOut, e = energy[y][x].e)
                }
            }
            width.let { width = height; height = it }
            seamWidth.let { seamWidth = seamHeight; seamHeight = it }
        }
        for (x in 0 until width)
            for (y in 0 until height)
                bufferedImage.setRGB(x, y, energy[x][y].pixelOut.let { bufferedImage.getRGB(it.first, it.second) })
        bufferedImage = bufferedImage.getSubimage(0, 0, width, height)
        ImageIO.write(bufferedImage, "png", File(outputFileName!!))
    }

    fun path(): List<Pair<Int, Int>> {
        val queue: PriorityQueue<PixelEnergy> = PriorityQueue(compareBy { it.sum!! })
        val finalPixel = PixelEnergy(0 to -1, 0 to -1, e = 0.0, sum = null)
        for (x in 0 until width) {
            energy[x][0].sum = energy[x][0].e
            queue.add(energy[x][0])
        }
        while (queue.isNotEmpty()) queue.remove().run {
            if (finalPixel.sum == null || sum!! < finalPixel.sum!!)
                if (pixelIn.second < height - 1)
                    for (i in (pixelIn.first - 1).coerceAtLeast(0)..(pixelIn.first + 1).coerceAtMost(width - 1))
                        energy[i][pixelIn.second + 1].let {
                            if (it.sum == null || it.sum!! > sum!! + it.e!!) {
                                if (it.sum == null) {
                                    it.sum = sum!! + it.e!!
                                    it.pixelParent = pixelIn
                                    queue.add(it)
                                } else {
                                    it.sum = sum!! + it.e!!
                                    it.pixelParent = pixelIn
                                }
                            }
                        }
                else finalPixel.let {
                    if (it.sum == null || it.sum!! > sum!! + it.e!!) {
                        if (it.sum == null) {
                            it.sum = sum!! + it.e!!
                            it.pixelParent = pixelIn
                            queue.add(it)
                        } else {
                            it.sum = sum!! + it.e!!
                            it.pixelParent = pixelIn
                        }
                    }
                }
        }

        var pixel: Pair<Int, Int>? = finalPixel.pixelParent
        val path = mutableListOf<Pair<Int, Int>>()
        while (pixel != null) {
            path.add(pixel)
            pixel = energy[pixel.first][pixel.second].pixelParent
        }
        return path.reversed()
    }

    private fun gradient(x1: Int, y1: Int, x2: Int, y2: Int): Int {
        val (r1, g1, b1) = bufferedImage.getRGB(energy[x1][y1].pixelOut.first, energy[x1][y1].pixelOut.second).toRGB()
        val (r2, g2, b2) = bufferedImage.getRGB(energy[x2][y2].pixelOut.first, energy[x2][y2].pixelOut.second).toRGB()
        return (r1 - r2) * (r1 - r2) + (g1 - g2) * (g1 - g2) + (b1 - b2) * (b1 - b2)
    }

    private fun initEnergy() {
        (1..height - 2).forEach { y ->
            (1..width - 2).forEach { x ->
                // draw all without borders
                energy[x][y].e = (gradient(x - 1, y, x + 1, y) + gradient(x, y - 1, x, y + 1)).toDouble().pow(0.5)
            }
            // left border without left/up and left/down corner
            energy[0][y].e = (gradient(0, y, 2, y) + gradient(0, y - 1, 0, y + 1)).toDouble().pow(0.5)

            // right border without right/up and right/down corner
            energy[width - 1][y].e =
                (gradient(width - 1, y, width - 3, y) + gradient(width - 1, y - 1, width - 1, y + 1)).toDouble()
                    .pow(0.5)
        }
        (1 until width - 1).forEach { x ->
            // top border
            energy[x][0].e = (gradient(x - 1, 0, x + 1, 0) + gradient(x, 0, x, 2)).toDouble().pow(0.5)

            // bottom border
            energy[x][height - 1].e =
                (gradient(x - 1, height - 1, x + 1, height - 1) + gradient(x, height - 1, x, height - 3)).toDouble()
                    .pow(0.5)
        }

        // corner left/up
        energy[0][0].e = (gradient(0, 0, 2, 0) + gradient(0, 0, 0, 2)).toDouble().pow(0.5)

        // corner right/up
        energy[width - 1][0].e =
            (gradient(width - 1, 0, width - 3, 0) + gradient(width - 1, 0, width - 1, 2)).toDouble().pow(0.5)

        // corner left/down
        energy[0][height - 1].e =
            (gradient(0, height - 1, 2, height - 1) + gradient(0, height - 1, 0, height - 3)).toDouble().pow(0.5)

        // corner right/down
        energy[width - 1][height - 1].e = (gradient(width - 1, height - 1, width - 3, height - 1) +
                gradient(width - 1, height - 1, width - 1, height - 3)).toDouble().pow(0.5)
    }
}

fun main(args: Array<String>) {
    val inputFileName = Regex("(?:-in )(\\S+)").find(args.joinToString(" "))?.groupValues?.get(1)
    val outputFileName = Regex("(?:-out )(\\S+)").find(args.joinToString(" "))?.groupValues?.get(1)
    val seamWidth = Regex("(?:-width )(\\S+)").find(args.joinToString(" "))?.groupValues?.get(1)?.toInt()
    val seamHeight = Regex("(?:-height )(\\S+)").find(args.joinToString(" "))?.groupValues?.get(1)?.toInt()
    SeamCarving(inputFileName, outputFileName, seamWidth, seamHeight)
}

@file:Suppress("unused")

package co.csadev.adventOfCode

import com.sksamuel.scrimage.ImmutableImage
import com.sksamuel.scrimage.color.Color
import com.sksamuel.scrimage.nio.ImmutableImageLoader
import com.sksamuel.scrimage.nio.StreamingGifWriter
import com.sksamuel.scrimage.nio.StreamingGifWriter.GifStream
import java.awt.image.BufferedImage
import java.io.File
import java.time.Duration
import java.util.SortedMap

private var writeCount = 0

/**
 * Creates a GifStream for use in visualization of problems
 */
fun createVisualization(
    path: String = "./visualization_output.gif",
    frameDelay: Duration = Duration.ofMillis(5)
): GifStream = StreamingGifWriter(frameDelay, false).run {
    writeCount = 0
    File(path).run {
        createNewFile()
        prepareStream(this, BufferedImage.TYPE_INT_ARGB)
    }
}

/**
 * Close and finalize the visualization GIF
 */
fun GifStream.finalizeVisualization() {
    println("Finalizing Visualization")
    close()
    println("Visualization Complete")
}

/**
 * Adds a visualized frame to a GifStream
 */
fun GifStream.visualizeFrame(imageMap: SortedMap<Point2D, Color>): Unit = ImmutableImage.create(
    imageMap.maxOf { it.key.x } + 1,
    imageMap.maxOf { it.key.y } + 1,
    imageMap.map { it.key.toPixel(it.value.toRGB().toARGBInt()) }.toTypedArray()
).run {
    writeCount++
    // output(PngWriter.NoCompression, "./visualization_frame_$writeCount.png")
    println("Visualizing Frame: $writeCount")
    writeFrame(this)
}

fun <T> Map<Point2D, T>.getCurrentState(
    path: List<Point2D>,
    queue: List<Point2D> = emptyList(),
    visualize: (current: Point2D, best: List<Point2D>, queue: List<Point2D>) -> Color,
    expandBy: Int = 5
): SortedMap<Point2D, Color> {
    return map { it.key to visualize(it.key, path, queue) }.map { (p, c) ->
        (1..expandBy).flatMap { y ->
            (1..expandBy).map { x ->
                val point = Point2D((p.x * expandBy) + x - 1, (p.y * expandBy) + y - 1)
                // println("Expand point: $p -> $point")
                point to c
            }
        }
    }.flatten().toMap().toSortedMap()
}

/**
 * Uses data taken from https://matplotlib.org/cmocean/
 *
 * Horizontal Offset is always [96-476]
 * Vertical Offset is 11 + 43 * index
 */
enum class GradientNames {
    THERMAL,
    HALINE,
    SOLAR,
    ICE,
    GRAY,
    OXY,
    DEEP,
    DENSE,
    ALGAE,
    MATTER,
    TURBID,
    SPEED,
    AMP,
    TEMPO,
    RAIN,
    PHASE,
    TOPO,
    BALANCE,
    DELTA,
    CURL,
    DIFF,
    TARN;

    private val startX = 96
    private val endX = 476
    private val startY = 11
    private val yOffset = 43

    fun getRange(count: Int): List<Point2D> {
        val y = startY + (yOffset * ordinal)
        val xOffset = (endX - startX) / count
        return (0 until count).map { Point2D(startX + (it * xOffset), y) }
    }
}

/**
 * Gets a list of color shades
 */
fun colorShades(count: Int, base: GradientNames = GradientNames.ALGAE): List<Color> =
    ImmutableImageLoader.create().fromFile(Resources.resourceAsFile("GradiantsAll.webp")).run {
        base.getRange(count).map { color(it.x, it.y) }
    }

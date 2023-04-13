package org.thoughtcrime.securesms.components.spoiler

import android.graphics.Canvas
import android.graphics.Paint
import android.text.Layout
import androidx.annotation.ColorInt
import androidx.annotation.Px
import org.thoughtcrime.securesms.util.LayoutUtil

/**
 * Handles drawing the spoiler sparkles for a TextView.
 */
class SpoilerRenderer(
  private val spoilerDrawable: SpoilerDrawable,
  private val renderForComposing: Boolean,
  @Px private val padding: Int,
  @ColorInt composeBackgroundColor: Int
) {

  private val lineTopCache = HashMap<Int, Int>()
  private val lineBottomCache = HashMap<Int, Int>()
  private val paint = Paint().apply { color = composeBackgroundColor }

  fun draw(
    canvas: Canvas,
    layout: Layout,
    startLine: Int,
    endLine: Int,
    startOffset: Int,
<<<<<<< HEAD
<<<<<<< HEAD
    endOffset: Int
  ) {
    if (startLine == endLine) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    endOffset: Int,
    spoilerDrawables: List<SpoilerDrawable>
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    endOffset: Int,
    spoilerDrawables: List<SpoilerDrawable>
=======
    endOffset: Int
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  )

  protected fun getLineTop(layout: Layout, line: Int): Int {
    return LayoutUtil.getLineTopWithoutPadding(layout, line)
  }

  protected fun getLineBottom(layout: Layout, line: Int): Int {
    return LayoutUtil.getLineBottomWithoutPadding(layout, line)
  }

  protected inline fun MutableMap<Int, Int>.get(line: Int, layout: Layout, default: () -> Int): Int {
    return getOrPut(line * 31 + layout.hashCode() * 31, default)
  }

  class SingleLineSpoilerRenderer(private val spoilerDrawable: SpoilerDrawable) : SpoilerRenderer() {
    private val lineTopCache = HashMap<Int, Int>()
    private val lineBottomCache = HashMap<Int, Int>()

    override fun draw(
      canvas: Canvas,
      layout: Layout,
      startLine: Int,
      endLine: Int,
      startOffset: Int,
      endOffset: Int
    ) {
=======
    endOffset: Int
  )

  protected fun getLineTop(layout: Layout, line: Int): Int {
    return LayoutUtil.getLineTopWithoutPadding(layout, line)
  }

  protected fun getLineBottom(layout: Layout, line: Int): Int {
    return LayoutUtil.getLineBottomWithoutPadding(layout, line)
  }

  protected inline fun MutableMap<Int, Int>.get(line: Int, layout: Layout, default: () -> Int): Int {
    return getOrPut(line * 31 + layout.hashCode() * 31, default)
  }

  class SingleLineSpoilerRenderer(private val spoilerDrawable: SpoilerDrawable) : SpoilerRenderer() {
    private val lineTopCache = HashMap<Int, Int>()
    private val lineBottomCache = HashMap<Int, Int>()

    override fun draw(
      canvas: Canvas,
      layout: Layout,
      startLine: Int,
      endLine: Int,
      startOffset: Int,
      endOffset: Int
    ) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      val lineTop = lineTopCache.get(startLine, layout) { getLineTop(layout, startLine) }
      val lineBottom = lineBottomCache.get(startLine, layout) { getLineBottom(layout, startLine) }
      val left = startOffset.coerceAtMost(endOffset)
      val right = startOffset.coerceAtLeast(endOffset)

<<<<<<< HEAD
<<<<<<< HEAD
      if (renderForComposing) {
        canvas.drawComposeBackground(left, lineTop, right, lineBottom)
      } else {
        spoilerDrawable.setBounds(left, lineTop, right, lineBottom)
        spoilerDrawable.draw(canvas)
      }

      return
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      spoilerDrawables[0].setBounds(left, lineTop, right, lineBottom)
      spoilerDrawables[0].draw(canvas)
=======
      spoilerDrawable.setBounds(left, lineTop, right, lineBottom)
      spoilerDrawable.draw(canvas)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      spoilerDrawables[0].setBounds(left, lineTop, right, lineBottom)
      spoilerDrawables[0].draw(canvas)
=======
      spoilerDrawable.setBounds(left, lineTop, right, lineBottom)
      spoilerDrawable.draw(canvas)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    }

    val paragraphDirection = layout.getParagraphDirection(startLine)

    val lineEndOffset: Float = if (paragraphDirection == Layout.DIR_RIGHT_TO_LEFT) layout.getLineLeft(startLine) else layout.getLineRight(startLine)
    var lineBottom = lineBottomCache.get(startLine, layout) { getLineBottom(layout, startLine) }
    var lineTop = lineTopCache.get(startLine, layout) { getLineTop(layout, startLine) }
    drawPartialLine(canvas, startOffset, lineTop, lineEndOffset.toInt(), lineBottom)

    for (line in startLine + 1 until endLine) {
      val left: Int = layout.getLineLeft(line).toInt()
      val right: Int = layout.getLineRight(line).toInt()

      lineTop = getLineTop(layout, line)
      lineBottom = getLineBottom(layout, line)

      if (renderForComposing) {
        canvas.drawComposeBackground(left, lineTop, right, lineBottom)
      } else {
        spoilerDrawable.setBounds(left, lineTop, right, lineBottom)
        spoilerDrawable.draw(canvas)
      }
    }

    val lineStartOffset: Float = if (paragraphDirection == Layout.DIR_RIGHT_TO_LEFT) layout.getLineRight(startLine) else layout.getLineLeft(startLine)
    lineBottom = lineBottomCache.get(endLine, layout) { getLineBottom(layout, endLine) }
    lineTop = lineTopCache.get(endLine, layout) { getLineTop(layout, endLine) }
    drawPartialLine(canvas, lineStartOffset.toInt(), lineTop, endOffset, lineBottom)
  }

<<<<<<< HEAD
<<<<<<< HEAD
  private fun drawPartialLine(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int) {
    if (renderForComposing) {
      canvas.drawComposeBackground(start, top, end, bottom)
      return
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  class MultiLineSpoilerRenderer : SpoilerRenderer() {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  class MultiLineSpoilerRenderer : SpoilerRenderer() {
=======
  class MultiLineSpoilerRenderer(private val spoilerDrawable: SpoilerDrawable) : SpoilerRenderer() {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    private val lineTopCache = HashMap<Int, Int>()
    private val lineBottomCache = HashMap<Int, Int>()

    override fun draw(
      canvas: Canvas,
      layout: Layout,
      startLine: Int,
      endLine: Int,
      startOffset: Int,
      endOffset: Int
    ) {
      val paragraphDirection = layout.getParagraphDirection(startLine)

      val lineEndOffset: Float = if (paragraphDirection == Layout.DIR_RIGHT_TO_LEFT) layout.getLineLeft(startLine) else layout.getLineRight(startLine)
      var lineBottom = lineBottomCache.get(startLine, layout) { getLineBottom(layout, startLine) }
      var lineTop = lineTopCache.get(startLine, layout) { getLineTop(layout, startLine) }
      drawStart(canvas, startOffset, lineTop, lineEndOffset.toInt(), lineBottom)

      for (line in startLine + 1 until endLine) {
        val left: Int = layout.getLineLeft(line).toInt()
        val right: Int = layout.getLineRight(line).toInt()

        lineTop = getLineTop(layout, line)
        lineBottom = getLineBottom(layout, line)

        spoilerDrawable.setBounds(left, lineTop, right, lineBottom)
        spoilerDrawable.draw(canvas)
      }

      val lineStartOffset: Float = if (paragraphDirection == Layout.DIR_RIGHT_TO_LEFT) layout.getLineRight(startLine) else layout.getLineLeft(startLine)
      lineBottom = lineBottomCache.get(endLine, layout) { getLineBottom(layout, endLine) }
      lineTop = lineTopCache.get(endLine, layout) { getLineTop(layout, endLine) }
<<<<<<< HEAD
      drawEnd(canvas, lineStartOffset.toInt(), lineTop, endOffset, lineBottom, spoilerDrawables)
=======
  class MultiLineSpoilerRenderer(private val spoilerDrawable: SpoilerDrawable) : SpoilerRenderer() {
    private val lineTopCache = HashMap<Int, Int>()
    private val lineBottomCache = HashMap<Int, Int>()

    override fun draw(
      canvas: Canvas,
      layout: Layout,
      startLine: Int,
      endLine: Int,
      startOffset: Int,
      endOffset: Int
    ) {
      val paragraphDirection = layout.getParagraphDirection(startLine)

      val lineEndOffset: Float = if (paragraphDirection == Layout.DIR_RIGHT_TO_LEFT) layout.getLineLeft(startLine) else layout.getLineRight(startLine)
      var lineBottom = lineBottomCache.get(startLine, layout) { getLineBottom(layout, startLine) }
      var lineTop = lineTopCache.get(startLine, layout) { getLineTop(layout, startLine) }
      drawStart(canvas, startOffset, lineTop, lineEndOffset.toInt(), lineBottom)

      for (line in startLine + 1 until endLine) {
        val left: Int = layout.getLineLeft(line).toInt()
        val right: Int = layout.getLineRight(line).toInt()

        lineTop = getLineTop(layout, line)
        lineBottom = getLineBottom(layout, line)

        spoilerDrawable.setBounds(left, lineTop, right, lineBottom)
        spoilerDrawable.draw(canvas)
      }

      val lineStartOffset: Float = if (paragraphDirection == Layout.DIR_RIGHT_TO_LEFT) layout.getLineRight(startLine) else layout.getLineLeft(startLine)
      lineBottom = lineBottomCache.get(endLine, layout) { getLineBottom(layout, endLine) }
      lineTop = lineTopCache.get(endLine, layout) { getLineTop(layout, endLine) }
      drawEnd(canvas, lineStartOffset.toInt(), lineTop, endOffset, lineBottom)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      drawEnd(canvas, lineStartOffset.toInt(), lineTop, endOffset, lineBottom, spoilerDrawables)
=======
      drawEnd(canvas, lineStartOffset.toInt(), lineTop, endOffset, lineBottom)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    }

<<<<<<< HEAD
<<<<<<< HEAD
    if (start > end) {
      spoilerDrawable.setBounds(end, top, start, bottom)
    } else {
      spoilerDrawable.setBounds(start, top, end, bottom)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    private fun drawStart(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int, spoilerDrawables: List<SpoilerDrawable>) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    private fun drawStart(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int, spoilerDrawables: List<SpoilerDrawable>) {
=======
    private fun drawStart(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (start > end) {
        spoilerDrawable.setBounds(end, top, start, bottom)
      } else {
        spoilerDrawable.setBounds(start, top, end, bottom)
      }
<<<<<<< HEAD
=======
    private fun drawStart(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int) {
      if (start > end) {
        spoilerDrawable.setBounds(end, top, start, bottom)
      } else {
        spoilerDrawable.setBounds(start, top, end, bottom)
      }
      spoilerDrawable.draw(canvas)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
      spoilerDrawable.draw(canvas)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    }
    spoilerDrawable.draw(canvas)
  }

<<<<<<< HEAD
<<<<<<< HEAD
  private fun getLineTop(layout: Layout, line: Int): Int {
    return LayoutUtil.getLineTopWithoutPadding(layout, line)
  }

  private fun getLineBottom(layout: Layout, line: Int): Int {
    return LayoutUtil.getLineBottomWithoutPadding(layout, line)
  }

  private inline fun MutableMap<Int, Int>.get(line: Int, layout: Layout, default: () -> Int): Int {
    return getOrPut(line * 31 + layout.hashCode() * 31, default)
  }

  private fun Canvas.drawComposeBackground(start: Int, top: Int, end: Int, bottom: Int) {
    drawRoundRect(
      start.toFloat() - padding,
      top.toFloat() - padding,
      end.toFloat() + padding,
      bottom.toFloat(),
      padding.toFloat(),
      padding.toFloat(),
      paint
    )
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    private fun drawEnd(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int, spoilerDrawables: List<SpoilerDrawable>) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    private fun drawEnd(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int, spoilerDrawables: List<SpoilerDrawable>) {
=======
    private fun drawEnd(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (start > end) {
        spoilerDrawable.setBounds(end, top, start, bottom)
      } else {
        spoilerDrawable.setBounds(start, top, end, bottom)
      }
      spoilerDrawable.draw(canvas)
    }
=======
    private fun drawEnd(canvas: Canvas, start: Int, top: Int, end: Int, bottom: Int) {
      if (start > end) {
        spoilerDrawable.setBounds(end, top, start, bottom)
      } else {
        spoilerDrawable.setBounds(start, top, end, bottom)
      }
      spoilerDrawable.draw(canvas)
    }
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }
}

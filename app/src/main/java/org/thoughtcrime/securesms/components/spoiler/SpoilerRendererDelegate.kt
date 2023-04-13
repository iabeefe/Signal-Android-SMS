package org.thoughtcrime.securesms.components.spoiler

import android.animation.TimeAnimator
import android.graphics.Canvas
import android.text.Annotation
import android.text.Layout
import android.text.Spanned
<<<<<<< HEAD
<<<<<<< HEAD
import android.view.View
import android.view.View.OnAttachStateChangeListener
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
import android.view.View
import android.view.View.OnAttachStateChangeListener
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
import android.view.animation.LinearInterpolator
=======
import android.view.View
import android.view.View.OnAttachStateChangeListener
import android.view.animation.LinearInterpolator
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import org.signal.core.util.dp
import org.thoughtcrime.securesms.R
import org.thoughtcrime.securesms.components.spoiler.SpoilerAnnotation.SpoilerClickableSpan
import org.thoughtcrime.securesms.util.AccessibilityUtil
import org.thoughtcrime.securesms.util.getLifecycle

/**
 * Performs initial calculation on how to render spoilers and then delegates to actually drawing the spoiler sparkles.
 */
class SpoilerRendererDelegate @JvmOverloads constructor(
  private val view: TextView,
  private val renderForComposing: Boolean = false
) {

<<<<<<< HEAD
  private val renderer: SpoilerRenderer
  private val spoilerDrawable: SpoilerDrawable
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  private val single: SpoilerRenderer
  private val multi: SpoilerRenderer
<<<<<<< HEAD
=======
  private val single: SpoilerRenderer
  private val multi: SpoilerRenderer
  private val spoilerDrawable: SpoilerDrawable
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
  private val spoilerDrawable: SpoilerDrawable
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  private var animatorRunning = false
  private var textColor: Int
<<<<<<< HEAD
  private var canAnimate = false

||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)

<<<<<<< HEAD
  private var spoilerDrawablePool = mutableMapOf<Annotation, List<SpoilerDrawable>>()
  private var nextSpoilerDrawablePool = mutableMapOf<Annotation, List<SpoilerDrawable>>()

=======

>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  private var spoilerDrawablePool = mutableMapOf<Annotation, List<SpoilerDrawable>>()
  private var nextSpoilerDrawablePool = mutableMapOf<Annotation, List<SpoilerDrawable>>()

=======
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  private val cachedAnnotations = HashMap<Int, Map<Annotation, SpoilerClickableSpan?>>()
  private val cachedMeasurements = HashMap<Int, SpanMeasurements>()

<<<<<<< HEAD
  private var systemAnimationsEnabled = !AccessibilityUtil.areAnimationsDisabled(view.context)

  private val animator = TimeAnimator().apply {
    setTimeListener { _, _, _ ->
      SpoilerPaint.update()
      view.invalidate()
    }
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  private val animator = ValueAnimator.ofInt(0, 100).apply {
    duration = 1000
    interpolator = LinearInterpolator()
    addUpdateListener {
      SpoilerPaint.update()
      view.invalidate()
    }
    repeatCount = ValueAnimator.INFINITE
    repeatMode = ValueAnimator.REVERSE
=======
  private val animator = ValueAnimator.ofInt(0, 100).apply {
    duration = 1000
    interpolator = LinearInterpolator()
    addUpdateListener {
      SpoilerPaint.update()
      view.invalidate()
    }
    repeatCount = ValueAnimator.INFINITE
    repeatMode = ValueAnimator.REVERSE
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }

  init {
    textColor = view.textColors.defaultColor
<<<<<<< HEAD
<<<<<<< HEAD
    spoilerDrawable = SpoilerDrawable(textColor)
    renderer = SpoilerRenderer(
      spoilerDrawable = spoilerDrawable,
      renderForComposing = renderForComposing,
      padding = 2.dp,
      composeBackgroundColor = ContextCompat.getColor(view.context, R.color.signal_colorOnSurfaceVariant1)
    )

    view.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
      override fun onViewDetachedFromWindow(v: View) = stopAnimating()
      override fun onViewAttachedToWindow(v: View) {
        view.getLifecycle()?.addObserver(object : DefaultLifecycleObserver {
          override fun onResume(owner: LifecycleOwner) {
            canAnimate = true
            systemAnimationsEnabled = !AccessibilityUtil.areAnimationsDisabled(view.context)
            view.invalidate()
          }

          override fun onPause(owner: LifecycleOwner) {
            canAnimate = false
            stopAnimating()
          }
        })
      }
    })
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
    spoilerDrawable = SpoilerDrawable(textColor)
    single = SingleLineSpoilerRenderer(spoilerDrawable)
    multi = MultiLineSpoilerRenderer(spoilerDrawable)

    view.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
      override fun onViewDetachedFromWindow(v: View) = stopAnimating()
      override fun onViewAttachedToWindow(v: View) = Unit
    })
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
=======
    spoilerDrawable = SpoilerDrawable(textColor)
    single = SingleLineSpoilerRenderer(spoilerDrawable)
    multi = MultiLineSpoilerRenderer(spoilerDrawable)

    view.addOnAttachStateChangeListener(object : OnAttachStateChangeListener {
      override fun onViewDetachedFromWindow(v: View) = stopAnimating()
      override fun onViewAttachedToWindow(v: View) = Unit
    })
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
  }

  fun updateFromTextColor() {
    val color = view.textColors.defaultColor
    if (color != textColor) {
      spoilerDrawable.setTintColor(color)
      textColor = color
    }
  }

  fun draw(canvas: Canvas, text: Spanned, layout: Layout) {
    if (!canAnimate) {
      return
    }

    var hasSpoilersToRender = false
    val annotations: Map<Annotation, SpoilerClickableSpan?> = cachedAnnotations.getFromCache(text) { SpoilerAnnotation.getSpoilerAndClickAnnotations(text) }

    for ((annotation, clickSpan) in annotations.entries) {
      if (clickSpan?.spoilerRevealed == true) {
        continue
      }

      val spanStart: Int = text.getSpanStart(annotation)
      val spanEnd: Int = text.getSpanEnd(annotation)
      if (spanStart >= spanEnd) {
        continue
      }

      val measurements = cachedMeasurements.getFromCache(annotation.value, layout) {
        val startLine = layout.getLineForOffset(spanStart)
        val endLine = layout.getLineForOffset(spanEnd)
        SpanMeasurements(
          startLine = startLine,
          endLine = endLine,
          startOffset = (layout.getPrimaryHorizontal(spanStart) + -1 * layout.getParagraphDirection(startLine)).toInt(),
          endOffset = (layout.getPrimaryHorizontal(spanEnd) + layout.getParagraphDirection(endLine)).toInt()
        )
      }

<<<<<<< HEAD
      renderer.draw(canvas, layout, measurements.startLine, measurements.endLine, measurements.startOffset, measurements.endOffset)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      val renderer: SpoilerRenderer = if (measurements.startLine == measurements.endLine) single else multi

<<<<<<< HEAD
      renderer.draw(canvas, layout, measurements.startLine, measurements.endLine, measurements.startOffset, measurements.endOffset, drawables)
      nextSpoilerDrawablePool[annotation] = drawables
=======
      val renderer: SpoilerRenderer = if (measurements.startLine == measurements.endLine) single else multi

      renderer.draw(canvas, layout, measurements.startLine, measurements.endLine, measurements.startOffset, measurements.endOffset)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      renderer.draw(canvas, layout, measurements.startLine, measurements.endLine, measurements.startOffset, measurements.endOffset, drawables)
      nextSpoilerDrawablePool[annotation] = drawables
=======
      renderer.draw(canvas, layout, measurements.startLine, measurements.endLine, measurements.startOffset, measurements.endOffset)
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      hasSpoilersToRender = true
    }

<<<<<<< HEAD
<<<<<<< HEAD
    if (hasSpoilersToRender && systemAnimationsEnabled) {
||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    val temporaryPool = spoilerDrawablePool
    spoilerDrawablePool = nextSpoilerDrawablePool
    nextSpoilerDrawablePool = temporaryPool

||||||| parent of f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    val temporaryPool = spoilerDrawablePool
    spoilerDrawablePool = nextSpoilerDrawablePool
    nextSpoilerDrawablePool = temporaryPool

=======
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
    if (hasSpoilersToRender) {
=======
    if (hasSpoilersToRender) {
>>>>>>> f04b383b47 (Bumped to upstream version 6.18.0.0-JW.)
      if (!animatorRunning) {
        animator.start()
        animatorRunning = true
      }
    } else {
      stopAnimating()
    }
  }

  private fun stopAnimating() {
    animator.pause()
    animatorRunning = false
  }

  private inline fun <V> MutableMap<Int, V>.getFromCache(vararg keys: Any, default: () -> V): V {
    if (renderForComposing) {
      return default()
    }
    return getOrPut(keys.contentHashCode(), default)
  }

  private data class SpanMeasurements(
    val startLine: Int,
    val endLine: Int,
    val startOffset: Int,
    val endOffset: Int
  )
}

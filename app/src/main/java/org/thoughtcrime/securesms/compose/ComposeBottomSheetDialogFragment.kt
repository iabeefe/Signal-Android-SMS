package org.thoughtcrime.securesms.compose

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.ViewCompositionStrategy
import androidx.compose.ui.unit.dp
import org.signal.core.ui.theme.SignalTheme
import org.thoughtcrime.securesms.components.FixedRoundedCornerBottomSheetDialogFragment
import org.thoughtcrime.securesms.util.DynamicTheme

abstract class ComposeBottomSheetDialogFragment : FixedRoundedCornerBottomSheetDialogFragment() {

  protected open val forceDarkTheme = false

  override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
    return ComposeView(requireContext()).apply {
      setViewCompositionStrategy(ViewCompositionStrategy.DisposeOnViewTreeLifecycleDestroyed)
      setContent {
        SignalTheme(
          isDarkMode = forceDarkTheme || DynamicTheme.isDarkTheme(LocalContext.current)
        ) {
          Surface(
            shape = RoundedCornerShape(18.dp, 18.dp),
            color = SignalTheme.colors.colorSurface1,
            contentColor = MaterialTheme.colorScheme.onSurface
          ) {
            SheetContent()
          }
        }
      }
    }
  }

  @Composable
  abstract fun SheetContent()
<<<<<<< HEAD
||||||| parent of 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)

  /**
   * BottomSheet Handle, according to our design specs.
   * This can be placed in a column with the other page content like so:
   *
   * ```
   * Column(modifier = Modifier
   *   .fillMaxWidth()
   *   .wrapContentSize(Alignment.Center)
   * ) {
   *   Handle()
   *   Text("Hello!")
   * }
   * ```
   */
  @Composable
  protected fun Handle(modifier: Modifier = Modifier) {
    Box(
      modifier = modifier
        .size(width = 48.dp, height = 22.dp)
        .padding(vertical = 10.dp)
        .clip(RoundedCornerShape(1000.dp))
        .background(MaterialTheme.colorScheme.outline)
    )
  }
=======

  /**
   * BottomSheet Handle, according to our design specs.
   * This can be placed in a column with the other page content like so:
   *
   * ```
   * Column(modifier = Modifier
   *   .fillMaxWidth()
   *   .wrapContentSize(Alignment.Center)
   * ) {
   *   Handle()
   *   Text("Hello!")
   * }
   * ```
   */
  @Composable
  protected fun Handle(modifier: Modifier = Modifier) {
    Box(
      modifier = modifier
        .size(width = 48.dp, height = 22.dp)
        .padding(vertical = 10.dp)
        .clip(RoundedCornerShape(1000.dp))
        .background(MaterialTheme.colorScheme.outline)
    )
  }
>>>>>>> 4783e1bcc9 (Bumped to upstream version 6.17.0.0-JW.)
}

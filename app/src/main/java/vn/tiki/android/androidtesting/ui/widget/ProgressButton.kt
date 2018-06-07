package vn.tiki.android.androidtesting.ui.widget

import android.content.Context
import android.graphics.Canvas
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.support.v4.widget.CircularProgressDrawable
import android.util.AttributeSet
import android.widget.Button
import androidx.core.content.dip
import androidx.core.os.repeat
import vn.tiki.android.androidtesting.R

class ProgressButton(ctx: Context, attrs: AttributeSet? = null) : Button(ctx, attrs) {

  companion object {
    private const val TOKEN = "redraw"
  }

  private val _handler = Handler()

  private val drawable: CircularProgressDrawable = CircularProgressDrawable(ctx)
      .apply {
        setStyle(CircularProgressDrawable.DEFAULT)
        setColorSchemeColors(ContextCompat.getColor(context, R.color.colorAccent))
      }

  private var _isProgressEnabled: Boolean = false

  var isProgressEnabled: Boolean = false
    set(value) {
      if (value == _isProgressEnabled) {
        return
      }
      _isProgressEnabled = value
      isEnabled = !value

      if (value) {
        drawable.start()
        _handler.repeat(16, TOKEN, { invalidate() })
      } else {
        drawable.stop()
        _handler.removeCallbacksAndMessages(TOKEN)
        invalidate()
      }
    }

  override fun onDraw(canvas: Canvas?) {
    super.onDraw(canvas)
    if (_isProgressEnabled) {
      drawable.draw(canvas)
    }
  }

  override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
    super.onSizeChanged(w, h, oldw, oldh)
    if (h > 0) {
      val drawableSize = context.dip(32)
      val topPadding = (h - drawableSize) / 2
      drawable.setBounds(paddingLeft, topPadding, drawableSize, topPadding + drawableSize)
    }
  }
}

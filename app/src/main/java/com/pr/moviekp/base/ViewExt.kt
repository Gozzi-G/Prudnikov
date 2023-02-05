package com.pr.moviekp.base

import android.view.View
import android.view.ViewGroup

const val DEFAULT_CLICK_DELAY_MS = 750L

inline fun View.onClickDebounce(
    delayMs: Long = DEFAULT_CLICK_DELAY_MS,
    crossinline callback: (View?) -> Unit
) {
    setOnClickListener(object : View.OnClickListener {
        private var notClicked = true
        override fun onClick(view: View) {
            if (notClicked) {
                notClicked = false
                callback(view)
                view.postDelayed({ notClicked = true }, delayMs)
            }
        }
    })
}

fun View.setMarginTop(marginTop: Int) {
    val menuLayoutParams = this.layoutParams as ViewGroup.MarginLayoutParams
    menuLayoutParams.setMargins(0, marginTop, 0, 0)
    this.layoutParams = menuLayoutParams
}

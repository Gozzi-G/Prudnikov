package com.pr.moviekp.base

import android.view.View

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

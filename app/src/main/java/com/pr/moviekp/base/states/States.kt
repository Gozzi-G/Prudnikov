package com.pr.moviekp.base.states

import android.content.Context
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.core.content.ContextCompat.getColor
import com.pr.moviekp.R
import com.pr.moviekp.base.dp

class States(private val viewState: ViewState) {

    fun createLoadView(
        contentView: View,
    ): View {
        return ProgressBar(contentView.context, null, android.R.attr.progressBarStyle).apply {
            layoutParams = when (contentView.parent) {
                is FrameLayout -> FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply { gravity = Gravity.CENTER }
                is RelativeLayout -> RelativeLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE)
                }
                is CoordinatorLayout -> CoordinatorLayout.LayoutParams(
                    CoordinatorLayout.LayoutParams.MATCH_PARENT,
                    CoordinatorLayout.LayoutParams.WRAP_CONTENT
                ).apply { gravity = Gravity.CENTER }
                is ConstraintLayout -> ConstraintLayout.LayoutParams(
                    ConstraintLayout.LayoutParams.MATCH_PARENT,
                    ConstraintLayout.LayoutParams.WRAP_CONTENT
                ).apply {
                    topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                    bottomToBottom = ConstraintLayout.LayoutParams.PARENT_ID
                    verticalBias = 0.5f
                    horizontalBias = 0.5f
                }
                else -> ViewGroup.MarginLayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT
                )
            }
            indeterminateDrawable.colorFilter = PorterDuffColorFilter(
                getColor(context, R.color.bluePrimary),
                PorterDuff.Mode.SRC_IN
            )
        }
    }

    fun createErrorView(
        context: Context,
    ): View {
        return LinearLayout(context).apply {
            layoutParams = ViewGroup.MarginLayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            orientation = LinearLayout.VERTICAL
            gravity = Gravity.CENTER

            viewState.tvError = TextView(context).apply {
                setPadding(16f.dp, 16f.dp, 16f.dp, 16f.dp)
                gravity = Gravity.CENTER
                textSize = 16f
                setTextColor(getColor(context, R.color.bluePrimary))
            }

            viewState.tvError!!.text = context.getString(R.string.error)
            addView(viewState.tvError)

            viewState.btnRetry = Button(context).apply {
                layoutParams = LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
                )
                setPadding(27f.dp, 16f.dp, 27f.dp, 16f.dp)
                text = context.getString(R.string.error_view_reload)
                setTextColor(getColor(context, R.color.white))
                setBackgroundResource(R.drawable.bg_error_btn)
            }
            addView(viewState.btnRetry)
        }
    }
}


package com.pr.moviekp.base.states

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isVisible
import java.util.*

class ViewState private constructor(contentView: View) {

    companion object State {
        private const val CONTENT = "content"
        private const val LOAD = "load"
        private const val ERROR = "error"
    }

    class Builder(private val contentView: View) {
        private val viewState = ViewState(contentView)
        private val states = States(viewState)

        fun load(): Builder {
            val loadView = states.createLoadView(contentView)
            viewState.addState(LOAD, loadView)
            return this
        }

        fun error(): Builder {
            val errorView = states.createErrorView(contentView.context)
            viewState.addState(ERROR, errorView)
            return this
        }

        fun state(state: String, target: View): Builder {
            viewState.addState(state, target)
            return this
        }

        fun build() = viewState
    }

    private val states = HashMap<String, View>()
    private val parent: ViewGroup
    private var currentView = contentView

    /** TextView that is used when error state massage display */
    var tvError: TextView? = null

    /** Button that is used when error state is display */
    var btnRetry: Button? = null

    init {
        states[CONTENT] = contentView
        parent = contentView.parent as ViewGroup
    }

    fun addState(state: String, target: View) {
        if (state in states) {
            throw IllegalStateException("State $state already added")
        }
        target.isVisible = false
        states[state] = target
    }

    fun content(animate: Boolean) {
        state(CONTENT)
    }

    fun load(animate: Boolean) {
        state(LOAD)
    }

    fun error(animate: Boolean) {
        state(ERROR)
    }

    fun state(state: String) {
        val target = states[state] ?: throw IllegalStateException("State $state was not added yet")

        if (target.parent != parent) {
            parent.addView(target)
        }
        if (currentView != target) {
            currentView.isVisible = false
            target.isVisible = true
            currentView = target
        }
    }


}
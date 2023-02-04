package com.pr.moviekp.base.states

sealed class StateLCE(open val animate: Boolean = false) {

    class Loading(
        override val animate: Boolean,
    ): StateLCE(animate)

    class Content(
        override val animate: Boolean,
    ): StateLCE(animate)

    class Error(
        override val animate: Boolean = false,
        customMessage: String = "",
    ): StateLCE(animate)

}
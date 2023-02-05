package com.pr.moviekp.base.states

sealed class StateLCE() {

    class Loading(
    ): StateLCE()

    class Content(
    ): StateLCE()

    class Error(
        val customMessage: String? = "",
    ): StateLCE()

}
package com.pr.moviekp.base

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.pr.moviekp.base.states.StateLCE
import org.koin.core.component.KoinComponent

abstract class BaseViewModel : ViewModel(), KoinComponent {

    protected val stateLCE = MutableLiveData<StateLCE>()

    fun observeStateLCE(owner: LifecycleOwner, observer: Observer<StateLCE>) {
        stateLCE.observe(owner, observer)
    }

    protected fun loading() {
        stateLCE.value = StateLCE.Loading()
    }

    protected fun content(subState: String = "") {
        val state = StateLCE.Content()
        stateLCE.value = state
    }

    protected fun error(
        throwable: Throwable? = null,
        customMessage: String = "",
    ) {
        stateLCE.value = StateLCE.Error()
    }

}
package com.pr.moviekp.base

import androidx.annotation.AnyThread
import androidx.annotation.MainThread
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import java.util.concurrent.atomic.AtomicBoolean

class LiveEvent<T> : MutableLiveData<T>() {

    private val stateHolder: AtomicBoolean = AtomicBoolean(false)

    @MainThread
    override fun observe(owner: LifecycleOwner, observer: Observer<in T>) {
        val singleObserver = Observer<T> {
            if (stateHolder.compareAndSet(true, false)) {
                observer.onChanged(it)
            }
        }
        super.observe(owner, singleObserver)
    }

    @MainThread
    override fun setValue(value: T) {
        stateHolder.set(true)
        super.setValue(value)
    }

    @AnyThread
    override fun postValue(value: T) {
        stateHolder.set(true)
        super.postValue(value)
    }

}
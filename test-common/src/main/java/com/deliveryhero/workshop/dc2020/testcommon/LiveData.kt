package com.deliveryhero.workshop.dc2020.testcommon

import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.nhaarman.mockitokotlin2.mock

inline fun <T> LiveData<T>.withObserver(asserter: (Observer<T>) -> Unit) {
    val observer = mock<Observer<T>>()
    observeForever(observer)
    asserter(observer)
}

package com.deliveryhero.workshop.dc2020.ui.launcher

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.switchMap
import com.deliveryhero.workshop.dc2020.data.config.ConfigRepository
import javax.inject.Inject

class LauncherViewModel @Inject constructor(private val configRepository: ConfigRepository) : ViewModel() {

    private val trigger = MutableLiveData(Unit)

    val initializerLiveData = trigger.switchMap {
        liveData {
            val result = runCatching { configRepository.getConfig() }
            emit(result.isSuccess)
        }
    }

    fun reload() {
        trigger.value = Unit
    }
}

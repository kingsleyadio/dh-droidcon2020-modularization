package com.deliveryhero.workshop.dc2020.rdp.di

import com.deliveryhero.workshop.dc2020.rdp.ui.RdpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun providesRdpActivity(): RdpActivity
}

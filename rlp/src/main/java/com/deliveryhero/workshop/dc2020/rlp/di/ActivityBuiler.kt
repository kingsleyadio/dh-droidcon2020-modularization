package com.deliveryhero.workshop.dc2020.rlp.di

import com.deliveryhero.workshop.dc2020.rlp.RlpActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun providesRlpActivity(): RlpActivity
}
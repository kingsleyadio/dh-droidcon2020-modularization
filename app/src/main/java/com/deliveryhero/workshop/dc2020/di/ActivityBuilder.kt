package com.deliveryhero.workshop.dc2020.di

import com.deliveryhero.workshop.dc2020.rlp.RdpLauncher
import com.deliveryhero.workshop.dc2020.ui.RdpLauncherImpl
import com.deliveryhero.workshop.dc2020.ui.launcher.LauncherActivity
import com.deliveryhero.workshop.dc2020.ui.rdp.RdpActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityBuilder {

    @ContributesAndroidInjector
    internal abstract fun providesLauncherActivity(): LauncherActivity

    @ContributesAndroidInjector
    internal abstract fun providesRdpActivity(): RdpActivity

    @Binds
    internal abstract fun providesRdpLauncher(impl: RdpLauncherImpl): RdpLauncher
}

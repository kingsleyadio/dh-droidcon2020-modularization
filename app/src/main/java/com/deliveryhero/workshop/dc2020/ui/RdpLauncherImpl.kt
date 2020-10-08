package com.deliveryhero.workshop.dc2020.ui

import android.content.Context
import com.deliveryhero.workshop.dc2020.rdp.RdpProvider
import com.deliveryhero.workshop.dc2020.rlp.RdpLauncher
import javax.inject.Inject

class RdpLauncherImpl @Inject constructor() : RdpLauncher {

    override fun launchRdp(context: Context, restaurantId: Int) {
        context.startActivity(RdpProvider.getRdpStartIntent(context, restaurantId))
    }
}

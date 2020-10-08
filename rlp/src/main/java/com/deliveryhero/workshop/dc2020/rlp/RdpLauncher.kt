package com.deliveryhero.workshop.dc2020.rlp

import android.content.Context

interface RdpLauncher {
    fun launchRdp(context: Context, restaurantId: Int)
}
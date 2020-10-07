package com.deliveryhero.workshop.dc2020.ui.launcher

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.deliveryhero.workshop.dc2020.R
import com.deliveryhero.workshop.dc2020.ui.rdp.RdpActivity
import com.deliveryhero.workshop.dc2020.ui.common.ViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<LauncherViewModel>
    private val viewModel by viewModels<LauncherViewModel> { viewModelFactory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_launcher)

        viewModel.initializerLiveData.observe(this, ::handleInit)
    }

    private fun handleInit(isInitialized: Boolean) {
        if (isInitialized) {
            startActivity(RdpActivity.newIntent(this, 1001))
            finish()
        } else {
            AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage("Something went wrong. App was unable to initialize. Please try again")
                .setNegativeButton("Quit") { _, _ -> finish() }
                .setPositiveButton("Retry") { _, _ -> viewModel.reload() }
                .show()
        }
    }
}

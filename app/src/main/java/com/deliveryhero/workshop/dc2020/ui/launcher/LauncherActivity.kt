package com.deliveryhero.workshop.dc2020.ui.launcher

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.deliveryhero.translation.generated.TranslationKeys
import com.deliveryhero.workshop.dc2020.R
import com.deliveryhero.workshop.dc2020.localization.StringLocalizer
import com.deliveryhero.workshop.dc2020.ui.common.ViewModelFactory
import com.deliveryhero.workshop.dc2020.ui.rlp.RlpActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LauncherActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var stringLocalizer: StringLocalizer
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
            startActivity(RlpActivity.newIntent(this))
            finish()
        } else {
            AlertDialog.Builder(this)
                .setCancelable(false)
                .setMessage(stringLocalizer.getText(TranslationKeys.APP_INITIALIZATION_FAILED))
                .setNegativeButton(stringLocalizer.getText(TranslationKeys.QUIT)) { _, _ -> finish() }
                .setPositiveButton(stringLocalizer.getText(TranslationKeys.RETRY)) { _, _ -> viewModel.reload() }
                .show()
        }
    }
}

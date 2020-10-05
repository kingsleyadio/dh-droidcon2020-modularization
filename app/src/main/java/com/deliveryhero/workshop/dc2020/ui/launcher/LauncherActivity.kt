package com.deliveryhero.workshop.dc2020.ui.launcher

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.deliveryhero.workshop.dc2020.R
import kotlinx.coroutines.delay

class LauncherActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.a_launcher)

        lifecycleScope.launchWhenCreated {
            Toast.makeText(this@LauncherActivity, "Closing now...", Toast.LENGTH_SHORT).show()
            delay(2_000)
            finish()
        }
    }
}

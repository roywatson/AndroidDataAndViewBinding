package com.delasystems.androiddataandviewbinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.delasystems.androiddataandviewbinding.ui.main.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}
package com.jg2022.demoproject.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.jg2022.demoproject.utils.setStatusBarColor


open class BaseActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setStatusBarColor()

    }


}
package com.edward.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import com.yfbx.edit.helpter.GridEditHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val list = arrayOf(codeTxt1, codeTxt2, codeTxt3, codeTxt4, codeTxt5, codeTxt6)

        GridEditHelper().addViews(list).onInputResult {

            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }


    }
}

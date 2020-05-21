package com.excercise.androidtesting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn_show.setOnClickListener { tv_text.text = "This is Rangga" }

        val spinnerAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, resources.getStringArray(R.array.spinner_options))
        spinner_test.adapter = spinnerAdapter
        spinner_test.onItemSelectedListener = object : AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                tv_text.text = getString(R.string.spinner_result, spinnerAdapter.getItem(position))
            }
        }
    }
}

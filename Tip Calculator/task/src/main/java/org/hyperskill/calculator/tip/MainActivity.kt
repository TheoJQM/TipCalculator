package org.hyperskill.calculator.tip

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n", "DefaultLocale")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editText = findViewById<EditText>(R.id.edit_text)
        val billValueTv = findViewById<TextView>(R.id.bill_value_tv)
        val tipPercentTv = findViewById<TextView>(R.id.tip_percent_tv)
        val seekBar = findViewById<SeekBar>(R.id.seek_bar)
        val tipAmountTv = findViewById<TextView>(R.id.tip_amount_tv)


        editText.addTextChangedListener {
            when {
                it.isNullOrEmpty() -> {
                    billValueTv.text = ""
                    tipPercentTv.text =  ""
                    tipAmountTv.text = ""
                }
                it.toString().toDouble() > 0 -> {
                    billValueTv.text =  String.format("Bill Value: \$%.2f", it.toString().toDouble())
                    tipPercentTv.text =  String.format("Tip: %d%s", seekBar.progress, "%")

                    val tipAmount = it.toString().toDouble() * seekBar.progress / 100
                    tipAmountTv.text = String.format("Tip Amount: \$%.2f", tipAmount)
                }
                else -> {
                    billValueTv.text = ""
                    tipPercentTv.text =  ""
                    tipAmountTv.text = ""
                }
            }

        }

        seekBar.setOnSeekBarChangeListener(object: SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(sb: SeekBar, progress: Int, fromUser: Boolean) {
                if (!editText.text.isNullOrEmpty()) {
                    tipPercentTv.text = String.format("Tip: %d%s", progress, "%")

                    val tipAmount = editText.text.toString().toDouble() * seekBar.progress / 100
                    tipAmountTv.text = String.format("Tip Amount: \$%.2f", tipAmount)
                }
            }

            override fun onStartTrackingTouch(p0: SeekBar?) {}
            override fun onStopTrackingTouch(p0: SeekBar?) {}
        })


    }
}
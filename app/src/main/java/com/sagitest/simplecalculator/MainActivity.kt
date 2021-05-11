package com.sagitest.simplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import android.util.Log
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Number Listeners
        btn0.setOnClickListener{appendOnClick(true, "0")}
        btn1.setOnClickListener{appendOnClick(true, "1")}
        btn2.setOnClickListener{appendOnClick(true, "2")}
        btn3.setOnClickListener{appendOnClick(true, "3")}
        btn4.setOnClickListener{appendOnClick(true, "4")}
        btn5.setOnClickListener{appendOnClick(true, "5")}
        btn6.setOnClickListener{appendOnClick(true, "6")}
        btn7.setOnClickListener{appendOnClick(true, "7")}
        btn8.setOnClickListener{appendOnClick(true, "8")}
        btn9.setOnClickListener{appendOnClick(true, "9")}
        btnDot.setOnClickListener{appendOnClick(true, ".")}

        //Operators
        btnPlus.setOnClickListener{appendOnClick(false, "+")}
        btnMinus.setOnClickListener{appendOnClick(false, "-")}
        btnMultiply.setOnClickListener{appendOnClick(false, "*")}
        btnDivide.setOnClickListener{appendOnClick(false, "/")}

        btnLeftB.setOnClickListener{appendOnClick(false, "(")}
        btnRightB.setOnClickListener{appendOnClick(false, ")")}

        btnBack.setOnClickListener{
            val string = tvIn.text.toString()
            if(string.isNotEmpty()) {
                tvIn.text = string.substring(0, string.length-1)
            }
            tvOut.text = ""
        }

        btnClear.setOnClickListener{clear()}
        btnEqual.setOnClickListener{calculate()}
    }

    private fun appendOnClick(clear: Boolean, string: String) {
        if (tvOut.text.isNotEmpty()) {
            tvIn.text = ""
        }
        if(clear) {
            tvOut.text = ""
            tvIn.append(string)
        } else {
            tvIn.append(tvOut.text)
            tvIn.append(string)
            tvOut.text = ""
        }
    }

    private fun clear() {
        tvIn.text = ""
        tvOut.text = ""
    }

    private fun calculate() {
        try {
            val expr = ExpressionBuilder(tvIn.text.toString()).build()
            val result = expr.evaluate()
            val longResult = result.toLong()
            if(result== longResult.toDouble()) {
                tvOut.text = longResult.toString()
            }else {
                tvOut.text = result.toString()
            }
        }catch (e:Exception){
            Log.d("Exception", "message :" + e.message)
        }
    }
}

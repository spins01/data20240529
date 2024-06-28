package com.app.module.base.common

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import com.spins.module.base.R

class SpinsDatePickerDialog(
    context: Context,
    private val onDateSetListener: (view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) -> Unit,
    year: Int,
    month: Int,
    dayOfMonth: Int
) : DatePickerDialog(context, { view, year, month, dayOfMonth ->onDateSetListener(view,year,month,dayOfMonth) }, year, month, dayOfMonth) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        getButton(BUTTON_POSITIVE).text = "Confirm" // 设置确定按钮文字
        getButton(BUTTON_NEGATIVE).text = "Cancel" // 设置取消按钮文字
    }


}

package com.example.mobiledevandroide.ui.components.detailscreen

import android.app.DatePickerDialog
import android.util.Log
import android.widget.DatePicker
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

@Composable
fun DateField(
    initialValue: String,
    onValueChange: (String) -> Unit,
) {
    val mContext = LocalContext.current

    val mCalendar = Calendar.getInstance()

    try {
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault()).parse(initialValue)
            ?.let {
                mCalendar.time = it
            }
    } catch (e: ParseException) {
        Log.e("MyDateField", e.toString())
    }

    val mSelectedDate = remember {
        mutableStateOf(
            SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(mCalendar.time),
        )
    }

    val mDatePickerDialog = DatePickerDialog(
        mContext,
        { _: DatePicker, mYear: Int, mMonth: Int, mDayOfMonth: Int ->
            mCalendar.apply {
                set(Calendar.YEAR, mYear)
                set(Calendar.MONTH, mMonth)
                set(Calendar.DAY_OF_MONTH, mDayOfMonth)
            }
            val selectedDate = SimpleDateFormat("dd-MM-yyyy", Locale.getDefault())
                .format(mCalendar.time)

            mSelectedDate.value = selectedDate
            onValueChange(selectedDate)
        },
        mCalendar[Calendar.YEAR],
        mCalendar[Calendar.MONTH],
        mCalendar[Calendar.DAY_OF_MONTH],
    )
    ReadonlyTextField(
        value = TextFieldValue(mSelectedDate.value),
        onValueChange = {},
        label = { Text("Select Date") },
        modifier = Modifier
            .clickable {
                mDatePickerDialog.show()
            }
            .fillMaxWidth(),
    )
    Spacer(modifier = Modifier.size(16.dp))
    onValueChange(mSelectedDate.value)
}
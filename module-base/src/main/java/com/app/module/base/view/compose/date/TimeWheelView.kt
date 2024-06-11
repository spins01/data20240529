package com.app.module.base.view.compose.date

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xiaojinzi.support.ktx.LogSupport
import com.xiaojinzi.support.ktx.getHourOfDayByTimeStamp
import com.xiaojinzi.support.ktx.getMinuteByTimeStamp
import com.xiaojinzi.support.ktx.nothing
import com.app.module.base.support.LogKeyword
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

@ExperimentalMaterialApi
@Composable
fun TimeWheelView(
    modifier: Modifier = Modifier,
    dateTimeTimeState: DateTimeWheelState = rememberDateTimeWheelState(
        initTime = System.currentTimeMillis()
    ),
    visibleCount: Int = 3,
    lineThickness: Dp = 1.dp,
    lineWidthPercent: Float = 1f,
    itemHeight: Dp = 48.dp,
) {

    val hourWheelState: HourWheelState = rememberHourWheelState(
        initValue = getHourOfDayByTimeStamp(timeStamp = dateTimeTimeState.currentTime)
    )
    val minuteWheelState: MinuteWheelState = rememberMinuteWheelState(
        initValue = getMinuteByTimeStamp(timeStamp = dateTimeTimeState.currentTime)
    )

    val resultCalendar = Calendar.getInstance()
    resultCalendar.timeInMillis = dateTimeTimeState.currentTime
    resultCalendar.set(Calendar.HOUR_OF_DAY, hourWheelState.currentValue)
    resultCalendar.set(Calendar.MINUTE, minuteWheelState.currentValue)
    resultCalendar.set(Calendar.SECOND, 0)
    dateTimeTimeState.currentTime = resultCalendar.timeInMillis

    LogSupport.d(
        content = "dayStartTime = ${
            SimpleDateFormat(
                "yyyy MM dd HH:mm:ss",
                Locale.getDefault(),
            ).format(
                Date(
                    dateTimeTimeState.currentTime
                )
            )
        }",
        keywords = arrayOf(LogKeyword.dateTime),
    )

    Row(
        modifier = modifier,
    ) {
        HourWheelView(
            modifier = Modifier
                .weight(weight = 1f, fill = true)
                .wrapContentHeight()
                .nothing(),
            hourWheelState = hourWheelState,
            visibleCount = visibleCount,
            lineThickness = lineThickness,
            lineWidthPercent = lineWidthPercent,
            itemHeight = itemHeight,
        )
        MinuteWheelView(
            modifier = Modifier
                .weight(weight = 1f, fill = true)
                .wrapContentHeight()
                .nothing(),
            minuteWheelState = minuteWheelState,
            visibleCount = visibleCount,
            lineThickness = lineThickness,
            lineWidthPercent = lineWidthPercent,
            itemHeight = itemHeight,
        )
    }

}
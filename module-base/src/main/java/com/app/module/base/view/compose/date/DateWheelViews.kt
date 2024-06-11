package com.app.module.base.view.compose.date

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xiaojinzi.support.annotation.TimeValue
import com.xiaojinzi.support.ktx.LogSupport
import com.xiaojinzi.support.ktx.getMonthByTimeStamp
import com.xiaojinzi.support.ktx.getMonthInterval
import com.xiaojinzi.support.ktx.getYearByTimeStamp
import com.xiaojinzi.support.ktx.nothing
import com.xiaojinzi.support.ktx.toStringItemDto
import com.app.module.base.support.LogKeyword
import com.app.module.base.view.compose.WheelItem
import com.app.module.base.view.compose.WheelViewWithIndex
import com.app.module.base.view.compose.rememberWheelState
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

private val currentYear: Int
    get() = getYearByTimeStamp(
        timeStamp = System.currentTimeMillis(),
    )

/**
 * 日期选择的视图
 */
@ExperimentalMaterialApi
@Composable
fun DateWheelView(
    modifier: Modifier = Modifier,
    dateTimeTimeState: DateTimeWheelState = rememberDateTimeWheelState(
        initTime = System.currentTimeMillis(),
    ),
    yearRange: List<Int> = (1970..(currentYear + 20)).toList(),
    showMonth: Boolean = true,
    showDay: Boolean = true,
    visibleCount: Int = 3,
    lineThickness: Dp = 1.dp,
    lineWidthPercent: Float = 1f,
    itemHeight: Dp = 48.dp,
) {

    val yearWheelState: YearWheelState =
        rememberYearWheelState(
            initValue = getYearByTimeStamp(timeStamp = dateTimeTimeState.currentTime)
        )
    val monthWheelState: MonthWheelState =
        rememberMonthWheelState(
            initValue = getMonthByTimeStamp(timeStamp = dateTimeTimeState.currentTime)
        )

    val resultCalendar = Calendar.getInstance()
    resultCalendar.timeInMillis = dateTimeTimeState.currentTime

    LogSupport.d(
        content = "resultCalendar0 = ${
            SimpleDateFormat(
                "yyyy MM dd HH:mm:ss",
                Locale.getDefault()
            ).format(resultCalendar.time)
        }, dateTimeTimeState = ${
            dateTimeTimeState.currentTime
        } yearWheelState = ${
            yearWheelState.currentValue
        }, monthWheelState = ${
            monthWheelState.currentValue
        }",
        keywords = arrayOf(LogKeyword.dateTime),
    )

    // 先重置为1, 避免干扰
    resultCalendar.set(Calendar.DAY_OF_MONTH, 1)
    resultCalendar.set(Calendar.YEAR, yearWheelState.currentValue)
    resultCalendar.set(Calendar.MONTH, monthWheelState.currentValue)

    LogSupport.d(
        content = "resultCalendar1 = ${
            SimpleDateFormat(
                "yyyy MM dd HH:mm:ss",
                Locale.getDefault()
            ).format(resultCalendar.time)
        }, dateTimeTimeState = ${
            dateTimeTimeState.currentTime
        } yearWheelState = ${
            yearWheelState.currentValue
        }, monthWheelState = ${
            monthWheelState.currentValue
        }",
        keywords = arrayOf(LogKeyword.dateTime),
    )

    val tempCalendar = Calendar.getInstance()

    // 计算出当前时间的 day 的范围, 比如：1..31
    val (dayStartTime, dayEndTime) = getMonthInterval(timeStamp = resultCalendar.timeInMillis)
    tempCalendar.timeInMillis = dayStartTime
    val dayStart = tempCalendar.get(Calendar.DAY_OF_MONTH)
    tempCalendar.timeInMillis = dayEndTime
    val dayEnd = tempCalendar.get(Calendar.DAY_OF_MONTH)

    // 计算当前时间的 day
    tempCalendar.timeInMillis = dateTimeTimeState.currentTime
    val targetDay = tempCalendar.get(Calendar.DAY_OF_MONTH)

    val dayWheelState = rememberWheelState(initIndex = targetDay - 1)
    dayWheelState.currentIndex = dayWheelState.currentIndex.coerceIn(
        minimumValue = 0, maximumValue = dayEnd - 1,
    )

    // 最终计算时间戳, 保存
    resultCalendar.set(Calendar.DAY_OF_MONTH, dayWheelState.currentIndex + 1)
    dateTimeTimeState.currentTime = resultCalendar.timeInMillis

    // 日志
    run {
        LogSupport.d(
            content = "dayStartTime = ${
                SimpleDateFormat(
                    "yyyy MM dd HH:mm:ss",
                    Locale.getDefault(),
                ).format(
                    Date(
                        dayStartTime
                    )
                )
            }",
            keywords = arrayOf(LogKeyword.dateTime),
        )
        LogSupport.d(
            content = "dayEndTime = ${
                SimpleDateFormat(
                    "yyyy MM dd HH:mm:ss",
                    Locale.getDefault(),
                ).format(Date(dayEndTime))
            }",
            keywords = arrayOf(LogKeyword.dateTime),
        )
        LogSupport.d(
            content = "year = ${yearWheelState.currentValue}",
            keywords = arrayOf(LogKeyword.dateTime),
        )
        LogSupport.d(
            content = "month = ${monthWheelState.currentValue}",
            keywords = arrayOf(LogKeyword.dateTime),
        )
        LogSupport.d(
            content = "dayStart = $dayStart",
            keywords = arrayOf(LogKeyword.dateTime),
        )
        LogSupport.d(
            content = "dayEnd = $dayEnd",
            keywords = arrayOf(LogKeyword.dateTime),
        )
        LogSupport.d(
            content = "wheelState.currentIndex = ${dayWheelState.currentIndex}",
            keywords = arrayOf(LogKeyword.dateTime),
        )
        LogSupport.d(
            content = "tempCalendar.timeInMillis = ${
                SimpleDateFormat(
                    "yyyy MM dd HH:mm:ss",
                    Locale.getDefault(),
                ).format(
                    Date(tempCalendar.timeInMillis)
                )
            }",
            keywords = arrayOf(LogKeyword.dateTime),
        )
    }

    Row(
        modifier = modifier
    ) {
        YearWheelView(
            modifier = Modifier
                .weight(weight = 1f, fill = true)
                .wrapContentHeight()
                .nothing(),
            yearRange = yearRange,
            yearWheelState = yearWheelState,
            visibleCount = visibleCount,
            lineThickness = lineThickness,
            lineWidthPercent = lineWidthPercent,
            itemHeight = itemHeight,
        )
        if (showMonth) {
            MonthWheelView(
                modifier = Modifier
                    .weight(weight = 1f, fill = true)
                    .wrapContentHeight()
                    .nothing(),
                monthWheelState = monthWheelState,
                visibleCount = visibleCount,
                lineThickness = lineThickness,
                lineWidthPercent = lineWidthPercent,
                itemHeight = itemHeight,
            )
            if (showDay) {
                WheelViewWithIndex(
                    modifier = Modifier
                        .weight(weight = 1f, fill = true)
                        .wrapContentHeight()
                        .nothing(),
                    items = (dayStart..dayEnd).toList(),
                    wheelState = dayWheelState,
                    visibleCount = visibleCount,
                    lineThickness = lineThickness,
                    lineWidthPercent = lineWidthPercent,
                    itemHeight = itemHeight,
                ) { index, item ->
                    WheelItem(
                        content = "${item}日".toStringItemDto(),
                        isSelected = index == dayWheelState.currentIndex,
                    )
                }
            }
        }
    }

}

class DateTimeWheelState(currentTime: Long) {

    @TimeValue(value = TimeValue.Type.MILLISECOND)
    var currentTime by mutableStateOf(currentTime)

    companion object {
        val Saver = object : Saver<DateTimeWheelState, Long> {
            override fun restore(value: Long): DateTimeWheelState {
                return DateTimeWheelState(currentTime = value)
            }

            override fun SaverScope.save(value: DateTimeWheelState): Long {
                return value.currentTime
            }
        }
    }

}

@Composable
fun rememberDateTimeWheelState(
    @TimeValue(value = TimeValue.Type.MILLISECOND)
    initTime: Long = 0
) = rememberSaveable(saver = DateTimeWheelState.Saver) {
    DateTimeWheelState(currentTime = initTime)
}
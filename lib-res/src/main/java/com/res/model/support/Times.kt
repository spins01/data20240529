package com.xiaojinzi.tally.lib.res.model.support

import java.util.TimeZone

const val SecondMillisecond = 1000L

const val MinuteMillisecond = 60 * SecondMillisecond

const val HourMillisecond = 60 * MinuteMillisecond

const val DayMillisecond = 24 * HourMillisecond

@JvmInline
value class TallyTimeDay(val value: Long) {

    fun toTimeStamp(
        timeZone: TimeZone = TimeZone.getDefault()
    ): TallyTimeStamp = TallyTimeStamp(value = this.value * DayMillisecond - timeZone.rawOffset)

}

@JvmInline
value class TallyTimeStamp(val value: Long) {

    fun toTimeDay(
        timeZone: TimeZone = TimeZone.getDefault()
    ): TallyTimeDay = TallyTimeDay(value = (this.value + timeZone.rawOffset) / DayMillisecond)

    operator fun plus(target: TallyTimeStamp): TallyTimeStamp = TallyTimeStamp(
        value = this.value + target.value,
    )

    operator fun minus(target: TallyTimeStamp): TallyTimeStamp = TallyTimeStamp(
        value = this.value - target.value,
    )

}

/**
 * 计算时间戳
 */
fun Long.calculate(
    timeZone: TimeZone = TimeZone.getDefault()
): Long = (this + timeZone.rawOffset) / DayMillisecond

/**
 * 往后一个比一个 flag 大, 后面的一定会显示 flag 比自身小的
 */
enum class DateTimeType(val flag: Int) {
    // 年
    Year(flag = 1),

    // 月
    Month(flag = 2),

    // 日期
    Day(flag = 4),

    // 时间
    Time(flag = 8),

}

enum class DateTimeModel {
    // 不会舍弃时间, 以当前时间为基础, 调整各个参数到用户选择的时间
    Current,
    // 舍弃后面的, 当你选择的时间到天为止的话, 后面的小时、分、秒都会被置为 0
    DropLast,
}
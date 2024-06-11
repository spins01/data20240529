package com.app.module.base.view.compose.date

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xiaojinzi.support.ktx.getYearByTimeStamp
import com.xiaojinzi.support.ktx.toStringItemDto
import com.app.module.base.view.compose.WheelItem
import com.app.module.base.view.compose.WheelViewWithIndex
import com.app.module.base.view.compose.rememberWheelState

private val currentYear: Int
    get() = getYearByTimeStamp(
        timeStamp = System.currentTimeMillis(),
    )

@ExperimentalMaterialApi
@Composable
fun YearWheelView(
    modifier: Modifier = Modifier,
    yearRange: List<Int> = (1970 .. (currentYear + 20)).toList(),
    yearWheelState: YearWheelState = rememberYearWheelState(
        initValue = currentYear,
    ),
    visibleCount: Int = 5,
    lineThickness: Dp = 1.dp,
    lineWidthPercent: Float = 1f,
    itemHeight: Dp = 48.dp,
) {
    val initIndex = yearRange.indexOf(element = yearWheelState.currentValue)
    val wheelState = rememberWheelState(initIndex = if (initIndex > -1) initIndex else 0)
    LaunchedEffect(key1 = wheelState) {
        snapshotFlow { wheelState.currentIndex }
            .collect {
                if (yearRange[it] != yearWheelState.currentValue) {
                    yearWheelState.currentValue = yearRange[it]
                }
            }
    }
    WheelViewWithIndex(
        modifier = modifier,
        items = yearRange,
        wheelState = wheelState,
        visibleCount = visibleCount,
        lineThickness = lineThickness,
        lineWidthPercent = lineWidthPercent,
        itemHeight = itemHeight,
    ) { index, item ->
        WheelItem(
            content = "${item}年".toStringItemDto(),
            isSelected = index == wheelState.currentIndex,
        )
    }
}

class YearWheelState(currentValue: Int) {

    var currentValue by mutableIntStateOf(currentValue)

    companion object {
        val Saver = object : Saver<YearWheelState, Int> {
            override fun restore(value: Int): YearWheelState {
                return YearWheelState(currentValue = value)
            }

            override fun SaverScope.save(value: YearWheelState): Int {
                return value.currentValue
            }
        }
    }

}

@Composable
fun rememberYearWheelState(
    initValue: Int = 0
) = rememberSaveable(saver = YearWheelState.Saver) {
    YearWheelState(currentValue = initValue)
}
package com.app.module.base.view.compose.date

import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.SaverScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.xiaojinzi.support.ktx.getMinuteByTimeStamp
import com.xiaojinzi.support.ktx.toStringItemDto
import com.app.module.base.view.compose.WheelItem
import com.app.module.base.view.compose.WheelViewWithIndex
import com.app.module.base.view.compose.rememberWheelState
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

private val currentMinute: Int get() = getMinuteByTimeStamp(timeStamp = System.currentTimeMillis())

private val numberList = (0..59).toList()

@ExperimentalMaterialApi
@Composable
fun MinuteWheelView(
    modifier: Modifier = Modifier,
    minuteWheelState: MinuteWheelState = rememberMinuteWheelState(initValue = currentMinute),
    visibleCount: Int = 5,
    lineThickness: Dp = 1.dp,
    lineWidthPercent: Float = 1f,
    itemHeight: Dp = 48.dp,
) {
    val initIndex = numberList.indexOf(element = minuteWheelState.currentValue)
    val wheelState = rememberWheelState(initIndex = if (initIndex > -1) initIndex else 0)
    LaunchedEffect(key1 = wheelState) {
        snapshotFlow { wheelState.currentIndex }
            .onEach {
                if (numberList[it] != minuteWheelState.currentValue) {
                    minuteWheelState.currentValue = numberList[it]
                }
            }
            .launchIn(scope = this)
    }
    WheelViewWithIndex(
        modifier = modifier,
        items = numberList,
        wheelState = wheelState,
        visibleCount = visibleCount,
        lineThickness = lineThickness,
        lineWidthPercent = lineWidthPercent,
        itemHeight = itemHeight,
    ) { index, item ->
        WheelItem(
            content = "${item}分".toStringItemDto(),
            isSelected = index == wheelState.currentIndex,
        )
    }
}

class MinuteWheelState(currentValue: Int) {

    var currentValue by mutableStateOf(currentValue)

    companion object {
        val Saver = object : Saver<MinuteWheelState, Int> {
            override fun restore(value: Int): MinuteWheelState {
                return MinuteWheelState(currentValue = value)
            }

            override fun SaverScope.save(value: MinuteWheelState): Int {
                return value.currentValue
            }
        }
    }

}

@Composable
fun rememberMinuteWheelState(
    initValue: Int = 0
) = rememberSaveable(saver = MinuteWheelState.Saver) {
    MinuteWheelState(currentValue = initValue)
}
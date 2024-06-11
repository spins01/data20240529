package com.app.module.base.ktx

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.snapshotFlow
import com.xiaojinzi.support.ktx.LogSupport
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun PagerState.syncWith(onIndexChangeAction: suspend (index: Int) -> Unit): PagerState {
    val targetPagerState = this
    LaunchedEffect(this) {
        snapshotFlow { targetPagerState.currentPage }
            .drop(1)
            .onEach {
                onIndexChangeAction.invoke(it)
            }
            .launchIn(scope = this)
    }
    return targetPagerState
}

/**
 * Syncs the [PagerState] with the target [MutableSharedFlow].
 */
@OptIn(
    ExperimentalFoundationApi::class,
    ExperimentalCoroutinesApi::class,
)
@Composable
fun PagerState.syncWith(targetFlow: MutableSharedFlow<Int>): PagerState {
    LaunchedEffect(key1 = targetFlow) {
        targetFlow
            .distinctUntilChanged()
            .filter { it != this@syncWith.currentPage }
            // 自定义的 flow 的
            .onEach {
                LogSupport.d(
                    tag = "PagerState.syncWith",
                    content = "animateScrollToPage $it",
                )
                this@syncWith.animateScrollToPage(
                    page = it,
                )
            }
            .flatMapLatest { value1 ->
                snapshotFlow { this@syncWith.currentPage }
                    .onEach {
                        LogSupport.d(
                            tag = "PagerState.syncWith",
                            content = "flatMapLatest onEach $it",
                        )
                    }
                    .drop(1)
                    .map { value2 ->
                        value1 to value2
                    }
            }
            .onEach {
                if (it.first != it.second) {
                    LogSupport.d(
                        tag = "PagerState.syncWith",
                        content = "targetFlow emit $it",
                    )
                    targetFlow.emit(value = it.second)
                }
            }
            .launchIn(scope = this)
    }
    return this
}
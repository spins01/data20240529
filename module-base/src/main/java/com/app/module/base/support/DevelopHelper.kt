package com.app.module.base.support

import com.xiaojinzi.support.ktx.MutableSharedStateFlow
import kotlinx.coroutines.flow.Flow

object DevelopHelper {

    private val isDevelopObservableDto_ = MutableSharedStateFlow(initValue = false)
    val isDevelopObservableDto: Flow<Boolean> = isDevelopObservableDto_

    val isDevelop: Boolean
        get() = isDevelopObservableDto_.value

    fun init(isDevelop: Boolean) {
        isDevelopObservableDto_.value = isDevelop
    }

}
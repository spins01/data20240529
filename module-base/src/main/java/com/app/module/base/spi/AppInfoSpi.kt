package com.app.module.base.spi

import androidx.annotation.DrawableRes
import com.xiaojinzi.support.annotation.StateHotObservable
import kotlinx.coroutines.flow.Flow

interface AppInfoSpi {

    companion object {
        const val THEME_FOLLOW_SYSTEM = 0
        const val THEME_LIGHT = 1
        const val THEME_DARK = 2
    }

    /**
     * 类似 1.0.0 这种
     */
    val appVersionName: String

    /**
     * App 版本号
     */
    val appVersionCode: Long

    /**
     * App Icon 的资源 Id
     */
    @get:DrawableRes
    val appIconRsd: Int

    /**
     * 昼夜主题 Index
     * 1    亮色
     * 2    暗色
     * else 跟随系统
     */
    @StateHotObservable
    val themeIndexStateOb: Flow<Int>

    /**
     * 是否使用暗色主题
     */
    @StateHotObservable
    val isDarkThemeStateOb: Flow<Boolean>

    /**
     * 主题配色 Index
     */
    @StateHotObservable
    val themeColorIndexStateOb: Flow<Int>

    /**
     * 切换主题, <= 0 是默认的主题
     * 1    亮色
     * 2    暗色
     * else 跟随系统
     */
    fun switchTheme(
        themeIndex: Int,
    )

    /**
     * 切换主题配色, <= 0 是默认的主题
     */
    fun switchThemeColor(
        themeIndex: Int
    )

}
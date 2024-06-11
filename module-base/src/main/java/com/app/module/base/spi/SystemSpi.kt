package com.app.module.base.spi

import android.content.ClipData
import com.xiaojinzi.component.anno.ServiceAnno
import com.xiaojinzi.support.ktx.app
import com.xiaojinzi.support.ktx.clipboardManager

interface SystemSpi {

    /**
     * 复制文本到剪贴板
     */
    fun copyToClipboard(content: String)

}

@ServiceAnno(SystemSpi::class)
class SystemSpiImpl : SystemSpi {

    override fun copyToClipboard(content: String) {
        try {
            app.clipboardManager?.setPrimaryClip(
                ClipData.newPlainText("content", content)
            )
        } catch (e: Exception) {
            // ignore
        }
    }

}
package com.app.module.user.module.loading.domain

import android.content.Context
import androidx.annotation.UiContext
import com.app.module.base.support.AppRouterUserApi
import com.app.module.base.support.finishAppAllTask
import com.xiaojinzi.component.impl.routeApi
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.BusinessUseCaseImpl
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.annotation.ViewModelLayer
import com.xiaojinzi.support.ktx.tryFinishActivity

sealed class LoadingIntent {

    data class GO(
        @UiContext val context: Context,
    ) : LoadingIntent()

}

@ViewModelLayer
interface LoadingUseCase : BusinessUseCase {
    // TODO
}

@ViewModelLayer
class LoadingUseCaseImpl(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
) : BusinessUseCaseImpl(
    commonUseCase = commonUseCase,
), LoadingUseCase {

    private suspend fun goNext(
        @UiContext context: Context,
    ) {
        AppRouterUserApi::class
            .routeApi()
            .toLoginView(
                context = context,
            ) {
                context.tryFinishActivity()
            }
    }

    @IntentProcess
    private suspend fun go(
        intent: LoadingIntent.GO,
    ) {
        try {
            AppRouterUserApi::class
                .routeApi()
                .privacyAgreementBySuspend(
                    context = intent.context,
                )
            goNext(
                context = intent.context,
            )
        } catch (e: Exception) {
            finishAppAllTask()
        }
    }

    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}
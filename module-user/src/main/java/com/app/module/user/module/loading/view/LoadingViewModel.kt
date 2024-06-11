package com.app.module.user.module.loading.view

import com.app.module.user.module.loading.domain.LoadingUseCase
import com.app.module.user.module.loading.domain.LoadingUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class LoadingViewModel(
    private val useCase: LoadingUseCase = LoadingUseCaseImpl(),
): BaseViewModel(),
    LoadingUseCase by useCase{
}
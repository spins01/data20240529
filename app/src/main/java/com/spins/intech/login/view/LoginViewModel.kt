package com.spins.intech.login.view

import com.spins.intech.login.domain.LoginUseCase
import com.spins.intech.login.domain.LoginUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class LoginViewModel(
    private val useCase: LoginUseCase = LoginUseCaseImpl(),
): BaseViewModel(),
    LoginUseCase by useCase{
}
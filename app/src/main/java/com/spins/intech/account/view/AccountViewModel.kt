package com.spins.intech.account.view

import com.spins.intech.account.domain.AccountUseCase
import com.spins.intech.account.domain.AccountUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class AccountViewModel(
    private val useCase: AccountUseCase = AccountUseCaseImpl(),
): BaseViewModel(),
    AccountUseCase by useCase{
}
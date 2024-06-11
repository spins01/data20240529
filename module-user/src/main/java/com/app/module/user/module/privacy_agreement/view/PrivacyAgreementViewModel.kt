package com.app.module.user.module.privacy_agreement.view

import com.app.module.user.module.privacy_agreement.domain.PrivacyAgreementUseCase
import com.app.module.user.module.privacy_agreement.domain.PrivacyAgreementUseCaseImpl
import com.xiaojinzi.reactive.view.BaseViewModel
import com.xiaojinzi.support.annotation.ViewLayer

@ViewLayer
class PrivacyAgreementViewModel(
    private val useCase: PrivacyAgreementUseCase = PrivacyAgreementUseCaseImpl(),
): BaseViewModel(),
    PrivacyAgreementUseCase by useCase{
}
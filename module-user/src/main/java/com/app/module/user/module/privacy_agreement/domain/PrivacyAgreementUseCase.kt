package com.app.module.user.module.privacy_agreement.domain

import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.BusinessUseCaseImpl
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.annotation.ViewModelLayer

sealed class PrivacyAgreementIntent {

    data object Submit : PrivacyAgreementIntent()

}

@ViewModelLayer
interface PrivacyAgreementUseCase : BusinessUseCase {
    // TODO
}

@ViewModelLayer
class PrivacyAgreementUseCaseImpl(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
) : BusinessUseCaseImpl(
    commonUseCase = commonUseCase,
), PrivacyAgreementUseCase {

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun submit(intent: PrivacyAgreementIntent.Submit) {
        // TODO
    }
    
    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}
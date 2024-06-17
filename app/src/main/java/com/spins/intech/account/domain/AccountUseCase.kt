package com.spins.intech.account.domain


import androidx.compose.foundation.ScrollState
import androidx.compose.material3.DrawerState
import androidx.compose.material3.DrawerValue
import com.google.accompanist.pager.PagerState
import com.xiaojinzi.reactive.anno.IntentProcess
import com.xiaojinzi.reactive.template.domain.BusinessUseCase
import com.xiaojinzi.reactive.template.domain.BusinessUseCaseImpl
import com.xiaojinzi.reactive.template.domain.CommonUseCase
import com.xiaojinzi.reactive.template.domain.CommonUseCaseImpl
import com.xiaojinzi.support.annotation.ViewModelLayer
import kotlinx.coroutines.flow.MutableStateFlow


sealed class AccountIntent {

    data object Submit : AccountIntent()

}

@ViewModelLayer
interface AccountUseCase : BusinessUseCase {
    val pagerState: MutableStateFlow<PagerState>
    val memberListScrollState: MutableStateFlow<ScrollState>
    val roleManagerScrollState: MutableStateFlow<ScrollState>
    val drawerState : MutableStateFlow<DrawerState>
}

@ViewModelLayer
class AccountUseCaseImpl(
    private val commonUseCase: CommonUseCase = CommonUseCaseImpl(),
) : BusinessUseCaseImpl(
    commonUseCase = commonUseCase,
), AccountUseCase {
    override val pagerState: MutableStateFlow<PagerState> =
        MutableStateFlow<PagerState>(PagerState())
    override val memberListScrollState: MutableStateFlow<ScrollState> =
        MutableStateFlow<ScrollState>(
            ScrollState(initial = 0)
        )
    override val roleManagerScrollState: MutableStateFlow<ScrollState> =
        MutableStateFlow<ScrollState>(ScrollState(initial = 0))
    override val drawerState: MutableStateFlow<DrawerState>
        = MutableStateFlow<DrawerState>(DrawerState(initialValue = DrawerValue.Closed))

    @IntentProcess
    @BusinessUseCase.AutoLoading
    private suspend fun submit(intent: AccountIntent.Submit) {
        // TODO
    }


    override fun destroy() {
        super.destroy()
        commonUseCase.destroy()
    }

}
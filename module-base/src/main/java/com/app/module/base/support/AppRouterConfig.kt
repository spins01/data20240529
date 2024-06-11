package com.app.module.base.support

object AppRouterConfig {

    const val xxx: String = "xxx/xxx"

    private const val HOST_BASE = "base"

    private const val HOST_USER = "user"
    const val USER_PRIVACY_AGREEMENT = "$HOST_USER/privacyAgreement"
    const val USER_LOADING = "$HOST_USER/loading"
    const val USER_LOGIN = "$HOST_USER/login"

    private const val HOST_CUSTOM = "custom"
    const val CUSTOM_SYSTEM_SHARE = "$HOST_CUSTOM/systemShare"

}
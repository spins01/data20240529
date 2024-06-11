package com.xiaojinzi.tally.lib.res.model.user

import androidx.annotation.Keep
import com.xiaojinzi.support.annotation.MillisecondTime
import com.xiaojinzi.support.annotation.ModelDto
import com.xiaojinzi.support.annotation.ModelForJson

@Keep
class ThirdLoginBindPhoneException(
    val authId: String,
): Exception()

@Keep
@ModelForJson
data class TokenRes(
    val token: String,
    val expireTime: Long,
)

@Keep
@ModelForJson
data class LoginRes(
    val token: TokenRes,
    val userInfo: UserInfoRes,
)

@Keep
@ModelForJson
data class WxLoginRes(
    val authId: String?,
    val loginResult: LoginRes?,
)

@Keep
@ModelForJson
data class UserInfoRes(
    val id: String,
    val name: String,
    val timeCreate: Long,
)

@Keep
@ModelDto
data class TokenResDto(
    val token: String,
    val expireTime: Long,
)

@Keep
@ModelDto
data class UserInfoDto(
    val id: String,
    val name: String,
    val timeCreate: Long,
)

@Keep
@ModelDto
data class LoginResDto(
    val tokenInfo: TokenResDto,
    val userInfo: UserInfoDto,
)

@Keep
@ModelDto
data class WxLoginResDto(
    val authId: String?,
    val loginResult: LoginResDto?,
)

@Keep
data class UserInfoCacheDto(
    val id: String,
    val name: String?,
    // 过期时间
    @MillisecondTime
    val timeExpire: Long,
)

fun TokenRes.toDto() = TokenResDto(
    token = token,
    expireTime = expireTime,
)

fun UserInfoRes.toDto(): UserInfoDto {
    return UserInfoDto(
        id = id,
        name = name,
        timeCreate = timeCreate,
    )
}

fun LoginRes.toDto() = LoginResDto(
    tokenInfo = token.toDto(),
    userInfo = userInfo.toDto(),
)

fun WxLoginRes.toDto() = WxLoginResDto(
    authId = authId,
    loginResult = loginResult?.toDto(),
)

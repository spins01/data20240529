package com.xiaojinzi.tally.lib.res.model.exception

/**
 * 表示业务异常
 */
open class CommonBusinessException(
    val messageRsd: Int? = null,
    message: String? = null,
    cause: Throwable? = null,
) : RuntimeException(message, cause)

/**
 * 表示不用管的异常
 */
class CommonIgnoreException(
    cause: Throwable? = null,
) : Exception(cause)

/**
 * 未登录的异常
 */
class NotLoggedInException(
    cause: Throwable? = null,
) : CommonBusinessException(
    cause = cause,
    message = "用户未登录",
)

/**
 *  不是 vip 的异常
 */
class NotVipException(
    cause: Throwable? = null,
) : CommonBusinessException(
    cause = cause,
    message = "用户未开通 Vip",
)

/**
 *  未安装微信
 */
class WxNotInstallException(
    cause: Throwable? = null,
) : CommonBusinessException(
    cause = cause,
    message = "微信未安装",
)

/**
 * 用户 id 和上次登录不匹配
 */
class UserIdDoesNotMatchTheLastLoginException(
    cause: Throwable? = null,
) : CommonBusinessException(cause = cause)

/**
 * 没有选中的账本
 */
class NoBookSelectException(
    cause: Throwable? = null,
) : CommonBusinessException(cause = cause)

/**
 * 未同步数据的异常
 */
class NotSyncDataException(
    cause: Throwable? = null,
) : CommonBusinessException(cause = cause)

/**
 * 数值的绝对值不能超过 9999999
 */
class AbsNumberCanNotGreaterThan9999999Exception(
    cause: Throwable? = null,
) : CommonBusinessException(
    cause = cause,
    message = "值的绝对值不能超过 9999999",
)


/**
 * 上报的异常
 */
class CommonReportException(
    val reportMessage: String,
    cause: Throwable? = null,
) : CommonBusinessException(cause = cause)

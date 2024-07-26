package com.app.module.base.bean

import android.util.Log
import com.spins.module.base.R


data class BaseResponse<T>(
    val code: Int,
    val data: T,
    val message: String,
    val links: Links?,
    val count: Int?,
    val total_pages: Int?,
    val current_page: Int?,
    val error: String
)

data class UserInfoBean(
    val csv_username: String,
    val extensin_number: String,
    val role: Int, // 这个需要进行区分，需要根据这个字段，在AccountAct进行区分
    val token: String,
    val username: String
) {
    companion object {
        /**
         * role 分类
         *  管理员 = 1
         *  电销主管 = 2
         *  电销专员 = 3
         *  超级管理员 = 4
         *
         *  电销专员 只能查看自己的
         *  其他的都可以看全部
         */
        const val ROLE_ADMINISTRATOR = 1
        const val ROLE_TELEMARKETING_SUPERVISOR = 2
        const val ROLE_TELEMARKETING_STAFF = 3
        const val ROLE_SUPER_ADMINISTRATOR = 4

        // 只能查看自己的
        var isOnlyViewOwn = false
        /**
         * 校验是否是自己的
         */
        fun checkSelf(role: Int) {
            isOnlyViewOwn = role == ROLE_TELEMARKETING_STAFF
        }
    }
}

data class BasePageResponse(
    val code: Int,
    val count: Int,
    val current_page: Int,
    val `data`: List<Data>,
    val links: Links,
    val total_pages: Int
)

data class Data(
    val commissioner: String,
    val create_time: String,
    val dial_time: String,
    val dial_type: String,
    val id: Int,
    val name: String,
    val status: Int
)

open class ListParent {
    var links: Links? = null
    var count: Int? = null
    var total_pages: Int = 0
    var current_page: Int = 0
}

data class Links(
    val next: Any,
    val previous: Any
)

data class SearchBean(
    val commissioner: String,
    val create_time: String,
    val dial_time: Any,
    val dial_type: String,
    val id: Int,
    val name: String,
    val status: Int
) : ListParent() {
    companion object {
        /**
         * status
         * 1 已拨打
         * 0 未拨打
         * -1 拨打失败
         */
        const val STATUS_DIALED = 1
        const val STATUS_NOT_DIALED = 0
        const val STATUS_DIAL_FAILED = -1

        /**
         * 根据status获取字段id
         */
        fun getStringIdByStatus(status: Int? = STATUS_NOT_DIALED): Int {
            return when (status) {
                STATUS_DIALED -> return com.res.R.string.res_called
                STATUS_NOT_DIALED -> return com.res.R.string.res_not_called
                STATUS_DIAL_FAILED -> return com.res.R.string.res_failed
                else -> com.res.R.string.res_failed
            }
        }
    }

}
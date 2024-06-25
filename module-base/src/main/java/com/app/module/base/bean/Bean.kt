package com.app.module.base.bean


data class BaseResponse<T>(
    val code: Int,
    val data: T,
    val message: String,
)

data class BasePageResponse<T>(
    val code: Int,
    val count: Int,
    val current_page: Int,
    val `data`: T?,
    val links: Links,
    val total_pages: Int
)

data class Links(
    val next: Int?,
    val previous: Int?
)
data class UserInfoBean(
    val csv_username: String,
    val extensin_number: String,
    val role: Int,
    val token: String,
    val username: String
)
data class SearchBean(
    val commissioner: String,
    val create_time: String,
    val dial_time: String,
    val dial_type: String,
    val id: Int,
    val name: String,
    val status: Int
)

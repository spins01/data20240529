package com.app.module.base.bean


data class BaseResponse<T>(
    val code: Int,
    val data: T,
    val message: String,
    val token:String
)

data class BasePageResponse<T>(
    val code: Int,
    val count: Int,
    val current_page: Int,
    val data: List<T>,
    val links: Links,
    val total_pages: Int
)

data class Links(
    val next: Int?,
    val previous: Int?
)

package com.app.module.base.bean


data class BaseResponse<T>(
    val code: Int,
    val data: T,
    val message: String,
    val links:Links?,
    val count:Int? ,
    val total_pages:Int?,
    val current_page:Int?
)

data class UserInfoBean(
    val csv_username: String,
    val extensin_number: String,
    val role: Int,
    val token: String,
    val username: String
)
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
open class ListParent{
    var links:Links? = null
    var count:Int? = null
    var total_pages:Int = 0
    var current_page:Int =0
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
):ListParent()
package com.cuile.msgforward.msgreceiver

data class FeiGeResponseBody(
    val data: Data,
    val code: Int,
    val msg: String
)

data class Data(
    val count: Int
)
package com.power.suntechpower.Models

data class TreeIdataResponse(
    val childItems: List<ChildItem>
)
data class ChildItem(
    val userid: String,
    val name: String
)
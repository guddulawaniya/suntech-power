package com.power.suntechpower.Models

data class SelfIncomeResponse(
    val sno : String,
    val date : String,
    val customername : String,
    val customermobile : String,
    val customerpan : String,
    val cv : String,
    val commission : String,
    val commission_amount : String,
    val tds : String,
    val final_amount : String,
)

package com.power.suntechpower.Models

data class LevelIncomeReportResponse(
    val sno : String,
    val date : String,
    val userid : String,
    val level : String,
    val customerpname : String,
    val customerpmobile : String,
    val customerpan : String,
    val cv : String,
    val commission : String,
    val commission_amount : String,
    val tds : String,
    val final_amount : String,
    val commission_status : String,
)

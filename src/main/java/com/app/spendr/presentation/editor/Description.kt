package com.app.spendr.presentation.editor


sealed class Description(val text: String) {
    object Friend : Description("Friend")
    object Family : Description("Family")
    object Salary : Description("Salary")
    object Reward : Description("Reward")
    object Online : Description("Online")
    object Rent : Description("Rent")
    object Other : Description("Other")
    object Food : Description("Food")
    object Shopping : Description("Shopping")


}

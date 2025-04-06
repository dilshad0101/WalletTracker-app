package com.app.spendr.presentation.stats

import com.app.spendr.presentation.editor.Description

fun getRecommendation(description: String): Pair<String, String> {
    return when (description) {
        Description.Friend.text -> Pair("Friend Expense Management", "Manage your expenses when spending on friends. Consider setting limits or budgeting for friend-related activities.")
        Description.Family.text -> Pair("Family Financial Planning", "Be mindful of your spending when it comes to family. Plan your family-related expenses and ensure they align with your financial goals.")
        Description.Salary.text -> Pair("Salary Budgeting Tips", "Make sure to budget your salary effectively. Set aside savings, pay off debts, and plan your expenses wisely.")
        Description.Reward.text -> Pair("Maximizing Reward Benefits", "When using rewards or loyalty programs, compare the benefits and make informed decisions to maximize your savings or rewards.")
        Description.Online.text -> Pair("Smart Online Spending", "Exercise caution while spending online. Be mindful of impulse purchases and ensure the security of your online transactions.")
        Description.Rent.text -> Pair("Rent Expense Management", "Allocate a portion of your income towards rent. Aim to keep your rent expense within a reasonable percentage of your monthly budget.")
        Description.Other.text -> Pair("Optimizing Miscellaneous Expenses", "For expenses that fall under 'Other', review your spending habits to identify areas where you can cut back or optimize your budget.")
        Description.Food.text -> Pair("Smart Food Spending", "Create a food budget and plan your meals to avoid overspending on dining out or unnecessary grocery purchases.")
        Description.Shopping.text -> Pair("Effective Shopping Strategies", "Set a budget for shopping and differentiate between needs and wants. Avoid impulse purchases and consider alternative options for cost savings.")
        else -> {
            Pair("Friend Expense Management", "Manage your expenses when spending on friends. Consider setting limits or budgeting for friend-related activities.")
        }
    }
}
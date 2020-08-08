package org.bizties.mypantry.repository

enum class Category(val description: String) {
    CARBS("Carbs"),
    BEANS_AND_LENTILS("Beans and Lentils"),
    CANNED_FOOD("Canned Food"),
    DRINKS("Drinks"),
    DAIRY("Dairy");

    companion object {

        fun fromDescription(description: String): Category? {
            return values().find { it.description.equals(description, ignoreCase = true) }
        }
    }
}
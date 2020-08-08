package org.bizties.mypantry.repository

enum class StockType {
    IN_USE,
    IN_STOCK;

    fun isInStock(): Boolean = this == IN_STOCK
    fun isInUse(): Boolean = this == IN_USE
}
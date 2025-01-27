package com.newposandroid.data.locale

import androidx.room.Database
import androidx.room.RoomDatabase
import com.newposandroid.domain.model.MainUser

@Database(
//    entities = [MainUser::class, TerminalUsers::class, Products::class, Orders::class, OrdersProducts::class, Customers::class, Categories::class],
    entities = [MainUser::class],
    version = 1)
abstract class PosDatabase: RoomDatabase() {
//    abstract fun mainUserDao(): MainUserDao
//    abstract fun terminalUsersDao(): TerminalUsersDao
//    abstract fun categoriesDao(): CategoriesDao
//    abstract fun productsDao(): ProductsDao
//    abstract fun ordersDao(): OrdersDao
//    abstract fun customersDao(): CustomersDao
//    abstract fun ordersProductsDao(): OrdersProductsDao
}
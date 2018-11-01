package com.example.luan.synthdecor

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.Query

@Dao
interface OrderDAO {
    @Query("SELECT * FROM `order` where id = :id")
    fun getById(id: Long) : Order?

    @Query("SELECT * FROM `order`")
    fun findAll(): List<Order>

    @Insert
    fun insert(order: Order)

    @Delete
    fun delete(order: Order)

}


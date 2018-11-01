package com.example.luan.synthdecor

import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase

@Database(entities = arrayOf(Order::class), version = 1)
abstract class SynthDatabase: RoomDatabase() {
    abstract fun orderDAO(): OrderDAO
}
package com.example.luan.synthdecor

import android.arch.persistence.room.Room

object DatabaseManager {
    // singleton
    private var dbInstance: SynthDatabase

    init {
        val appContext = SynthApplication.getInstance().applicationContext
        dbInstance = Room.databaseBuilder(
                appContext, // contexto global
                SynthDatabase::class.java, // Referência da classe do banco
                "synth.sqlite" // nome do arquivo do banco

        ).build()
    }

    fun getOrderDAO(): OrderDAO {
        return dbInstance.orderDAO()
    }
}
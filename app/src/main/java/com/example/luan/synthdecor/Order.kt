package com.example.luan.synthdecor

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import java.io.Serializable

@Entity(tableName = "order")
class Order : Serializable {
    @PrimaryKey
    var id:Long = 0
    var product = ""
    var nome = ""
    var value = ""
    var quantity = ""
    var image = ""

    override fun toString(): String {
        return "Orders(nome='$nome')"
    }

    fun toJson(): String {
        return GsonBuilder().create().toJson(this)
    }
}


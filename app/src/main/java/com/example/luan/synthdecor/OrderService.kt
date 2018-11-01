package com.example.luan.synthdecor

import android.content.Context
import android.provider.CalendarContract
import android.util.Log
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.json.JSONArray
import java.net.URL

object OrderService{
    val host = "http://oliveiraluan.pythonanywhere.com"
    val TAG = "WS_Synth"

    fun getOrders(context: Context ): List<Order> {
        var orders = ArrayList<Order>()
        if (AndroidUtils.isInternetDisponivel(context)) {
            val url = "$host/orders"
            val json = HttpHelper.get(url)
            orders = parserJson(json)
            // salvar offline
            for (o in orders) {
                saveOffline(o)
            }
            return orders
        } else {
            val dao = DatabaseManager.getOrderDAO()
            val orders = dao.findAll()
            return orders
        }
    }

    fun save(order: Order): Response {
        val json = HttpHelper.post("$host/orders", order.toJson())
        return parserJson(json)
    }

    fun saveOffline(order: Order) : Boolean {
        val dao = DatabaseManager.getOrderDAO()

        if (! existsOrder(order)) {
            dao.insert(order)
        }
        return true
        }
    fun existsOrder(order: Order): Boolean {
        val dao = DatabaseManager.getOrderDAO()
        return dao.getById(order.id) != null
    }
        fun delete(order: Order): Response {
            if (AndroidUtils.isInternetDisponivel(SynthApplication.getInstance().applicationContext)) {
                val url = "$host/orders/${order.id}"
                val json = HttpHelper.delete(url)
                return parserJson(json)
            } else {
                val dao = DatabaseManager.getOrderDAO()
                dao.delete(order)
                return Response(status = "OK", msg = "Dados salvos localmente")

            }
        }

        inline fun <reified T> parserJson(json: String): T {
            val type = object : TypeToken<T>(){}.type
            return Gson().fromJson<T>(json, type)
        }
}

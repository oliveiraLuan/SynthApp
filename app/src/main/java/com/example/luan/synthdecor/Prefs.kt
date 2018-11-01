package com.example.luan.synthdecor


import android.content.SharedPreferences


object Prefs {
    val PREF_ID = "Synth"

    // retorna o armazém de preferências PREF_ID
    private fun prefs(): SharedPreferences {
        val contexto = SynthApplication.getInstance().applicationContext
        return contexto.getSharedPreferences(PREF_ID, 0)
    }

    fun setBoolean(flag: String, valor: Boolean) =  prefs().edit().putBoolean(flag, valor).apply()

    fun getBoolean(flag: String) = prefs().getBoolean(flag, false)

    fun setString(flag: String, valor: String) =  prefs().edit().putString(flag, valor).apply()

    fun getString(flag: String) = prefs().getString(flag, "")

}
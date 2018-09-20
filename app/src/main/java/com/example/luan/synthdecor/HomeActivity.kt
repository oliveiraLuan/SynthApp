package com.example.luan.synthdecor

import android.app.Activity
import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import android.support.v7.widget.Toolbar
import android.content.Intent
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import kotlinx.android.synthetic.*

class HomeActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Synth Decor"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val usuario = intent.extras.getString("username")
        val senha = intent.extras.getString("password")

        mensagem_inicial.text = "$usuario - $senha"

        button_sair.setOnClickListener{
            val returnIntent = Intent()
            returnIntent.putExtra("result", "Saída do App")
            setResult(Activity.RESULT_OK, returnIntent)
            finish()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)


        return true
    }
}

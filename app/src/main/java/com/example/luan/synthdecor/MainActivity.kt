package com.example.luan.synthdecor

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.login.*

class MainActivity : DebugActivity() {

    private val context: Context get() = this
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        campo_imagem.setImageResource(R.drawable.logo_login)

        button_login.setOnClickListener {

            val username = text_username.text.toString()
            val password = text_password.text.toString()

            if (username == "aluno" && password == "impacta") {

                Toast.makeText(context, "$username - $password", Toast.LENGTH_SHORT).show()

                val intent = Intent(context, HomeActivity::class.java)

                intent.putExtra("username", username)
                intent.putExtra("password", password)

                startActivityForResult(intent, 1)
            } else {
                Toast.makeText(context, "Usuário e senha incorretos", Toast.LENGTH_SHORT).show()
            }
        }
    }
}

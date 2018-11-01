package com.example.luan.synthdecor

import android.app.Activity
import android.content.Context
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_home.*
import android.support.v7.widget.Toolbar
import android.content.Intent
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.example.luan.synthdecor.R.id.myhome


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
class HomeActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this
    private var orders = listOf<Order>()
    var recyclerOrders: RecyclerView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Synth Decor"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // configuração do menu lateral
        configuraMenuLateral()

        recyclerOrders = findViewById<RecyclerView>(R.id.recyclerOrders)
        recyclerOrders?.layoutManager = LinearLayoutManager(context)
        recyclerOrders?.itemAnimator = DefaultItemAnimator()
        recyclerOrders?.setHasFixedSize(true)
    }

    override fun onResume() {
        super.onResume()
        taskOrders()
    }


    fun taskOrders() {

        // Criar a Thread
        Thread {
            // Código para procurar as disciplinas
            // que será executado em segundo plano / Thread separada
            this.orders = OrderService.getOrders(context)
            runOnUiThread {
                // Código para atualizar a UI com a lista de disciplinas
                recyclerOrders?.adapter = OrderAdapter(this.orders) { onClickOrder(it) }
                // enviar notificação
                enviaNotificacao(this.orders.get(0))

            }
        }.start()

    }


    fun enviaNotificacao(order: Order) {
        // Intent para abrir tela quando clicar na notificação
        val intent = Intent(this, OrderActivity::class.java)
        // parâmetros extras
        intent.putExtra("pedido", order)
        // Disparar notificação
        NotificationUtil.create(this, 1, intent, "Synth", "Você tem um novo ${order.nome}")
    }


    fun onClickOrder(order: Order) {
        Toast.makeText(context, "Clicou no pedido: ${order.id}", Toast.LENGTH_SHORT).show()
        val intent = Intent(context, OrderDetailActivity::class.java)
        intent.putExtra("pedido: ", order)
        startActivity(intent)

    }
//

    // configuração do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral =  findViewById<DrawerLayout>(R.id.layoutMenuLateral)

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)

        navigationView.setCheckedItem(myhome)
    }

    // método que deve ser implementado quando a activity implementa a interface NavigationView.OnNavigationItemSelectedListener
    // para tratar os eventos de clique no menu lateral
    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        val intent_home = Intent(context, HomeActivity::class.java)
        val intent_cart = Intent(context, CartActivity::class.java)
        val intent_notification = Intent(context, NotificationActivity::class.java)
        val intent_myaccount = Intent(context, MyAccountActivity::class.java)
        val intent_orders = Intent(context, OrderActivity::class.java)

        when (item.itemId) {
            R.id.myhome -> startActivityForResult(intent_home, 1)
            R.id.myaccount -> startActivityForResult(intent_myaccount, 1)
            R.id.mycart -> startActivityForResult(intent_cart, 1)
            R.id.myorders -> startActivityForResult(intent_orders, 1)
            R.id.mynotifications -> startActivityForResult(intent_notification, 1)
            R.id.botao_sair_app -> finish()
        }



        val drawer = findViewById<DrawerLayout>(R.id.layoutMenuLateral)
        drawer.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        (menu?.findItem(R.id.action_pesquisar)?.actionView as SearchView).setOnQueryTextListener(
                object : SearchView.OnQueryTextListener {
                    override fun onQueryTextChange(newText: String): Boolean {
                        return false
                    }
                    override fun onQueryTextSubmit(query: String): Boolean {
                        return false
                    }
                })
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        val id = item?.itemId
        val intent_cart = Intent(context, CartActivity::class.java)
        val intent_notification = Intent(context, NotificationActivity::class.java)

        when (id) {
            R.id.action_carrinho -> startActivityForResult(intent_cart, 1)
            R.id.action_notification -> startActivityForResult(intent_notification, 1)

            android.R.id.home -> finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
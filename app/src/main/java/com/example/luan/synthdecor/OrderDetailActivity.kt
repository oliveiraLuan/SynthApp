package com.example.luan.synthdecor

import android.content.Context
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.widget.SearchView
import android.support.v7.widget.Toolbar
import android.view.Menu
import android.view.MenuItem

class OrderDetailActivity : DebugActivity(), NavigationView.OnNavigationItemSelectedListener {

    private val context: Context get() = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)

        supportActionBar?.title = "Detalhes do Pedido"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // configuração do menu lateral
        configuraMenuLateral()
    }

    // configuração do navigation Drawer com a toolbar
    private fun configuraMenuLateral() {
        var toolbar = findViewById<Toolbar>(R.id.toolbar)
        var menuLateral = findViewById<DrawerLayout>(R.id.layoutMenuLateral)

        // ícone de menu (hamburger) para mostrar o menu
        var toogle = ActionBarDrawerToggle(this, menuLateral, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close)

        menuLateral.addDrawerListener(toogle)
        toogle.syncState()

        val navigationView = findViewById<NavigationView>(R.id.menu_lateral)
        navigationView.setNavigationItemSelectedListener(this)
    }

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
            R.id.botao_sair_app -> System.exit(0)
        }

        if (item.isChecked) item.setChecked(true) else item.setChecked(false)

        // fecha menu depois de tratar o evento
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
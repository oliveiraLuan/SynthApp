package com.example.luan.synthdecor

import android.support.v7.widget.CardView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import com.squareup.picasso.Picasso

class OrderAdapter (
        val orders: List<Order>,
        val onClick: (Order) -> Unit):
        RecyclerView.Adapter<OrderAdapter.OrdersViewHolder>() {
    // ViewHolder com os elemetos da tela
    class OrdersViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val cardNome: TextView
        val cardImg : ImageView
        var cardProgress: ProgressBar
        var cardView: CardView
        init {
            cardNome = view.findViewById<TextView>(R.id.cardNome)
            cardImg = view.findViewById<ImageView>(R.id.cardImg)
            cardProgress = view.findViewById<ProgressBar>(R.id.cardProgress)
            cardView = view.findViewById<CardView>(R.id.card_orders)
        }
    }
    // Quantidade de pedidos na lista
    override fun getItemCount() = this.orders.size
    // inflar layout do adapter
    override fun onCreateViewHolder(
            parent: ViewGroup, viewType: Int): OrdersViewHolder {
// infla view no adapter
        val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.adapter_order, parent, false)
// retornar ViewHolder
        val holder = OrdersViewHolder(view)
        return holder
    }

    // bind para atualizar Views com os dados
    override fun onBindViewHolder(holder: OrdersViewHolder, position: Int) {
        val context = holder.itemView.context

        val order = orders[position]

        holder.cardNome.text = order.nome
        holder.cardProgress.visibility = View.VISIBLE

        Picasso.with(context).load(order.image).fit().into(holder.cardImg,
                object: com.squareup.picasso.Callback{
                    override fun onSuccess() {
                        holder.cardProgress.visibility = View.GONE
                    }
                    override fun onError() {
                        holder.cardProgress.visibility = View.GONE
                    }
                })

        holder.itemView.setOnClickListener {onClick(order)}
    }
}
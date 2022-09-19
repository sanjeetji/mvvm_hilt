package com.sanjeet.simpletrancsaction.view.Home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjeet.simpletrancsaction.databinding.TransactionsItemBinding
import com.sanjeet.simpletrancsaction.domain.model.TransactionDetails

class TransactionAdapter : RecyclerView.Adapter<TransactionAdapter.MyViewHolder>() {


    private var listener: ((TransactionDetails) -> Unit)? = null

    var list = mutableListOf<TransactionDetails>()

    fun setContentList(list: MutableList<TransactionDetails>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val viewHolder: TransactionsItemBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            TransactionsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l: (TransactionDetails) -> Unit) {
        listener = l
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.transactions = this.list[position]

        holder.viewHolder.root.setOnClickListener {
            listener?.let {
                it(this.list[position])
            }
        }

    }

    override fun getItemCount(): Int {
        return this.list.size
    }
}
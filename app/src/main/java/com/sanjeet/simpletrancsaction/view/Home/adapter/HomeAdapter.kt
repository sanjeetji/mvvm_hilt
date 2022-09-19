package com.sanjeet.simpletrancsaction.view.Home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.sanjeet.simpletrancsaction.databinding.AccountsItemBinding
import com.sanjeet.simpletrancsaction.domain.model.AccountDetails

class HomeAdapter : RecyclerView.Adapter<HomeAdapter.MyViewHolder>() {


    private var listener: ((AccountDetails) -> Unit)? = null

    var list = mutableListOf<AccountDetails>()

    fun setContentList(list: MutableList<AccountDetails>) {
        this.list = list
        notifyDataSetChanged()
    }

    class MyViewHolder(val viewHolder: AccountsItemBinding) :
        RecyclerView.ViewHolder(viewHolder.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val binding =
            AccountsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MyViewHolder(binding)
    }

    fun itemClickListener(l: (AccountDetails) -> Unit) {
        listener = l
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.viewHolder.accounts = this.list[position]

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
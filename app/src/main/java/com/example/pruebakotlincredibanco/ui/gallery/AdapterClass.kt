package com.example.pruebakotlincredibanco.ui.gallery

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebakotlincredibanco.R
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain


class AdapterClass(private var transactionList: List<TransactionDomain>) : RecyclerView.Adapter<AdapterClass.ViewHolderClass>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = transactionList[position]
        holder.rvReceiptId.text = "receiptId: ${currentItem.receiptId}"
        holder.rvRrn.text = "rrn: ${currentItem.rrn}"
        holder.rvStatusCode.text = "statusCode: ${currentItem.statusCode}"
        holder.rvStatusDescription.text = "statusDescription: ${currentItem.statusDescription}"

    }


    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var rvReceiptId: TextView = itemView.findViewById(R.id.tvReceiptId)
        val rvRrn: TextView = itemView.findViewById(R.id.tvRrn)
        val rvStatusCode: TextView = itemView.findViewById(R.id.tvStatusCode)
        val rvStatusDescription : TextView = itemView.findViewById(R.id.tvStatusDescription)

    }

    fun updateData(newTransactionList: List<TransactionDomain>) {
        transactionList = newTransactionList
        notifyDataSetChanged()
    }
}
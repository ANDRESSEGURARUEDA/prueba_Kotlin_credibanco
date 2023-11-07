package com.example.pruebakotlincredibanco.ui.slideshow

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebakotlincredibanco.R
import com.example.pruebakotlincredibanco.data.database.entity.TransactionsEntity
import javax.inject.Inject


class AdapterSearch @Inject constructor(private var transactionList: List<TransactionsEntity>) : RecyclerView.Adapter<AdapterSearch.ViewHolderClass>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolderClass {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recycle_search_layout, parent, false)
        return ViewHolderClass(itemView)
    }

    override fun getItemCount(): Int {
        return transactionList.size
    }

    override fun onBindViewHolder(holder: ViewHolderClass, position: Int) {
        val currentItem = transactionList[position]
        val formattedText = "Receipt ID: ${currentItem.receiptId}\n" +
                "RRN: ${currentItem.rrn}\n" +
                "Status Code: ${currentItem.statusCode}\n" +
                "Status Description: ${currentItem.statusDescription}"

        holder.textView.text = formattedText
    }


    class ViewHolderClass(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val textView : TextView = itemView.findViewById(R.id.tvTransasctionAll)

    }

    fun updateData(newTransactionList: List<TransactionsEntity>) {
        transactionList = newTransactionList
        notifyDataSetChanged()
    }
}
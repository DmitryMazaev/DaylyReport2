package com.example.daylyreport.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daylyreport.databinding.NewWorkItemBinding

class TypeOfWorkAdapter() : RecyclerView.Adapter<TypeOfWorkAdapter.ViewHolder>() {
    private var countElements: Int = 0
    fun setData() {
        countElements +=1
        notifyDataSetChanged()

    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            NewWorkItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = countElements
    }

    override fun getItemCount(): Int {
        return countElements
    }

    class ViewHolder(val binding: NewWorkItemBinding) : RecyclerView.ViewHolder(binding.root)
}
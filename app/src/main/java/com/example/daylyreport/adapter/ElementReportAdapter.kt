package com.example.daylyreport.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.daylyreport.classes.Report
import com.example.daylyreport.databinding.ElementListReportItemBinding
import com.example.daylyreport.databinding.NewWorkItemBinding

class ElementReportAdapter(private val reportList: ArrayList<Report>): RecyclerView.Adapter<ElementReportAdapter.ViewHolder>() {
    class ViewHolder(val binding: ElementListReportItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ElementReportAdapter.ViewHolder(
            ElementListReportItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return reportList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = reportList[position]
        holder.apply {
            binding.keyDaylyReport.text = "тут будет ключ отчета"
            binding.date.text = item.dateOfWork
            binding.constructionObject.text = item.constructionObject
            binding.foreman.text = "прораб"
        }
    }
}
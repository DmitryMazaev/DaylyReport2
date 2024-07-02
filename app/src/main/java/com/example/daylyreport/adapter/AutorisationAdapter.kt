package com.example.daylyreport.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.daylyreport.AutorisationFragmentDirections
import com.example.daylyreport.ListReportFragmentDirections
import com.example.daylyreport.databinding.ElementListForemansItemBinding
//import com.example.daylyreport.ListReportFragmentDirections
import com.example.daylyreport.entitys.Report
import com.example.daylyreport.databinding.ElementListReportItemBinding
import com.example.daylyreport.databinding.FragmentAutorisationBinding
import com.example.daylyreport.entitys.Foreman

class AutorisationAdapter(private val foremansList: ArrayList<Foreman>): RecyclerView.Adapter<AutorisationAdapter.ViewHolder>() {

    class ViewHolder(val binding: ElementListForemansItemBinding): RecyclerView.ViewHolder(binding.root)
    var onItemClick: ((Foreman) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return AutorisationAdapter.ViewHolder(
            ElementListForemansItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return foremansList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = foremansList[position]
        holder.apply {
            binding.foremanName.text = item.name.toString()
        }
        holder.binding.root.setOnClickListener {
            onItemClick?.invoke(item)
        }
    }

}
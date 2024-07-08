package com.example.daylyreport.adapter

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import androidx.core.view.children
import androidx.recyclerview.widget.RecyclerView
import com.example.daylyreport.R
import com.example.daylyreport.databinding.NewMaterialItemBinding
import com.example.daylyreport.databinding.NewWorkItemBinding
import com.example.daylyreport.entitys.Material
import com.google.android.material.textfield.TextInputEditText

class TypeOfWorkAdapter() : RecyclerView.Adapter<TypeOfWorkAdapter.ViewHolder>() {
    private var countElements: Int = 0

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
        val context = holder.binding.root.context
        holder.binding.buttonAddNewMaterial.setOnClickListener {
            val view = MaterialView(context)
            holder.binding.newMaterialRecyclerView.addView(view)
        }
    }

    override fun getItemCount(): Int {
        return countElements
    }

    class ViewHolder(val binding: NewWorkItemBinding) : RecyclerView.ViewHolder(binding.root)
}
class MaterialView @JvmOverloads constructor(
    context: Context,
    attrSet: AttributeSet? = null,
    a: Int = 0
) : FrameLayout(context, attrSet, a) {

    val binding: NewMaterialItemBinding
    init {
        binding = NewMaterialItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

}
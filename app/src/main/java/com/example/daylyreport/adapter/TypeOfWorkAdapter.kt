package com.example.daylyreport.adapter

import android.content.Context
import android.util.AttributeSet
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
val context = holder.binding.root.context
        val materials = listOf<Material>(
            Material("1", "1", 3.0),
            Material("2", "2", 3.0),
        )
        materials.forEach {
            val view = MaterialView(context)
            view.setALlText(it.toString())
            holder.binding.newMaterialRecyclerView.addView(view)
            /*holder.binding.newMaterialRecyclerView.children.forEach {
                (it as? MaterialView)?.let {
                 it.setALlText()
                }
            }

            val view2 = holder.binding.newMaterialRecyclerView.children.find {
                (it as? MaterialView)?.materialId == "myId"
            } as MaterialView
            view.setALlText()*/

            val materials = holder.binding.newMaterialRecyclerView.children
                .mapNotNull { it as? MaterialView }
                .map { view ->
                   // view.createMaterial()
                    val mateName = view.getMaterialName()

                    Material(
                        mateName,
                        "",
                        1.0
                    )
                }
        }

        /*

        offers.forech { offer ->
            item { Offer() }
            offer.materials.forEch {
                item { Material }
            }
        }

         */
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

    val materialId = ""

    val binding: NewMaterialItemBinding
    init {
        binding = NewMaterialItemBinding.inflate(LayoutInflater.from(context), this, true)
    }

    fun setALlText(text: String) {
        binding.materialEditTextForEnter.setText(text)
    }

    fun getMaterialName(): String {
        return  binding.materialEditTextForEnter.text.toString()
    }
}
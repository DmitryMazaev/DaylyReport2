package com.example.daylyreport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.BundleCompat
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daylyreport.adapter.TypeOfWorkAdapter
import com.example.daylyreport.classes.ReportViewModel
import com.example.daylyreport.databinding.FragmentReportBinding
import com.example.daylyreport.databinding.NewMaterialItemBinding
import com.example.daylyreport.databinding.NewWorkItemBinding
import com.example.daylyreport.entitys.Location
import com.example.daylyreport.entitys.Material
import com.example.daylyreport.entitys.Report
import com.example.daylyreport.entitys.TypeOfWork
import com.example.daylyreport.entitys.TypicalWork
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.database.FirebaseDatabase
import java.util.Calendar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ReportFragment : Fragment() {
    
    private var _binding: FragmentReportBinding? = null
    
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: ReportViewModel by viewModels()
    private val typeOfWorkAdapter = TypeOfWorkAdapter()
    private val calendar = Calendar.getInstance()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    val args by navArgs<ReportFragmentArgs>()
    private val firebase = FirebaseDatabase.getInstance().getReference("reportList")
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inflater = LayoutInflater.from(requireContext())
        //viewModel.load(args.id)
        //Вставка id в отчет
        //binding.reportIdEditText.setText(args.reportId)
        
        val report =
            arguments?.let { BundleCompat.getParcelable(it, REPORT_KEY, Report::class.java) }
                ?: Report()
        
        with(binding) {
//            reportIdEditText.setText(report.reportId)
//            constructionObjectEditTextForEnter.setText(report.constructionObject)
//            dateFromDateAndTime.setText(report.dateOfWork)
            
        }
        
        binding.buttonAddNewReport.setOnClickListener {
            val workList = binding.newWorkRecyclerView.children.map { work ->
                val typeOfWork =
                    work.findViewById<TextInputEditText>(R.id.typical_work_edit_text_for_enter).text.toString()
                val comment =
                    work.findViewById<TextInputEditText>(R.id.location_comment_edit_text_for_enter).text.toString()
                
                val materials = work.findViewById<LinearLayout>(R.id.new_material_recycler_view)
                val materialList = materials.children.map { material ->
                    val material =
                        materials.findViewById<TextInputEditText>(R.id.material_edit_text_for_enter).text.toString()
                    val quantity =
                        materials.findViewById<TextInputEditText>(R.id.quantity_of_work_edit_text_for_enter).text.toString()
                            .toDouble()
                    Material(material, quantity)
                }.toList()
                TypeOfWork(
                    TypicalWork(typeOfWork),
                    Location(commentLocation = comment),
                    materialList,
                    null,
                    null
                )
            }.toList()
            
            val updatedReport = report.copy(typeOfWorkList = workList)
            firebase.child(updatedReport.reportId).setValue(updatedReport)
            
            viewModel.addNewReportAlt(binding)
            findNavController().navigate(R.id.action_ReportFragment_to_ListReportFragment)
        }
        binding.buttonAddNewWork.setOnClickListener {
            val newWorkItemView = NewWorkItemBinding.inflate(inflater)
            binding.newWorkRecyclerView.addView(newWorkItemView.root)
            newWorkItemView.buttonAddNewMaterial.setOnClickListener {
                val materialView = NewMaterialItemBinding.inflate(inflater)
                newWorkItemView.newMaterialRecyclerView.addView(materialView.root)
            }
        }
        
        binding.buttonUpdateReport.setOnClickListener {
            viewModel.updateReport(binding, args)
        }
        
    }
    
    companion object {
        const val REPORT_KEY = "report"
    }
}


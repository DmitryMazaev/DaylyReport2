package com.example.daylyreport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.os.BundleCompat
import androidx.core.view.children
import androidx.core.view.size
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daylyreport.adapter.TypeOfWorkAdapter
import com.example.daylyreport.classes.ReportViewModel
import com.example.daylyreport.databinding.FragmentReportBinding
import com.example.daylyreport.databinding.NewMaterialItemBinding
import com.example.daylyreport.databinding.NewPersonnelItemBinding
import com.example.daylyreport.databinding.NewTransportItemBinding
import com.example.daylyreport.databinding.NewWorkItemBinding
import com.example.daylyreport.entitys.Location
import com.example.daylyreport.entitys.Material
import com.example.daylyreport.entitys.Personnel
import com.example.daylyreport.entitys.Report
import com.example.daylyreport.entitys.TransportVehicle
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
        

        
        /*with(binding) {
            reportIdEditText.setText(report.reportId)
            constructionObjectEditTextForEnter.setText(report.constructionObject)
            dateFromDateAndTime.setText(report.dateOfWork)
            
        }*/
        
        binding.buttonAddNewReport.setOnClickListener {
            addNewReport()
        }
        binding.buttonAddNewWork.setOnClickListener {
            val newWorkItemView = NewWorkItemBinding.inflate(inflater)
            binding.newWorkRecyclerView.addView(newWorkItemView.root)
            newWorkItemView.buttonAddNewMaterial.setOnClickListener {
                val materialView = NewMaterialItemBinding.inflate(inflater)
                newWorkItemView.newMaterialRecyclerView.addView(materialView.root)
            }
            newWorkItemView.buttonAddNewTransport.setOnClickListener {
                val transportView = NewTransportItemBinding.inflate(inflater)
                newWorkItemView.newTransportRecyclerView.addView(transportView.root)
            }
            newWorkItemView.buttonAddNewPersonnel.setOnClickListener {
                val personnelView = NewPersonnelItemBinding.inflate(inflater)
                newWorkItemView.newPersonnelRecyclerView.addView(personnelView.root)
            }
        }
        
        binding.buttonDate.setOnClickListener {
            viewModel.enterDate(binding, parentFragmentManager)
        }
        
    }
    
    companion object {
        const val REPORT_KEY = "report"
    }

    private fun addNewReport() {
        val report =
            arguments?.let { BundleCompat.getParcelable(it, REPORT_KEY, Report::class.java) }
                ?: Report()
        val workList = binding.newWorkRecyclerView.children.map { work ->
            val typeOfWork =
                work.findViewById<TextInputEditText>(R.id.typical_work_edit_text_for_enter).text.toString()
            val beginningPiket = work.findViewById<TextInputEditText>(R.id.pk_start_edit_text_for_enter).text.toString()
            val beginningPlus = work.findViewById<TextInputEditText>(R.id.plus_start_edit_text_for_enter).text.toString()
            val endingPiket = work.findViewById<TextInputEditText>(R.id.pk_end_edit_text_for_enter).text.toString()
            val endingPlus = work.findViewById<TextInputEditText>(R.id.plus_end_edit_text_for_enter).text.toString()
            val comment =
                work.findViewById<TextInputEditText>(R.id.location_comment_edit_text_for_enter).text.toString()
            val quantityOfWork = work.findViewById<TextInputEditText>(R.id.quantity_of_work_edit_text_for_enter).text.toString()
            val materials = work.findViewById<LinearLayout>(R.id.new_material_recycler_view)
            val materialList = materials.children.map { materialView ->
                val material =
                    materialView.findViewById<TextInputEditText>(R.id.material_edit_text_for_enter).text.toString()
                val quantity =
                    materialView.findViewById<TextInputEditText>(R.id.quantity_of_material_edit_text_for_enter).text.toString()
                        .toDouble()
                Material(material, quantity)
            }.toList()
            val transports = work.findViewById<LinearLayout>(R.id.new_transport_recycler_view)
            val transportList = transports.children.map { transport ->
                val transport =
                    transports.findViewById<TextInputEditText>(R.id.transport_edit_text_for_enter).text.toString()
                val quantity =
                    transports.findViewById<TextInputEditText>(R.id.quantity_of_transport_edit_text_for_enter).text.toString()
                        .toDouble()
                TransportVehicle(transport, quantity)
            }.toList()
            val personnel = work.findViewById<LinearLayout>(R.id.new_personnel_recycler_view)
            val personnelList = personnel.children.map { person ->
                val person =
                    personnel.findViewById<TextInputEditText>(R.id.personnel_edit_text_for_enter).text.toString()
                val quantity =
                    personnel.findViewById<TextInputEditText>(R.id.quantity_of_personnel_edit_text_for_enter).text.toString()
                        .toDouble()
                Personnel(person, quantity)
            }.toList()
            TypeOfWork(
                TypicalWork(typeOfWork = typeOfWork, quantityOfWork = quantityOfWork.toDouble()),
                Location(beginningPiket = beginningPiket, beginningPlus = beginningPlus, endingPiket = endingPiket, endingPlus = endingPlus, commentLocation = comment),
                materialList,
                transportList,
                personnelList
            )
        }.toList()
        val updatedReport = report.copy(typeOfWorkList = workList)
        firebase.child(updatedReport.reportId).setValue(updatedReport)

        viewModel.addNewReportAlt(binding)
        findNavController().navigate(R.id.action_ReportFragment_to_ListReportFragment)
    }

}


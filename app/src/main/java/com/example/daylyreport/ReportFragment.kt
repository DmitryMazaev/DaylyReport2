package com.example.daylyreport

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.os.BundleCompat
import androidx.core.view.children
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.daylyreport.classes.ReportViewModel
import com.example.daylyreport.databinding.FragmentReportBinding
import com.example.daylyreport.databinding.NewMaterialItemBinding
import com.example.daylyreport.databinding.NewPersonnelItemBinding
import com.example.daylyreport.databinding.NewTransportItemBinding
import com.example.daylyreport.databinding.NewWorkItemBinding
import com.example.daylyreport.entitys.ConstructionObject
import com.example.daylyreport.entitys.Foreman
import com.example.daylyreport.entitys.Location
import com.example.daylyreport.entitys.Material
import com.example.daylyreport.entitys.Personnel
import com.example.daylyreport.entitys.Report
import com.example.daylyreport.entitys.TransportVehicle
import com.example.daylyreport.entitys.TypeOfWork
import com.example.daylyreport.entitys.TypicalWork
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.timepicker.MaterialTimePicker
import com.google.firebase.database.FirebaseDatabase
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.LocalTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ReportFragment : Fragment() {
    
    private var _binding: FragmentReportBinding? = null
    
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ReportViewModel by viewModels()
    private val calendar = Calendar.getInstance()
    private var constructionObjectList = listOf<ConstructionObject>()
    private var typicalWorkList = listOf<TypicalWork>()
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    private val firebase = FirebaseDatabase.getInstance().getReference("reportList")
    
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val inflater = LayoutInflater.from(requireContext())
        
        val report = arguments?.let {
            BundleCompat.getParcelable(it, REPORT_KEY, Report::class.java)
        } ?: Report()
        
        showReport(report)
        setConstructionObjectAdapter()

        
        binding.buttonAddNewReport.setOnClickListener {
            addNewReport(report)
        }
        binding.buttonAddNewWork.setOnClickListener {
            val newWorkItemView = NewWorkItemBinding.inflate(inflater)
            binding.newWorkRecyclerView.addView(newWorkItemView.root)
            setTypicalWorkAdapter()
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
            val dateDialog = MaterialDatePicker.Builder.datePicker()
                .build()
            
            dateDialog.addOnPositiveButtonClickListener {timeInMillis ->
                calendar.timeInMillis = timeInMillis
                val dateFormat = SimpleDateFormat("dd-MM-yyyy")
                Snackbar.make(binding.buttonDate, dateFormat.format(calendar.time), Snackbar.LENGTH_SHORT).show()
                binding.dateFromDateAndTime.text = dateFormat.format(calendar.time)
            }
            dateDialog.show(parentFragmentManager, "DatePicker")
        }
        
        binding.buttonTime.setOnClickListener {
            val timePicker = MaterialTimePicker.Builder().build()
            timePicker.addOnPositiveButtonClickListener {
                val time = LocalTime.of(timePicker.hour, timePicker.minute)
                val dtf = DateTimeFormatter.ofPattern("HH:mm")
                binding.timeFromDateAndTime.text = time.format(dtf)
            }
            timePicker.show(parentFragmentManager, null)
        }
        
    }
    
    companion object {
        const val REPORT_KEY = "report"
    }
    
    private fun showReport(report: Report) {
        binding.foremanEditText.setText(report.foreman?.name)
        binding.reportIdEditText.setText(report.reportId)
        binding.autoCompleteConstructionObject.setText(report.constructionObject?.name)
        binding.dateFromDateAndTime.setText(report.dateOfWork)
        binding.timeFromDateAndTime.setText(report.timeOfWork)
        report.typeOfWorkList.forEach { work ->
            val workView = NewWorkItemBinding.inflate(layoutInflater)
            workView.typicalWorkEditText.setText(work.typicalWork?.typeOfWork.toString())
            workView.pkStartEditTextForEnter.setText(work.location?.beginningPiket)
            workView.plusStartEditTextForEnter.setText(work.location?.beginningPlus)
            workView.pkEndEditTextForEnter.setText(work.location?.endingPiket)
            workView.plusEndEditTextForEnter.setText(work.location?.endingPlus)
            workView.locationCommentEditTextForEnter.setText(work.location?.commentLocation)
            workView.quantityOfWorkEditTextForEnter.setText(work.typicalWork?.quantityOfWork.toString())
            work.materialList.forEach {
                val materialView = NewMaterialItemBinding.inflate(layoutInflater)
                materialView.materialEditTextForEnter.setText(it.nameOfMaterial)
                materialView.quantityOfMaterialEditTextForEnter.setText(it.quantityOfMaterial.toString())
                workView.newMaterialRecyclerView.addView(materialView.root)
            }
            work.transportVehicleList.forEach {
                val transportView = NewTransportItemBinding.inflate(layoutInflater)
                transportView.transportEditTextForEnter.setText(it.transportNumber)
                transportView.quantityOfTransportEditTextForEnter.setText(it.timeOfWorkTransport.toString())
                workView.newTransportRecyclerView.addView(transportView.root)
            }
            work.personnelList.forEach {
                val personnelView = NewPersonnelItemBinding.inflate(layoutInflater)
                personnelView.personnelEditTextForEnter.setText(it.personnelType)
                personnelView.quantityOfPersonnelEditTextForEnter.setText(it.timeOfWorkPersonnel.toString())
                workView.newPersonnelRecyclerView.addView(personnelView.root)
            }
            binding.newWorkRecyclerView.addView(workView.root)
        }
    }

    private fun addNewReport(report: Report) {

        val workList = binding.newWorkRecyclerView.children.map { work ->
            val typeOfWork =
                work.findViewById<TextInputEditText>(R.id.typical_work_edit_text).text.toString()
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
            val transportList = transports.children.map { transportView ->
                val transport =
                    transportView.findViewById<TextInputEditText>(R.id.transport_edit_text_for_enter).text.toString()
                val quantity =
                    transportView.findViewById<TextInputEditText>(R.id.quantity_of_transport_edit_text_for_enter).text.toString()
                        .toDouble()
                TransportVehicle(transport, quantity)
            }.toList()
            val personnel = work.findViewById<LinearLayout>(R.id.new_personnel_recycler_view)
            val personnelList = personnel.children.map { personView ->
                val person =
                    personView.findViewById<TextInputEditText>(R.id.personnel_edit_text_for_enter).text.toString()
                val quantity =
                    personView.findViewById<TextInputEditText>(R.id.quantity_of_personnel_edit_text_for_enter).text.toString()
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
        val updatedReport = report.copy(
            constructionObject = ConstructionObject(binding.autoCompleteConstructionObject.text.toString()),
            dateOfWork = binding.dateFromDateAndTime.text.toString(),
            timeOfWork = binding.timeFromDateAndTime.text.toString(),
            typeOfWorkList = workList)
        firebase.child(updatedReport.reportId).setValue(updatedReport)

        findNavController().navigate(R.id.action_ReportFragment_to_ListReportFragment)
    }
    fun setConstructionObjectAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.constructionObjectFlow.collect {
                    constructionObjectList = it
                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        it.map { it.name })
                    binding.autoCompleteConstructionObject.setAdapter(adapter)
                    binding.autoCompleteConstructionObject.threshold = 1
                }
            }
        }
    }

    fun setTypicalWorkAdapter() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.typicalWorkFlow.collect {
                    typicalWorkList = it
                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        it.map { it.typeOfWork })
                    Log.e("++++++", typicalWorkList.toString())
                    binding.newWorkRecyclerView.children.map {work ->
                        work.findViewById<AutoCompleteTextView>(R.id.typical_work_edit_text).setAdapter(adapter)
                        work.findViewById<AutoCompleteTextView>(R.id.typical_work_edit_text).threshold = 1
                    }
                }
            }

        }
    }

}


package com.example.daylyreport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daylyreport.adapter.TypeOfWorkAdapter
import com.example.daylyreport.classes.ReportViewModel
import com.example.daylyreport.databinding.FragmentReportBinding
import com.google.android.material.datepicker.MaterialDatePicker
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonDate.setOnClickListener {
            enterDate()
        }
        binding.buttonAddNewReport.setOnClickListener {
            viewModel.addNewReportAlt(binding)
            findNavController().navigate(R.id.action_ReportFragment_to_ListReportFragment)
        }
        binding.buttonAddNewWork.setOnClickListener {
            binding.newWorkRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.newWorkRecyclerView.adapter = typeOfWorkAdapter
            typeOfWorkAdapter.setData()
        }
    }
    private fun enterDate() {
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
}


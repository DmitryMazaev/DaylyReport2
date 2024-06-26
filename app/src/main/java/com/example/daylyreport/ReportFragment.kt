package com.example.daylyreport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.daylyreport.adapter.ItemTypeOfWork
import com.example.daylyreport.adapter.TypeOfWorkAdapter
import com.example.daylyreport.classes.Report
import com.example.daylyreport.classes.ReportViewModel
import com.example.daylyreport.classes.TypeOfWork
import com.example.daylyreport.databinding.FragmentReportBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ReportFragment : Fragment() {

    private var _binding: FragmentReportBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: ReportViewModel by viewModels()
    private val items = mutableListOf<ItemTypeOfWork>()
    private lateinit var recyclerView: RecyclerView
    private val typeOfWorkAdapter = TypeOfWorkAdapter()
    val data:ArrayList<TypeOfWork> = ArrayList()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.buttonAddNewReport.setOnClickListener {
            val constructionObject: String = binding.constructionObjectEditTextForEnter.text.toString()
            val report = Report(
                constructionObject,
                "24.06.2024",
                "08:00",
                null,
                null,
                null)
            viewModel.addNewReport(report)
        }
        binding.buttonAddNewWork.setOnClickListener {
            //data.add(TypeOfWork(null,null, null, null, null))
            binding.newWorkRecyclerView.layoutManager = LinearLayoutManager(requireContext())
            binding.newWorkRecyclerView.adapter = typeOfWorkAdapter
            typeOfWorkAdapter.setData()
            }
        }
    }

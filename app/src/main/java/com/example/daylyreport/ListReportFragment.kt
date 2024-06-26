package com.example.daylyreport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daylyreport.adapter.ElementReportAdapter
import com.example.daylyreport.classes.Report
import com.example.daylyreport.databinding.FragmentListReportBinding
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ListReportFragment : Fragment() {

    private var _binding: FragmentListReportBinding? = null
    private val binding get() = _binding!!
    private lateinit var reportList: ArrayList<Report>
    private val firebase = FirebaseDatabase.getInstance().getReference("reportList")


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListReportBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        reportList = arrayListOf()
        fetchData()
        binding.reportListRecyclerView.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(this.context)
        }
        binding.buttonNewReport.setOnClickListener {
            findNavController().navigate(R.id.action_ListReportFragment_to_ReportFragment)
        }
    }
    private fun fetchData() {
        firebase.addValueEventListener(object: ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                reportList.clear()
                if (snapshot.exists()) {
                    for (reportOne in snapshot.children) {
                        val report = reportOne.getValue(Report::class.java)
                        reportList.add(report!!)
                    }
                }
                val elementReportAdapter = ElementReportAdapter(reportList)
                binding.reportListRecyclerView.adapter = elementReportAdapter
            }

            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
}

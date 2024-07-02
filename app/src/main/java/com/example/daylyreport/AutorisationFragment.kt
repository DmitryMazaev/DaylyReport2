package com.example.daylyreport

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.daylyreport.classes.AutorisationViewModel
import com.example.daylyreport.databinding.FragmentAutorisationBinding
import java.util.Calendar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AutorisationFragment : Fragment() {

    private var _binding: FragmentAutorisationBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    val viewModel: AutorisationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAutorisationBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.addToAutoComplite(binding)
        //val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, viewModel.listString)
        //binding.autoComplete.setAdapter(adapter)
        binding.buttonAutorisation.setOnClickListener {
            findNavController().navigate(R.id.action_AutorisationFragment_to_ListReportFragment)
        }
    }

}


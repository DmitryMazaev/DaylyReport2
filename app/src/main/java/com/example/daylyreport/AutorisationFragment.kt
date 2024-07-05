package com.example.daylyreport

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.core.content.ContentProviderCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import com.example.daylyreport.classes.AutorisationViewModel
import com.example.daylyreport.data.UserInfoRepository
import com.example.daylyreport.databinding.FragmentAutorisationBinding
import com.example.daylyreport.entitys.Foreman
import com.google.android.material.navigation.NavigationBarView.OnItemSelectedListener
import kotlinx.coroutines.launch
import java.util.Calendar

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AutorisationFragment : Fragment() {
    
    private var _binding: FragmentAutorisationBinding? = null
    
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: AutorisationViewModel by viewModels()
    private var foremanList = listOf<Foreman>()
    
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAutorisationBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.foremanFlow.collect {
                    foremanList = it
                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_list_item_1,
                        it.map { it.name })
                    binding.autoComplete.setAdapter(adapter)
                    binding.autoComplete.threshold = 1
                }
            }
        }
        binding.autoComplete.setOnItemClickListener { _, _, position, _ ->
            val foreman = foremanList[position]
            binding.loginIdEditText.setText(foreman.login)
        }

//        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_list_item_1, viewModel.listString)
        //binding.autoComplete.setAdapter(adapter)
        binding.buttonAutorisation.setOnClickListener {
            UserInfoRepository.setUser(
                Foreman(
                    binding.autoComplete.text.toString(),
                    binding.loginIdEditText.text.toString(),
                    binding.passwordEditTextForEnter.text.toString()
                )
            )
            findNavController().navigate(R.id.action_AutorisationFragment_to_ListReportFragment)
        }
    }
    
}


package com.example.pruebakotlincredibanco.ui.slideshow

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebakotlincredibanco.data.database.entity.toDatabase
import com.example.pruebakotlincredibanco.databinding.FragmentSlideshowBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SlideshowFragment : Fragment() {

    private var _binding: FragmentSlideshowBinding? = null
    private lateinit var recycleView: RecyclerView
   // private val slideshowViewModel: SlideshowViewModel by viewModels()

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val slideshowViewModel =
            ViewModelProvider(this).get(SlideshowViewModel::class.java)

        _binding = FragmentSlideshowBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvTitle
        slideshowViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }

        slideshowViewModel.onCreate()
        val adapter = AdapterSearch(emptyList())
        recycleView = binding.rvTransaction
        recycleView.layoutManager = LinearLayoutManager(requireContext())
        recycleView.adapter = adapter
        slideshowViewModel.observeTransactions().observe(viewLifecycleOwner) { transactions ->
            if (transactions != null) {
                adapter.updateData(transactions.map { it.toDatabase() })
            }
        }
        return root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
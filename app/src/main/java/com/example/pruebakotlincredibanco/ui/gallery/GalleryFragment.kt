package com.example.pruebakotlincredibanco.ui.gallery

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.pruebakotlincredibanco.databinding.FragmentGalleryBinding
import com.example.pruebakotlincredibanco.domain.model.TransactionDomain
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class GalleryFragment : Fragment() {

    private var _binding: FragmentGalleryBinding? = null
    private lateinit var recycleView: RecyclerView
    private val galleryViewModel: GalleryViewModel by viewModels()

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val galleryViewModel =
            ViewModelProvider(this).get(GalleryViewModel::class.java)

        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvTitle
        galleryViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        getTransactionById()

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun getTransactionById(){
        binding.etSearch.setOnClickListener {
            val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
            val receipId = binding.etSearch.text.toString()
            if(receipId.isEmpty()){
                Snackbar.make(requireView(), "verifica tu numero de recibo", Snackbar.LENGTH_SHORT).show()
            }
            galleryViewModel.onCreate(receipId)
            galleryViewModel.mensajeResultado.observe(viewLifecycleOwner, Observer { mensaje ->
                Snackbar.make(
                    requireView(),
                    mensaje,
                    Snackbar.LENGTH_LONG
                ).show()
            })
            val adapter = AdapterClass(emptyList())
            recycleView = binding.recyclerView
            recycleView.layoutManager = LinearLayoutManager(requireContext())
            recycleView.adapter = adapter
            galleryViewModel.observeTransactions().observe(viewLifecycleOwner) { transactions ->
                if (transactions != null) {
                    adapter.updateData(transactions)
                    annulmetTransaction(transactions[0])
                }
            }
        }
    }

     fun annulmetTransaction(transactions : TransactionDomain?) {
         binding.btnCancel.setOnClickListener {
             if (transactions != null) {
                 galleryViewModel.annulmentTransaction(transactions)
                 val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                 inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
                 galleryViewModel.mensajeResultado.observe(viewLifecycleOwner, Observer { mensaje ->
                     Snackbar.make(
                         requireView(),
                         mensaje,
                         Snackbar.LENGTH_LONG
                     ).show()
                 })
             }
         }
    }

}
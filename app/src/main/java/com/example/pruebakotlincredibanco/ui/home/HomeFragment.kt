package com.example.pruebakotlincredibanco.ui.home

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
import com.example.pruebacredibanco.network.TransactionModel
import com.example.pruebakotlincredibanco.databinding.FragmentHomeBinding
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val homeViewModel: HomeViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val homeViewModel =
            ViewModelProvider(this).get(HomeViewModel::class.java)

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val textView: TextView = binding.tvTitle
        homeViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        }
        authorizacion()
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    fun authorizacion(){
        binding.btnSend.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch{
                val transactionModel = TransactionModel(
                    binding.etId.text.toString(),
                    binding.etCommerceCode.text.toString(),
                    binding.etTerminalCode.text.toString(),
                    binding.etAmount.text.toString(),
                    binding.etCard.text.toString()
                )
                if (transactionModel.id?.isEmpty() == true && transactionModel.commerceCode?.isEmpty() == true && transactionModel.terminalCode?.isEmpty() == true && transactionModel.amount?.isEmpty() == true && transactionModel.card?.isEmpty() == true) {
                } else if (!transactionModel.card?.equals("1234567890123456")!!) {
                    Snackbar.make(requireView(), "La tarjeta no existe", Snackbar.LENGTH_LONG)
                        .show()
                }
                homeViewModel.onCreate(transactionModel)
                withContext(Dispatchers.Main) {
                    val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                    inputMethodManager.hideSoftInputFromWindow(view?.windowToken, 0)
                    homeViewModel.mensajeResultado.observe(viewLifecycleOwner, Observer { mensaje ->
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


}
package com.example.lugares.ui.lugar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.lugares.R
import com.example.lugares.databinding.FragmentAddLugarBinding

import com.example.lugares.model.Lugar
import com.example.lugares.viewmodel.LugarViewModel

class AddLugarFragment : Fragment() {
   //El objeto para interactuar con la tabla
   private lateinit var lugarViewModel:LugarViewModel
    private var _binding: FragmentAddLugarBinding? = null
    //jjiojoijiojiojioj


    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        _binding = FragmentAddLugarBinding.inflate(inflater, container, false)


binding.btAgregar.setOnClickListener{ addLugar()}

        return binding.root
    }
    private fun addLugar() {
        val nombre =
            binding.etNombre.text.toString() //obtiene el texto de lo que el usuario escribio
        if (nombre.isNotEmpty()) { // si se escribio algo en el nombre se guarda
            val correo =
                binding.etCorreo.text.toString() //obtiene el texto de lo que el usuario escribio
            val telefono =
                binding.etTelefono.text.toString() //obtiene el texto de lo que el usuario escribio
            val web = binding.etWeb.text.toString() //obtiene el texto de lo que el usuario escribio
            val lugar = Lugar(0, nombre, correo, telefono, web, 0.0, 0.0, 0.0, "", "")

            //Se procede a registrar el nuevo lugar...

            lugarViewModel.saveLugar(lugar)
            Toast.makeText(
                requireContext(), getString(R.string.msg_lugar_added),
                Toast.LENGTH_SHORT
            ).show()
            findNavController().navigate(R.id.action_addLugarFragment_to_nav_lugar)
        }else{//No se puede registrar el lugar... falta info
            Toast.makeText(requireContext(),
                getString(R.string.msg_data),
                Toast.LENGTH_LONG).show()

        }
    }
override fun onDestroyView(){
    super.onDestroyView()
    _binding=null
}

    }

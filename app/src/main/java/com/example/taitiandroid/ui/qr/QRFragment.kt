package com.example.taitiandroid.ui.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.taitiandroid.databinding.FragmentQrBinding

import java.util.Scanner

class QRFragment : Fragment() {

   private var _binding: FragmentQrBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    //Creamos la vista
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        //Llamamos a la clase que va a crear el QR
        val QRdViewModel =
            ViewModelProvider(this)[QRViewModel::class.java]

        _binding = FragmentQrBinding.inflate(inflater, container, false)
        val root:View=binding.root
        //Almacenamos el QR en el ImageView que tenemos en el fragment
        val qrImage: ImageView=binding.imageViewQR
        qrImage.setImageBitmap(QRdViewModel.bmp)

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
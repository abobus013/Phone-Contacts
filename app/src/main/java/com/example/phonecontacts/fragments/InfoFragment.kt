package com.example.phonecontacts.fragments

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.phonecontacts.R
import com.example.phonecontacts.databinding.FragmentInfoBinding


class InfoFragment: Fragment(R.layout.fragment_info) {
    private lateinit var binding: FragmentInfoBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentInfoBinding.bind(view)

        val name = arguments?.getString("name").toString()
        val number = arguments?.getString("phone").toString()

        binding.tvName.text = name
        binding.tvNumber.text = number

        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }

        val bundle = arguments
        val bitmap = bundle?.getParcelable<Bitmap>("image")


        if (bitmap != null) {
            binding.imgPerson.setImageBitmap(bitmap)
        } else {
            binding.imgPerson.setImageResource(R.drawable.ic_round_person_24)
        }

        binding.btnCall.setOnClickListener {
            Log.d("FFFF", "number: $number")
            val dialIntent = Intent(Intent.ACTION_DIAL)
            dialIntent.data = Uri.parse("tel:$number")
            startActivity(dialIntent)
        }
    }
}
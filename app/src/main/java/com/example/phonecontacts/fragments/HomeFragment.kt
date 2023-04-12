package com.example.phonecontacts.fragments

import android.annotation.SuppressLint
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.provider.ContactsContract
import android.provider.MediaStore
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.navigation.fragment.NavHostFragment.Companion.findNavController
import androidx.navigation.fragment.findNavController
import com.example.phonecontacts.*
import com.example.phonecontacts.databinding.FragmentHomeBinding

class HomeFragment : Fragment(R.layout.fragment_home) {
    private lateinit var binding: FragmentHomeBinding
    private lateinit var contactList: MutableList<Contacts>
    private val adapter = RcvAdapter()

    @SuppressLint("Range", "NotifyDataSetChanged")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)


        contactList = mutableListOf()

        binding.recyclerView.adapter = adapter
        adapter.models = contactList

        val contact = context?.contentResolver?.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )
        if (contact != null) {
            while (contact.moveToNext()) {
                val name =
                    contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                val img =
                    contact.getString(contact.getColumnIndex(ContactsContract.CommonDataKinds.Phone.PHOTO_URI))

                Log.d("TTTT", "Photo: $img")

                val obj = Contacts(name, number, null)
                if (img != null) {
                    val bitmapImage = MediaStore.Images.Media.getBitmap(context?.contentResolver, Uri.parse(img))
                    obj.img = bitmapImage
                }
                contactList.add(obj)

            }
        }
        contact?.close()

        adapter.setOnUserClickListener {
            val bundle = Bundle()
            bundle.putString("name", it.name)
            bundle.putString("phone", it.number)
            findNavController().navigate(R.id.action_homeFragment_to_infoFragment,bundle)
        }

        binding.recyclerView.adapter?.notifyDataSetChanged()
    }
}
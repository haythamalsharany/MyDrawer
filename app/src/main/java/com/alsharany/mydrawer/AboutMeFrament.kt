package com.alsharany.mydrawer

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.provider.ContactsContract
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.ActivityCompat
import kotlinx.android.synthetic.main.fragment_about_me_frament.*
import java.security.Permission
import java.security.Permissions

class AboutMeFrament : Fragment() {

     lateinit var contactButton: Button
     lateinit var locationButton: Button
     lateinit var emailButton: Button
     lateinit var facebooktButton: Button
     lateinit var gitHubtButton: Button
     lateinit var linkedIntButton: Button
    lateinit var nameTextView: TextView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_about_me_frament, container, false)
        contactButton=view.findViewById(R.id.add_contact_btn)
        locationButton=view.findViewById(R.id.location_btn)
        emailButton=view.findViewById(R.id.email_btn)
        facebooktButton=view.findViewById(R.id.facebook_btn)
        gitHubtButton=view.findViewById(R.id.github_btn)
        linkedIntButton=view.findViewById(R.id.linkedin_btn)
        nameTextView=view.findViewById(R.id.name_tv)
        // Inflate the layout for this fragment
        return view
    }

    companion object {

        @JvmStatic
        fun newInstance() =
            AboutMeFrament().apply {

                }
            }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onStart() {
        super.onStart()
        contactButton.setOnClickListener{
            val int=getIntent(R.id.add_contact_btn)

           if(checkContactExist(requireActivity(),int.getStringExtra(ContactsContract.Intents.Insert.PHONE))) {
               val intent=Intent(Intent.ACTION_DIAL).apply {
                   data=Uri.parse("tel:int.getStringExtra(ContactsContract.Intents.Insert.PHONE")
               }
           }
            else {

               if (ActivityCompat.checkSelfPermission(
                       requireActivity(),
                       Manifest.permission.WRITE_CONTACTS
                   ) != PackageManager.PERMISSION_GRANTED
               ) {
                   val permissions = arrayOf(Manifest.permission.WRITE_CONTACTS)
                   ActivityCompat.requestPermissions(requireActivity(), permissions, 1)

               } else {
                   addContact()

               }
           }


        }
        locationButton.setOnClickListener{
            val intent=getIntent(R.id.location_btn)
           // val packageManager=
            if(requireActivity()
                    .packageManager
                    .resolveActivity(intent,PackageManager
                        .MATCH_DEFAULT_ONLY)!=null)
           {val chooserIntent=Intent.createChooser(intent,"open map ")
               startActivity(chooserIntent)}
           else{
                Toast.makeText(requireContext(),"no activity found",Toast.LENGTH_LONG).show()
            }
        }
        emailButton.setOnClickListener{
            val intent=getIntent(R.id.email_btn)
           // val packageManager=
            if(requireActivity()
                    .packageManager
                    .resolveActivity(intent,PackageManager
                        .MATCH_DEFAULT_ONLY)!=null) {
                val chooserIntent=Intent.createChooser(intent,"send by ")
                startActivity(chooserIntent)
            }
           else{
                Toast.makeText(requireContext(),"no activity found",Toast.LENGTH_LONG).show()
            }
        }
        facebooktButton.setOnClickListener{
            val intent=getIntent(R.id.facebook_btn)
           // val packageManager=
            if(requireActivity()
                    .packageManager
                    .resolveActivity(intent,PackageManager
                        .MATCH_DEFAULT_ONLY)!=null) {
                val chooserIntent=Intent.createChooser(intent,"open facebook  ")
                startActivity(chooserIntent)
            }
           else{
                Toast.makeText(requireContext(),"no activity found",Toast.LENGTH_LONG).show()
            }
        }
        linkedIntButton.setOnClickListener{
            val intent=getIntent(R.id.linkedin_btn)
           // val packageManager=
            if(requireActivity()
                    .packageManager
                    .resolveActivity(intent,PackageManager
                        .MATCH_DEFAULT_ONLY)!=null) {
                val chooserIntent=Intent.createChooser(intent,"open lnkedin  ")
                startActivity(chooserIntent)
            }
           else{
                Toast.makeText(requireContext(),"no activity found",Toast.LENGTH_LONG).show()
            }
        }
        gitHubtButton.setOnClickListener{
            val intent=getIntent(R.id.github_btn)
           // val packageManager=
            if(requireActivity()
                    .packageManager
                    .resolveActivity(intent,PackageManager
                        .MATCH_DEFAULT_ONLY)!=null) {
                val chooserIntent=Intent.createChooser(intent,"open github  ")
                startActivity(chooserIntent)

            }
           else{
                Toast.makeText(requireContext(),"no activity found",Toast.LENGTH_LONG).show()
            }
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode){
            1 ->{
                if(grantResults.size> 0 &&grantResults[0]==PackageManager.PERMISSION_GRANTED)
                {
                   addContact()
                }
            }
            else-> {
                Toast.makeText(requireActivity(),"permission Denyed",Toast.LENGTH_LONG).show()

            }
        }

    }
    fun getIntent(viewId:Int):Intent{
        val intent=Intent()
        when(viewId) {
            R.id.location_btn -> {
                intent.apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("geo:15.33,44.22")
                }
            }
            R.id.email_btn -> {
                intent.apply {
                    action = Intent.ACTION_SEND
                    type = "text/plain"
                    putExtra(Intent.EXTRA_EMAIL, arrayOf("talsharany@gmail.com"))
                    putExtra(Intent.EXTRA_SUBJECT, "letter title")
                    putExtra(Intent.EXTRA_TEXT, "Hi I am new friend ")
                }
            }
            R.id.github_btn -> {
                intent.apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("https://github.com/haythamalsharany")
                }
            }
            R.id.facebook_btn -> {
                intent.apply {
                    action = Intent.ACTION_VIEW
                    data = Uri.parse("https://m.facebook.com/h772967092")
                }
            }
            R.id.linkedin_btn -> {
                intent.apply {

                    action = Intent.ACTION_VIEW
                   data = Uri.parse("https://www.linkedin.com/in/haytham-alsharany-a5a857b3")
                }

            }
            R.id.add_contact_btn->{
                intent.apply {
                    action=ContactsContract.Intents.Insert.ACTION
                    type=ContactsContract.RawContacts.CONTENT_TYPE
                    putExtra(ContactsContract.Intents.Insert.NAME,"haytham alsharany")
                    putExtra(ContactsContract.Intents.Insert.PHONE,"772967092")
                    putExtra(ContactsContract.Intents.Insert.PHONE_TYPE,ContactsContract.CommonDataKinds.Phone.TYPE_HOME)

                }
            }
        }
      return intent
    }
    fun addContact(){
        val intent=getIntent(R.id.add_contact_btn)
        // val packageManager=
        if(requireActivity()
                .packageManager
                .resolveActivity(intent,PackageManager
                    .MATCH_DEFAULT_ONLY)!=null)
            startActivity( intent)

    }
    @RequiresApi(Build.VERSION_CODES.O)
    fun checkContactExist(activity:Activity, number : String):Boolean {
        val lookUpUri = Uri.withAppendedPath(
            ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
            Uri.encode(number)
        )
        val myPhoneNumberProjection = arrayOf(
            ContactsContract.PhoneLookup._ID,
            ContactsContract.PhoneLookup.NUMBER,
            ContactsContract.PhoneLookup.DISPLAY_NAME
        )

        val cursor =
            activity.contentResolver.query(lookUpUri, myPhoneNumberProjection, null, null)



        try {
            if (cursor != null) {
                if (cursor.moveToFirst())
                    return true

            }
        } finally {
            if (cursor != null)
                cursor.close()
        }
        return false
    }


    }





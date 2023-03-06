package com.example.valommunityapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.example.valommunityapp.LoginRegister.Publication
import com.example.valommunityapp.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class AddPublicationFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var sendButton: Button
    private lateinit var username: String
    private lateinit var url: String
    private lateinit var description: String

    private lateinit var FirebaseAuth: FirebaseAuth
    private lateinit var firebaseDatabase: FirebaseDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_add_publication, container, false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val db = FirebaseDatabase.getInstance().reference

        sendButton = view.findViewById(R.id.sendButton)
        sendButton.setOnClickListener {
            username = view.findViewById<EditText?>(R.id.publication_username).text.toString()
            url = view.findViewById<EditText?>(R.id.publication_url).text.toString()
            description = view.findViewById<EditText?>(R.id.publication_description).text.toString()
            db.child(username.toString()).setValue(Publication(username, url, description))
            Toast.makeText(view.context, "send", Toast.LENGTH_SHORT).show()
        }
    }
    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            AddPublicationFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
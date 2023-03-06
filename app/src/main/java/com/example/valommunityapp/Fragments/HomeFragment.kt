package com.example.valommunityapp.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valommunityapp.LoginRegister.Publication
import com.example.valommunityapp.R
import com.example.valommunityapp.adapter.PublicationAdapter
import com.google.firebase.database.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    private lateinit var recyclerView: RecyclerView
    private lateinit var newsPublicationsArrayList: ArrayList<Publication>
    private lateinit var adapter: PublicationAdapter

    private lateinit var dbref: DatabaseReference

    lateinit var namePublication: Array<String>
    lateinit var imagePublication: Array<Int>
    lateinit var descriptionPublication: Array<String>

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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }
    companion object {
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerHomeFragment)
        recyclerView.layoutManager = layoutManager
        recyclerView.setHasFixedSize(true)
        getData()
    }
    private fun getData(){
        dbref = FirebaseDatabase.getInstance().getReference("/")
        newsPublicationsArrayList = arrayListOf<Publication>()
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()) {
                    for (publicationSnapshot in snapshot.children) {
                        val publication = publicationSnapshot.getValue(Publication::class.java)
                        newsPublicationsArrayList.add(publication!!)
                    }
                    recyclerView.adapter = PublicationAdapter(newsPublicationsArrayList)
                }
            }
            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }
        })
    }
}
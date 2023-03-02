package com.example.valommunityapp.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.valommunityapp.Publication
import com.example.valommunityapp.PublicationProvider
import com.example.valommunityapp.R
import com.example.valommunityapp.adapter.PublicationAdapter
import com.google.firebase.firestore.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"



/**
 * A simple [Fragment] subclass.
 * Use the [HomeFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class HomeFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    private lateinit var adapter: PublicationAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var newsPublicationsArrayList: ArrayList<Publication>

    private lateinit var db : FirebaseFirestore

    lateinit var namePublication: Array<String>
    lateinit var imagePublication: Array<Int>
    lateinit var descriptionPublication: Array<String>
    lateinit var news: Array<String>

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
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment HomeFragment.
         */
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
        //dataInit()
        val layoutManager = LinearLayoutManager(context)
        recyclerView = view.findViewById(R.id.recyclerHomeFragment)

        recyclerView.layoutManager = layoutManager

        recyclerView.setHasFixedSize(true)

        newsPublicationsArrayList = arrayListOf()

        adapter = PublicationAdapter(newsPublicationsArrayList)

        recyclerView.adapter = adapter

        EventChangeListener()
    }
    private fun EventChangeListener(){

        db = FirebaseFirestore.getInstance()
        db.collection("Publications").
                addSnapshotListener(object : EventListener<QuerySnapshot>{
                    override fun onEvent(
                        value: QuerySnapshot?,
                        error: FirebaseFirestoreException?
                    ) {
                        if (error != null){
                            Log.e("Firestore Error: ", error.message.toString())
                            return
                        }
                        for (dc : DocumentChange in value?.documentChanges!!){
                            if (dc.type == DocumentChange.Type.ADDED){
                                newsPublicationsArrayList.add(dc.document.toObject(Publication::class.java))
                            }
                        }
                        adapter.notifyDataSetChanged()
                    }
                })
    }
    private fun dataInit(){
        newsPublicationsArrayList = arrayListOf<Publication>()

        namePublication = arrayOf(
            getString(R.string.name_1),
            getString(R.string.name_2),
            getString(R.string.name_3)
        )
        imagePublication = arrayOf(
            R.drawable.a,
            R.drawable.b,
            R.drawable.c
        )
        descriptionPublication = arrayOf(
            getString(R.string.desc_1),
            getString(R.string.desc_2),
            getString(R.string.desc_3)
        )
        for(i in imagePublication.indices){
            val newsPublications = Publication(namePublication[i], imagePublication[i].toString(), descriptionPublication[i])
            newsPublicationsArrayList.add(newsPublications)
        }
    }
}
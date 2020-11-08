package com.alsharany.mydrawer

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView


// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_ID = "id"
private const val ARG_NAME = "name"

/**
 * A simple [Fragment] subclass.
 * Use the [CategoryFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CategoryFragment : Fragment() {

    private var id: Int? = null
    private var name: String? = null
    private var color: Int=0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            id = it.getInt(ARG_ID)
            name = it.getString(ARG_NAME)
            color=it.getInt("myColor")

        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_category, container, false)
        view.setBackgroundColor(color)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val categoryNameTextView=view.findViewById(R.id.category_tv) as TextView
        categoryNameTextView.text= """${this.id.toString()}-$name"""
    }

    companion object {


        @JvmStatic
        fun newInstance(id: Int, name: String?,color:Int) =
            CategoryFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_ID, id)
                    putString(ARG_NAME, name)
                    putInt("myColor",color)
                }
            }
    }
}
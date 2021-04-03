package com.example.nightcode4

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.nightcode4.adapters.LecRecyclerAdapter
import com.example.nightcode4.databinding.FragmentLecBinding
import com.example.nightcode4.model.Lecs
import com.example.nightcode4.model.Lecs.themes

class ChooseLecFragment : Fragment(R.layout.fragment_lec) {
    lateinit var binding: FragmentLecBinding
    lateinit var adapter: LecRecyclerAdapter
    lateinit var lectiums4Choose : MutableList<Lecs.Lec>
    val progress = mutableListOf<Int>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        progress.clear()
        themes.forEach {
            progress.add(0)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_lec, container, false)
        Log.d("checksad", progress.toString())
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        themes.forEach {
//            lectiums4Choose.add(Lecs.createLec(it))
//        }

        adapter = LecRecyclerAdapter(requireContext(), themes, progress)
        binding.recyclerLec.adapter = adapter

    }

    override fun onResume() {
        super.onResume()
        val sharedpref = activity?.getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE)

        themes.forEachIndexed { index, s ->
            val value = sharedpref?.getInt(s, 0)
            if (value != null){
                progress[index] = value
            }
            adapter.notifyDataSetChanged()
            Log.d("onresume", s)
        }
    }
}
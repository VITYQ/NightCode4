package com.example.nightcode4

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.nightcode4.databinding.FragmentTestsBinding
import com.example.nightcode4.model.Lecs


class TestsFragment : Fragment(R.layout.fragment_tests) {
    lateinit var binding: FragmentTestsBinding
    var counter = 0
    var atAll = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tests, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.cardViewBestOfDay.setOnClickListener {
            val intent = Intent(requireContext(), LecActivity::class.java)
            intent.putExtra("theme", "Семейное право")
            intent.putExtra("page", "5")
            context?.startActivity(intent)
        }

        initIndicator()



    }

    private fun initIndicator() {
        Lecs.themes.forEachIndexed { index, s ->
            val sharedpref = activity?.getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE)
            val value = sharedpref?.getInt(s, 0)
            if (value != null){
                counter+=value
            }
        }

        atAll += Lecs.Algoritm.size
        atAll += Lecs.Trud.size
        atAll += Lecs.Zhile.size
        atAll += Lecs.Potrebitel.size
        atAll += Lecs.Semya.size
        atAll += Lecs.SocPravo.size
        atAll += Lecs.InternetPravo.size
        atAll += Lecs.BazarSMentami.size
        atAll += Lecs.Financi.size
        atAll += Lecs.Business.size
        atAll += Lecs.Bankrot.size
        atAll += Lecs.Vycet.size
        atAll += Lecs.Moshenniki.size

        binding.progressMain.progress = ((counter.toFloat()/atAll.toFloat())*100).toInt()
        binding.textViewProgress.text = "${counter}/${atAll}"


    }


}
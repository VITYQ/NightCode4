package com.example.nightcode4

import android.content.AbstractThreadedSyncAdapter
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.ContextMenu
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.nightcode4.adapters.FavouritesRecyclerAdapter
import com.example.nightcode4.adapters.LecRecyclerAdapter
import com.example.nightcode4.databinding.FragmentFavouritesBinding
import com.example.nightcode4.model.Favourite
import com.example.nightcode4.model.Lecs


class FavouritesFragment : Fragment(R.layout.fragment_favourites) {
    val favourites = mutableListOf<Favourite>()
    lateinit var adapter: FavouritesRecyclerAdapter
    lateinit var binding: FragmentFavouritesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_favourites, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val favouritePages



        setData()

        setupRecycler()


    }

    private fun setupRecycler() {
        adapter = FavouritesRecyclerAdapter(requireContext(), favourites)
        binding.recyclerViewFavourites.adapter = adapter
    }

    private fun fetchFavouritePages(it: String, page: Int, title: String){
        val sharedPref = activity?.getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE)
        if (sharedPref?.getInt("${it}favouritepage", 0) == 1){
            Log.d("asfdsaf", "found favourite page $it")
            favourites.add(Favourite("${it}", page, title))
        }
    }

    private fun setData(){
        val sharedPref = activity?.getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE)
        Lecs.themes.forEach {
            if (sharedPref?.getInt("${it}favourite", 0) == 1){
                Log.d("asfdsaf", "found favourite $it")
                favourites.add(Favourite("${it}", -1, ""))
            }
        }

        Lecs.Algoritm.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[0])
        }
        Lecs.Trud.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[1])
        }
        Lecs.Zhile.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[2])
        }
        Lecs.Potrebitel.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[3])
        }
        Lecs.Semya.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[4])
        }
        Lecs.SocPravo.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[5])
        }
        Lecs.InternetPravo.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[6])
        }
        Lecs.BazarSMentami.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[7])
        }
        Lecs.Financi.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[8])
        }
        Lecs.Business.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[9])
        }
        Lecs.Bankrot.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[10])
        }
        Lecs.Vycet.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[11])
        }
        Lecs.Moshenniki.forEachIndexed { index, s ->
            fetchFavouritePages(s, index, Lecs.themes[12])
        }

        if (favourites.isEmpty()){
            binding.textviewAttention.visibility = View.VISIBLE
        }

    }
}
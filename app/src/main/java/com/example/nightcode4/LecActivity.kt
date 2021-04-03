package com.example.nightcode4

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.example.nightcode4.adapters.LecViewerPagerAdapter
import com.example.nightcode4.databinding.ActivityLecBinding
import com.example.nightcode4.model.Lecs

class LecActivity : AppCompatActivity() {
    lateinit var binding: ActivityLecBinding
    lateinit var adapter: LecViewerPagerAdapter
    lateinit var lectiums : Lecs.Lec
    var currentPage = 0
    var theme = ""
//    lateinit var
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_lec)
        if (intent!= null){
            if(!intent.getStringExtra("page").isNullOrEmpty()){
                Log.d("checkintent", intent.getStringExtra("page")!!)
                currentPage = intent.getStringExtra("page")!!.toInt()
            }
            if (!intent.getStringExtra("theme").isNullOrEmpty()) {
                theme = intent.getStringExtra("theme")!!
            }



            Log.d("checkintent", currentPage.toString())
            lectiums = Lecs.createLec(theme!!)
            adapter = LecViewerPagerAdapter(this, lectiums)
            binding.viewPager.adapter = adapter

            binding.viewPager.setCurrentItem(currentPage-1, true)
            binding.toolBar.title = "(${currentPage}/${lectiums.lec.size}) ${lectiums.theme}"
        }
        setToolbarClickListener()
        setViewPagerPageChangeCallback()
        setNavigationBackListener()

    }

    private fun setToolbarClickListener() {
        binding.toolBar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.delete_progress -> {
                    deleteThemeProgress()
                    this.finish()
                    true
                }
                R.id.add_to_favourites_lec -> {
                    addLecToFavourites()
                    true
                }
                R.id.add_to_favourites_page -> {
                    addPageToFavourites()
                    true
                }
                else -> false
            }
        }
    }

    private fun addPageToFavourites() {
        Log.d("sthrte", lectiums.lec[binding.viewPager.currentItem].title)
        val sharedPref = getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putInt("${lectiums.lec[binding.viewPager.currentItem].title}favouritepage", 1)
            commit()
        }
    }

    private fun addLecToFavourites() {
        val sharedPref = getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE)
        with(sharedPref.edit()){
            putInt("${theme}favourite", 1)
            commit()
        }
    }

    private fun deleteThemeProgress() {
        val sharedpref = getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE)
        with(sharedpref.edit()){
            putInt(theme, 0)
            commit()
        }
    }

    private fun setViewPagerPageChangeCallback() {

        binding.viewPager.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels)
            }
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                val sharedpref = getSharedPreferences(getString(R.string.pref), Context.MODE_PRIVATE)
                binding.toolBar.title = "(${position+1}/${lectiums.lec.size}) ${lectiums.theme}"
                val preValue = sharedpref.getInt(theme, 0)
                if(position+1 > preValue){
                    with(sharedpref.edit()){
                        putInt(theme, preValue+1)
                        commit()
                    }
                }

                Log.e("Selected_Page", position.toString())
            }

            override fun onPageScrollStateChanged(state: Int) {
                super.onPageScrollStateChanged(state)
            }
        })

    }


    override fun onResume() {
        super.onResume()

    }
    private fun setNavigationBackListener() {
        binding.toolBar.setNavigationOnClickListener {
            this.finish()
        }
    }
}
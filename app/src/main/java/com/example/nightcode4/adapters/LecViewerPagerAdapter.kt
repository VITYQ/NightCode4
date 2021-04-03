package com.example.nightcode4.adapters

import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nightcode4.LecActivity
import com.example.nightcode4.databinding.ItemLecBinding
import com.example.nightcode4.databinding.PageItemBinding
import com.example.nightcode4.model.Lecs

class LecViewerPagerAdapter(val context: Context, val lecs: Lecs.Lec) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }


    inner class Lec(val binding: PageItemBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.textViewTitle.text = lecs.lec[position].title
            binding.textViewContent.text = lecs.lec[position].content
            Log.d("gfdgsg ", lecs.toString())

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = PageItemBinding.inflate(inflater, parent, false)
        return Lec(binding)




    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as Lec).bind(position)

    }

    override fun getItemCount() = lecs.lec.size



}
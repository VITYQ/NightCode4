package com.example.nightcode4.adapters

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.nightcode4.LecActivity
import com.example.nightcode4.databinding.ItemLecBinding
import com.example.nightcode4.model.Lecs

class LecRecyclerAdapter(val context: Context, val lecs: List<String>, var progress: MutableList<Int>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }


    inner class Lec(val binding: ItemLecBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.lecTitle.text = lecs[position]

            val lecFull = Lecs.createLec(lecs[position])
            if (progress[position] == lecFull.lec.size){
                binding.textViewProgress.text = "Все ${progress[position]}/${lecFull.lec.size} проойдены!"
                binding.textViewProgress.setTextColor(Color.parseColor("#04A200"))
            }
            else{
                binding.textViewProgress.text = "Пройдено ${progress[position]}/${lecFull.lec.size}"
                binding.textViewProgress.setTextColor(Color.parseColor("#777777"))
            }

            binding.progressIndicator.progress = ((progress[position].toFloat()/lecFull.lec.size.toFloat())*100).toInt()
            binding.constraintLecItem.setOnClickListener {
                val intent = Intent(context, LecActivity::class.java)
                intent.putExtra("theme", binding.lecTitle.text)
                intent.putExtra("page", progress[position].toString())
                Log.d("checkintent",progress[position].toString())
                context.startActivity(intent)
            }

        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemLecBinding.inflate(inflater, parent, false)
        return Lec(binding)




    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as Lec).bind(position)

    }

    override fun getItemCount() = lecs.size



}
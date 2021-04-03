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
import com.example.nightcode4.databinding.ItemFavouriteBinding
import com.example.nightcode4.databinding.ItemLecBinding
import com.example.nightcode4.model.Favourite
import com.example.nightcode4.model.Lecs

class FavouritesRecyclerAdapter(val context: Context, val list: MutableList<Favourite>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){

    }


    inner class Lec(val binding: ItemFavouriteBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(position: Int){
            binding.textViewTitle.text = list[position].title
            if (list[position].type != -1){
                binding.textViewTag.text = "Страница раздела"

                binding.constraintFavourite.setOnClickListener {
                    val intent = Intent(context, LecActivity::class.java)
                    intent.putExtra("theme", list[position].description)
                    intent.putExtra("page", (list[position].type+1).toString())
                    context.startActivity(intent)
                }

                binding.imageviewDelete.setOnClickListener {

                    Log.d("chasdf", list.toString())
                    val sharedPref = context.getSharedPreferences("com.vityq.dluv.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)
                    with(sharedPref.edit()){
                        remove("${list[position].title}favouritepage")
                        commit()
                    }
                    list.remove(list[position])
                    notifyItemRemoved(position)


                }

            }
            else{
                binding.textViewTag.text = "Раздел"
                binding.constraintFavourite.setOnClickListener {
                    val intent = Intent(context, LecActivity::class.java)
                    intent.putExtra("theme", list[position].title)
                    context.startActivity(intent)
                }
                binding.imageviewDelete.setOnClickListener {

                    Log.d("chasdf", list.toString())
                    val sharedPref = context.getSharedPreferences("com.vityq.dluv.PREFERENCE_FILE_KEY", Context.MODE_PRIVATE)
                    with(sharedPref.edit()){
                        remove("${list[position].title}favourite")
                        commit()
                    }
                    list.remove(list[position])
                    notifyItemRemoved(position)


                }
            }
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)

        val binding = ItemFavouriteBinding.inflate(inflater, parent, false)
        return Lec(binding)




    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        (holder as Lec).bind(position)

    }

    override fun getItemCount() = list.size



}
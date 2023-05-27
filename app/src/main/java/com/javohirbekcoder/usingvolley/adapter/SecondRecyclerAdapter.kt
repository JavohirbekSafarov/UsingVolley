package com.javohirbekcoder.usingvolley.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.javohirbekcoder.usingvolley.R
import com.javohirbekcoder.usingvolley.databinding.SecondRecyclerItemBinding


/*
Created by Javohirbek on 24.05.2023 at 16:33
*/
class SecondRecyclerAdapter(private val context: Context, private val list :MutableList<String>): RecyclerView.Adapter<SecondRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: SecondRecyclerItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun onBind(item: String) {
            Glide
                .with(context)
                .load(item.toString())
                .centerCrop()
                .placeholder(R.drawable.img_place_holder)
                .into(binding.imageView);
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = SecondRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}
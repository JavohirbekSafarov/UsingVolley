package com.javohirbekcoder.usingvolley.adapter

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.javohirbekcoder.usingvolley.ProductModel
import com.javohirbekcoder.usingvolley.R
import com.javohirbekcoder.usingvolley.databinding.MainRecyclerItemBinding


/*
Created by Javohirbek on 24.05.2023 at 16:08
*/
class MainRecyclerAdapter(
    private val context: Context,
    private val list: MutableList<ProductModel>
) :
    RecyclerView.Adapter<MainRecyclerAdapter.MyViewHolder>() {

    inner class MyViewHolder(private val binding: MainRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun onBind(item: ProductModel) {
            binding.titleText.text = item.title
            binding.descriptionText.text = item.description
            binding.ratingText.text = item.rating.toString()
            binding.priceText.text = "${item.price} $"
            Glide
                .with(context)
                .load(item.thumbnail)
                .centerCrop()
                .placeholder(R.drawable.img_place_holder)
                .into(binding.profilePic)
            val secondList: MutableList<String> = ArrayList()
            secondList.addAll(item.images)

            //Set second adapter for viewPager
            val adapter = SecondRecyclerAdapter(context, secondList)
            binding.imagesPager.orientation = ViewPager2.ORIENTATION_HORIZONTAL
            binding.imagesPager.adapter = adapter

            //Notify indicators
            val wormDotsIndicator = binding.wormDotsIndicator
            wormDotsIndicator.attachTo(binding.imagesPager)


            //Set Auto slide
            var page = 0
            val totalPages: Int = adapter.itemCount

            val handler = Handler(Looper.getMainLooper())
            val runnable = object : Runnable {
                override fun run() {
                    if (page == totalPages)
                        page = 0
                    binding.imagesPager.setCurrentItem(page++, true)
                    handler.postDelayed(this, 3000)
                }
            }
            handler.post(runnable)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = MainRecyclerItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.onBind(list[position])
    }
}
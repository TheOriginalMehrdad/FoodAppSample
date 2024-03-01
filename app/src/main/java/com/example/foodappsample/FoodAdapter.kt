package com.example.foodappsample

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.foodappsample.databinding.FoodItemBinding
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(private val data: ArrayList<Food>, private val foodEvent: FoodEvents) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    private lateinit var binding: FoodItemBinding

    inner class FoodViewHolder(private val binding: FoodItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")

        fun bindData(position: Int) {

            binding.itemTxtSubject.text = data[position].subjectData
            binding.itemTxtCity.text = data[position].cityData
            binding.itemTxtPrice.text = "$${data[position].priceData} vip"
            binding.itemTxtDistance.text = "${data[position].distanceData} miles from you"
            binding.itemRatingBar.rating = data[position].ratingData
            binding.itemRatingNumber.text = "(${data[position].ratingNumberData}) Ratings"

            Glide.with(binding.root.context).load(data[position].imageUrlData).transform(
                RoundedCornersTransformation(18, 6)
            ).into(binding.itemImgMain)

            itemView.setOnClickListener {
                foodEvent.onFoodClicked(
                    data[adapterPosition],
                    adapterPosition
                )
            }
            itemView.setOnLongClickListener {
                foodEvent.onFoodLongClicked(data[adapterPosition], adapterPosition)
                true
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        binding = FoodItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)

        // if you want use findByViewId should write bellow code
        //val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.bindData(position)
    }

    fun addNewFood(newFood: Food) {

        // Add new item to recycler view
        data.add(0, newFood)
        notifyItemInserted(0)
    }

    fun removeFood(oldFood: Food, oldPosition: Int) {

        // Removing food item from recycler view
        data.remove(oldFood)
        notifyItemRemoved(oldPosition)
    }

    fun updateFood(newFood: Food, position: Int) {

        //Updating existing food items
        data[position] = newFood
        notifyItemChanged(position)
    }

    fun setData(newList: ArrayList<Food>) {

        // Refreshing all new items
        data.clear()
        data.addAll(newList)
        notifyDataSetChanged()
    }

    interface FoodEvents {

        fun onFoodClicked(food: Food, position: Int)
        fun onFoodLongClicked(food: Food, position: Int)
    }
}
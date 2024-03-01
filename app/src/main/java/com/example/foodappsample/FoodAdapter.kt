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
import jp.wasabeef.glide.transformations.RoundedCornersTransformation

class FoodAdapter(private val data: ArrayList<Food>) :
    RecyclerView.Adapter<FoodAdapter.FoodViewHolder>() {

    inner class FoodViewHolder(itemView: View, private val context: Context) :
        RecyclerView.ViewHolder(itemView) {

        private val image = itemView.findViewById<ImageView>(R.id.item_img_main)
        private val subject = itemView.findViewById<TextView>(R.id.item_txt_subject)
        private val city = itemView.findViewById<TextView>(R.id.item_txt_city)
        private val price = itemView.findViewById<TextView>(R.id.item_txt_price)
        private val distance = itemView.findViewById<TextView>(R.id.item_txt_distance)
        private val myRatingBar = itemView.findViewById<RatingBar>(R.id.item_rating_bar)
        private val myRatingNumber = itemView.findViewById<TextView>(R.id.item_rating_number)

        @SuppressLint("SetTextI18n")
        fun bindData(position: Int) {
            subject.text = data[position].subjectData
            city.text = data[position].cityData
            price.text = "$${data[position].priceData} vip"
            distance.text = "${data[position].distanceData} miles from you"
            myRatingBar.rating = data[position].ratingData
            myRatingNumber.text = "(${data[position].ratingNumberData}) Ratings"

            Glide.with(context).load(data[position].imageUrlData).transform(
                RoundedCornersTransformation(18, 6)
            ).into(image)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FoodViewHolder {

        val view = LayoutInflater.from(parent.context).inflate(R.layout.food_item, parent, false)
        return FoodViewHolder(view, parent.context)
    }

    override fun getItemCount(): Int {

        return data.size
    }

    override fun onBindViewHolder(holder: FoodViewHolder, position: Int) {

        holder.bindData(position)
    }
}
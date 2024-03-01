package com.example.foodappsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.foodappsample.databinding.ActivityMainBinding
import com.example.foodappsample.databinding.DialogAddNewItemBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val foodList = arrayListOf(
            Food(
                "Hamburger",
                "15",
                "3",
                "Isfahan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food1.jpg",
                20,
                4.5f
            ),
            Food(
                "Grilled fish",
                "20",
                "2.1",
                "Tehran, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food2.jpg",
                10,
                4f
            ),
            Food(
                "Lasania",
                "40",
                "1.4",
                "Isfahan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food3.jpg",
                30,
                2f
            ),
            Food(
                "pizza",
                "10",
                "2.5",
                "Zahedan, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food4.jpg",
                80,
                1.5f
            ),
            Food(
                "Sushi",
                "20",
                "3.2",
                "Mashhad, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food5.jpg",
                200,
                3f
            ),
            Food(
                "Roasted Fish",
                "40",
                "3.7",
                "Jolfa, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food6.jpg",
                50,
                3.5f
            ),
            Food(
                "Fried chicken",
                "70",
                "3.5",
                "NewYork, USA",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food7.jpg",
                70,
                2.5f
            ),
            Food(
                "Vegetable salad",
                "12",
                "3.6",
                "Berlin, Germany",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food8.jpg",
                40,
                4.5f
            ),
            Food(
                "Grilled chicken",
                "10",
                "3.7",
                "Beijing, China",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food9.jpg",
                15,
                5f
            ),
            Food(
                "Baryooni",
                "16",
                "10",
                "Ilam, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food10.jpg",
                28,
                4.5f
            ),
            Food(
                "Ghorme Sabzi",
                "11.5",
                "7.5",
                "Karaj, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food11.jpg",
                27,
                5f
            ),
            Food(
                "Rice",
                "12.5",
                "2.4",
                "Shiraz, Iran",
                "https://dunijet.ir/YaghootAndroidFiles/DuniFoodSimple/food12.jpg",
                35,
                2.5f
            ),
        )
        val myAdapter = FoodAdapter(foodList)

        binding.recycleViewMain.adapter = myAdapter
        binding.recycleViewMain.layoutManager =
            LinearLayoutManager(this, RecyclerView.VERTICAL, false)


        // Adding new food items
        binding.btnAdd.setOnClickListener {

            val dialog = AlertDialog.Builder(this).create()
            val dialogBinding = DialogAddNewItemBinding.inflate(layoutInflater)

            dialog.setView(dialogBinding.root)
            dialog.setCancelable(true)

            dialog.show()

            dialogBinding.dialogBtnDone.setOnClickListener {

                if (
                    dialogBinding.dialogFoodName.length() > 0 &&
                    dialogBinding.dialogFoodPrice.length() > 0 &&
                    dialogBinding.dialogFoodDistance.length() > 0 &&
                    dialogBinding.dialogFoodCity.length() > 0
                ) {

                    // For getting basic food info
                    val txtName = dialogBinding.dialogFoodName.text.toString()
                    val txtPrice = dialogBinding.dialogFoodPrice.text.toString()
                    val txtDistance = dialogBinding.dialogFoodDistance.text.toString()
                    val txtCity = dialogBinding.dialogFoodCity.text.toString()
                    val randomRatingNumber: Int = (1..150).random()

                    // For getting random star
                    val min = 0f
                    val max = 5f
                    val random = min + Random.nextFloat() * (max - min)
                    val randomRatingStarBar: Float = random

                    // For getting random image
                    val randomNumber = (1..11).random()
                    val randomUrlPic = foodList[randomNumber].imageUrlData

                    // Creating a new item
                    val newFood = Food(
                        txtName,
                        txtPrice,
                        txtDistance,
                        txtCity,
                        randomUrlPic,
                        randomRatingNumber,
                        randomRatingStarBar
                    )
                    myAdapter.addNewFood(newFood)
                    binding.recycleViewMain.smoothScrollToPosition(0)
                    dialog.dismiss()

                } else {
                    Toast.makeText(this, "Please fill all the sections", Toast.LENGTH_SHORT).show()
                }
            }

        }

    }
}



















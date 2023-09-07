package com.wowapp2

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone
import kotlin.math.roundToInt


class ResultActivity : AppCompatActivity() {
    private lateinit var cityName: TextView
    private lateinit var cityDateTime: TextView
    private lateinit var temperature: TextView
    private lateinit var description: TextView
    private lateinit var imageWeather: ImageView
    private lateinit var imageHumidity: ImageView
    private lateinit var imagePressure: ImageView
    private lateinit var imageWind: ImageView
    private lateinit var humidityNumber: TextView
    private lateinit var pressureNumber: TextView
    private lateinit var windSpeed: TextView
    private lateinit var backButton: Button
    var city: String? = null

    private val apiKey = "e44fdd50626cc7af9cbae08bd14636a3"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val intent = intent
        city = intent.getStringExtra("cityName")

        this.cityName = findViewById(R.id.city_name)
        cityDateTime = findViewById(R.id.city_date_time)
        temperature = findViewById(R.id.temperature)
        description = findViewById(R.id.description)
        imageWeather = findViewById(R.id.image_weather)
        backButton = findViewById(R.id.back_button)
        humidityNumber = findViewById(R.id.humidity_number)
        pressureNumber = findViewById(R.id.pressure_number)
        windSpeed = findViewById(R.id.wind_speed)
        imageHumidity = findViewById(R.id.image_humidity)
        imagePressure = findViewById(R.id.image_pressure)
        imageWind = findViewById(R.id.image_wind)

        cityDateTime.text = ""

        if (city != null) {
            getWeatherData(city!!)
        }

        backButton.setOnClickListener {
            finish()
        }
    }

    private fun getWeatherData(city: String) {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val weatherApi = retrofit.create(WeatherApi::class.java)
        val call = weatherApi.getWeatherData(city, apiKey, "metric")

        call.enqueue(object : Callback<WeatherData> {
            @SuppressLint("SetTextI18n")
            override fun onResponse(call: Call<WeatherData>, response: Response<WeatherData>) {
                if (!response.isSuccessful) {
                    handleApiError(response, city)
                } else {
                    val data = response.body()
                    if (data != null) {
                        val cityCountry = "${data.name}, ${data.sys.country}"
                        cityName.text = cityCountry
                        temperature.text = "${data.main.temp.roundToInt()}°C"
                        description.text = data.weather[0].description
                        humidityNumber.text = "${data.main.humidity}%"
                        windSpeed.text = "${data.wind.speed}m/s"
                        pressureNumber.text = "${data.main.pressure.roundToInt()}hPa"
                        setDescriptionImage(data)
                        setIcons()
                        getTime(data.timezone)
                    } else {
                        // For valid inout city name, but API do not have weather info
                        description.setText(R.string.error_not_have_info)
                    }
                }
            }

            override fun onFailure(call: Call<WeatherData>, t: Throwable) {
                handleError(t)
            }
        })
    }

    private fun getTime(timezoneOffsetInSeconds: Int) {
        val deviceTimezone = TimeZone.getDefault()
        val deviceOffsetInSeconds = deviceTimezone.getOffset(System.currentTimeMillis()) / 1000
        val totalOffsetInSeconds = timezoneOffsetInSeconds - deviceOffsetInSeconds
        val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
        calendar.add(Calendar.SECOND, totalOffsetInSeconds)
        val localTime = calendar.time

        val dateFormat = SimpleDateFormat("dd MMM EEE", Locale.ENGLISH)
        val formattedDate = dateFormat.format(localTime)

        val timeFormat = SimpleDateFormat("HH:mm")
        val formattedTime = timeFormat.format(localTime)
        cityDateTime.text = "${formattedDate}\n${formattedTime}"
    }

    private fun setDescriptionImage(data: WeatherData) {
        val iconName = "im${data.weather[0].icon}"
        val iconId = resources.getIdentifier(iconName, "drawable", packageName)
        if (iconId != 0) {
            imageWeather.setImageResource(iconId)
        } else {
            imageWeather.setImageResource(R.drawable.im01d)
        }
    }

    private fun setIcons() {
        imageWind.setImageResource(R.drawable.wind)
        imageHumidity.setImageResource(R.drawable.humidity)
        imagePressure.setImageResource(R.drawable.pressure)
    }

    private fun handleApiError(response: Response<WeatherData>, cityName: String) {
        if (response.code() == 404) {
            // For invalid input city name
            val errorMassage = "City: ${cityName} not found"
            description.text = errorMassage
        } else {
            val errorMessage = "API Error: ${response.code()}"
            Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun handleError(t: Throwable) {
        val errorMessage = "Network Error: ${t.localizedMessage}"
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
    }
}

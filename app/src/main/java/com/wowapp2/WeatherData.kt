package com.wowapp2

data class WeatherData(
    val main: Main,
    val weather: Array<Weather>,
    val name: String,
    val wind: Wind,
    val timezone: Int,
    val sys: Sys
) {
    data class Main(
        val temp: Double,
        val humidity: Int,
        val pressure: Double
    )

    data class Weather(
        val description: String,
        val icon: String
    )

    data class Wind(
        val speed: Double
    )

    data class Sys(
        val country: String
    )

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as WeatherData

        if (main != other.main) return false
        if (!weather.contentEquals(other.weather)) return false
        if (name != other.name) return false
        if (wind != other.wind) return false

        return true
    }

    override fun hashCode(): Int {
        var result = main.hashCode()
        result = 31 * result + weather.contentHashCode()
        result = 31 * result + name.hashCode()
        result = 31 * result + wind.hashCode()
        return result
    }
}

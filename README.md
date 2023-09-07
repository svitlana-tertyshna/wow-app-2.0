# The WoW App 2.0 (Kotlin edition) 🌦️
> The Wow 2.0 is also an Android application that allows users to check the weather in different cities, but with __*new fiches*__ like local time & date in the chosen city, and automatically showing the weather in your current location (if you give permit).
<img src="wow2_exsample.gif" alt="How the app works" height="400px" style="margin-right: 20px;">

## 🎯 Technologies Used

- *`compileSdk`: `33`*
- *`minSdk`: min `25`*
- *`targetSdk`: `33`*
- *Kotlin: `"1.9.0"`*
- *AndroidX library:*
   - *`core-ktx`: `"1.10.1"`*
   - *`appcompat`: `"1.6.1"`*
   - *`material`: `"1.9.0"`*
   - *`constraintlayout`: `"2.1.4"`*
- *Retrofit: `"2.9.0"`*
- *Google Play Services Location: `"21.0.1"`*
- *Android Gradle Plugin: `"8.1.1"` for com.android.application and `"1.9.0"` for org.jetbrains.kotlin.android*
- *Geocoder*
- *XML for models*
- *Permission System*
- *RESTful API*
- *Gradle*
- *Android Studio: `"4.2.2"`*
- *OpenWeatherMap API*: <sup>The project uses the API version with "metric" units (degrees Celsius).<sup>

## 🔧 Functionality
📱**Main Screen:** The app opens to a main screen where users can input the city name they want to check the weather for or <u>if the user gives a permit it shows the weather in the current location.</u>

🌍**Weather Data Retrieval:** After entering a city name and clicking "Gey weather" the app sends a request to an external weather API to retrieve weather data for the specified city.

☀️**Display Weather Information:**
- City name & __*initials of the country*__
- __*Local date & time in the chosen city*__
- Current temperature in degrees Celsius
- Weather description (e.g., "Cloudy," "Sunny," "Rainy")
- Humidity percentage
- Wind speed in meters per second
- Atmospheric pressure in hPa (hectopascal)
- Weather icon corresponding to the current weather conditions
 
⚠️**Error Handling:** The app handles various types of errors:
- If the specified text isn't a city name, it displays an error message like "City not found."
- If the city's name is specified, but there is no information about the weather in the API, the appropriate message will be displayed.
- If there's an issue with the API or network connectivity, it shows a network error message.
 
↩️**Return to Main Screen:** Users can return to the main screen by clicking the "Back" button.

## 💻 Instructions for launching the project
1. Clone the repository from GitHub to your computer.
2. Open the project in your preferred Android development environment, such as Android Studio.
3. Ensure that your device or emulator is connected and configured for running Android applications.
4. Run the project on your device or emulator.
5. Enter the name of the city for which you want to retrieve weather data.
6. Click the "Search" button.\
**Enjoy the up-to-date weather information for the selected city!** 😎

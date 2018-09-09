package com.example.arondillqs5328.weatherappliteversion.Common;

import android.location.Location;

import com.example.arondillqs5328.weatherappliteversion.R;

public class Common {
    public static final String APP_ID = "44303b01fa269decd0c714681eebf16e";
    public static Location currentLocation = null;

    public static Integer getWeatherIcon(String icon) {
        switch (icon) {
            case "01d":
                return R.drawable.clear_day;
            case "01n":
                return R.drawable.clear_night;

            case "02d":
                return R.drawable.partly_cloudy;
            case "02n":
                return R.drawable.partly_cloudy_night;

            case "03d":
                return R.drawable.mostly_cloudy;
            case "03n":
                return R.drawable.mostly_cloudy_night;

            case "04d":
                return R.drawable.cloudy_weather;
            case "04n":
                return R.drawable.cloudy_weather;

            case "09d":
                return R.drawable.rainy_weather;
            case "09n":
                return R.drawable.rainy_weather;

            case "10d":
                return R.drawable.rainy_day;
            case "10n":
                return R.drawable.rainy_night;

            case "11d":
                return R.drawable.storm_weather_day;
            case "11n":
                return R.drawable.storm_weather_night;

            case "13d":
                return R.drawable.snow_day;
            case "13n":
                return R.drawable.snow_night;

            case "50d":
                return R.drawable.haze_day;
            case "50n":
                return R.drawable.haze_night;

            default:
                return R.drawable.unknown;
        }
    }
}

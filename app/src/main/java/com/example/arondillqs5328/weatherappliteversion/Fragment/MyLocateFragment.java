package com.example.arondillqs5328.weatherappliteversion.Fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.arondillqs5328.weatherappliteversion.Common.Common;
import com.example.arondillqs5328.weatherappliteversion.Model.WeatherResult;
import com.example.arondillqs5328.weatherappliteversion.R;
import com.example.arondillqs5328.weatherappliteversion.Retrofit.IOpenWeatherMap;
import com.example.arondillqs5328.weatherappliteversion.Retrofit.RetrofitClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;

public class MyLocateFragment extends Fragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private TextView mCityName, mTemperature, mWeather, mMinTemp, mMaxTemp,
            mHumidity, mVisibility, mUvIndex;
    private ImageView mWeatherIcon;

    private CompositeDisposable mCompositeDisposable;
    private IOpenWeatherMap mIOpenWeatherMap;

    public MyLocateFragment() {
        mCompositeDisposable = new CompositeDisposable();
        Retrofit retrofit = RetrofitClient.getRetrofitInstance();
        mIOpenWeatherMap = retrofit.create(IOpenWeatherMap.class);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_my_locate, container, false);

        mCityName = view.findViewById(R.id.city_name);
        mTemperature = view.findViewById(R.id.temp_textView);
        mWeather = view.findViewById(R.id.weather_textView);
        mMinTemp = view.findViewById(R.id.min_temp_textView);
        mMaxTemp = view.findViewById(R.id.max_temp_textView);
        mHumidity = view.findViewById(R.id.humidity_textView);
        mVisibility = view.findViewById(R.id.visibility_textView);
        mUvIndex = view.findViewById(R.id.UV_textView);
        mWeatherIcon = view.findViewById(R.id.weather_icon);

        mSwipeRefreshLayout = view.findViewById(R.id.swiperefresh);
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getWeatherInformation();
            }
        });

        getWeatherInformation();

        return view;
    }

    private void getWeatherInformation() {
        double lat = Common.currentLocation.getLatitude();
        double lon = Common.currentLocation.getLongitude();

        mCompositeDisposable.add(mIOpenWeatherMap.getWeatherByLatLon(String.valueOf(lat),
                String.valueOf(lon),
                Common.APP_ID,
                "metric")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<WeatherResult>() {
                    @Override
                    public void accept(WeatherResult weatherResult) throws Exception {
                        setWeatherInfo(weatherResult);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Toast.makeText(getContext(), "ERROR", Toast.LENGTH_LONG).show();
                    }
                }));

        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void setWeatherInfo(WeatherResult weatherResult) {
        String temperature = String.valueOf(weatherResult.getMain().getTemp());
        temperature = temperature.substring(0, temperature.indexOf(".")) + "°";

        mCityName.setText(weatherResult.getName());
        mTemperature.setText(temperature);
        mWeather.setText(weatherResult.getWeather().get(0).getDescription());

        //TODO: min and max temp not working fine, because I get min/max in current time - not all day!!!
        mMinTemp.setText(new StringBuilder().append(String.valueOf(weatherResult.getMain().getTemp_min())).append("°C").toString());
        mMaxTemp.setText(new StringBuilder().append(String.valueOf(weatherResult.getMain().getTemp_max())).append("°C").toString());

        mHumidity.setText(new StringBuilder().append(String.valueOf(weatherResult.getMain().getHumidity())).append("%").toString());
        mVisibility.setText(new StringBuilder().append(String.valueOf(weatherResult.getVisibility() / 1000)).append(" km").toString());

        mUvIndex.setText("23");

        mWeatherIcon.setImageResource(Common.getWeatherIcon(weatherResult.getWeather().get(0).getIcon()));
    }
}

package com.example.murphy.coolweather.util;

import android.text.TextUtils;

import com.example.murphy.coolweather.db.CoolWeatherDB;
import com.example.murphy.coolweather.model.City;
import com.example.murphy.coolweather.model.County;
import com.example.murphy.coolweather.model.Province;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Murphy on 16/8/23.
 */
public class Utility {
    public synchronized static boolean handleProvincesResponse(CoolWeatherDB coolWeatherDB, String response){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject jsonObject = new JSONObject(response);
                JSONArray allProvinces = jsonObject.getJSONArray("result").getJSONArray(0);
                if (allProvinces != null && allProvinces.length() > 0){
                    for (int i = 0; i < allProvinces.length(); i++) {
                        JSONObject jsonProvince = allProvinces.getJSONObject(i);
                        String id = jsonProvince.getString("id");
                        String name = jsonProvince.getString("fullname");
                        Province province = new Province();
                        province.setProvinceCode(id);
                        province.setProvinceName(name);
                        coolWeatherDB.saveProvince(province);
                    }
                    return true;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public static boolean handleCitiesResponse(CoolWeatherDB coolWeatherDB, String response, int provinceId){
        if(!TextUtils.isEmpty(response)){
            try{
                JSONObject jsonObject = new JSONObject(response);
                JSONArray allCities = jsonObject.getJSONArray("result").getJSONArray(0);
                if (allCities != null && allCities.length() > 0){
                    for (int i = 0; i < allCities.length(); i++) {
                        JSONObject jsonCity = allCities.getJSONObject(i);
                        String id = jsonCity.getString("id");
                        String name = jsonCity.getString("fullname");
                        City city = new City();
                        city.setCityName(name);
                        city.setCityCode(id);
                        city.setProvinceId(provinceId);
                        coolWeatherDB.saveCity(city);
                    }
                    return true;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }

    public  static boolean handleCountiesResponse(CoolWeatherDB coolWeatherDB, String response, int cityId){
        if (!TextUtils.isEmpty(response)){
            try{
                JSONObject jsonObject = new JSONObject(response);
                JSONArray allCounties = jsonObject.getJSONArray("result").getJSONArray(0);
                if (allCounties != null && allCounties.length() > 0){
                    for (int i = 0; i < allCounties.length(); i++) {
                        JSONObject jsonCounty = allCounties.getJSONObject(i);
                        String id = jsonCounty.getString("id");
                        String name = jsonCounty.getString("fullname");
                        County county = new County();
                        county.setCountyName(name);
                        county.setCountyCode(id);
                        county.setCityId(cityId);
                        coolWeatherDB.saveCounty(county);
                    }
                    return true;
                }
            } catch (Exception e){
                e.printStackTrace();
            }
        }
        return false;
    }
}

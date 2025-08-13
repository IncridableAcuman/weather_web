package com.weather.server.service;

import com.weather.server.dto.Weather;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class WeatherService {
    private final RestTemplate restTemplate;
    @Value("${weather.api.url}")
    private String apiUrl;
    @Value("${weather.api.key}")
    private String apiKey;

    @Cacheable(value = "weather",key = "#city")
    public Weather getWeather(String city){
        System.out.println("API: "+city);
        String url = String.format("%s?q=%s&appid=%s&units=metric", apiUrl, city, apiKey);
        Map response=restTemplate.getForObject(url, Map.class);
        if(response==null || response.get("main")==null){
            throw new RuntimeException("City not found");
        }
        Map<String, Object> main = (Map<String, Object>) response.get("main");
        List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
        Weather w = new Weather();
        w.setCity(city);
        w.setTemperature(((Number) main.get("temp")).doubleValue());
        w.setDescription((String) weatherList.get(0).get("description"));
        w.setCondition((String) weatherList.get(0).get("main"));
        return w;
    }
}

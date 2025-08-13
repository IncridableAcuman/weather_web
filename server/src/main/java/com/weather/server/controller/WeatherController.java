package com.weather.server.controller;

import com.weather.server.dto.Weather;
import com.weather.server.service.WeatherService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/weather")
@RequiredArgsConstructor
public class WeatherController {
    private final WeatherService service;

    @GetMapping("/{city}")
    public ResponseEntity<Weather> getWeather(@PathVariable String city){
        return ResponseEntity.ok(service.getWeather(city));
    }
}

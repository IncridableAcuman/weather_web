package com.weather.server.dto;

import lombok.Data;

@Data
public class Weather {
    private String city;
    private String description;
    private double temperature;
}

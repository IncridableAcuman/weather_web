package com.weather.server.dto;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Weather {
    private String city;
    private String description;
    private double temperature;
}

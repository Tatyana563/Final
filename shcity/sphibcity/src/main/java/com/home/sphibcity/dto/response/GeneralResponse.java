package com.home.sphibcity.dto.response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class GeneralResponse {
    private Integer id;
    private String name;
    private String description;
    private Integer population;
    private Double square;
}

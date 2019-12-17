package com.home.sphibcity.dto.request;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public abstract class GeneralRequest {
    protected Integer id;
    protected String name;
    protected String description;
    protected Integer population;
    protected Double square;

}

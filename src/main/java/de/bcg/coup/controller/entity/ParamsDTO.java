package de.bcg.coup.controller.entity;

import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.Size;
import java.util.List;

public class ParamsDTO {

    @Size(min = 1, max = 100)
    private List<Integer> scooters;

    @Range(min = 1, max = 999)
    private Integer c;

    @Range(min = 1, max = 1000)
    private Integer p;

    public ParamsDTO() {
    }

    public ParamsDTO(List<Integer> scooters, Integer c, Integer p) {
        this.scooters = scooters;
        this.c = c;
        this.p = p;
    }

    public List<Integer> getScooters() {
        return scooters;
    }

    public Integer getC() {
        return c;
    }

    public Integer getP() {
        return p;
    }

    public void setScooters(List<Integer> scooters) {
        this.scooters = scooters;
    }

    public void setC(Integer c) {
        this.c = c;
    }

    public void setP(Integer p) {
        this.p = p;
    }

}

package com.example.roadpitmap.entities;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@Entity
public class Pit {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private Float latitude;

    private Float longitude;

    private Float velocity;

    private String date;

    public String toYAJSON() {
        StringBuilder sb = new StringBuilder();
        sb.append("{")
                .append("id: \"id" + id + "\", ")
                .append("type: \"Feature\",")
                .append("geometry: {")
                .append("type: \"Point\",")
                .append("coordinates: [" + latitude + ", " + longitude + "]")
                .append("}")
                .append("} ");

        return sb.toString();
    }
}

package com.example.roadpitmap.services;

import com.example.roadpitmap.entities.Pit;
import com.example.roadpitmap.repositories.PitRepository;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PitService {
    @Autowired
    private PitRepository pitRepository;

    public void save(Pit pit) {
        this.pitRepository.save(pit);
    }

    public Iterable<Pit> findAll() {
        return this.pitRepository.findAll();
    }

    public JsonNode getAllPoints() {
        StringBuilder sb = new StringBuilder();
        Iterable<Pit> pits = this.findAll();
        JsonNode jsonNobeObject = null;

        sb.append("{")
                .append("type: \"FeatureCollection\",")
                .append( "features: [");

        for (Pit pit: pits) {
            sb.append(pit.toYAJSON())
                    .append(",");
        }

        sb.delete(sb.length() - 1,sb.length());
        sb.append("")
                .append("]}");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        try {
            jsonNobeObject = objectMapper.readValue(sb.toString(), JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return jsonNobeObject;
    }

    public JsonNode geBasicStatistic() {
        StringBuilder sb = new StringBuilder();
        JsonNode jsonNobeObject = null;
        sb.append("{")
                .append("\"count\": ")
                .append( "\"" + this.pitRepository.count() +"\",")
                .append("\"avgVelocity\": ")
                .append( "\"" + this.pitRepository.selectVelocityAvg() +"\",")
                .append("\"maxVelocity\": ")
                .append( "\"" + this.pitRepository.selectVelocityMax() +"\",")
                .append("\"minVelocity\": ")
                .append( "\"" + this.pitRepository.selectVelocityMin() +"\"")
                .append("}");
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        try {
            jsonNobeObject = objectMapper.readValue(sb.toString(), JsonNode.class);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return jsonNobeObject;
    }

    public Iterable<Pit> getAdditionalStatistic(String name, String action, String value) {
        switch (name){
            case "Id":
                switch (action){
                    case "Bigger":
                        return this.pitRepository.streamByIdAfter(Integer.valueOf(value));
                    case "Less":
                        return this.pitRepository.streamByIdBefore(Integer.valueOf(value));
                    case "Equal":
                        return this.pitRepository.streamByIdEquals(Integer.valueOf(value));
                }
            case "Date":
                switch (action){
                    case "Bigger":
                        return this.pitRepository.streamByDateAfter(value);
                    case "Less":
                        return this.pitRepository.streamByDateBefore(value);
                    case "Equal":
                        return this.pitRepository.streamByDateEquals(value);
                }
            case "Latitude":
                switch (action){
                    case "Bigger":
                        return this.pitRepository.streamByLatitudeAfter(Float.valueOf(value));
                    case "Less":
                        return this.pitRepository.streamByLatitudeBefore(Float.valueOf(value));
                    case "Equal":
                        return this.pitRepository.streamByLatitudeEquals(Float.valueOf(value));
                }
            case "Longitude":
                switch (action){
                    case "Bigger":
                        return this.pitRepository.streamByLongitudeAfter(Float.valueOf(value));
                    case "Less":
                        return this.pitRepository.streamByLongitudeBefore(Float.valueOf(value));
                    case "Equal":
                        return this.pitRepository.streamByLongitudeEquals(Float.valueOf(value));
                }
            case "Velocity":
                switch (action){
                    case "Bigger":
                        return this.pitRepository.streamByVelocityAfter(Float.valueOf(value));
                    case "Less":
                        return this.pitRepository.streamByVelocityBefore(Float.valueOf(value));
                    case "Equal":
                        return this.pitRepository.streamByVelocityEquals(Float.valueOf(value));
                }
        }
        return null;
    }
}

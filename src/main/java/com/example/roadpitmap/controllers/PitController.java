package com.example.roadpitmap.controllers;

import com.example.roadpitmap.entities.Pit;
import com.example.roadpitmap.services.PitService;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class PitController {

    private final PitService pitService;

    @Autowired
    public PitController(PitService pitService) {
        this.pitService = pitService;
    }

    @PostMapping("/add")
    public String addNewPit (@ModelAttribute("pit") Pit pit) {
        pitService.save(pit);
        return "Saved";
    }

    @PostMapping(value = "/addjson", consumes = "application/json", produces = "application/json")
    public Pit addNewPitJson (@RequestBody Pit pit) {
        pitService.save(pit);
        return pit;
    }

    @GetMapping("/all")
    public Iterable<Pit> getAllPits() {
        return pitService.findAll();
    }

    @GetMapping("/allpoints")
    public JsonNode getAllPoints() {
        return pitService.getAllPoints();
    }

    @GetMapping("/basicstatistics")
    public JsonNode getBasicStatistic(){
        return pitService.geBasicStatistic();
    }

    @GetMapping("/additionalstatistic")
    public Iterable<Pit> getAdditionalStatistic(@RequestParam("name") String name,
                                                @RequestParam("action") String action,
                                                @RequestParam("value") String value){
        return pitService.getAdditionalStatistic(name, action, value);
    }

}
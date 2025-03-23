
package com.example.mapdata_processor.controller;

import com.example.mapdata_processor.service.DataProcessingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/locations")
public class DataProcessingController {
    private final DataProcessingService locationService;

    public DataProcessingController(DataProcessingService locationService) {
        this.locationService = locationService;
    }

    @GetMapping("/analysis")
    public Map<String, Object> getSummary() throws IOException, IOException {
        return locationService.processData();
    }
}


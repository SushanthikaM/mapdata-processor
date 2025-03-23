package com.example.mapdata_processor.service;

import com.example.mapdata_processor.model.Location;
import com.example.mapdata_processor.model.Metadata;
import com.example.mapdata_processor.model.MergeLocation;
import com.example.mapdata_processor.utils.DataLoader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class DataProcessingService {
    private final List<Location> locations;
    private final List<Metadata> metadata;

    @Autowired
    public DataProcessingService() {
        List<Location> tempLocations;
        List<Metadata> tempMetadata;
        try {
            tempLocations = DataLoader.loadLocations();
            tempMetadata = DataLoader.loadMetadata();
        } catch (IOException e) {
            tempLocations = new ArrayList<>();
            tempMetadata = new ArrayList<>();
            System.err.println("Error loading data: " + e.getMessage());
        }
        this.locations = tempLocations;
        this.metadata = tempMetadata;
    }

    public Map<String, Object> processData() {
        Map<String, Metadata> metadataMap = metadata.stream()
                .collect(Collectors.toMap(Metadata::getId, m -> m, (m1, m2) -> m1));

        List<MergeLocation> mergedLocations = locations.stream()
                .map(loc -> new MergeLocation(
                        loc.getId(), loc.getLatitude(), loc.getLongitude(),
                        metadataMap.getOrDefault(loc.getId(), null)
                ))
                .collect(Collectors.toList());

        // Count valid points per type
        Map<String, Long> typeCount = mergedLocations.stream()
                .filter(m -> m.getMetadata() != null)
                .collect(Collectors.groupingBy(m -> m.getMetadata().getType(), Collectors.counting()));

        // Average rating per type
        Map<String, Double> avgRatings = mergedLocations.stream()
                .filter(m -> m.getMetadata() != null)
                .collect(Collectors.groupingBy(
                        m -> m.getMetadata().getType(),
                        Collectors.averagingDouble(m -> Optional.ofNullable(m.getMetadata().getRating()).orElse(0.0))
                ));

        // Location with highest reviews
        Optional<MergeLocation> maxReviews = mergedLocations.stream()
                .filter(m -> m.getMetadata() != null)
                .max(Comparator.comparingInt(m -> Optional.ofNullable(m.getMetadata().getReviews()).orElse(0)));

        // Incomplete data
        List<String> incompleteData = mergedLocations.stream()
                .filter(m -> m.getMetadata() == null)
                .map(MergeLocation::getId)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("valid_points_per_type", typeCount);
        result.put("average_rating_per_type", avgRatings);
        result.put("most_reviewed_location", maxReviews.map(MergeLocation::getId).orElse("No location found"));
        result.put("incomplete_data", incompleteData);
        return result;
    }
}

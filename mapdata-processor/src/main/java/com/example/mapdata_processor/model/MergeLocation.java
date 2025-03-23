package com.example.mapdata_processor.model;

public class MergeLocation {
    private String id;
    private double latitude;
    private double longitude;
    private Metadata metadata;

    public MergeLocation(String id, double latitude, double longitude, Metadata metadata) {
        this.id = id;
        this.latitude = latitude;
        this.longitude = longitude;
        this.metadata = metadata;
    }

    public String getId() {
        return id;
    }

    public Metadata getMetadata() {
        return metadata;
    }
}

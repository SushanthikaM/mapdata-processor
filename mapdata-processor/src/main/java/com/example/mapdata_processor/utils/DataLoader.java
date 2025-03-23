package com.example.mapdata_processor.utils;

import com.example.mapdata_processor.model.Location;
import com.example.mapdata_processor.model.Metadata;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public class DataLoader {

    private static final ObjectMapper objectMapper = new ObjectMapper();

    public static List<Location> loadLocations() throws IOException {
        return loadJsonData("locations.json", new TypeReference<>() {});
    }

    public static List<Metadata> loadMetadata() throws IOException {
        return loadJsonData("metadata.json", new TypeReference<>() {});
    }

    private static <T> T loadJsonData(String fileName, TypeReference<T> typeReference) throws IOException {
        ClassLoader classLoader = DataLoader.class.getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(fileName)) {
            if (inputStream == null) {
                throw new IOException("File not found: " + fileName);
            }
            return objectMapper.readValue(inputStream, typeReference);
        }
    }
}

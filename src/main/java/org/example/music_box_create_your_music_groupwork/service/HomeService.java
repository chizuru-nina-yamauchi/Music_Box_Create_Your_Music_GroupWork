package org.example.music_box_create_your_music_groupwork.service;

import org.example.music_box_create_your_music_groupwork.model.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Service class for handling operations related to Home.
 */
@Service
public class HomeService {

    /**
     * Logger for this class.
     */
    private static final Logger logger = Logger.getLogger(HomeService.class.getName());

    /**
     * ResourceLoader to load resources.
     */
    @Autowired
    private ResourceLoader resourceLoader;

    /**
     * Creates a map of instruments.
     *
     * @param namePrefix The prefix for the name of the instruments.
     * @param count The number of instruments to create.
     * @return A map of instruments where the key is the name of the instrument and the value is the Instrument object.
     */
    public Map<String, Instrument> createInstrumentMap(String namePrefix, int count) {
        Map<String, Instrument> instruments = new LinkedHashMap<>();
        for (int i = 1; i <= count; i++) {
            Instrument instrument = new Instrument();
            String name = namePrefix.toLowerCase() + i;
            instrument.setName(name);
            String audioPath = "/audio/" + name + ".mp3";
            Resource resource = resourceLoader.getResource("classpath:/static" + audioPath);
            try{
                if (resource.exists()) {
                    instrument.setAudioFile(audioPath);
                    instruments.put(name, instrument);
                } else {
                    logger.severe("Error accessing audio file at path: " + resource.getURL());
                }
            } catch (IOException e) {
                logger.severe("Error getting URL of resource: " + e.getMessage());
            }
        }
        return instruments;
    }
}
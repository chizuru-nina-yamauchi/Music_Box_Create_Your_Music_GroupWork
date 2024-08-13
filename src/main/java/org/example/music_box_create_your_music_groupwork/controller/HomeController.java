package org.example.music_box_create_your_music_groupwork.controller;

import org.example.music_box_create_your_music_groupwork.model.Instrument;
import org.example.music_box_create_your_music_groupwork.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * Controller class for handling operations related to Home.
 */
@Controller
public class HomeController {


    /**
     * HomeService to handle business logic related to Home.
     */
    @Autowired
    private HomeService homeService;

    /**
     * Mapping for "/home" URL.
     * Creates maps of instruments and adds them to the model.
     *
     * @param model The Model object to add attributes to for rendering in the view.
     * @return The name of the view to render.
     */
    @GetMapping("/home")
    public String home(Model model) {
        // Create maps of instruments
        Map<String, Instrument> drums = homeService.createInstrumentMap("Drums", 4);
        Map<String, Instrument> bass = homeService.createInstrumentMap("Bass", 4);
        Map<String, Instrument> chords = homeService.createInstrumentMap("Chords", 4);
        Map<String, Instrument> melodies = homeService.createInstrumentMap("Melody", 4);

        // Add instruments to the model
        model.addAttribute("drums", drums);
        model.addAttribute("bass", bass);
        model.addAttribute("chords", chords);
        model.addAttribute("melodies", melodies);

        return "home";
    }
}
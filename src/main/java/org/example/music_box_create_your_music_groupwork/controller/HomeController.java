package org.example.music_box_create_your_music_groupwork.controller;

import org.example.music_box_create_your_music_groupwork.model.Instrument;
import org.example.music_box_create_your_music_groupwork.service.HomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

/**
 * HomeController class handles HTTP requests for the home page.
 */
@Controller
public class HomeController {

    @Autowired
    private HomeService homeService;

    /**
     * Handles GET requests to the /home endpoint.
     *
     * @param model the Model object to add attributes to
     * @return the name of the view to be rendered
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
package com.factory13.audiox.controller;

import com.factory13.audiox.service.AudioService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Slf4j
@RequiredArgsConstructor
@Controller
public class AudioXController {

    private final AudioService audioService;

    @GetMapping("/")
    public String getHomePage(Model model) {
        populateModel(model);
        return "index";
    }

    @GetMapping("/set-index-file")
    public String setIndexFile
            (@RequestParam(value = "file-location", required = false) String fileLocation, Model model) {

        log.info("New index file location: " + fileLocation);
        audioService.setIndexFileLocation(fileLocation);

        populateModel(model);
        return "index";
    }

    @GetMapping("/set-audio-file")
    public String setAudioFile(@RequestParam(value = "audio-file-location", required = false) String fileLocation, Model model) {

        log.info("New audio file location: " + fileLocation);
        audioService.setAudioFileLocation(fileLocation);

        populateModel(model);
        return "index";
    }

    @GetMapping("/read-file")
    public String readAudioSplitterFile(Model model) {

        audioService.readIndexAndPopulateTrackList();

        populateModel(model);
        return "index";
    }

    @GetMapping("/split-audio")
    public String splitIntoChunks(Model model) {
        audioService.splitAudioIntoChunks();

        populateModel(model);
        return "index";
    }

    private void populateModel(Model model) {
        model.addAttribute("audioIndexList", audioService.getTrackList());
        model.addAttribute("faudiolocation", audioService.getAudioFileLocation());
        model.addAttribute("flocation", audioService.getIndexFileLocation());
    }

}

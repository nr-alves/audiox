package com.factory13.audiox.service;

import com.factory13.audiox.domain.Track;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static com.factory13.audiox.util.FileReaderUtil.readFromInputStream;

@Slf4j
@Service
public class AudioService {

    private static final String FFMPEG = "C:\\Tools\\ffmpeg-2022-08-31_build\\bin\\ffmpeg.exe";
    private static final String OUTPUT_DIRECTORY = "C:\\Users\\nuno\\Documents\\projects\\output\\";

    private List<Track> trackList = new LinkedList<>();
    private String indexFileLocation = "C:\\Users\\nuno\\Documents\\projects\\index-splitter.txt";
    private String audioFileLocation = "C:\\Users\\nuno\\Documents\\projects\\audio-test.mp3";

    public List<Track> getTrackList() {
        return trackList;
    }

    public String[][] getTrackMatrix(){

        String[][] result = new String[trackList.size()][2];
        for(int i = 0; i < trackList.size(); i++){
            var track = trackList.get(i);
            result[i][0] = track.getTime();
            result[i][1] = track.getFileName();
        }

        return result;
    }

    public String getAudioFileLocation() {
        return audioFileLocation;
    }

    public void setAudioFileLocation(String audioFileLocation) {
        this.audioFileLocation = audioFileLocation;
    }

    public String getIndexFileLocation() {
        return indexFileLocation;
    }

    public void setIndexFileLocation(String location) {
        indexFileLocation = location;
    }

    public void readIndexAndPopulateTrackList() {
        if (indexFileLocation == null) {
            log.info("File location not set...");
            return;
        }

        FileInputStream fileIs = null;
        try {
            fileIs = new FileInputStream(indexFileLocation);
            String data = readFromInputStream(fileIs);
            populateTrackListFromIndexFile(data);

            log.info("\n" + data);

        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (fileIs != null) {
                try {
                    fileIs.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void populateTrackListFromIndexFile(String data) {
        // Create new Track list
        trackList = new LinkedList<>();

        var dataLines = data.split("\n");

        Arrays.stream(dataLines)
                .forEach(line -> {
                    var parts = line.split(" ", 2);
                    trackList.add(new Track(parts[0], parts[1]));
                });
    }

    public void splitAudioIntoChunks() {
        if (audioFileLocation == null) {
            log.info("Audio file location not set...");
            return;
        }

        readIndexAndPopulateTrackList();

        for (int i = 0; i < trackList.size(); i++) {
            var currentTrack = trackList.get(i);
            var title = "\"" + OUTPUT_DIRECTORY + currentTrack.getFileName() + "\"";
            var time = currentTrack.getLocalTime();

            var nextTrackIndex = i + 1;
            if (nextTrackIndex < trackList.size()) {
                var nextTrack = trackList.get(nextTrackIndex);
                var nextTrackTime = nextTrack.getLocalTime();
                var currentTrackDuration = Duration.between(time, nextTrackTime).getSeconds();

                executeFFMpegCommand(currentTrack.getTime(), currentTrackDuration, title, i + 1, trackList.size() - 1);
            }
        }

    }

    private void executeFFMpegCommand(String time, long durationInSeconds, String trackTitle, int trackNumber, int totalTracks) {
        //

        var command = FFMPEG + " -i %s -ss %s -t %s -metadata track=\"%s/%s\" -acodec copy %s";
        command = String.format(command, "\"" + audioFileLocation + "\"", time, durationInSeconds, trackNumber, totalTracks, trackTitle);
        log.info("Executing command: " + command);

        try {
            final var rt = Runtime.getRuntime();
            final var process = rt.exec(command);
            process.waitFor(10, TimeUnit.SECONDS);
            process.destroy();

        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}

package org.example.music_box_create_your_music_groupwork.model;


public class Instrument {

    private String name;
    private String audioFile;

    public Instrument() {
    }

    public Instrument(String name, String audioFile) {
        this.name = name;
        this.audioFile = audioFile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAudioFile() {
        return audioFile;
    }

    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }
}

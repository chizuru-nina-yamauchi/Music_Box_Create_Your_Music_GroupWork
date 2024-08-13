package org.example.music_box_create_your_music_groupwork.model;

/**
 * The Instrument class represents a musical instrument with a name and an associated audio file.
 */
public class Instrument {

    private String name;
    private String audioFile;

    /**
     * Default constructor for creating an empty Instrument object.
     */
    public Instrument() {
    }

    /**
     * Constructor for creating an Instrument object with specified name and audio file.
     *
     * @param name the name of the instrument
     * @param audioFile the audio file associated with the instrument
     */
    public Instrument(String name, String audioFile) {
        this.name = name;
        this.audioFile = audioFile;
    }

    /**
     * Gets the name of the instrument.
     *
     * @return the name of the instrument
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the instrument.
     *
     * @param name the name of the instrument
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the audio file associated with the instrument.
     *
     * @return the audio file associated with the instrument
     */
    public String getAudioFile() {
        return audioFile;
    }

    /**
     * Sets the audio file associated with the instrument.
     *
     * @param audioFile the audio file associated with the instrument
     */
    public void setAudioFile(String audioFile) {
        this.audioFile = audioFile;
    }
}
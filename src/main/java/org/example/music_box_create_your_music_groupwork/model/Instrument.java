
package org.example.music_box_create_your_music_groupwork.model;

/**
 * Model class for Instrument.
 * An Instrument has a name and an audio file associated with it.
 */

public class Instrument {

    /**
     * The name of the instrument.
     */
    private String name;
    /**
     * The audio file associated with the instrument.
     */
    private String audioFile;

    /**
     * Default constructor for Instrument.
     */
    public Instrument() {
    }

    /**
     * Constructor for Instrument with name and audio file.
     *
     * @param name The name of the instrument.
     * @param audioFile The audio file associated with the instrument.
     */
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

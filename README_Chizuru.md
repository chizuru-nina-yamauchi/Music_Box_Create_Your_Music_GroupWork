# MusicBox_Create_Your_Music

Create the music web application like https://learningmusic.ableton.com/index.html with Spring Boot, Java, JavaScript, HTML, CSS, and Thymeleaf.

## Sound Design and Sound Production for the instrument sounds

With 'Ableton Live Suit 10'

## Configuration the Database
(For the user login, signup, and user management, transaction and the future implementation of Spotify API)

**Configure 'application.properties'(It is git ignored to secure the data privacy) file**

```
# Database Configuration
spring.datasource.url=jdbc:postgresql://localhost:5432/database_name
spring.datasource.username=username
spring.datasource.password=password
spring.datasource.driver-class-name=org.postgresql.Driver

# JPA/Hibernate properties
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true

# Spotify API Configuration(if it is implemented)
spotify.client-id=your_client_id
spotify.client-secret=your_client_secret
spotify.redirect-uri=http://localhost:8080/your_correct_endpoint


```

## Structure

1. Run the application
2. Go to http://localhost:8080 on the web browser
3. Login or signup screen shows up
4. If the user has account, login
5. If not, signup -> email confirmation -> login
6. The main menu shows up 'User Details', 'Transaction', 'Main Music Application', 'Logout'

- User Details: Shows the user's details
- Transaction: Shows the user's transaction history and the user can add the transaction
- Main Music Application: The main music application that the user can create the music with the instrument sounds(localhost:8080/home)
- Logout: Logout the user

### Admin Role
If we add 'admin' role in addition to the user role, the admin can see the user list and the transaction list and can delete the user and the transaction.
- User List: Shows the user list
- Transaction List: Shows the transaction list
- Delete User: Delete the user
- Delete Transaction: Delete the transaction
- Logout: Logout the admin

### Premium User Role
If we add the 'premium-user' role in addition to the user role after they pay, the user can use the Spotify API to get the music reference and can download the music in Main Music Application.


## The necessary structure for the music application

```
├── src/main/java/org/example/music_box_create_your_music_groupwork
│   ├── config
│   │   └── WebConfig.java          # Web configuration class for serving static resources
│   ├── controller
│   │   └── HomeController.java     # Controller class for handling Music App operations
│   ├── model
│   │   └── Instrument.java         # Model class representing an Instrument
│   ├── service
│   │   └── HomeService.java        # Service class for handling business logic
│   └── MusicBoxCreateYourMusicGroupWorkApplication.java  # Main Spring Boot application class
├── src/main/resources/static/audio # Directory containing audio files for the instruments
├── src/main/resources/templates
│   └── home.html                   # Thymeleaf template for the home page and main function for music and sound with Tone.js
├── src/main/resources
│   └── application.properties      # Later it will be needed for Spotify API if it is implemented
├── src/main/resources/static/css   # Directory containing CSS files
├── src/main/resources/static/js    # Directory containing JavaScript files
├── pom.xml                         # Maven configuration file
└── README.md                       # Project documentation (this file)

```


### HomeService class

The `HomeService` class is responsible for the business logic related to the home page of the application. 
Its main function is to create a map of `Instrument` objects and associate them with their respective audio files.

The `createInstrumentMap` method takes a name prefix(Ex. 'drum', 'melody' etc.) and a count as parameters. 
It generates `count` number of `Instrument` objects, assigns them a name based on the `namePrefix` parameter, and associates them with an audio file located at a specific path in the resources directory. The method returns a map where the key is the name of the instrument and the value is the `Instrument` object.

If the audio file for an instrument does not exist at the expected path, an error message is logged.


### home.html template

The `home.html` file is the main HTML template for the Music Box application. It uses Thymeleaf to generate the user interface based on the data provided by the server. The page includes sections for different types of instruments (Drums, Bass, Chords, Melodies), each with a set of buttons for playing different sounds.

The Tone.js (from JavaScript library) is used in this file to handle audio playback. (It is a library to create interactive music in the browser. It is used to load and play audio files, manage the audio context, and handle user interactions related to playing sounds.)

**Key parts related to Tone.js:**

- `resumeToneContext()`: This function checks if the Tone.js audio context is running and resumes it if it's not. This is necessary because some browsers require user interaction before they will play audio.

- `loadAndPlay(audioFile)`: This function creates a new `Tone.Player` instance, loads an audio file into it, and starts playback. The `audioFile` parameter is the path to the audio file to play.

- `selectSound(element, type)`: This function is called when a user clicks on an instrument button. It gets the audio file associated with the clicked button, stops any currently playing sound of the same type, and starts playing the new sound.

- `playAll()`: This function plays all selected sounds together. It retrieves the selected sounds for each instrument type from hidden input fields and plays them using the `loadAndPlay` function.

- Event listeners: Event listeners are attached to each instrument button to call `selectSound` when clicked, to the 'Start' button to call `resumeToneContext`, and to the 'Play All' button to call `playAll`.

In summary, Tone.js is used in `home.html` to manage the audio context, load and play audio files, and handle user interactions related to playing sounds.



## Usage
Once the application is running:

1. Open a web browser and go to http://localhost:8080/home.
2. Choose the instrument sounds you want to play by clicking on the buttons for Drums, Bass, Chords, and Melodies.
3. Click "Play All" to play the selected sounds together.
4. Use the "Start" button to initialize the audio context if needed

## The function can be improved for music application: 
1. Looping
2. Volume
3. Timing and synchronizing
4. Recording
5. Download the music
6. Jump to user's spotify libraries with Spotify API for the music reference 
etc

## What I used for this project
- Java
- Spring Boot
- JavaScript(Tone.js)
- HTML
- Thymeleaf
- CSS




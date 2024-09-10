# Music_Box_Create_Your_Music_GroupWork
Final Project for DCI Java course 


# MusicBox_Create_Your_Music

Create the music web application like https://learningmusic.ableton.com/index.html with Spring Boot, Java, JavaScript, HTML, CSS, and Thymeleaf.
This project is for all the users to feel music closer and enjoy creating the music with the default instrument sounds.

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
2. Go to http://localhost:8080 on the google browser
3. Login or signup screen shows up
4. If the user has account, login
5. If not, signup -> email confirmation -> login
6. If the user forgot their password -> reset password
7. The main page shows up 'Main Music Application', 'Logout'

- Main Music Application: The main music application that the user can create the music with the instrument sounds(localhost:8080/)
- Logout: Logout the user


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
│   └── drums1.wav, drums2.wav .... # all the audio files for the instruments
├── src/main/resources/static/css   # Directory containing CSS files
│   └── style2.css                  # CSS file for styling the home.html template
├── src/main/resources/templates
│   └── home.html                   # Thymeleaf template for the home page and main function for music and sound with Tone.js
├── src/main/resources
│   └── application.properties      # Later it will be needed for Spotify API if it is implemented
├── pom.xml                         # Maven configuration file
└── README.md                       # Project documentation (this file)

```



## Usage
Once the application is running:

1. Open the google browser and go to http://localhost:8080/.
2. Use the "Start" button to initialize the audio context if needed.
3. Choose the instrument sounds you want to play by clicking on the buttons for Drums, Bass, Chords, and Melodies.
4. Click "Play All" to play the selected sounds together.
5. Click "Start Recording" to record the music
6. Click "Stop Recording" to stop the recording
7. Click 'Play' in the table to play the recorded music
8. Click 'Stop' in the table to stop the recorded music
9. Click 'Download' in the table to download the recorded music


## The function can be improved for music application: 
1. Looping(Done)
2. Volume
3. Timing and synchronizing
4. Recording(Done)
5. Download the music(Done)
6. Jump to user's spotify libraries with Spotify API for the music reference 
etc

## What we used for this project
- Java
- Spring Boot
- JavaScript(Tone.js)
- HTML
- Thymeleaf
- CSS


## Our role in the project:
- Chizuru: Sound Box (with all connected features)
- Ioannis: Subscription (not implemented)
- Michelle: Login (with all connected features)



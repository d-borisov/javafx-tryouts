package sample.utils;

import sample.controllers.AppScreen;

import java.net.URL;

/*
   Объяснение, как нужно подгружать fxml-файлы.
   http://stackoverflow.com/questions/22000423/javafx-and-maven-nullpointerexception-location-is-required

   Либо:
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("sampleView.fxml"));
        Parent root = FXMLLoader.load(getClass().getResource("/sampleView.fxml"));

 */
public class ResourceLoader {

    public static URL load(AppScreen screen) {
        return url(screen.getLocation());
    }

    public static URL url(String resource) {
        return ResourceLoader.class.getResource(resource);
    }
}

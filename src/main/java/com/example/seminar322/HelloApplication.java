package com.example.seminar322;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
        /*
        JavaFX 101

        Modalitati de a aranja controalele pe fereastra:
        1. Incarcand un fisier .fxml editat manual (fiecare fereastra a aplicatiei va avea
        propriul fisier .fxml)

        2. Lucrand cu un fisier .fxml in Scene Builder (https://gluonhq.com/products/scene-builder/,
        https://www.jetbrains.com/help/idea/opening-fxml-files-in-javafx-scene-builder.html)

        3. Din cod :)
        */

    @Override
    public void start(Stage stage) throws IOException {
        SQLPersonRepository repo = new SQLPersonRepository("persons.db");

//        try {
//            repo.add(new Person(100, "Pop Andrei"));
//            repo.add(new Person(101, "Rupea Andreea"));
//            repo.add(new Person(102, "Dorel"));
//        } catch (RepositoryException e) {
//            throw new RuntimeException(e);
//        }

//        for (var p : repo) {
//            System.out.println(p);
//        }


        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        HelloController hc = fxmlLoader.getController();
        hc.setRepository(repo);
        hc.loadPersons();

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
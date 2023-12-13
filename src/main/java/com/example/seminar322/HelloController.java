package com.example.seminar322;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class HelloController {
    public ListView<Person> listPersons;
    public TextField txtId;
    public TextField txtNume;
    public Button btnAdd;

    private IRepository<Person> repo;

    ObservableList<Person> personList = FXCollections.observableList(new ArrayList<Person>());

    public void loadPersons() {

        // sablonul de proiectare factory method https://refactoring.guru/design-patterns/factory-method
        // sablonul de proiectare wrapper https://refactoring.guru/design-patterns/adapter
//        var personList = FXCollections.observableList(new ArrayList<Person>());

        // aici setam lista ca si sursa de date pentru componenta grafica
        // componenta grafica se aboneaza la modificarile din lista "personList"
        // sablonul de proiectare observer https://refactoring.guru/design-patterns/observer
        listPersons.setItems(personList);


        for (var p : repo) {
            // lista de persoane notifica componenta grafica ca si-a schimbat continutul
            personList.add(p);
        }
    }

    public void setRepository(IRepository<Person> repo) {
        this.repo = repo;
    }

    public void btnAddPressed(ActionEvent actionEvent) {
        // TODO Error handling
        var personId = Integer.parseInt(txtId.getText());
        var personName = txtNume.getText();

        Person p = new Person(personId, personName);
        try {
            repo.add(p);
            personList.add(p);
        } catch (RepositoryException e) {
            Alert hopa = new Alert(Alert.AlertType.ERROR, e.getMessage());
            hopa.show();
        }


    }
}
package view;

/**
 * Created by robert on 18.01.2017.
 */

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import java.util.List;
import java.awt.Toolkit;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;


import model.*;
import utils.*;
import Controller.*;



public class MainWindow {
    private Controller ctrl;
    private ListView<String> prgs;
    private List<Statement> list;
    private Button execute;

    public MainWindow(Controller ctrl, List<Statement> list) {
        this.ctrl = ctrl;
        this.list = list;

        prgs = new ListView<>();
        prgs.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        execute = new Button("Execute program");
        execute.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e){
                int index = prgs.getSelectionModel().getSelectedIndex();
                if (index <0){
                    Toolkit.getDefaultToolkit().beep();
                } else {
                    prgs.getItems().remove(index);

                    //initializing the new PrgState for the corresponding selected Statement
                    initPrgState(list.get(index));

                    Stage prgView = new Stage();
                    prgView.setTitle("PrgState");

                    // created aditional view for the PrgState
                    PrgStateView view=new PrgStateView(ctrl);
                    view.populateAll();

                    prgView.setScene(new Scene(view.getView(), 1000, 500));
                    prgView.show();
                }
            }
        });
    }

    public void initPrgState(Statement stmt) {
        PrgState initPrgState = new PrgState(
                new ExeStack<Statement>(),
                new SymbolTable<String,Integer>(),
                new Output<Integer>(),
                new FileTable<Integer,FileData>(),
                new Heap<Integer>(),
                new LockTable<Integer,Integer>(),stmt);
        this.ctrl.addPrgState(initPrgState);
    }

    public HBox getView() {
        HBox view = new HBox();
        javafx.scene.control.Label title = new javafx.scene.control.Label("Runnable programs");
        populateList();
        view.getChildren().addAll(title, this.prgs, this.execute);
        return view;
    }

    public void populateList() {
        this.prgs.getItems().clear();
        int index = 1;
        for(Statement prg : this.list){
            this.prgs.getItems().add("Run example " + index + ": "+ prg.toString());
            index++;
        }
    }
}


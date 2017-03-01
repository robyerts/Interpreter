package view;

import Controller.Controller;
import model.PrgState;
import utils.InterpretorException;

import java.util.Scanner;

/**
 * Created by robert on 07.12.2016.
 */
public class DeserializeCommand extends Command{
    private Controller ctrl;

    public DeserializeCommand(String key, String description, Controller ctrl) {
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in); // ??
        String fileName = scanner.nextLine();
        try {
            PrgState pg = this.ctrl.deserialize(fileName);
            System.out.println(pg);
        } catch (InterpretorException e1){
            System.out.println(e1.getMessage());
        }

    }
}

package view;
import Controller.*;
import utils.InterpretorException;

import java.util.Scanner;

/**
 * Created by robert on 07.12.2016.
 */
public class SerializeCommand extends Command {
    private Controller ctrl;

    public SerializeCommand(String key, String description, Controller ctrl) {
        super(key, description);
        this.ctrl = ctrl;
    }

    @Override
    public void execute() {
        Scanner scanner = new Scanner(System.in); // ??
        String fileName = scanner.nextLine();
        try {
            this.ctrl.serialize(fileName);
        } catch (InterpretorException e1){
            System.out.println(e1.getMessage());
        }

    }
}

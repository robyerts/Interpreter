package view;
import Controller.*;
import utils.InterpretorException;

/**
 * Created by robert on 09.11.2016.
 */
public class RunExample extends Command {
    private Controller ctr;
    public RunExample(String key, String desc,Controller ctr){
        super(key, desc);
        this.ctr=ctr;
    }
    @Override
    public void execute() {

        try {
            ctr.oneStepGUI();
        }catch(ArithmeticException ar)
        {
            System.out.println(ar.getMessage());
        }
        catch(InterpretorException ru)
        {
            System.out.println(ru.getMessage());
        }

    }
}

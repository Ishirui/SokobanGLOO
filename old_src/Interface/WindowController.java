package Interface;

import javax.swing.JFrame;

public interface WindowController extends Runnable{
    public void refresh();
    public JFrame getWindow();
    public void setKeyboardHandler(KeyboardHandler keyboardHandler);

    //Any WindowController is implicitly Runnable, and their run function is simply to refresh - basically, I've just created an alias of the run method required by Swing.
    default void run(){
        refresh();
    }
}

package Interface;

public interface WindowController extends Runnable{
    public void refresh();

    //Any WindowController is implicitly Runnable, and their run function is simply to refresh - basically, I've just created an alias of the run method required by Swing.

    default void run(){
        refresh();
    }
}

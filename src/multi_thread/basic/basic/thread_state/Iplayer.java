package multi_thread.basic.basic.thread_state;

import java.util.Collections;

public interface Iplayer {
    void action();
}

class Player1 implements Iplayer{
    private String name;
    private String weapon;

    public Player1(String name, String weapon){
        this.name = name;
        this.weapon = weapon;
    }
    @Override
    public void action() {
        System.out.println("Perform " + weapon + " attach!");
        Collections.emptyList();
    }
}

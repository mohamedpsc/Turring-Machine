import java.util.HashMap;

enum Actions{
    ACCEPT,
    REJECT,
    LEFT,
    RIGHT
}

public class State {
    private HashMap<Character, Transition> transitions;
    private String name;

    public State(String name){
        this.transitions = new HashMap<>();
        this.name = name;
    }

    public void addTransition(char read, State nextState, char write, char action) throws Exception {
        transitions.put(read, new Transition(nextState, write, action));
    }

    public void addTransition(char read, Transition transition){
        transitions.put(read, transition);
    }

    public Transition next(char c) throws Exception {
        Transition temp = transitions.get(c);
        if(temp == null)
            throw new Exception("No transition for (" + c + ") from State (" + name + ") specified.");
        return temp;
    }

    @Override
    public String toString(){
        return name;
    }
}
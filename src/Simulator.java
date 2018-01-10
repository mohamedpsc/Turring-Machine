import java.util.ArrayList;

public class Simulator {
    private ArrayList<State> states;
    private State startState;
    private Alphabet alphabet;
    private Cursor cursor;
    private Transition finalTransition;

    public Simulator(String rules, String input) throws Exception {
        this.states = new ArrayList<>();
        this.alphabet = new Alphabet();
        this.alphabet.addChar('#');
        this.cursor = new Cursor(input);
        prepareData(rules);
    }

    private void prepareData(String rules) throws Exception {
        if(rules.isEmpty())
            throw new Exception("Please Enter valid Transitions.");
        String lines[] = rules.split("\\r?\\n");
        for (int i=0; i<lines.length; i++){
            String line = lines[i].replaceAll("[(,)]", " ");
            String terms[] = line.trim().split("\\s+");
            System.out.println(terms.length);
            if(terms.length < 5) {
                throw new Exception("Wrong transition format in line " + (i+1));
            }
            State fromState = addNode(terms[0]);
            char read = addAlphabet(terms[1]);
            State toState = addNode(terms[2]);
            char write = addAlphabet(terms[3]);
            char action = terms[4].toCharArray()[0];
            fromState.addTransition(read, toState, write, action);
        }
    }

    private State addNode(String name){
        for(State state :states){
            if(state.toString().equals(name)){
                return state;
            }
        }
        State newState = new State(name);
        states.add(newState);
        return newState;
    }

    private char addAlphabet(String c){
        char temp = c.toCharArray()[0];
        alphabet.addChar(temp);
        return temp;
    }

    public void setStartState(String start) throws Exception {
        for(State state :states){
            if(state.toString().equals(start)){
                this.startState = state;
                return;
            }
        }
        throw new Exception("Unknown Start State.");
    }

    public void compute() throws Exception {
        State currentState = startState;
        if(currentState != null){
            char previous = '\0';
            while(cursor.read() != '#' || previous != '#'){
                finalTransition = currentState.next(cursor.read());
                if(finalTransition.action() == Actions.RIGHT){
                    cursor.write(finalTransition.write());
                    previous = cursor.read();
                    cursor.increment();
                }else if(finalTransition.action() == Actions.LEFT){
                    cursor.write(finalTransition.write());
                    previous = cursor.read();
                    cursor.decrement();
                }else{
                    cursor.write((finalTransition.write()));
                    return;
                }
                currentState = finalTransition.getState();
            }
            throw new Exception("Input Completely Consumed");
        }else{
            throw new Exception("Start State is not specified");
        }
    }

    public ArrayList<State> getStates() {
        return states;
    }

    public Alphabet getAlphabet() {
        return alphabet;
    }

    public Transition getFinalTransition() { return finalTransition; }

    public Cursor getCursor() { return cursor; }

    public String getOutput() { return cursor.getData(); }
}
/*
q0 a q0 a r
q0 b q1 b r
q0 # q0 # n
q1 a q1 a n
q1 b q1 b r
q1 # q1 # y
 */
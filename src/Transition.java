public class Transition {
    private State state;
    private char write;
    private Actions action;

    public Transition(State to, char write, char action) throws Exception {
        this.state = to;
        this.write = write;
        switch (action){
            case 'y':
                this.action = Actions.ACCEPT;
                break;
            case 'n':
                this.action = Actions.REJECT;
                break;
            case 'r':
                this.action = Actions.RIGHT;
                break;
            case 'l':
                this.action = Actions.LEFT;
                break;
            default:
                throw new Exception("Please Enter a valid Action.");
        }
    }

    public State getState(){
        return state;
    }

    public char write(){
        return write;
    }

    public Actions action(){
        return action;
    }

    @Override
    public String toString(){
        return "" + state + " " + write + " " + action;
    }
}

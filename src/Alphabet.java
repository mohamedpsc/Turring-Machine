import java.util.ArrayList;

public class Alphabet {
    private ArrayList<Character> alphabet;

    public Alphabet(){
        this.alphabet = new ArrayList<>();
    }

    public void addChar(char c){
        if(!has(c))
            this.alphabet.add(c);
    }

    public void removeChar(char c){
        this.alphabet.remove(c);
    }

    public boolean has(char c){
        return this.alphabet.contains(c);
    }

    public int size(){
        return alphabet.size();
    }

    @Override
    public String toString(){
        String temp = "";
        for(int i=0; i< size(); i++){
            temp += alphabet.get(i).toString() + "\n";
        }
        return temp;
    }
}

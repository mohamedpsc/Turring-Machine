import java.util.ArrayList;

public class Cursor {
    private char data[];
    private ArrayList<Character> extendedInput;
    private int cursor;

    public Cursor(String data) throws Exception {
        if(data.isEmpty()){
            throw new Exception("Please Enter a valid Input.");
        }
        this.cursor = 0;
        this.data = data.replaceAll(" ", "#").toCharArray();
        this.extendedInput = new ArrayList<>();
    }

    public void increment(){
        cursor++;
        if(cursor >= data.length + extendedInput.size())
            extendedInput.add('#');
    }

    public void decrement() throws Exception {
        cursor--;
        if(cursor<0){
            throw new Exception("Cursor is out of bounds");
        }
    }

    public char read(){
        return cursor < data.length? data[cursor]: extendedInput.get(cursor - data.length);
    }

    public void write(char c){
        if(cursor < data.length)
            data[cursor] = c;
        else
            extendedInput.set(cursor - data.length, c);
    }

    public int getCursor() { return cursor; }

    public String getData(){
        return new String(data) + extendedInput.toString().replaceAll("[\\[\\]]", "");
    }

    @Override
    public String toString() {
        return "" + cursor;
    }
}

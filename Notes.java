import java.util.*;
import java.time.*;

public class Notes extends SuperMain implements Comparable<Notes>{
    private LocalTime Time;
    private String Text;
    private boolean important;
    public Notes(LocalTime Time, String Text, boolean important){
        this.Text = Text;
        this.Time = Time;
        this.important = important;
        CharacterFrequency = new HashMap<Character, Integer>();
        CharacterFrequency.put('h',1);
        NoteList.add(this);
    }
    public String toString(){
        return Text+" "+Time+" "+important;
    }
    public LocalTime getTime(){
        return Time;
    }
    public String getText(){
        return Text;
    }
    public boolean getImportance(){
        return important;
    }
    public int compareTo(Notes other){
        if(this.important && !other.getImportance()){
            return -1;
        }
        else if(!this.important && other.getImportance()){
            return 1;
        }
        else{
            if(this.Time.compareTo(other.getTime())==1){
                return 1;
            }
            else{
                if(this.Time.compareTo(other.getTime())==0){
                    return 0;
                }
                else{
                    return -1;
                }
                
            }
        }
        
    }
}

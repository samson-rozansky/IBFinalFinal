import java.util.*;

class TimeComparator implements Comparator<Notes> {
  
    // override the compare() method
    public int compare(Notes s1, Notes s2)
    {
        return (s1.getTime()).compareTo(s2.getTime());
    }
}

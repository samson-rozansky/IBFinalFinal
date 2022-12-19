import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.time.*;
import java.io.*;

public class SuperMain{
    public static ArrayList<Notes> NoteList;
    public static HashMap<Character, Integer> CharacterFrequency;

    public static void main(String[] args) throws IOException {
        File TheNotes = new File("notes.txt");
        TheNotes.delete();
        NoteList = new ArrayList<Notes>();
        CharacterFrequency = new HashMap<Character, Integer>();
        new Main();
    }
}

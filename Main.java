import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.time.*;
import java.io.*;

public class Main extends SuperMain{
    private JFrame frame;
    private JTextArea notesTextArea;
    private JButton createNote;
    private JButton TimeSort;
    private JButton ImportantTimeSort;
    private JButton ImportantSort;
    protected JLabel Label;
    public Main() {        
        
        create();
    
        // create a new note
        createNote = new JButton("Create Note");
        createNote.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            frame.dispose();
            new NotesApp();
            Label = update();
            //
          }
        });
        // output the notes in order of Time
        TimeSort = new JButton("Time Created Of Note");
        TimeSort.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            try {
                TimeSorting();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
          }
        });
        // output only important notes in order of time
        ImportantSort = new JButton("Important Only");
        ImportantSort.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            try {
                ImportantSorting();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
          }
        });
        // output the notes in order of Importance then Time
        ImportantTimeSort = new JButton("Important Then Time");
        ImportantTimeSort.addActionListener(new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            try {
                ImportantTimeSorting();
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
          }
        });

        
        // add the components to the frame
        frame.add(Label,BorderLayout.NORTH);
        frame.add(createNote, BorderLayout.SOUTH);
        frame.add(TimeSort, BorderLayout.WEST);
        frame.add(ImportantSort, BorderLayout.CENTER);
        frame.add(ImportantTimeSort, BorderLayout.EAST);
        // show the frame
        frame.setVisible(true);
        
      }
    public void WriteToFile(boolean importantOnly) throws IOException{
        File TheNotes = new File("notes.txt");
        TheNotes.delete();
        FileWriter myWriter = new FileWriter("notes.txt",true);
        for(Notes i : NoteList){
            int hour = Integer.parseInt(i.getTime().toString().substring(0, 2));
            int minute = Integer.parseInt(i.getTime().toString().substring(3, 5));
            int second = Integer.parseInt(i.getTime().toString().substring(6, 8));
            if(importantOnly){
                if(i.getImportance()){
                    myWriter.write(i.getText()+"\n");
                    System.out.println(i.getText());
                    System.out.printf("Hour: %-2d  Minute: %-2d  Second: %-2d%n",hour,minute,second);
                }
            }
            else{
                myWriter.write(i.getText()+"\n");
                System.out.println(i.getText());
                System.out.printf("Hour: %-2d  Minute: %-2d  Second: %-2d%n",hour,minute,second);
            }
            
        }
        myWriter.close();
    }
    //Sort by Time Created
    public void TimeSorting() throws IOException{
        Collections.sort(NoteList, new TimeComparator());
        WriteToFile(false);
        
    }
    public void ImportantSorting() throws IOException{
        Collections.sort(NoteList);
        WriteToFile(true);
    }
    public void ImportantTimeSorting() throws IOException{
        Collections.sort(NoteList);
        WriteToFile(false);
    }
    public void create(){
        // create the frame
        frame = new JFrame("Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        Label = update();
    }
    public JLabel update(){
        Label = new JLabel();

        if(!CharacterFrequency.isEmpty()){
            CharacterFrequency = new HashMap<Character, Integer>();
            for(Notes Note : NoteList){
                for(Character chars : Note.getText().toCharArray() ){
                    chars = Character.toLowerCase(chars);
                    try{
                        CharacterFrequency.put(chars,CharacterFrequency.get(chars)+1);
                    }catch(Exception e){
                        CharacterFrequency.put(chars,1);
                    }
                }
            }
            int frequent = 0;
            char thingy = ' ';
            for(Character k : CharacterFrequency.keySet()){
                if(frequent<CharacterFrequency.get(k)){
                    thingy = k;
                    frequent = CharacterFrequency.get(k);
                }
            }
            Label.setText("Most Frequent character is "+thingy+" with "+frequent+" times");
        }
        else{
            Label.setText("No Characters yet");
        }
        return Label;
    }
}

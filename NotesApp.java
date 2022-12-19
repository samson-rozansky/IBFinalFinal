import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.time.*;
import java.io.*;

public class NotesApp extends Main{
  private JFrame frame;
  private JTextArea notesTextArea;
  private JScrollPane scrollPane;
  private JButton saveButton;
  
  public NotesApp() {
    // create the frame
    frame = new JFrame("Notes App");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(500, 500);

    // create the text area and add it to a scroll pane
    notesTextArea = new JTextArea();
    scrollPane = new JScrollPane(notesTextArea);

    // create the save button
    saveButton = new JButton("Save");
    saveButton.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent e) {
        saveNotes();
      }
    });

    // add the components to the frame
    frame.add(scrollPane, BorderLayout.CENTER);
    frame.add(saveButton, BorderLayout.SOUTH);
    // show the frame
    frame.setVisible(true);
  }

  // save the notes to a file
  private void saveNotes() {
        LocalTime CurrentTime = LocalTime.now();
        String TextInside = notesTextArea.getText();
        boolean isImportant = TextInside.charAt(0)=='*';
        if(isImportant){
          TextInside = TextInside.substring(1);
        }
        //Add note to the ArrayList
        new Notes(CurrentTime,TextInside,isImportant);
        frame.setVisible(false);
        frame.dispose();
        try {
          File myObj = new File("notes.txt");
          if (myObj.createNewFile()) {
            //System.out.println("File created: " + myObj.getName());

          } else {
            //System.out.println("File already exists.");
          }
        } catch (IOException e) {
          //System.out.println("An error occurred12.");
          e.printStackTrace();
        }
        try {
          FileWriter myWriter = new FileWriter("notes.txt",true);
          myWriter.write(TextInside+" "+CurrentTime.toString()+"\n");
          myWriter.close();
          //System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
          //System.out.println("An error occurred.");
          e.printStackTrace();
        }
        

      }
}

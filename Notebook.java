package notebook;
import java.text.*;

public class Notebook {
    private  Note[] notebook;
    private static final int DEFAULT_INITIAL_SIZE = 5;
    private  int countOfNotes = 0;

    public Notebook() {
        System.out.println("Create notebook with default initial size");
        notebook = new Note[ DEFAULT_INITIAL_SIZE];
    }

    public void addNote(String title, String message) {
        // check the same titles
        boolean alreadyHasThisTitle;
        do {
            System.out.println("Adding note " + title);
            alreadyHasThisTitle = false;
            for (Note note: notebook) {
                if (note != null && title.equals(note.getNoteTitle())) {
                    alreadyHasThisTitle = true;
                    break;
                }
            }
            if (alreadyHasThisTitle) {
                title = "copy of " + title;
                System.out.println("Note with " + title + " already exists.");
            }
        } while (alreadyHasThisTitle);

        //increase array size if it necessary
        if (countOfNotes >= notebook.length) {
            Note[] notebookNew = new Note[(int) (notebook.length * 1.2)];
            System.arraycopy(notebook, 0, notebookNew, 0, countOfNotes);
            notebook = notebookNew;
        }

        notebook[countOfNotes] = new Note(title, message);
        //add text of note
        System.out.println("Added note with title " + title);
        countOfNotes++;
    }

    // delete note by title
    public void dropNote(String title) {
        boolean isExist = false;
        int index = -1;
        System.out.println("Dropping note " + title);
        for (int i = 0; i <= countOfNotes; i++) {
            if (notebook[i] != null && title.equals(notebook[i].getNoteTitle())) {
                deleteNoteNumber(i);
                index = i;
                System.out.println("Dropped note " + title);
                isExist = true;
                break;
            }
        }
        // move elements to the previous positions; the last note will be null
        for (int i = index; i <= countOfNotes; i++) {
            if(index != -1 && i != countOfNotes){
                notebook[i] = notebook[i+1];
            }
            if(isExist && i == countOfNotes) {
                notebook[i] = null;
            }
        }
        //decrease array size if it necessary
        if (countOfNotes <= notebook.length / 2) {
            Note[] notebookNew = new Note[notebook.length / 2 + 1];
            System.arraycopy(notebook, 0, notebookNew, 0, countOfNotes);
            notebook = notebookNew;
        }
        if (!isExist){
            System.out.println("Error: This note doesn't exist.");
        }
    }

    public void deleteAll() {
        countOfNotes = 0;
        notebook = new Note[DEFAULT_INITIAL_SIZE];
        System.out.println("Deleted all notes");
    }

    public void editNote(String title, String newNote) {
        System.out.println("Editing note " + title);
        boolean isExist = false;
        for (int i = 0; i <= countOfNotes; i++) {
            if (notebook[i] != null && title.equals(notebook[i].getNoteTitle())) {
                editNoteNumber(i, title, newNote);
                System.out.println("Edited note " + title);
                isExist = true;
                break;
            }
        }
        // note doesn't exist - create it)
        if (!isExist) {
            System.out.println("Unable to find note.");
            addNote(title, newNote);
        }
    }

    public void showAll() {
        if (countOfNotes == 0){
            System.out.println("There are no notes");
        }
        else {
            String format = "%-15s%-20s%-35s%-50s%n";
            System.out.println("Count of notes = " + countOfNotes + ". Notebook array size = " + notebook.length +".");
            System.out.printf(format, "Note number", "title", "date and time of last change ", "note");
            System.out.println("--------------------------------------------------------------------------------");
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy  hh:mm:ss:SS");
            for (int i = 0; i < notebook.length; i++) {
                if (notebook[i] != null) {
                    System.out.printf(format, i, notebook[i].getNoteTitle(),
                            formatForDateNow.format(notebook[i].getNoteDate()), notebook[i].getNoteBody());
                }
            }
        }
    }

    //delete note by number in notebook
    private void deleteNoteNumber(int noteNumber) {
        // check of this note
        if (noteNumber > countOfNotes) {
            System.out.println("Error: Note index is out of range. Unable to delete.");
        }
        else if (noteNumber < 0) {
            System.out.println("Error: Note index is negative. Unable to delete.");
        }
        else {
            notebook[noteNumber] = null;
            countOfNotes--;
        }
    }

    //edit note by number in notebook
    private void editNoteNumber(int noteNumber, String title, String newNote) {
        // check of this note
        if (noteNumber > countOfNotes) {
            addNote(title, newNote);
            System.out.println("Unable to find note. Created new note.");
        }
        else if (noteNumber < 0) {
            System.out.println("Error: Note index is negative. Unable to edit.");
        }
        else {
            notebook[noteNumber].editNote(newNote);
        }
    }
}

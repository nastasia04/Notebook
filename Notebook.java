package notebook;
import java.text.*;

public class Notebook {
    private static Note[] notebook;
    private int initial_size = 5;
    private static int count_of_notes = 0;

    public Notebook() {
        System.out.println("Create notebook with default initial size");
        notebook = new Note[initial_size];
    }

    //create notebook with user initial_size
    public Notebook(int initial_size) {
        System.out.println("Create notebook with user initial size");
        this.initial_size = initial_size;
        notebook = new Note[initial_size];
    }

    public static void addNote(String title, String message) {
        // check the same titles
        boolean already_has_this_title;
        do {
            System.out.println("Adding note " + title);
            already_has_this_title = false;
            for (Note note : notebook) {
                if (note != null && title.equals(note.getNote_title())) {
                    already_has_this_title = true;
                    break;
                }
            }
            if (already_has_this_title) {
                title = "copy of " + title;
                System.out.println("Note with " + title + " already exists.");
            }
        } while (already_has_this_title);

        //increase array size if it necessary
        if (count_of_notes >= notebook.length) {
            Note[] notebook_new = new Note[(int) (notebook.length * 1.2)];
            System.arraycopy(notebook, 0, notebook_new, 0, count_of_notes);
            notebook = notebook_new;
        }

        notebook[count_of_notes] = new Note();
        //add text of note
        notebook[count_of_notes].setNote(title, message);
        System.out.println("Added note with title " + title);
        count_of_notes++;
    }

    //delete note by number in notebook
    private void deleteNote_number(int note_number) {
        // check of this note
        if (note_number > count_of_notes)
            System.out.println("Error: This note doesn't exist. Unable to delete.");
        else {
            notebook[note_number] = null;
            count_of_notes--;
        }
    }

    public void remove_Null_elements() {
        Note[] array_without_nulls_in_the_middle = new Note[count_of_notes + 1];
        int counter = 0;
        for (int i = 0; i < notebook.length; i++) {
            Note note = notebook[i];
            if (note != null) {
                array_without_nulls_in_the_middle[counter] = note;
                counter++;
            }
        }
        notebook = array_without_nulls_in_the_middle;
    }

    // delete note by title
    public void dropNote(String title) {
        boolean is_exist = false;
        System.out.println("Dropping note " + title);
        for (int i = 0; i <= count_of_notes; i++) {
            if (notebook[i] != null && title.equals(notebook[i].getNote_title())) {
                deleteNote_number(i);
                System.out.println("Dropped note " + title);
                is_exist = true;
                break;
            }
        }
        if (!is_exist)
            System.out.println("Error: This note doesn't exist.");
    }

    public void deleteAll() {
        count_of_notes = 0;
        notebook = new Note[initial_size];
        System.out.println("Deleted all notes");
    }

    //edit note by number in notebook
    private void editNote_number(int note_number, String title, String new_note) {
        // check of this note
        if (note_number > count_of_notes) {
            addNote(title, new_note);
            System.out.println("Unable to find note. Created new note.");
        } else {
            notebook[note_number].setNote(title, new_note);
        }
    }

    public void editNote(String title, String new_note) {
        System.out.println("Editing note " + title);
        boolean is_exist = false;
        for (int i = 0; i <= count_of_notes; i++) {
            if (notebook[i] != null && title.equals(notebook[i].getNote_title())) {
                editNote_number(i, title, new_note);
                System.out.println("Edited note " + title);
                is_exist = true;
                break;
            }
        }
        // note doesn't exist - create it)
        if (!is_exist) {
            System.out.println("Unable to find note.");
            addNote(title, new_note);
        }
    }

    public void showall() {
        if (count_of_notes == 0)
            System.out.println("There are no notes");
        else {
            String format = "%-15s%-20s%-35s%-50s%n";
            System.out.println("Count of notes = " + count_of_notes + ". Notebook array size = " + notebook.length +".");
            System.out.printf(format, "Note number", "title", "date and time of last change ", "note");
            System.out.println("--------------------------------------------------------------------------------");
            SimpleDateFormat formatForDateNow = new SimpleDateFormat("E dd.MM.yyyy  hh:mm:ss:SS");
            for (int i = 0; i < notebook.length; i++)
                if (notebook[i] != null)
                    System.out.printf(format, i, notebook[i].getNote_title(),
                            formatForDateNow.format(notebook[i].getNote_date()), notebook[i].getNote_body());
        }
    }
}

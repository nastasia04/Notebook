package notebook;

public class Main {

    public static void main(String[] args) {
        Notebook newNotebook = new Notebook();
        newNotebook.showall();

        System.out.println("Add 10 notes");
        for (int i = 0; i < 10; i++)
            newNotebook.addNote("title_" + i, "note_" + i);

        newNotebook.showall();

        String edit_title = "title_2";
        newNotebook.editNote(edit_title, "edited_note");
        newNotebook.showall();

        System.out.println("Delete 5 notes by title");
        for (int i = 0; i < 5; i++)
            newNotebook.dropNote("title_" + i);

        newNotebook.remove_Null_elements();
        newNotebook.showall();

        edit_title = "title98";
        newNotebook.dropNote(edit_title);
        newNotebook.editNote(edit_title, "something");
        newNotebook.addNote(edit_title, "something");
        newNotebook.showall();

        newNotebook.deleteAll();
        newNotebook.showall();
    }
}

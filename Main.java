package notebook;

public class Main {

    public static void main(String[] args) {
        Notebook newNotebook = new Notebook();
        newNotebook.showAll();

        System.out.println("Add 10 notes");
        for (int i = 0; i < 10; i++) {
            newNotebook.addNote("title_" + i, "note_" + i);
        }

        newNotebook.showAll();

        String editTitle = "title_2";
        newNotebook.editNote(editTitle, "edited_note");
        newNotebook.showAll();

        System.out.println("Delete 5 notes by title");
        for (int i = 0; i < 5; i++) {
            newNotebook.dropNote("title_" + i);
        }

        newNotebook.showAll();

        editTitle = "title98";
        newNotebook.dropNote(editTitle);
        newNotebook.editNote(editTitle, "something");
        newNotebook.addNote(editTitle, "something");
        newNotebook.showAll();

        newNotebook.deleteAll();
        newNotebook.showAll();
    }
}

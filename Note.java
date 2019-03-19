package notebook;
import java.util.Date;

public class Note {
    private String title;
    private String body;
    private Date date;

    public Note(String title, String body) {
        this.body = body;
        this.title = title;
        this.date = new Date();
    }

    public void editNote(String body) {
        this.body = body;
        this.date = new Date();
    }
    public String getNoteBody() {
        return this.body;
    }

    public String getNoteTitle() {
        return this.title;
    }

    public Date getNoteDate() {
        return this.date;
    }
}

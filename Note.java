package notebook;

import java.util.Date;

public class Note {

    private String title;
    private String body;
    private Date date;

    public void setNote(String title, String body) {
        this.body = body;
        this.title = title;
        this.date = new Date();
    }

    public String getNote_body() {
        return this.body;
    }

    public String getNote_title() {
        return this.title;
    }

    public Date getNote_date() {
        return this.date;
    }
}



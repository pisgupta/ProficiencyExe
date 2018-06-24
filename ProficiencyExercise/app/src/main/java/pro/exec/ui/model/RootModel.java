package pro.exec.ui.model;


import pro.exec.service.BaseResponse;

/**
 * Created by gupta on 6/16/2018.
 * This class is a root model which will contain the object of @{@link Rows}
 */

public class RootModel {
    private String title;
    private Rows[] rows;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Rows[] getRows() {
        return rows;
    }

    public void setRows(Rows[] rows) {
        this.rows = rows;
    }
}


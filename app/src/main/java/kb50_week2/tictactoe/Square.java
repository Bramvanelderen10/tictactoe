package kb50_week2.tictactoe;

/**
 * Created by Bram on 5/4/2015.
 */
public class Square {
    private String value = "";
    private boolean canEdit = true;
    private int id;

    public int getId() {
        return id;
    }

    public Square(int id) {
        this.id = id;
    }

    public boolean setValue(String value) {
        boolean result = false;
        if (this.canEdit) {
            this.value = value;
            this.canEdit = false;
            result = true;
        }

        return result;
    }

    public String getValue() {

        return value;
    }

    public boolean canEdit() {

        return canEdit;
    }

    public boolean reset() {
        this.canEdit = true;
        this.value = "";

        return true;
    }
}

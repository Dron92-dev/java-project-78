package hexlet.code.schemas;

import java.util.List;

public class StringSchema {
    private boolean required;
    Integer minLength;
    List<String> contains;

    public boolean isValid(Object value) {
        if (value == null && required) {
            return false;
        } else {
            return true;
        }
    }

    public boolean minLength(int minLength) {
        return true;
    }
}

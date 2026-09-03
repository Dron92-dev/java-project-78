package hexlet.code.schemas;

public class StringSchema {
    private boolean isRequired;
    private Integer minLength;
    private String substring;

    public boolean isValid(String value) {
        if (value == null || value.isEmpty()) {
            return !isRequired;
        } else if (minLength != null && value.length() < minLength) {
            return false;
        } else if (substring != null && !value.contains(substring)) {
            return false;
        } else  {
            return true;
        }
    }

    public StringSchema required() {
        isRequired = true;
        return this;
    }

    public StringSchema minLength(int minLength) {
        this.minLength = minLength;
        return this;
    }

    public StringSchema contains(String substring) {
        this.substring = substring;
        return this;
    }
}

package hexlet.code;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class StringSchemaTest {

    @Test
    void testRequired() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.required();

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid(" "));
        assertTrue(schema.isValid("Test"));
    }

    @Test
    void testMinLength() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.minLength(5);
        assertFalse(schema.isValid("Test"));
        assertTrue(schema.isValid("Hello"));
        assertTrue(schema.isValid("Hexlet"));

        schema.minLength(3);
        assertTrue(schema.isValid("Test"));
    }

    @Test
    void testContains() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));

        schema.contains("est");
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("Test"));

        schema.contains("let");
        assertTrue(schema.isValid("Hexlet"));
        assertFalse(schema.isValid("Test"));
    }

    @Test
    void testMultipleConstraints() {
        Validator v = new Validator();
        StringSchema schema = v.string();
        schema.required()
                .minLength(5)
                .contains("let");

        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertFalse(schema.isValid(" "));
        assertFalse(schema.isValid("xlet"));
        assertFalse(schema.isValid("Hello"));
        assertTrue(schema.isValid("Hexlet"));
    }
}

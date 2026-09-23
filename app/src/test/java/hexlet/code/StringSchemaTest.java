package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.schemas.StringSchema;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;


/**
 * Тесты схемы валидации строк.
 */
public class StringSchemaTest {

  @Test
  @DisplayName("Проверка обязательности строки")
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
  @DisplayName("Проверка минимальной длины строки")
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
  @DisplayName("Проверка наличия подстроки")
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
  @DisplayName("Проверка совместной работы ограничений")
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

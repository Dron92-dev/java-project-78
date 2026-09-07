package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.schemas.NumberSchema;
import org.junit.jupiter.api.Test;

/**
 * Тесты схемы валидации чисел.
 */
public class NumberSchemaTest {
  @Test
  void testRequired() {
    Validator v = new Validator();
    NumberSchema schema = v.number();
    assertTrue(schema.isValid(null));
    assertTrue(schema.isValid(5));
    schema.required();
    assertFalse(schema.isValid(null));
    assertTrue(schema.isValid(6));
  }

  @Test
  void testPositive() {
    Validator v = new Validator();
    NumberSchema schema = v.number();
    assertTrue(schema.isValid(-10));
    assertTrue(schema.isValid(0));
    schema.positive();
    assertTrue(schema.isValid(null));
    assertFalse(schema.isValid(-10));
    assertFalse(schema.isValid(0));
    assertTrue(schema.isValid(10));
  }

  @Test
  void testRange() {
    Validator v = new Validator();
    NumberSchema schema = v.number();
    schema.range(5, 10);
    assertTrue(schema.isValid(5));
    assertTrue(schema.isValid(7));
    assertTrue(schema.isValid(9));
    assertTrue(schema.isValid(10));
    assertTrue(schema.isValid(null));
    assertFalse(schema.isValid(4));
    assertFalse(schema.isValid(11));
    schema.range(6, 9);
    assertFalse(schema.isValid(5));
    assertTrue(schema.isValid(9));
    assertFalse(schema.isValid(10));
  }
}

package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.schemas.MapSchema;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

/**
 * Тесты схемы валидации объектов Map.
 */
public class MapSchemaTest {
  @Test
  void testRequired() {
    Validator v = new Validator();
    MapSchema schema = v.map();
    assertTrue(schema.isValid(null));
    assertTrue(schema.isValid(new HashMap<>()));
    schema.required();
    assertFalse(schema.isValid(null));
    assertTrue(schema.isValid(new HashMap<>()));
  }

  @Test
  void testSizeof() {
    Validator v = new Validator();
    MapSchema schema = v.map();
    Map<String, String> data = new HashMap<>();
    assertTrue(schema.isValid(data));
    data.put("a", "1");
    assertTrue(schema.isValid(data));
    schema.sizeof(2);
    assertTrue(schema.isValid(null));
    assertFalse(schema.isValid(data));
    data.put("b", "2");
    assertTrue(schema.isValid(data));
    schema.sizeof(1);
    assertFalse(schema.isValid(data));
  }
}

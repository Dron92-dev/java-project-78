package hexlet.code;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import hexlet.code.schemas.BaseSchema;
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

  @Test
  void testShape() {
    Validator v = new Validator();
    MapSchema schema = v.map();

    Map<String, BaseSchema<String>> schemas = new HashMap<>();
    schemas.put("firstName", v.string().required().contains("oh"));
    schemas.put("lastName", v.string().required().minLength(2));
    schema.shape(schemas);

    Map<String, String> human1 = new HashMap<>();
    human1.put("firstName", "John");
    human1.put("lastName", "Smith");
    assertTrue(schema.isValid(human1));

    Map<String, String> human2 = new HashMap<>();
    human2.put("firstName", "John");
    human2.put("lastName", null);
    assertFalse(schema.isValid(human2));

    Map<String, String> human3 = new HashMap<>();
    human3.put("firstName", "John");
    human3.put("lastName", "B");
    assertFalse(schema.isValid(human3));

    Map<String, String> human4 = new HashMap<>();
    human4.put("firstName", "John");
    assertFalse(schema.isValid(human4));

    Map<String, String> human5 = new HashMap<>();
    human5.put("firstName", "Anna");
    human5.put("lastName", "Smith");
    assertFalse(schema.isValid(human5));
  }
}

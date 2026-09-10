package hexlet.code.schemas;

import java.util.Map;

/**
 * Схема для валидации объектов Map.
 */
public class MapSchema extends BaseSchema<Map<?, ?>> {

  public MapSchema required() {
    addCheck("required", value -> value != null);
    return this;
  }

  public MapSchema sizeof(int size) {
    addCheck("sizeof", value -> value == null || value.size() == size);
    return this;
  }

  /**
   * Добавляет схему валидации для значений объекта Map.
   *
   * @param schemas набор схем для проверки значений по ключам
   * @return текущая схема
   */
  public MapSchema shape(Map<String, BaseSchema<String>> schemas) {
    addCheck("shape", value -> {
      if (value == null) {
        return true;
      }
      for (Map.Entry<String, BaseSchema<String>> entry : schemas.entrySet()) {
        String key = entry.getKey();
        BaseSchema<String> fieldSchema = entry.getValue();

        String fieldValue = (String) value.get(key);
        if (!fieldSchema.isValid(fieldValue)) {
          return false;
        }
      }
      return true;
    });
    return this;
  }
}

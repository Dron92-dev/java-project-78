package hexlet.code;

import hexlet.code.schemas.NumberSchema;
import hexlet.code.schemas.StringSchema;

/**
 * Создает схемы валидации для разных типов данных.
 */
public class Validator {

  public StringSchema string() {
    return new StringSchema();
  }

  public NumberSchema number() {
    return new NumberSchema();
  }
}
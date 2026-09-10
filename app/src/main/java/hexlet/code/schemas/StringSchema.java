package hexlet.code.schemas;

/**
 * Схема для валидации строк.
 */
public class StringSchema extends BaseSchema<String> {

  /**
   * Делает значение обязательным.
   *
   * @return текущая схема
   */
  public StringSchema required() {
    addCheck("required", value -> value != null && !value.isEmpty());
    return this;
  }

  /**
   * Добавляет ограничение минимальной длины строки.
   *
   * @param minLength минимальная допустимая длина
   * @return текущая схема
   */
  public StringSchema minLength(int minLength) {
    addCheck("minLength",
        value -> (value == null) || (value.isEmpty()) || (value.length() >= minLength));
    return this;
  }

  /**
   * Добавляет ограничение на наличие подстроки.
   *
   * @param substring искомая подстрока
   * @return текущая схема
   */
  public StringSchema contains(String substring) {
    addCheck("contains",
        value -> (value == null) || (value.isEmpty()) || (value.contains(substring)));
    return this;
  }
}

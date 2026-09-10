package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

/**
 * Базовая схема для валидации данных.
 *
 * @param <T> тип валидируемого значения
 */
public class BaseSchema<T> {
  private final Map<String, Predicate<T>> rules = new HashMap<>();

  protected void addCheck(String nameRule, Predicate<T> rule) {
    rules.put(nameRule, rule);
  }

  /**
   * Проверяет значение на соответствие всем ограничениям схемы.
   *
   * @param value проверяемое значение
   * @return true, если значение соответствует всем ограничениям
   */
  public boolean isValid(T value) {
    for (Predicate<T> rule : rules.values()) {
      if (!rule.test(value)) {
        return false;
      }
    }
    return true;
  }
}

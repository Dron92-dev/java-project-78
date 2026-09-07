package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public class BaseSchema<T> {
  private final Map<String, Predicate<T>> rules = new HashMap<>();

  protected void addCheck(String nameRule, Predicate<T> rule) {
    rules.put(nameRule, rule);
  }

  public boolean isValid(T value) {
    for (Predicate<T> rule : rules.values()) {
      if (!rule.test(value)) {
        return false;
      }
    }
    return true;
  }
}

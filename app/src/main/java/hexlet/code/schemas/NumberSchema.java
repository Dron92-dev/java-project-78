package hexlet.code.schemas;

/**
 * Схема для валидации чисел.
 */
public class NumberSchema extends BaseSchema<Integer> {

  /**
   * Делает значение обязательным.
   *
   * @return текущая схема
   */
  public NumberSchema required() {
    addCheck("required", value -> value != null);
    return this;
  }

  /**
   * Добавляет ограничение на положительное значение числа.
   * @return текущая схема
   */
  public NumberSchema positive() {
    addCheck("positive", value -> value == null || value > 0);
    return this;
  }

  /**
   * Добавляет ограничение допустимого диапазона.
   *
   * @param minRange нижняя граница диапазона
   * @param maxRange верхняя граница диапазона
   * @return текущая схема
   */
  public NumberSchema range(int minRange, int maxRange) {
    addCheck("range", value -> value == null || (value >= minRange && value <= maxRange));
    return this;
  }

}

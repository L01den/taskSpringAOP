package com.example.demo;

/**
 * 2.* Создать аннотацию RecoverException, которую можно ставить только над методами.
 * У аннотации должен быть параметр noRecoverFor, в котором можно перечислить другие классы исключений.
 * Аннотация работает так: если во время исполнения метода был экспешн, то не прокидывать его выше и возвращать
 * из метода значение по умолчанию (null, 0, false, ...).
 * При этом, если тип исключения входит в список перечисленных в noRecoverFor исключений,
 * то исключение НЕ прерывается и прокидывается выше.
 * 3.*** Параметр noRecoverFor должен учитывать иерархию исключений.
 */

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@interface RecoverException {
    Class<? extends RuntimeException>[] noRecoverFor() default {};
}

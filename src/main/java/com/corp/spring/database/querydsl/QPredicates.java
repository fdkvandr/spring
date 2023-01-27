package com.corp.spring.database.querydsl;

import com.querydsl.core.types.ExpressionUtils;
import com.querydsl.core.types.Predicate;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE) // Паттерн билдер
public class QPredicates {

    private final List<Predicate> predicates = new ArrayList<>(); // Лист где будут храниться наши предикаты

    public static QPredicates builder() { // Паттерн билдер
        return new QPredicates();
    }

    public <T> QPredicates add(T object, Function<T, Predicate> function) { // Параметризованный тип, куда мы будем добавлять предикат
        if (object != null) {
            predicates.add(function.apply(object)); // Применяем нашу функцию, которую передали
        }
        return this; // Чтобы было invoked-chain pattern
    }

    public Predicate build() { // Собираем все предикаты в один через ADN
        return ExpressionUtils.allOf(predicates);
    }

    public Predicate buildOr() { // Собираем все предикаты в один через OR
        return ExpressionUtils.anyOf(predicates);
    }
}

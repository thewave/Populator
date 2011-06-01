package br.com.wave.populator.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.utils.ReflectionUtil;

public class Scanner {

    private PatternManager manager;

    public Scanner(PatternManager associator) {
        this.manager = associator;
    }

    public <T> List<Field> scan(T instance) throws PopulatorException {
        List<Field> fields = new ArrayList<Field>();

        for (Field field : Arrays.asList(instance.getClass().getDeclaredFields())) {
            boolean isNotStatic = !ReflectionUtil.isStatic(field);
            boolean isNotTransient = !ReflectionUtil.isTransient(field);
            boolean isNull = this.isNull(field, instance);

            if (isNotStatic && isNotTransient && isNull) {
                fields.add(field);
            }
        }

        return fields;
    }

    private <T> boolean isNull(Field field, T instance) throws PopulatorException {
        boolean isNull = false;
        boolean isNotFixedPattern = !this.manager.isFixedPattern(field.getType());
        boolean hasFields = field.getType().getDeclaredFields().length > 0;

        try {
            boolean accessible = field.isAccessible();

            field.setAccessible(true);
            Object value = field.get(instance);
            if (value == null) {
                isNull = true;
            } else {
                if (isNotFixedPattern && hasFields && !ReflectionUtil.contains(field, instance.getClass())) {
                    isNull = (this.scan(value).size() > 0);
                }
            }

            field.setAccessible(accessible);
        } catch (IllegalArgumentException e) {
            throw new PopulatorException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new PopulatorException(e.getMessage());
        }

        return isNull;
    }
}

package br.com.wave.populator.fillers;

import java.lang.reflect.Field;
import java.util.Collection;

import br.com.wave.populator.core.Populator;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.utils.ReflectionUtil;

public class OneToOneFiller extends Filler {

    public OneToOneFiller(Populator populator) {
        super(populator);
    }

    @Override
    public <T> void fill(Field field, T instance) throws PopulatorException {
        Class<?> klass = field.getType();

        if (!klass.equals(Collection.class) && !ReflectionUtil.contains(field, instance.getClass())) {
            try {
                Object value = field.get(instance);
                if (value == null) {
                    field.set(instance, this.getPopulator().populate(klass));
                } else {
                    this.getPopulator().populate(value);
                }
            } catch (IllegalArgumentException e) {
                throw new PopulatorException(e.getMessage());
            } catch (IllegalAccessException e) {
                throw new PopulatorException(e.getMessage());
            }
        } else {
            this.getSuccessor().fill(field, instance);
        }
    }
}

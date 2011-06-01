package br.com.wave.populator.core;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.List;

import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.fillers.OneToManyFiller;
import br.com.wave.populator.fillers.OneToOneBidirectionalFiller;
import br.com.wave.populator.fillers.OneToOneFiller;
import br.com.wave.populator.fillers.PatternFiller;
import br.com.wave.populator.utils.ReflectionUtil;

public class Populator {

    private PatternManager manager;
    private Scanner scanner;
    private Trail trail;
    private PatternFiller patternFiller;
    private OneToOneFiller oneToOneFiller;
    private OneToManyFiller oneToManyFiller;
    private OneToOneBidirectionalFiller oneToOneBidirectionalFiller;

    public Populator() {
        this.manager = new PatternManager();

        this.scanner = new Scanner(this.manager);

        this.trail = new Trail();

        this.patternFiller = new PatternFiller(this);
        this.oneToOneFiller = new OneToOneFiller(this);
        this.oneToManyFiller = new OneToManyFiller(this);
        this.oneToOneBidirectionalFiller = new OneToOneBidirectionalFiller(this);

        this.patternFiller.setSuccessor(this.oneToOneFiller);
        this.oneToOneFiller.setSuccessor(this.oneToManyFiller);
        this.oneToManyFiller.setSuccessor(this.oneToOneBidirectionalFiller);
    }

    public <T> T populate(Class<T> klass) throws PopulatorException {
        try {
            T instance = klass.newInstance();
            this.populate(instance);

            return instance;
        } catch (InstantiationException e) {
            throw new PopulatorException(e.getMessage());
        } catch (IllegalAccessException e) {
            throw new PopulatorException(e.getMessage());
        }
    }

    public <T> void populate(T instance) throws PopulatorException {
        if (!ReflectionUtil.isSerializable(instance.getClass())) {
            throw new PopulatorException(ErrorEnum.NOT_SERIALIZABLE.getMessage());
        }

        this.populateInstance(instance);
    }

    private <T> void populateInstance(T instance) throws PopulatorException {
        List<Field> fields = this.scanner.scan(instance);
        for (Field field : fields) {
            this.populateField(field, instance);
        }
        this.trail.add(instance);
    }

    private <T> void populateField(Field field, T instance) throws PopulatorException {
        try {
            boolean accessible = field.isAccessible();
            field.setAccessible(true);

            this.patternFiller.fill(field, instance);

            field.setAccessible(accessible);
        } catch (IllegalArgumentException e) {
            throw new PopulatorException(e.getMessage());
        }
    }

    public <T extends Serializable> void associate(Class<?> klass, T instance) throws PopulatorException {
        this.populate(instance);
        this.manager.addPattern(klass, instance);
    }

    public Trail getLog() {
        return trail;
    }

    public PatternManager getManager() {
        return manager;
    }

    public Scanner getScanner() {
        return scanner;
    }
}

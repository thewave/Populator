package br.com.wave.populator.core;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.exceptions.PopulatorException;
import br.com.wave.populator.scanners.Checker;
import br.com.wave.populator.scanners.CollectionChecker;
import br.com.wave.populator.scanners.FixedChecker;
import br.com.wave.populator.scanners.NullChecker;
import br.com.wave.populator.scanners.ObjectChecker;
import br.com.wave.populator.utils.ReflectionUtil;

public class Scanner {
	
	private Checker nullChecker;
	private Checker fixedChecker;
	private Checker objectChecker;
	private Checker collectionChecker;
	
	public Scanner() {
		this.nullChecker = new NullChecker(this);
		this.fixedChecker = new FixedChecker(this);
		this.objectChecker = new ObjectChecker(this);
		this.collectionChecker = new CollectionChecker(this);
		
		this.nullChecker.setSuccessor(this.fixedChecker);
		this.fixedChecker.setSuccessor(this.objectChecker);
		this.objectChecker.setSuccessor(this.collectionChecker);
	}

    public <T> List<Field> scan(T instance) throws PopulatorException {
    	if (instance == null) {
    		throw new PopulatorException(ErrorEnum.NULL.getMessage());
    	}
    	
        List<Field> fields = new ArrayList<Field>();

        for (Field field : Arrays.asList(instance.getClass().getDeclaredFields())) {
            boolean isNotStatic = !ReflectionUtil.isStatic(field);
            boolean isNotTransient = !ReflectionUtil.isTransient(field);

            if (isNotStatic && isNotTransient) {
            	boolean isNotFilled = !this.nullChecker.isFilled(field, instance);
            	if (isNotFilled) {
            		fields.add(field);
            	}
            }
        }

        return fields;
    }

}

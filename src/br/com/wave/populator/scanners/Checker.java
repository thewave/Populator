package br.com.wave.populator.scanners;

import java.lang.reflect.Field;

import br.com.wave.populator.core.Scanner;

public abstract class Checker {

	private Checker successor;
	
	private Scanner scanner;
	
	public Checker(Scanner scanner) {
		this.scanner = scanner;
	}

	public abstract <T> boolean isFilled(Field field, T instance);	
	
	public Checker getSuccessor() {
		return successor;
	}
	
	public void setSuccessor(Checker successor) {
		this.successor = successor;	
	}
	
	public Scanner getScanner() {
		return scanner;
	}
}

package br.com.wave.populator.examples;

import java.io.Serializable;

public class H implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private I i;

    public I getI() {
        return i;
    }

    public void setI(I i) {
        this.i = i;
    }
}

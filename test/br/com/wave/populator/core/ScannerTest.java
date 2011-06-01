package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.examples.A;
import br.com.wave.populator.examples.B;
import br.com.wave.populator.examples.C;
import br.com.wave.populator.examples.H;
import br.com.wave.populator.examples.I;
import br.com.wave.populator.exceptions.PopulatorException;

public class ScannerTest {

    private Scanner scanner;

    @Before
    public void setUp() {
        PatternManager associator = new PatternManager();
        this.scanner = new Scanner(associator);
    }

    @Test
    public void deveRetornarOsAtributosPovoaveisDeUmObjeto() throws PopulatorException {
        A instance = new A();
        List<Field> fields = this.scanner.scan(instance);

        assertEquals(fields.get(0).getName(), "stringField");
        assertEquals(fields.get(1).getName(), "integerField");
        assertEquals(fields.get(2).getName(), "longField");
        assertEquals(fields.get(3).getName(), "bigDecimalField");
        assertEquals(fields.get(4).getName(), "booleanField");
        assertEquals(fields.get(5).getName(), "calendarField");
        assertEquals(fields.get(6).getName(), "byteField");
    }

    @Test
    public void deveRetornarApenasOsAtributosNulosDeUmObjeto() throws PopulatorException {
        A instance = new A();
        instance.setStringField("Teste");
        List<Field> fields = this.scanner.scan(instance);

        assertEquals(fields.get(0).getName(), "integerField");
        assertEquals(fields.get(1).getName(), "longField");
        assertEquals(fields.get(2).getName(), "bigDecimalField");
        assertEquals(fields.get(3).getName(), "booleanField");
        assertEquals(fields.get(4).getName(), "calendarField");
        assertEquals(fields.get(5).getName(), "byteField");
    }

    @Test
    public void deveRetornarApenasOsAtributosNulosEQualquerAssociacaoDeUmObjeto() throws PopulatorException {
        B instance = new B();

        C c = new C();
        instance.setC(c);

        List<Field> fields = this.scanner.scan(instance);

        assertEquals(fields.get(0).getName(), "atributoNaoNulo");
        assertEquals(fields.get(1).getName(), "atributoNulo");
        assertEquals(fields.get(2).getName(), "c");
    }

    @Test
    public void deveRetornarApenasAtributoEAssociacoesNulasDeUmObjeto() throws PopulatorException {
        String valor = "teste";
        
        B instance = new B();

        C c = new C();
        c.setAtributoNaoNulo(valor);
        c.setAtributoNulo(valor);

        instance.setC(c);

        List<Field> fields = this.scanner.scan(instance);

        assertEquals(fields.size(), 2);
    }

    @Test
    public void devePreencherObjetosNulosComObjetosVazios() throws PopulatorException{
        B instance = new B();

        instance.setC(null);

        List<Field> fields = this.scanner.scan(instance);

        assertEquals(fields.get(0).getName(), "atributoNaoNulo");
        assertEquals(fields.get(1).getName(), "atributoNulo");
        assertEquals(fields.get(2).getName(), "c");
    }

    @Test
    public void deveRetornarApenasOsAtributosNulosEQueNaoSejamRecursivos() throws PopulatorException {
        H instance = new H();

        I i = new I();
        instance.setI(i);

        List<Field> fields = this.scanner.scan(instance);

        assertEquals(0, fields.size());
    }

    @After
    public void tearDown() {
        this.scanner = null;
    }
}

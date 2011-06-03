package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.Collection;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.enums.FixedPatternEnum;
import br.com.wave.populator.examples.AtributosFixos;
import br.com.wave.populator.examples.A1;
import br.com.wave.populator.examples.B;
import br.com.wave.populator.examples.B1;
import br.com.wave.populator.examples.B2;
import br.com.wave.populator.examples.C;
import br.com.wave.populator.examples.C1;
import br.com.wave.populator.examples.C2;
import br.com.wave.populator.examples.C3;
import br.com.wave.populator.examples.D;
import br.com.wave.populator.examples.E;
import br.com.wave.populator.examples.F;
import br.com.wave.populator.examples.G;
import br.com.wave.populator.examples.H;
import br.com.wave.populator.examples.J;
import br.com.wave.populator.examples.K;
import br.com.wave.populator.examples.NotSerializable;
import br.com.wave.populator.exceptions.PopulatorException;

public class PopulatorTest {

    private Populator populator;

    @Before
    public void setUp() {
        this.populator = new Populator();
    }

    @Test(expected = PopulatorException.class)
    public void deveSerSerializable() throws PopulatorException {
        this.populator.populate(NotSerializable.class);
    }

    @Test
    public void deveSerSerializableException() {
        try {
            this.populator.populate(NotSerializable.class);
        } catch (PopulatorException e) {
            assertEquals(ErrorEnum.NOT_SERIALIZABLE.getMessage(), e.getMessage());
        }
    }

    @Test
    public void deveTerOsValoresPadroesParaAClasseA() throws PopulatorException {
        AtributosFixos instance = this.populator.populate(AtributosFixos.class);

        assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());
        assertEquals(FixedPatternEnum.INTEGER.getValue(), instance.getIntegerField());
        assertEquals(FixedPatternEnum.LONG.getValue(), instance.getLongField());
        assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), instance.getBigDecimalField());
        assertEquals(FixedPatternEnum.BOOLEAN.getValue(), instance.getBooleanField());
        assertEquals(FixedPatternEnum.CALENDAR.getValue(), instance.getCalendarField());
        assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), instance.getByteField());
    }

    @Test
    public void deveTerOsValoresPadroesParaUmObjetoDaClasseA() throws PopulatorException {
        AtributosFixos instance = new AtributosFixos();
        this.populator.populate(instance);

        assertEquals(FixedPatternEnum.STRING.getValue(), instance.getStringField());
        assertEquals(FixedPatternEnum.INTEGER.getValue(), instance.getIntegerField());
        assertEquals(FixedPatternEnum.LONG.getValue(), instance.getLongField());
        assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), instance.getBigDecimalField());
        assertEquals(FixedPatternEnum.BOOLEAN.getValue(), instance.getBooleanField());
        assertEquals(FixedPatternEnum.CALENDAR.getValue(), instance.getCalendarField());
        assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), instance.getByteField());
    }

    @Test
    public void deveManterOsValoresParaUmObjetoDaClasseA() throws PopulatorException {
        String valor = "Teste";

        AtributosFixos instance = new AtributosFixos();
        instance.setStringField(valor);
        this.populator.populate(instance);

        assertEquals(valor, instance.getStringField());
        assertEquals(FixedPatternEnum.INTEGER.getValue(), instance.getIntegerField());
        assertEquals(FixedPatternEnum.LONG.getValue(), instance.getLongField());
        assertEquals(FixedPatternEnum.BIG_DECIMAL.getValue(), instance.getBigDecimalField());
        assertEquals(FixedPatternEnum.BOOLEAN.getValue(), instance.getBooleanField());
        assertEquals(FixedPatternEnum.CALENDAR.getValue(), instance.getCalendarField());
        assertEquals(FixedPatternEnum.BYTE_ARRAY.getValue(), instance.getByteField());
    }

    @Test
    public void deveTerUmObjetoDaClasseCNoObjetoDaClasseB() throws PopulatorException {
        B instance = new B();
        this.populator.populate(instance);

        assertNotNull(instance.getC());
    }

    @Test
    public void deveTerOsValoresPadroesParaUmObjetoDaClasseCNoObjetoDaClasseB() throws PopulatorException {
        B instance = new B();

        C c = new C();
        instance.setC(c);

        this.populator.populate(instance);

        assertEquals(FixedPatternEnum.STRING.getValue(), c.getAtributoNaoNulo());
    }

    @Test
    public void deveManterOsValoresParaUmObjetoDaClasseCNoObjetoDaClasseB() throws PopulatorException {
        String valor = "Teste";

        B instance = new B();

        C c = new C();
        c.setAtributoNaoNulo(valor);

        instance.setC(c);

        this.populator.populate(instance);

        assertEquals(valor, c.getAtributoNaoNulo());
        assertEquals(FixedPatternEnum.STRING.getValue(), c.getAtributoNulo());
    }

    @Test
    public void deveManterOsValoresParaUmObjetoDaClasseBQueTenhaUmObjetoDaClasseC() throws PopulatorException {
        String valor = "Teste";

        B instance = new B();
        instance.setAtributoNaoNulo(valor);

        C c = new C();
        instance.setC(c);

        this.populator.populate(instance);

        assertEquals(valor, instance.getAtributoNaoNulo());
        assertEquals(FixedPatternEnum.STRING.getValue(), instance.getAtributoNulo());
    }

    @Test
    public void deveManterOsValoresParaUmObjetoDaClasseBEOsValoresParaUmObjetoDaClasseC() throws PopulatorException {
        String valor = "Teste";

        B instance = new B();
        instance.setAtributoNaoNulo(valor);

        C c = new C();
        c.setAtributoNaoNulo(valor);

        instance.setC(c);

        this.populator.populate(instance);

        assertEquals(valor, instance.getAtributoNaoNulo());
        assertEquals(FixedPatternEnum.STRING.getValue(), instance.getAtributoNulo());
        assertEquals(valor, c.getAtributoNaoNulo());
        assertEquals(FixedPatternEnum.STRING.getValue(), c.getAtributoNulo());
    }

    @Test
    public void deveTerUmObjetoDaClasseFNoObjetoDaClasseEPertencenteAUmObjetoDaClasseD() throws PopulatorException {
        D instance = new D();
        this.populator.populate(instance);

        assertNotNull(instance.getE().getF());
    }

    @Test
    public void deveManterOsValoresDeUmObjetoDaClasseFNoObjetoDaClasseEPertencenteAUmObjetoDaClasseD() throws PopulatorException {
        String valor = "Teste";

        D instance = new D();

        E e = new E();
        instance.setE(e);

        F f = new F();
        f.setAtributoNaoNulo(valor);
        e.setF(f);

        this.populator.populate(instance);

        assertEquals(valor, f.getAtributoNaoNulo());
        assertEquals(FixedPatternEnum.STRING.getValue(), f.getAtributoNulo());
    }

    @Test
    public void devePovoarAClasseBComOObjetoDefinidoParaAClasseC() throws PopulatorException {
        C c = new C();

        this.populator.associate(C.class, c);
        B b = this.populator.populate(B.class);

        assertEquals(c, b.getC());
    }

    @Test
    public void devePovoarUmObjetoDaClasseBComOObjetoDefinidoParaAClasseC() throws PopulatorException {
        B b = new B();
        C c = new C();

        this.populator.associate(C.class, c);
        this.populator.populate(b);

        assertEquals(c, b.getC());
    }

    @Test
    public void deveManterOsValoresDoObjetoDefinidoParaAClasseCNaClasseB() throws PopulatorException {
        String valor = "Teste";

        C c = new C();
        c.setAtributoNaoNulo(valor);
        c.setAtributoNulo(valor);

        this.populator.associate(C.class, c);
        B b = this.populator.populate(B.class);

        assertEquals(valor, b.getC().getAtributoNaoNulo());
        assertEquals(valor, b.getC().getAtributoNulo());
    }

    @Test
    public void devePreencherOsValoresNaoDefinidosDoObjetoDefinidoParaAClasseCNaClasseB() throws PopulatorException {
        String valor = "Teste";

        C c = new C();
        c.setAtributoNaoNulo(valor);

        this.populator.associate(C.class, c);
        B b = this.populator.populate(B.class);

        assertEquals(valor, b.getC().getAtributoNaoNulo());
        assertEquals(FixedPatternEnum.STRING.getValue(), b.getC().getAtributoNulo());
    }

    @Test
    public void devePovoarUmObjetoDaClasseDComOObjetoDefinidoParaAClasseF() throws PopulatorException {
        F f = new F();

        this.populator.associate(F.class, f);
        D d = this.populator.populate(D.class);

        assertEquals(f, d.getE().getF());
    }

    @Test
    public void deveManterOsValoresDoObjetoDefinidoParaAClasseFNaClasseD() throws PopulatorException {
        String valor = "Teste";

        F f = new F();
        f.setAtributoNaoNulo(valor);

        this.populator.associate(F.class, f);
        D d = this.populator.populate(D.class);

        assertEquals(valor, d.getE().getF().getAtributoNaoNulo());
        assertEquals(FixedPatternEnum.STRING.getValue(), d.getE().getF().getAtributoNulo());
    }

    @Test
    public void deveTerUmObjetoNaColecao() throws PopulatorException {
        G g = this.populator.populate(G.class);

        Collection<F> fs = g.getFs();
        for (F f : fs) {
            System.out.println(f.getAtributoNaoNulo());
            System.out.println(f.getAtributoNulo());
        }
        assertEquals(1, g.getFs().size());
    }

    @Test
    public void deveGuardarAOrdemDePersistenciaDosObjetos() throws PopulatorException {
        this.populator.populate(A1.class);

        assertEquals(C1.class, this.populator.getLog().getInstances().get(0).getClass());
        assertEquals(B1.class, this.populator.getLog().getInstances().get(1).getClass());
        assertEquals(C2.class, this.populator.getLog().getInstances().get(2).getClass());
        assertEquals(C3.class, this.populator.getLog().getInstances().get(3).getClass());
        assertEquals(B2.class, this.populator.getLog().getInstances().get(4).getClass());
        assertEquals(A1.class, this.populator.getLog().getInstances().get(5).getClass());

        assertEquals(this.populator.getLog().getInstances().size(), 6);
    }

    @Test
    public void deveTerOMesmoObjetoDaClasseHDentroDaClasseI() throws PopulatorException {
        H h = this.populator.populate(H.class);

        assertEquals(h, h.getI().getH());
    }
    
    @Test
    public void devePreencherOsAtributosDaClasseI() throws PopulatorException {
    	H h = this.populator.populate(H.class);
    	
    	assertEquals(h, h.getI().getH());
    	assertNotNull(h.getI().getF());
    	assertNotNull(h.getI().getAtributo());
    }
    
    @Test
    public void devePreencherAColecaoDeObjetosDaClasseJ() throws PopulatorException {
		J j = this.populator.populate(J.class);
		
		Collection<K> ks = j.getKs();
		assertFalse(ks.isEmpty());
		
		for (K k : ks) {
			assertNotNull(k.getJ());
			assertEquals(j, k.getJ());
		}
	}

    @After
    public void tearDown() {
        this.populator = null;
    }
    
}

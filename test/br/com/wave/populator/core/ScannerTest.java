package br.com.wave.populator.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.lang.reflect.Field;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.com.wave.populator.enums.ErrorEnum;
import br.com.wave.populator.examples.AssociacaoSimples;
import br.com.wave.populator.examples.AssociacaoSimplesComAtributoEObjeto;
import br.com.wave.populator.examples.AssociacaoSimplesComObjetoComAtributos;
import br.com.wave.populator.examples.AssociacaoSimplesComObjetoComUmAtributo;
import br.com.wave.populator.examples.AssociacaoSimplesComObjetoPovoado;
import br.com.wave.populator.examples.AtributosFixos;
import br.com.wave.populator.examples.ObjetoSimples;
import br.com.wave.populator.examples.ObjetoSimplesComAtributos;
import br.com.wave.populator.examples.ObjetoSimplesComUmAtributo;
import br.com.wave.populator.examples.ObjetoSimplesPovoado;
import br.com.wave.populator.exceptions.PopulatorException;

public class ScannerTest {

    private Scanner scanner;
    
    private String valor;

    @Before
    public void setUp() {
        this.scanner = new Scanner();
        
        this.valor = "Teste";
    }
    
    @Test(expected = PopulatorException.class)
    public void naoDeveEscanearObjetosNulos() throws PopulatorException {
		this.scanner.scan(null);
	}
    
    @Test
    public void deveLancarExcecaoQuandoAInstanciaForNula() {
		try {
			this.scanner.scan(null);
		} catch (PopulatorException e) {
			assertEquals(ErrorEnum.NULL.getMessage(), e.getMessage());
		}
	}

    @Test
    public void deveRetornarOsAtributosPovoaveisDeUmObjeto() throws PopulatorException {
        AtributosFixos instance = new AtributosFixos();
        List<Field> fields = this.scanner.scan(instance);

        assertEquals("stringField", fields.get(0).getName());
        assertEquals("integerField", fields.get(1).getName());
        assertEquals("longField", fields.get(2).getName());
        assertEquals("bigDecimalField", fields.get(3).getName());
        assertEquals("booleanField", fields.get(4).getName());
        assertEquals("calendarField", fields.get(5).getName());
        assertEquals("byteField", fields.get(6).getName());
		assertEquals(7, fields.size());
    }

    @Test
    public void deveRetornarApenasOsAtributosPovoaveisDeUmObjeto() throws PopulatorException {
        AtributosFixos instance = new AtributosFixos();
        instance.setStringField(this.valor);
        List<Field> fields = this.scanner.scan(instance);

        assertEquals("integerField", fields.get(0).getName());
        assertEquals("longField", fields.get(1).getName());
        assertEquals("bigDecimalField", fields.get(2).getName());
        assertEquals("booleanField", fields.get(3).getName());
        assertEquals("calendarField", fields.get(4).getName());
        assertEquals("byteField", fields.get(5).getName());
		assertEquals(6, fields.size());

    }
    
    @Test
    public void deveRetornarOsAtributosPovoaveisDoTipoAssociacaoDeUmObjeto() throws PopulatorException {
		AssociacaoSimples instance = new AssociacaoSimples();
		List<Field> fields = this.scanner.scan(instance);
		
		assertEquals("objetoSimples", fields.get(0).getName());
		assertEquals(1, fields.size());
    }
    
    @Test
    public void naoDeveRetornarAsAssociacoesSemAtributosDeUmObjeto() throws PopulatorException {
    	AssociacaoSimples instance = new AssociacaoSimples();
    	
    	ObjetoSimples objetoSimples = new ObjetoSimples();
    	instance.setObjetoSimples(objetoSimples);
    	
    	List<Field> fields = this.scanner.scan(instance);
    	
		assertTrue(fields.isEmpty());
    }
    
    @Test
    public void deveRetornarAsAssociacoesQueTemPeloMenosUmAtributoPovoavelDeUmObjeto() throws PopulatorException {
    	AssociacaoSimplesComObjetoComUmAtributo instance = new AssociacaoSimplesComObjetoComUmAtributo();
    	
    	ObjetoSimplesComUmAtributo objetoSimplesComUmAtributo = new ObjetoSimplesComUmAtributo();
    	instance.setObjetoSimplesComUmAtributo(objetoSimplesComUmAtributo);
    	
    	List<Field> fields = this.scanner.scan(instance);
    	
    	Field field = fields.get(0);
		assertEquals("objetoSimplesComUmAtributo", field.getName());
    	assertEquals(1, fields.size());
    	
    	assertNull(objetoSimplesComUmAtributo.getNaoPreenchido());
    }
    
    @Test
    public void deveRetornarAsAssociacoesPovoaveisComAtributosPovoaveisDeUmObjeto() throws PopulatorException {
    	AssociacaoSimplesComObjetoComAtributos instance = new AssociacaoSimplesComObjetoComAtributos();
    	
    	ObjetoSimplesComAtributos objetoSimplesComAtributos = new ObjetoSimplesComAtributos();
    	objetoSimplesComAtributos.setPreenchido(this.valor);
    	instance.setObjetoSimplesComAtributos(objetoSimplesComAtributos);
    	
    	List<Field> fields = this.scanner.scan(instance);
    	
    	assertEquals("objetoSimplesComAtributos", fields.get(0).getName());
		assertEquals(1, fields.size());
    }
    
    @Test
    public void naoDeveRetornarAsAssociacoesJaPovoadasDeUmObjeto() throws PopulatorException {
    	AssociacaoSimplesComObjetoPovoado instance = new AssociacaoSimplesComObjetoPovoado();
    	
    	ObjetoSimplesPovoado objetoSimplesPovoado = new ObjetoSimplesPovoado();
    	objetoSimplesPovoado.setPreenchido(this.valor);
    	instance.setObjetoSimplesPovoado(objetoSimplesPovoado);
    	
    	List<Field> fields = this.scanner.scan(instance);
    	
    	assertTrue(fields.isEmpty());
    }
    
    @Test
    public void deveRetornarOsAtributosEAssociacoesPovoaveisDeUmObjeto() throws PopulatorException {
		AssociacaoSimplesComAtributoEObjeto instance = new AssociacaoSimplesComAtributoEObjeto();
		instance.setObjetoSimples(new ObjetoSimples());
		
		List<Field> fields = this.scanner.scan(instance);
		
		assertEquals("naoPreenchido", fields.get(0).getName());
		assertEquals(1, fields.size());
	}
    
//    @Test
//    public void deveRetornarAsAssociacoesBidirecionaisPovoaveisDeUmObjeto() throws PopulatorException, IllegalArgumentException, IllegalAccessException {
//		AssociacaoSimplesBidirecional instance = new AssociacaoSimplesBidirecional();
//		
//		List<Field> fields = this.scanner.scan(instance);
//		
//		Field field = fields.get(0);
//		assertEquals("objetoSimplesBidirecional", field.getName());
//		assertEquals(1, fields.size());
//		
//		ObjetoSimplesBidirecional objetoSimplesBidirecional = (ObjetoSimplesBidirecional) field.get(instance);
//		assertNull(objetoSimplesBidirecional.getAssociacaoSimplesBidirecional());
//	}
//    
//    @Test
//    public void deveRetornarAsAssociacoesBidirecionaisPovoaveisComAtributosPovoaveisDeUmObjeto() throws PopulatorException, IllegalArgumentException, IllegalAccessException {
//    	AssociacaoSimplesBidirecionalComObjetosComAtributos instance = new AssociacaoSimplesBidirecionalComObjetosComAtributos();
//    	
//    	ObjetoSimplesBidirecionalComAtributos objetoSimplesBidirecionalComAtributos = new ObjetoSimplesBidirecionalComAtributos();
//    	instance.setObjetoSimplesBidirecionalComAtributos(objetoSimplesBidirecionalComAtributos);
//    	
//    	objetoSimplesBidirecionalComAtributos.setAssociacaoSimplesBidirecionalComObjetosComAtributos(instance);
//    	
//    	List<Field> fields = this.scanner.scan(instance);
//    	
//    	assertEquals("objetoSimplesBidirecionalComAtributos", fields.get(0).getName());
//    	assertEquals(1, fields.size());
//    	
//    	assertNull(objetoSimplesBidirecionalComAtributos.getNaoPreenchido());
//    }
//    
//    @Test
//    public void naoDeveRetornarAsAssociacoesBidirecionaisJaPovoadasDeUmObjeto() throws PopulatorException, IllegalArgumentException, IllegalAccessException {
//    	AssociacaoSimplesBidirecional instance = new AssociacaoSimplesBidirecional();
//    	
//    	ObjetoSimplesBidirecional objetoSimplesBidirecional = new ObjetoSimplesBidirecional();
//    	instance.setObjetoSimplesBidirecional(objetoSimplesBidirecional);
//    	
//    	objetoSimplesBidirecional.setAssociacaoSimplesBidirecional(instance);
//    	
//    	List<Field> fields = this.scanner.scan(instance);
//    	
//    	assertTrue(fields.isEmpty());
//    }
//    
//    @Test
//    public void deveRetornarOsAtributosPovoaveisDeUmObjetoComposto() throws PopulatorException {
//		AssociacaoComposta instance = new AssociacaoComposta();
//		
//		List<Field> fields = this.scanner.scan(instance);
//		
//		assertEquals("objetosSimples", fields.get(0).getName());
//		assertEquals(1, fields.size());
//	}
//    
//    @Test
//    public void deveRetornarAsColecoesPovoaveisDeUmObjetoComposto() throws PopulatorException {
//    	AssociacaoComposta instance = new AssociacaoComposta();
//    	instance.setObjetosSimples(new ArrayList<ObjetoSimples>());
//    	
//    	List<Field> fields = this.scanner.scan(instance);
//    	
//    	assertEquals("objetosSimples", fields.get(0).getName());
//    	assertEquals(1, fields.size());
//    }
//    
//    @Test
//    public void test() throws PopulatorException, IllegalArgumentException, IllegalAccessException {
//		AssociacaoCompostaComObjetoComUmAtributo instance = new AssociacaoCompostaComObjetoComUmAtributo();
//		
//		ObjetoSimplesComUmAtributo objetoSimplesComUmAtributo = new ObjetoSimplesComUmAtributo();
//		instance.setObjetosSimplesComUmAtributo(Arrays.asList(objetoSimplesComUmAtributo));
//		
//		List<Field> fields = this.scanner.scan(instance);
//		
//		Field field = fields.get(0);
//		assertEquals("objetosSimplesComUmAtributo", field.getName());
//		assertEquals(1, fields.size());
//		
//		assertEquals(objetoSimplesComUmAtributo, field.get(instance));
//	}
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//    
//
//    @Test
//    public void devePreencherObjetosNulosComObjetosVazios() throws PopulatorException{
//        B instance = new B();
//
//        instance.setC(null);
//
//        List<Field> fields = this.scanner.scan(instance);
//
//        assertEquals(fields.get(0).getName(), "atributoNaoNulo");
//        assertEquals(fields.get(1).getName(), "atributoNulo");
//        assertEquals(fields.get(2).getName(), "c");
//    }
//
//    @Test
//    public void deveRetornarApenasOsAtributosNulosEQueNaoSejamRecursivos() throws PopulatorException {
//        H instance = new H();
//
//        I i = new I();
//        instance.setI(i);
//
//        List<Field> fields = this.scanner.scan(instance);
//
//        assertEquals(0, fields.size());
//    }

    @After
    public void tearDown() {
        this.scanner = null;
    }
	
}

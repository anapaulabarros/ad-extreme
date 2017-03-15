package br.edu.ufcg.computacao.si1.service;

import br.edu.ufcg.computacao.si1.model.Anuncio;
import br.edu.ufcg.computacao.si1.model.Notas;
import br.edu.ufcg.computacao.si1.repository.AnuncioRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

import static junit.framework.TestCase.*;

/**
 * Created by Marcus Oliveira on 28/12/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AnuncioServiceTest {

    @Autowired
    private AnuncioService anuncioService;

    @Autowired
    private AnuncioRepository anuncioRepository;

    private Anuncio anuncio1, anuncio2, anuncio3;


    @Before
    public void setUp() {
        anuncio1 = new Anuncio("Anuncio de Movel", new Date(), 100, Notas.notas[2], "movel");
        anuncio2 = new Anuncio("Anuncio de Imovel", new Date(), 100000, Notas.notas[3], "imovel");
        anuncio3 = new Anuncio("Anuncio de Emprego", new Date(), 0, Notas.notas[1], "emprego");
    }

    @After
    public void tearDown() {
        anuncioRepository.deleteAll();
    }

    @Test
    public void testInicializacao() {
        assertNotNull("AnuncioService não foi instânciado corretamente", anuncioService);
        assertTrue("AnuncioService não deveria conter nenhum item", anuncioService.getAllAnuncios().isEmpty());
    }


    @Test
    public void testCreateAd() {
        Anuncio anuncio1FromDB = anuncioService.create(anuncio1);
        Anuncio anuncio2FromDB = anuncioService.create(anuncio2);
        Anuncio anuncio3FromDB = anuncioService.create(anuncio3);

        assertNotNull(anuncio1FromDB);
        assertNotNull(anuncio2FromDB);
        assertNotNull(anuncio3FromDB);

        assertTrue(anuncioRepository.exists(anuncio1FromDB.get_id()));
        assertTrue(anuncioRepository.exists(anuncio2FromDB.get_id()));
        assertTrue(anuncioRepository.exists(anuncio3FromDB.get_id()));

        assertEquals(anuncio1FromDB, anuncio1);
        assertEquals(anuncio2FromDB, anuncio2);
        assertEquals(anuncio3FromDB, anuncio3);

        assertEquals(anuncio1FromDB, anuncioService.getAnuncioById(anuncio1FromDB.get_id()).get());
        assertEquals(anuncio2FromDB, anuncioService.getAnuncioById(anuncio2FromDB.get_id()).get());
        assertEquals(anuncio3FromDB, anuncioService.getAnuncioById(anuncio3FromDB.get_id()).get());
    }

    @Test
    public void testGetPorTipo() {

        int QTDE_ANUNCIOS_ESPERADA = 1;

        Anuncio anuncioMovel = anuncioService.create(anuncio1);
        Anuncio anuncioImovel = anuncioService.create(anuncio2);
        Anuncio anuncioEmprego = anuncioService.create(anuncio3);

        assertNotNull(anuncioMovel);
        assertNotNull(anuncioImovel);
        assertNotNull(anuncioEmprego);

        assertEquals(anuncioMovel.getTipo(), "movel");
        assertEquals(anuncioImovel.getTipo(), "imovel");
        assertEquals(anuncioEmprego.getTipo(), "emprego");

        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.get("movel").size());
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.get("imovel").size());
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.get("emprego").size());

        assertTrue(anuncioService.get("movel").contains(anuncioMovel));
        assertTrue(anuncioService.get("imovel").contains(anuncioImovel));
        assertTrue(anuncioService.get("emprego").contains(anuncioEmprego));
    }

    @Test
    public void testGetAll() {
        int QTDE_ANUNCIOS_ESPERADA = 3;

        Anuncio anuncioMovel = anuncioService.create(anuncio1);
        Anuncio anuncioImovel = anuncioService.create(anuncio2);
        Anuncio anuncioEmprego = anuncioService.create(anuncio3);

        assertNotNull(anuncioMovel);
        assertNotNull(anuncioImovel);
        assertNotNull(anuncioEmprego);

        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAllAnuncios().size());

        assertTrue(anuncioService.getAllAnuncios().contains(anuncioMovel));
        assertTrue(anuncioService.getAllAnuncios().contains(anuncioImovel));
        assertTrue(anuncioService.getAllAnuncios().contains(anuncioEmprego));

    }

    @Test
    public void testDelete() {
        int QTDE_ANUNCIOS_ESPERADA = 3;

        Anuncio anuncioMovel = anuncioService.create(anuncio1);
        Anuncio anuncioImovel = anuncioService.create(anuncio2);
        Anuncio anuncioEmprego = anuncioService.create(anuncio3);

        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAllAnuncios().size());
        assertTrue(anuncioService.getAllAnuncios().contains(anuncioMovel));
        assertTrue(anuncioService.getAllAnuncios().contains(anuncioImovel));
        assertTrue(anuncioService.getAllAnuncios().contains(anuncioEmprego));

        assertTrue(anuncioService.deleteAnuncio(anuncioMovel.get_id()));
        QTDE_ANUNCIOS_ESPERADA-=1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAllAnuncios().size());
        assertFalse(anuncioService.getAllAnuncios().contains(anuncioMovel));
        assertTrue(anuncioService.getAllAnuncios().contains(anuncioImovel));
        assertTrue(anuncioService.getAllAnuncios().contains(anuncioEmprego));

        assertTrue(anuncioService.deleteAnuncio(anuncioImovel.get_id()));
        QTDE_ANUNCIOS_ESPERADA-=1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAllAnuncios().size());
        assertFalse(anuncioService.getAllAnuncios().contains(anuncioMovel));
        assertFalse(anuncioService.getAllAnuncios().contains(anuncioImovel));
        assertTrue(anuncioService.getAllAnuncios().contains(anuncioEmprego));

        assertTrue(anuncioService.deleteAnuncio(anuncioEmprego.get_id()));
        QTDE_ANUNCIOS_ESPERADA-=1;
        assertEquals(QTDE_ANUNCIOS_ESPERADA, anuncioService.getAllAnuncios().size());
        assertFalse(anuncioService.getAllAnuncios().contains(anuncioMovel));
        assertFalse(anuncioService.getAllAnuncios().contains(anuncioImovel));
        assertFalse(anuncioService.getAllAnuncios().contains(anuncioEmprego));

        assertEquals(0, QTDE_ANUNCIOS_ESPERADA);

        assertFalse(anuncioService.deleteAnuncio(anuncioMovel.get_id()));
        assertFalse(anuncioService.deleteAnuncio(anuncioImovel.get_id()));
        assertFalse(anuncioService.deleteAnuncio(anuncioEmprego.get_id()));
    }

    @Test
    public void testUpdate() {

        String SUFIXO = " editado";

        Anuncio anuncioMovel = anuncioService.create(anuncio1);
        Anuncio anuncioImovel = anuncioService.create(anuncio2);
        Anuncio anuncioEmprego = anuncioService.create(anuncio3);

        assertEquals(anuncioMovel, anuncio1);
        assertEquals(anuncioImovel, anuncio2);
        assertEquals(anuncioEmprego, anuncio3);

        //Update Titulo
        anuncioMovel.setTitulo(anuncioMovel.getTitulo()+SUFIXO);
        anuncioImovel.setTitulo(anuncioImovel.getTitulo()+SUFIXO);
        anuncioEmprego.setTitulo(anuncioEmprego.getTitulo()+SUFIXO);

        assertTrue(anuncioService.updateAnuncio(anuncioMovel));
        assertTrue(anuncioService.updateAnuncio(anuncioImovel));
        assertTrue(anuncioService.updateAnuncio(anuncioEmprego));

        assertEquals(anuncioMovel.getTitulo(), anuncioService.getAnuncioById(anuncioMovel.get_id()).get().getTitulo());
        assertEquals(anuncioImovel.getTitulo(), anuncioService.getAnuncioById(anuncioImovel.get_id()).get().getTitulo());
        assertEquals(anuncioEmprego.getTitulo(), anuncioService.getAnuncioById(anuncioEmprego.get_id()).get().getTitulo());

        //Update preço
        anuncioMovel.setPreco(anuncioMovel.getPreco()*2);
        anuncioImovel.setPreco(anuncioImovel.getPreco()*2);
        anuncioEmprego.setPreco(anuncioEmprego.getPreco()*2);

        assertTrue(anuncioService.updateAnuncio(anuncioMovel));
        assertTrue(anuncioService.updateAnuncio(anuncioImovel));
        assertTrue(anuncioService.updateAnuncio(anuncioEmprego));

        assertEquals(anuncioMovel.getPreco(), anuncioService.getAnuncioById(anuncioMovel.get_id()).get().getPreco());
        assertEquals(anuncioImovel.getPreco(), anuncioService.getAnuncioById(anuncioImovel.get_id()).get().getPreco());
        assertEquals(anuncioEmprego.getPreco(), anuncioService.getAnuncioById(anuncioEmprego.get_id()).get().getPreco());

        //Update nota
        anuncioMovel.setNota(Notas.notas[4]);
        anuncioImovel.setNota(Notas.notas[4]);
        anuncioEmprego.setNota(Notas.notas[4]);

        assertTrue(anuncioService.updateAnuncio(anuncioMovel));
        assertTrue(anuncioService.updateAnuncio(anuncioImovel));
        assertTrue(anuncioService.updateAnuncio(anuncioEmprego));

        assertEquals(Notas.notas[4], anuncioService.getAnuncioById(anuncioMovel.get_id()).get().getNota());
        assertEquals(Notas.notas[4], anuncioService.getAnuncioById(anuncioImovel.get_id()).get().getNota());
        assertEquals(Notas.notas[4], anuncioService.getAnuncioById(anuncioEmprego.get_id()).get().getNota());
    }

}

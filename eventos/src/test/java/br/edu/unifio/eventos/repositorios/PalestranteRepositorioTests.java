package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.eventos.entidades.Palestrante;

@SpringBootTest
public class PalestranteRepositorioTests {

    @Autowired
    private PalestranteRepositorio palestranteRepositorio;

    @Test
    public void deveInserirPalestrante() {
        var palestrante = new Palestrante();

        palestrante.setNome("Carlos Silva");
        palestrante.setMiniBio("Especialista em desenvolvimento de software");
        palestrante.setEmail("carlos@email.com");

        var palestranteSalvo = palestranteRepositorio.save(palestrante);

        assertNotNull(palestranteSalvo.getId());
        assertEquals("Carlos Silva", palestranteSalvo.getNome());
        assertEquals("carlos@email.com", palestranteSalvo.getEmail());
    }

    @Test
    public void deveBuscarPalestrantePorId() {
        var palestrante = new Palestrante();

        palestrante.setNome("Mariana Souza");
        palestrante.setMiniBio("Desenvolvedora Java");
        palestrante.setEmail("mariana@email.com");

        var palestranteSalvo = palestranteRepositorio.save(palestrante);

        var palestranteEncontrado =
                palestranteRepositorio.findById(palestranteSalvo.getId());

        assertTrue(palestranteEncontrado.isPresent());
        assertEquals("Mariana Souza", palestranteEncontrado.get().getNome());
        assertEquals("mariana@email.com", palestranteEncontrado.get().getEmail());
    }

    @Test
    public void deveListarPalestrantes() {
        var palestrante1 = new Palestrante();
        palestrante1.setNome("João Santos");
        palestrante1.setMiniBio("Professor de programação");
        palestrante1.setEmail("joao@email.com");

        var palestrante2 = new Palestrante();
        palestrante2.setNome("Fernanda Lima");
        palestrante2.setMiniBio("Especialista em tecnologia");
        palestrante2.setEmail("fernanda@email.com");

        palestranteRepositorio.save(palestrante1);
        palestranteRepositorio.save(palestrante2);

        var palestrantes = palestranteRepositorio.findAll();

        assertTrue(palestrantes.size() >= 2);
        assertTrue(palestrantes.stream()
                .anyMatch(p -> p.getNome().equals("João Santos")));
        assertTrue(palestrantes.stream()
                .anyMatch(p -> p.getNome().equals("Fernanda Lima")));
    }

    @Test
    public void deveAlterarPalestrante() {
        var palestrante = new Palestrante();

        palestrante.setNome("Nome Antigo");
        palestrante.setMiniBio("Mini bio antiga");
        palestrante.setEmail("antigo@email.com");

        var palestranteSalvo = palestranteRepositorio.save(palestrante);

        palestranteSalvo.setNome("Nome Atualizado");

        palestranteRepositorio.save(palestranteSalvo);

        var palestranteAlterado =
                palestranteRepositorio.findById(palestranteSalvo.getId())
                        .orElseThrow();

        assertEquals("Nome Atualizado", palestranteAlterado.getNome());
        assertEquals(palestranteSalvo.getId(), palestranteAlterado.getId());
    }

    @Test
    public void deveExcluirPalestrante() {
        var palestrante = new Palestrante();

        palestrante.setNome("Palestrante para excluir");
        palestrante.setMiniBio("Teste de exclusão");
        palestrante.setEmail("excluir@email.com");

        var palestranteSalvo = palestranteRepositorio.save(palestrante);

        assertTrue(palestranteRepositorio
                .findById(palestranteSalvo.getId())
                .isPresent());

        palestranteRepositorio.deleteById(palestranteSalvo.getId());

        assertFalse(palestranteRepositorio
                .findById(palestranteSalvo.getId())
                .isPresent());
    }
}
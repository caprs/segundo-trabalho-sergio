package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.eventos.entidades.Local;

@SpringBootTest
public class LocalRepositorioTests {

    @Autowired
    private LocalRepositorio localRepositorio;

    @Test
    public void deveInserirLocal() {
        var local = new Local();

        local.setNome("Auditório Principal");
        local.setEndereco("Rua Central, 100");
        local.setCapacidade(200);

        var localSalvo = localRepositorio.save(local);

        assertNotNull(localSalvo.getId());
        assertEquals("Auditório Principal", localSalvo.getNome());
        assertEquals(200, localSalvo.getCapacidade());
    }

    @Test
    public void deveBuscarLocalPorId() {
        var local = new Local();

        local.setNome("Sala de Tecnologia");
        local.setEndereco("Bloco A");
        local.setCapacidade(50);

        var localSalvo = localRepositorio.save(local);

        var localEncontrado = localRepositorio.findById(localSalvo.getId());

        assertTrue(localEncontrado.isPresent());
        assertEquals("Sala de Tecnologia", localEncontrado.get().getNome());
        assertEquals(50, localEncontrado.get().getCapacidade());
    }

    @Test
    public void deveListarLocais() {
        var local1 = new Local();
        local1.setNome("Auditório A");
        local1.setEndereco("Bloco A");
        local1.setCapacidade(100);

        var local2 = new Local();
        local2.setNome("Auditório B");
        local2.setEndereco("Bloco B");
        local2.setCapacidade(150);

        localRepositorio.save(local1);
        localRepositorio.save(local2);

        var locais = localRepositorio.findAll();

        assertTrue(locais.size() >= 2);
        assertTrue(locais.stream()
                .anyMatch(l -> l.getNome().equals("Auditório A")));
        assertTrue(locais.stream()
                .anyMatch(l -> l.getNome().equals("Auditório B")));
    }

    @Test
    public void deveAlterarLocal() {
        var local = new Local();

        local.setNome("Sala Antiga");
        local.setEndereco("Rua A");
        local.setCapacidade(30);

        var localSalvo = localRepositorio.save(local);

        localSalvo.setNome("Sala Atualizada");

        localRepositorio.save(localSalvo);

        var localAlterado = localRepositorio
                .findById(localSalvo.getId())
                .orElseThrow();

        assertEquals("Sala Atualizada", localAlterado.getNome());
        assertEquals(localSalvo.getId(), localAlterado.getId());
    }

    @Test
    public void deveExcluirLocal() {
        var local = new Local();

        local.setNome("Local para excluir");
        local.setEndereco("Rua para excluir");
        local.setCapacidade(20);

        var localSalvo = localRepositorio.save(local);

        assertTrue(localRepositorio
                .findById(localSalvo.getId())
                .isPresent());

        localRepositorio.deleteById(localSalvo.getId());

        assertFalse(localRepositorio
                .findById(localSalvo.getId())
                .isPresent());
    }
}
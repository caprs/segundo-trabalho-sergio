package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.eventos.entidades.Categoria;
import br.edu.unifio.eventos.entidades.Evento;
import br.edu.unifio.eventos.entidades.Local;
import br.edu.unifio.eventos.entidades.Palestrante;

@SpringBootTest
public class EventoRepositorioTests {

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Autowired
    private LocalRepositorio localRepositorio;

    @Autowired
    private PalestranteRepositorio palestranteRepositorio;

    @Test
    public void deveInserirEvento() {
        var categoria = new Categoria();
        categoria.setNome("Tecnologia");
        categoria.setDescricao("Eventos de tecnologia");
        categoria = categoriaRepositorio.save(categoria);

        var local = new Local();
        local.setNome("Auditório Principal");
        local.setEndereco("Rua Central, 100");
        local.setCapacidade(200);
        local = localRepositorio.save(local);

        var palestrante = new Palestrante();
        palestrante.setNome("João da Silva");
        palestrante.setMiniBio("Especialista em tecnologia");
        palestrante.setEmail("joao@email.com");
        palestrante = palestranteRepositorio.save(palestrante);

        var evento = new Evento();

        evento.setNome("Workshop de Java");
        evento.setDescricao("Workshop sobre desenvolvimento Java");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now().plusHours(2));
        evento.setCapacidade(100);
        evento.setStatus("ATIVO");
        evento.setCategoria(categoria);
        evento.setLocal(local);
        evento.setPalestrante(palestrante);

        var eventoSalvo = eventoRepositorio.save(evento);

        assertNotNull(eventoSalvo.getId());
        assertEquals("Workshop de Java", eventoSalvo.getNome());
        assertEquals("ATIVO", eventoSalvo.getStatus());
        assertEquals(categoria.getId(), eventoSalvo.getCategoria().getId());
        assertEquals(local.getId(), eventoSalvo.getLocal().getId());
        assertEquals(palestrante.getId(), eventoSalvo.getPalestrante().getId());
    }

    @Test
    public void deveBuscarEventoPorId() {
        var categoria = new Categoria();
        categoria.setNome("Programação");
        categoria.setDescricao("Eventos de programação");
        categoria = categoriaRepositorio.save(categoria);

        var local = new Local();
        local.setNome("Sala A");
        local.setEndereco("Bloco A");
        local.setCapacidade(50);
        local = localRepositorio.save(local);

        var palestrante = new Palestrante();
        palestrante.setNome("Maria Souza");
        palestrante.setMiniBio("Desenvolvedora");
        palestrante.setEmail("maria@email.com");
        palestrante = palestranteRepositorio.save(palestrante);

        var evento = new Evento();
        evento.setNome("Curso de Spring");
        evento.setDescricao("Curso de Spring Boot");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now().plusHours(3));
        evento.setCapacidade(40);
        evento.setStatus("ATIVO");
        evento.setCategoria(categoria);
        evento.setLocal(local);
        evento.setPalestrante(palestrante);

        var eventoSalvo = eventoRepositorio.save(evento);

        var eventoEncontrado =
                eventoRepositorio.findById(eventoSalvo.getId());

        assertTrue(eventoEncontrado.isPresent());
        assertEquals("Curso de Spring", eventoEncontrado.get().getNome());
        assertEquals("ATIVO", eventoEncontrado.get().getStatus());
        assertEquals(categoria.getId(),
                eventoEncontrado.get().getCategoria().getId());
    }

    @Test
    public void deveListarEventos() {
        var categoria = new Categoria();
        categoria.setNome("Eventos");
        categoria.setDescricao("Categoria de eventos");
        categoria = categoriaRepositorio.save(categoria);

        var local = new Local();
        local.setNome("Auditório B");
        local.setEndereco("Bloco B");
        local.setCapacidade(150);
        local = localRepositorio.save(local);

        var palestrante = new Palestrante();
        palestrante.setNome("Pedro Lima");
        palestrante.setMiniBio("Professor");
        palestrante.setEmail("pedro@email.com");
        palestrante = palestranteRepositorio.save(palestrante);

        var evento1 = new Evento();
        evento1.setNome("Evento Java");
        evento1.setDescricao("Evento sobre Java");
        evento1.setDataInicio(LocalDateTime.now());
        evento1.setDataFim(LocalDateTime.now().plusHours(2));
        evento1.setCapacidade(100);
        evento1.setStatus("ATIVO");
        evento1.setCategoria(categoria);
        evento1.setLocal(local);
        evento1.setPalestrante(palestrante);

        var evento2 = new Evento();
        evento2.setNome("Evento Spring");
        evento2.setDescricao("Evento sobre Spring");
        evento2.setDataInicio(LocalDateTime.now());
        evento2.setDataFim(LocalDateTime.now().plusHours(2));
        evento2.setCapacidade(80);
        evento2.setStatus("ATIVO");
        evento2.setCategoria(categoria);
        evento2.setLocal(local);
        evento2.setPalestrante(palestrante);

        eventoRepositorio.save(evento1);
        eventoRepositorio.save(evento2);

        var eventos = eventoRepositorio.findAll();

        assertTrue(eventos.size() >= 2);
        assertTrue(eventos.stream()
                .anyMatch(e -> e.getNome().equals("Evento Java")));
        assertTrue(eventos.stream()
                .anyMatch(e -> e.getNome().equals("Evento Spring")));
    }

    @Test
    public void deveAlterarEvento() {
        var evento = new Evento();

        evento.setNome("Evento Antigo");
        evento.setDescricao("Descrição antiga");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now().plusHours(2));
        evento.setCapacidade(50);
        evento.setStatus("ATIVO");

        var eventoSalvo = eventoRepositorio.save(evento);

        eventoSalvo.setNome("Evento Atualizado");
        eventoSalvo.setStatus("ENCERRADO");

        eventoRepositorio.save(eventoSalvo);

        var eventoAlterado =
                eventoRepositorio.findById(eventoSalvo.getId())
                        .orElseThrow();

        assertEquals("Evento Atualizado", eventoAlterado.getNome());
        assertEquals("ENCERRADO", eventoAlterado.getStatus());
        assertEquals(eventoSalvo.getId(), eventoAlterado.getId());
    }

    @Test
    public void deveExcluirEvento() {
        var evento = new Evento();

        evento.setNome("Evento para excluir");
        evento.setDescricao("Teste de exclusão");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now().plusHours(1));
        evento.setCapacidade(20);
        evento.setStatus("ATIVO");

        var eventoSalvo = eventoRepositorio.save(evento);

        assertTrue(eventoRepositorio
                .findById(eventoSalvo.getId())
                .isPresent());

        eventoRepositorio.deleteById(eventoSalvo.getId());

        assertFalse(eventoRepositorio
                .findById(eventoSalvo.getId())
                .isPresent());
    }
}
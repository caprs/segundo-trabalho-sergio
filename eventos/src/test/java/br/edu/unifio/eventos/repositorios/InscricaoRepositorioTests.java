package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.eventos.entidades.Evento;
import br.edu.unifio.eventos.entidades.Inscricao;
import br.edu.unifio.eventos.entidades.Participante;

@SpringBootTest
public class InscricaoRepositorioTests {

    @Autowired
    private InscricaoRepositorio inscricaoRepositorio;

    @Autowired
    private EventoRepositorio eventoRepositorio;

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @Test
    public void deveInserirInscricao() {
        var evento = new Evento();

        evento.setNome("Congresso de Tecnologia");
        evento.setDescricao("Congresso sobre tecnologia");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now().plusHours(4));
        evento.setCapacidade(100);
        evento.setStatus("ATIVO");

        evento = eventoRepositorio.save(evento);

        var participante = new Participante();

        participante.setNome("Ana Oliveira");
        participante.setEmail("ana.oliveira@email.com");
        participante.setTelefone("14999999999");

        participante = participanteRepositorio.save(participante);

        var inscricao = new Inscricao();

        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("CONFIRMADA");
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);

        var inscricaoSalva = inscricaoRepositorio.save(inscricao);

        assertNotNull(inscricaoSalva.getId());
        assertEquals("CONFIRMADA", inscricaoSalva.getStatus());
        assertEquals(evento.getId(), inscricaoSalva.getEvento().getId());
        assertEquals(participante.getId(), inscricaoSalva.getParticipante().getId());
    }

    @Test
    public void deveBuscarInscricaoPorId() {
        var evento = new Evento();

        evento.setNome("Workshop de Java");
        evento.setDescricao("Workshop de programação Java");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now().plusHours(2));
        evento.setCapacidade(50);
        evento.setStatus("ATIVO");

        evento = eventoRepositorio.save(evento);

        var participante = new Participante();

        participante.setNome("Bruno Santos");
        participante.setEmail("bruno.santos@email.com");
        participante.setTelefone("14988888888");

        participante = participanteRepositorio.save(participante);

        var inscricao = new Inscricao();

        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("PENDENTE");
        inscricao.setEvento(evento);
        inscricao.setParticipante(participante);

        var inscricaoSalva = inscricaoRepositorio.save(inscricao);

        var inscricaoEncontrada =
                inscricaoRepositorio.findById(inscricaoSalva.getId());

        assertTrue(inscricaoEncontrada.isPresent());
        assertEquals("PENDENTE", inscricaoEncontrada.get().getStatus());
        assertEquals(evento.getId(),
                inscricaoEncontrada.get().getEvento().getId());
        assertEquals(participante.getId(),
                inscricaoEncontrada.get().getParticipante().getId());
    }

    @Test
    public void deveListarInscricoes() {
        var evento = new Evento();

        evento.setNome("Evento de Tecnologia");
        evento.setDescricao("Evento de tecnologia");
        evento.setDataInicio(LocalDateTime.now());
        evento.setDataFim(LocalDateTime.now().plusHours(2));
        evento.setCapacidade(100);
        evento.setStatus("ATIVO");

        evento = eventoRepositorio.save(evento);

        var participante1 = new Participante();
        participante1.setNome("Carlos Silva");
        participante1.setEmail("carlos@email.com");
        participante1.setTelefone("14977777777");

        var participante2 = new Participante();
        participante2.setNome("Fernanda Costa");
        participante2.setEmail("fernanda@email.com");
        participante2.setTelefone("14966666666");

        participante1 = participanteRepositorio.save(participante1);
        participante2 = participanteRepositorio.save(participante2);

        var inscricao1 = new Inscricao();
        inscricao1.setDataInscricao(LocalDateTime.now());
        inscricao1.setStatus("CONFIRMADA");
        inscricao1.setEvento(evento);
        inscricao1.setParticipante(participante1);

        var inscricao2 = new Inscricao();
        inscricao2.setDataInscricao(LocalDateTime.now());
        inscricao2.setStatus("PENDENTE");
        inscricao2.setEvento(evento);
        inscricao2.setParticipante(participante2);

        inscricaoRepositorio.save(inscricao1);
        inscricaoRepositorio.save(inscricao2);

        var inscricoes = inscricaoRepositorio.findAll();

        assertTrue(inscricoes.size() >= 2);
        assertTrue(inscricoes.stream()
                .anyMatch(i -> i.getStatus().equals("CONFIRMADA")));
        assertTrue(inscricoes.stream()
                .anyMatch(i -> i.getStatus().equals("PENDENTE")));
    }

    @Test
    public void deveAlterarInscricao() {
        var inscricao = new Inscricao();

        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("PENDENTE");

        var inscricaoSalva = inscricaoRepositorio.save(inscricao);

        inscricaoSalva.setStatus("CONFIRMADA");

        inscricaoRepositorio.save(inscricaoSalva);

        var inscricaoAlterada =
                inscricaoRepositorio.findById(inscricaoSalva.getId())
                        .orElseThrow();

        assertEquals("CONFIRMADA", inscricaoAlterada.getStatus());
        assertEquals(inscricaoSalva.getId(), inscricaoAlterada.getId());
    }

    @Test
    public void deveExcluirInscricao() {
        var inscricao = new Inscricao();

        inscricao.setDataInscricao(LocalDateTime.now());
        inscricao.setStatus("CANCELADA");

        var inscricaoSalva = inscricaoRepositorio.save(inscricao);

        assertTrue(inscricaoRepositorio
                .findById(inscricaoSalva.getId())
                .isPresent());

        inscricaoRepositorio.deleteById(inscricaoSalva.getId());

        assertFalse(inscricaoRepositorio
                .findById(inscricaoSalva.getId())
                .isPresent());
    }
}
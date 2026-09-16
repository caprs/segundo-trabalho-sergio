package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.eventos.entidades.Participante;

@SpringBootTest
public class ParticipanteRepositorioTests {

    @Autowired
    private ParticipanteRepositorio participanteRepositorio;

    @Test
    public void deveInserirParticipante() {
        var participante = new Participante();

        participante.setNome("Ana Silva");
        participante.setEmail("ana@email.com");
        participante.setTelefone("14999999999");

        var participanteSalvo = participanteRepositorio.save(participante);

        assertNotNull(participanteSalvo.getId());
        assertEquals("Ana Silva", participanteSalvo.getNome());
        assertEquals("ana@email.com", participanteSalvo.getEmail());
    }

    @Test
    public void deveBuscarParticipantePorId() {
        var participante = new Participante();

        participante.setNome("Bruno Santos");
        participante.setEmail("bruno@email.com");
        participante.setTelefone("14988888888");

        var participanteSalvo = participanteRepositorio.save(participante);

        var participanteEncontrado =
                participanteRepositorio.findById(participanteSalvo.getId());

        assertTrue(participanteEncontrado.isPresent());
        assertEquals("Bruno Santos", participanteEncontrado.get().getNome());
        assertEquals("bruno@email.com", participanteEncontrado.get().getEmail());
    }

    @Test
    public void deveListarParticipantes() {
        var participante1 = new Participante();
        participante1.setNome("Carlos Oliveira");
        participante1.setEmail("carlos@email.com");
        participante1.setTelefone("14977777777");

        var participante2 = new Participante();
        participante2.setNome("Fernanda Costa");
        participante2.setEmail("fernanda@email.com");
        participante2.setTelefone("14966666666");

        participanteRepositorio.save(participante1);
        participanteRepositorio.save(participante2);

        var participantes = participanteRepositorio.findAll();

        assertTrue(participantes.size() >= 2);
        assertTrue(participantes.stream()
                .anyMatch(p -> p.getNome().equals("Carlos Oliveira")));
        assertTrue(participantes.stream()
                .anyMatch(p -> p.getNome().equals("Fernanda Costa")));
    }

    @Test
    public void deveAlterarParticipante() {
        var participante = new Participante();

        participante.setNome("Nome Antigo");
        participante.setEmail("antigo@email.com");
        participante.setTelefone("14955555555");

        var participanteSalvo = participanteRepositorio.save(participante);

        participanteSalvo.setNome("Nome Atualizado");

        participanteRepositorio.save(participanteSalvo);

        var participanteAlterado =
                participanteRepositorio.findById(participanteSalvo.getId())
                        .orElseThrow();

        assertEquals("Nome Atualizado", participanteAlterado.getNome());
        assertEquals(participanteSalvo.getId(), participanteAlterado.getId());
    }

    @Test
    public void deveExcluirParticipante() {
        var participante = new Participante();

        participante.setNome("Participante para excluir");
        participante.setEmail("excluir@email.com");
        participante.setTelefone("14944444444");

        var participanteSalvo = participanteRepositorio.save(participante);

        assertTrue(participanteRepositorio
                .findById(participanteSalvo.getId())
                .isPresent());

        participanteRepositorio.deleteById(participanteSalvo.getId());

        assertFalse(participanteRepositorio
                .findById(participanteSalvo.getId())
                .isPresent());
    }
}
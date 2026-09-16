package br.edu.unifio.eventos.repositorios;

import br.edu.unifio.eventos.entidades.Participante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ParticipanteRepositorio extends JpaRepository<Participante, Integer> {
}
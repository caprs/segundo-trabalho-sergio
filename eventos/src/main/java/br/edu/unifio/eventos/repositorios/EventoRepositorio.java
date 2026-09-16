package br.edu.unifio.eventos.repositorios;

import br.edu.unifio.eventos.entidades.Evento;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventoRepositorio extends JpaRepository<Evento, Integer> {
}
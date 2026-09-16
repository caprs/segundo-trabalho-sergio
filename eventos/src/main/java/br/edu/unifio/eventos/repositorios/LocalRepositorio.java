package br.edu.unifio.eventos.repositorios;

import br.edu.unifio.eventos.entidades.Local;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LocalRepositorio extends JpaRepository<Local, Integer> {
}
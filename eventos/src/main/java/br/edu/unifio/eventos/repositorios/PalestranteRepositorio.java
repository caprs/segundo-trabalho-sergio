package br.edu.unifio.eventos.repositorios;

import br.edu.unifio.eventos.entidades.Palestrante;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PalestranteRepositorio extends JpaRepository<Palestrante, Integer> {
}
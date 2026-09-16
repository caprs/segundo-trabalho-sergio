package br.edu.unifio.eventos.repositorios;

import br.edu.unifio.eventos.entidades.Inscricao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscricaoRepositorio extends JpaRepository<Inscricao, Integer> {
}
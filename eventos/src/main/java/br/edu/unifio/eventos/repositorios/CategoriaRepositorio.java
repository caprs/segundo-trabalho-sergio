package br.edu.unifio.eventos.repositorios;

import br.edu.unifio.eventos.entidades.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepositorio extends JpaRepository<Categoria, Integer> {
}
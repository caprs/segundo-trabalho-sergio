package br.edu.unifio.eventos.repositorios;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.edu.unifio.eventos.entidades.Categoria;

@SpringBootTest
public class CategoriaRepositorioTests {

    @Autowired
    private CategoriaRepositorio categoriaRepositorio;

    @Test
    public void deveInserirCategoria() {
        var categoria = new Categoria();

        categoria.setNome("Tecnologia");
        categoria.setDescricao("Eventos relacionados à tecnologia");

        var categoriaSalva = categoriaRepositorio.save(categoria);

        assertNotNull(categoriaSalva.getId());
        assertEquals("Tecnologia", categoriaSalva.getNome());
        assertEquals("Eventos relacionados à tecnologia", categoriaSalva.getDescricao());
    }

    @Test
    public void deveBuscarCategoriaPorId() {
        var categoria = new Categoria();

        categoria.setNome("Programação");
        categoria.setDescricao("Eventos de programação");

        var categoriaSalva = categoriaRepositorio.save(categoria);

        var categoriaEncontrada = categoriaRepositorio.findById(categoriaSalva.getId());

        assertTrue(categoriaEncontrada.isPresent());
        assertEquals("Programação", categoriaEncontrada.get().getNome());
        assertEquals("Eventos de programação", categoriaEncontrada.get().getDescricao());
    }

    @Test
    public void deveListarCategorias() {
        var categoria1 = new Categoria();
        categoria1.setNome("Java");
        categoria1.setDescricao("Eventos sobre Java");

        var categoria2 = new Categoria();
        categoria2.setNome("Spring");
        categoria2.setDescricao("Eventos sobre Spring");

        categoriaRepositorio.save(categoria1);
        categoriaRepositorio.save(categoria2);

        var categorias = categoriaRepositorio.findAll();

        assertTrue(categorias.size() >= 2);
        assertTrue(categorias.stream()
                .anyMatch(c -> c.getNome().equals("Java")));
        assertTrue(categorias.stream()
                .anyMatch(c -> c.getNome().equals("Spring")));
    }

    @Test
    public void deveAlterarCategoria() {
        var categoria = new Categoria();

        categoria.setNome("Banco de Dados");
        categoria.setDescricao("Eventos sobre banco de dados");

        var categoriaSalva = categoriaRepositorio.save(categoria);

        categoriaSalva.setNome("Banco de Dados Avançado");

        categoriaRepositorio.save(categoriaSalva);

        var categoriaAlterada = categoriaRepositorio
                .findById(categoriaSalva.getId())
                .orElseThrow();

        assertEquals("Banco de Dados Avançado", categoriaAlterada.getNome());
        assertEquals(categoriaSalva.getId(), categoriaAlterada.getId());
    }

    @Test
    public void deveExcluirCategoria() {
        var categoria = new Categoria();

        categoria.setNome("Categoria para excluir");
        categoria.setDescricao("Categoria utilizada no teste de exclusão");

        var categoriaSalva = categoriaRepositorio.save(categoria);

        assertTrue(categoriaRepositorio
                .findById(categoriaSalva.getId())
                .isPresent());

        categoriaRepositorio.deleteById(categoriaSalva.getId());

        assertFalse(categoriaRepositorio
                .findById(categoriaSalva.getId())
                .isPresent());
    }
}
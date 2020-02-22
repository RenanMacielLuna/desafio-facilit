package br.com.renan.ecommerce.controller;

import br.com.renan.ecommerce.dao.IProdutoRepository;
import br.com.renan.ecommerce.entity.Produto;
import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping({"/produtos"})
@AllArgsConstructor
public class ProdutoController {

  private IProdutoRepository produtoRepository;

  @GetMapping
  public List findAll(){
    return produtoRepository.findAll();
  }

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<Produto> findById(@PathVariable long id){
    return produtoRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public Produto create(@RequestBody Produto produto){
    return produtoRepository.save(produto);
  }

  @PutMapping(value="/{id}")
  public ResponseEntity<Produto> update(@PathVariable("id") Long id,
      @RequestBody Produto produto){

    return produtoRepository.findById(id)
        .map(record -> {

          record.setNome(produto.getNome());
          record.setDescricao(produto.getDescricao());
          record.setEstoque(produto.getEstoque());
          record.setPreco(produto.getPreco());

          Produto updated = produtoRepository.save(record);

          return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping(path = {"/id"})
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    return produtoRepository.findById(id)
        .map(record -> {
          produtoRepository.deleteById(id);
          return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }

}

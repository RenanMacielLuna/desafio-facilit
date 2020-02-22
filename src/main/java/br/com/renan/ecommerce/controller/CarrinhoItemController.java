package br.com.renan.ecommerce.controller;

import br.com.renan.ecommerce.dao.ICarrinhoItemRepository;
import br.com.renan.ecommerce.entity.CarrinhoItem;
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
@RequestMapping({"/carrinho-item"})
@AllArgsConstructor
public class CarrinhoItemController {

  private ICarrinhoItemRepository carrinhoItemRepository;

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<CarrinhoItem> findById(@PathVariable long id){
    return carrinhoItemRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public CarrinhoItem create(@RequestBody CarrinhoItem carrinhoItem){
    return carrinhoItemRepository.save(carrinhoItem);
  }

  @PutMapping(value="/{id}")
  public ResponseEntity<CarrinhoItem> update(@PathVariable("id") Long id,
      @RequestBody CarrinhoItem carrinhoItem){

    return carrinhoItemRepository.findById(id)
        .map(record -> {

          record.setProduto(carrinhoItem.getProduto());
          record.setQuantidade(carrinhoItem.getQuantidade());
          record.setPrecoTotal(carrinhoItem.getPrecoTotal());
          record.setPrecoTotalComDesconto(carrinhoItem.getPrecoTotalComDesconto());

          CarrinhoItem updated = carrinhoItemRepository.save(record);

          return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping(path = {"/id"})
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    return carrinhoItemRepository.findById(id)
        .map(record -> {
          carrinhoItemRepository.deleteById(id);
          return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }
}

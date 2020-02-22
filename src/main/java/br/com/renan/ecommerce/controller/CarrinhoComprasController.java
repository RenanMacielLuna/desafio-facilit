package br.com.renan.ecommerce.controller;

import br.com.renan.ecommerce.dao.ICarrinhoComprasRepository;
import br.com.renan.ecommerce.dao.ICupomDescontoRepository;
import br.com.renan.ecommerce.entity.CarrinhoCompras;
import br.com.renan.ecommerce.entity.CarrinhoItem;
import br.com.renan.ecommerce.entity.CupomDesconto;
import java.util.Optional;
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
@RequestMapping({"/carrinho-compras"})
@AllArgsConstructor
public class CarrinhoComprasController {

  private ICarrinhoComprasRepository carrinhoComprasRepository;
  private ICupomDescontoRepository cupomDescontoRepository;

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<CarrinhoCompras> findById(@PathVariable long id){
    return carrinhoComprasRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public CarrinhoCompras create(@RequestBody CarrinhoCompras carrinhoCompras){
    return carrinhoComprasRepository.save(carrinhoCompras);
  }

  @PutMapping(value="/{id}/{id_c_c}")
  public ResponseEntity<CarrinhoCompras> update(@PathVariable("id") Long id, @PathVariable("id_c_c") Long idCupomDesconto,
      @RequestBody CarrinhoCompras carrinhoCompras){

    Optional<CupomDesconto> desconto = cupomDescontoRepository.findById(idCupomDesconto);
    return carrinhoComprasRepository.findById(id)
        .map(record -> {

          record.definirPrecoTotalCarrinho();
          record.setCarrinhoItens(carrinhoCompras.getCarrinhoItens());

          CarrinhoCompras updated = carrinhoComprasRepository.save(record);

          return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping(path = {"/id"})
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    return carrinhoComprasRepository.findById(id)
        .map(record -> {
          carrinhoComprasRepository.deleteById(id);
          return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }

}

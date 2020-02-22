package br.com.renan.ecommerce.controller;

import br.com.renan.ecommerce.dao.ICupomDescontoRepository;
import br.com.renan.ecommerce.entity.CupomDesconto;
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
@RequestMapping({"/cupom-desconto"})
@AllArgsConstructor
public class CupomDescontoController {

  private ICupomDescontoRepository cupomDescontoRepository;

  @GetMapping
  public List findAll(){
    return cupomDescontoRepository.findAll();
  }

  @GetMapping(path = {"/{id}"})
  public ResponseEntity<CupomDesconto> findById(@PathVariable long id){
    return cupomDescontoRepository.findById(id)
        .map(record -> ResponseEntity.ok().body(record))
        .orElse(ResponseEntity.notFound().build());
  }

  @PostMapping
  public CupomDesconto create(@RequestBody CupomDesconto cupomDesconto){
    return cupomDescontoRepository.save(cupomDesconto);
  }

  @PutMapping(value="/{id}")
  public ResponseEntity<CupomDesconto> update(@PathVariable("id") Long id,
      @RequestBody CupomDesconto cupomDesconto){

    return cupomDescontoRepository.findById(id)
        .map(record -> {

          record.setCupom(cupomDesconto.getCupom());
          record.setValido(cupomDesconto.isValido());
          record.setDesconto(cupomDesconto.getDesconto());

          CupomDesconto updated = cupomDescontoRepository.save(record);

          return ResponseEntity.ok().body(updated);
        }).orElse(ResponseEntity.notFound().build());
  }

  @DeleteMapping(path = {"/id"})
  public ResponseEntity<?> delete(@PathVariable("id") Long id) {
    return cupomDescontoRepository.findById(id)
        .map(record -> {
          cupomDescontoRepository.deleteById(id);
          return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
  }
}

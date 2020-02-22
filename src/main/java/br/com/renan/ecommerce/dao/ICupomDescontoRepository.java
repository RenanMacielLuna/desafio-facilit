package br.com.renan.ecommerce.dao;

import br.com.renan.ecommerce.entity.CupomDesconto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "cupomDesconto", path = "cupom_desconto")
public interface ICupomDescontoRepository extends JpaRepository<CupomDesconto, Long> {

}

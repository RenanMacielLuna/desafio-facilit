package br.com.renan.ecommerce.dao;

import br.com.renan.ecommerce.entity.CarrinhoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "carrinhoItem", path = "carrinho-item")
public interface ICarrinhoItemRepository extends JpaRepository<CarrinhoItem, Long> {

}

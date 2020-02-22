package br.com.renan.ecommerce.dao;

import br.com.renan.ecommerce.entity.CarrinhoCompras;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "carrinhoCompras", path = "carrinho-compras")
public interface ICarrinhoComprasRepository extends JpaRepository<CarrinhoCompras, Long> {

}

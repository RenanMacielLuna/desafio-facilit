package br.com.renan.ecommerce.dao;

import br.com.renan.ecommerce.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "produto", path = "produto")
public interface IProdutoRepository extends JpaRepository<Produto, Long> {

}

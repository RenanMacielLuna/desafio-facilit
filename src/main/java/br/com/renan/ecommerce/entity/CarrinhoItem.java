package br.com.renan.ecommerce.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "carrinho_item")
public class CarrinhoItem implements Serializable {


  private static final long serialVersionUID = -8765872549563936420L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne
  private Produto produto;

  private int quantidade;

  private double precoTotal;

  private double precoTotalComDesconto;

  public void definirPrecoTotal(){
    double total = 0;
    double totalComDesconto = 0;
    for (int i = 0; i < quantidade; i++) {
      total += produto.getPreco();
    }
    if (quantidade >= 10){
      totalComDesconto = total*0.90;
    }else {
      totalComDesconto = total;
    }
    setPrecoTotal(total);
    setPrecoTotalComDesconto(totalComDesconto);
  }

}

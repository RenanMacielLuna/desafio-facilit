package br.com.renan.ecommerce.entity;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "carrinho_compras")
public class CarrinhoCompras implements Serializable {

  private static final long serialVersionUID = -6330580135325554500L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private double totalCarrinho;

  private double totalCarrinhoComDesconto;

  private boolean cupomInserido;

  @OneToOne
  private CupomDesconto cupomDesconto = null;

  @OneToMany
  private List<CarrinhoItem> carrinhoItens = new ArrayList<CarrinhoItem>();

  public void definirPrecoTotalCarrinho(){
    double total = 0;
    double totalComdesconto = 0;

    for (CarrinhoItem carrinhoItem : carrinhoItens) {
      total += carrinhoItem.getPrecoTotalComDesconto();
    }
    if (total >= 1000.00 && total <= 5000.00){
      totalComdesconto = total * 0.95; //5% de desconto
    }else if (total >= 5000.00 && total <= 10000.00){
      totalComdesconto = total * 0.93; //7% de desconto
    }else if (total >= 10000.00){
      totalComdesconto = total * 0.90; //10% de desconto
    }else {
      totalComdesconto = total;
    }

    setTotalCarrinho(total);
    setTotalCarrinhoComDesconto(totalComdesconto);
  }

  public void inserirCupomDesconto(CupomDesconto cupomDescontoASerInserido){

    if (cupomDescontoASerInserido != null){

      if (cupomInserido == false && cupomDescontoASerInserido.isValido()){
        cupomInserido = true;
        setTotalCarrinhoComDesconto(this.getTotalCarrinhoComDesconto() -
            cupomDescontoASerInserido.getDesconto());
        this.setCupomDesconto(cupomDescontoASerInserido);
      }
      else if (cupomInserido == true && cupomDesconto.isValido()){

        if (cupomDescontoASerInserido.getDesconto() > this.getCupomDesconto().getDesconto()){
          setTotalCarrinhoComDesconto(this.getTotalCarrinhoComDesconto() -
              cupomDescontoASerInserido.getDesconto());
          this.setCupomDesconto(cupomDescontoASerInserido);
        }
      }
    }

  }

}

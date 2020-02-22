package br.com.renan.ecommerce.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "cupom_desconto")
@Data
public class CupomDesconto implements Serializable {

  private static final long serialVersionUID = -7870270837114583046L;

  @Id
  @GeneratedValue
  private Long id;

  @Column
  private String cupom;

  @Column
  private boolean valido;

  @Column
  private double desconto;
}

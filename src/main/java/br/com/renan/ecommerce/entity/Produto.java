package br.com.renan.ecommerce.entity;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
@Entity
@Table(name = "produtos")
public class Produto implements Serializable {

  private static final long serialVersionUID = -6996019172134102502L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @NotNull
  @Column
  private String nome;

  @Column
  private String descricao;

  @Column
  private double preco;

  @Column
  private int estoque;

}

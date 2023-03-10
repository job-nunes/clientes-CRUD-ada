package br.com.ada.programacaowebi.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_cliente")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idCliente")
    private Long id;

    @Column(unique = true)
    private String documento;

    private String nome;
    private String endereco;
    private String tipoPessoa;
    private Boolean ativo;


}

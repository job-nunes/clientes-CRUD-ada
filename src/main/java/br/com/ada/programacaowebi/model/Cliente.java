package br.com.ada.programacaowebi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

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

    @NotEmpty(message = "O Documento não pode ser vazio!")
    @NotBlank(message = "O Documento não pode ser vazio!")
    @Column(unique = true)
    private String documento;

    @NotBlank(message = "O Documento não pode ser vazio!")
    @NotEmpty(message = "O Nome não pode ser vazio!")
    private String nome;

    private String endereco;

    @NotNull(message = "O Tipo Pessoa não pode ser null")
    @Enumerated(EnumType.STRING)
    private TipoPessoa tipoPessoa;

    private Boolean ativo;


}

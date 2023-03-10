package br.com.ada.programacaowebi.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;

import static jakarta.persistence.CascadeType.ALL;

@Entity
@Table(name = "tb_veiculo")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Veiculo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Obrigatório o preenchimento da placa")
    @NotBlank(message = "Não pode ser vazio")
    @Size(min = 7, message = "Placa deve conter no minimo 7 caracteres")
    @Column(unique = true)
    private String placa;
    private String marca;

    private String modelo;

    @NotNull(message = "O Tipo Veiculo não pode ser null")
    @Enumerated(EnumType.STRING)
    private TipoVeiculo tipoVeiculo;

    private Boolean disponivel;

}
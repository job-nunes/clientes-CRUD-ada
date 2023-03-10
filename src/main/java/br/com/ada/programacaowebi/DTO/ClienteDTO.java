package br.com.ada.programacaowebi.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ClienteDTO {

    @NotBlank(message = "O Documento não pode ser vazio")
    private String documento;
    @NotBlank(message = "O Nome não pode ser vazio")
    private String nome;
    private String endereco;
    private String tipoPessoa;
    private Boolean ativo;
}

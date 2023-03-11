package br.com.ada.programacaowebi.model;


import ch.qos.logback.core.net.server.Client;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "tb_aluguel")
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluguel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    private Cliente cliente;

    @ManyToOne
    @JoinColumn(name = "idVeiculo")
    private Veiculo veiculo;

    private Boolean ativo;
}

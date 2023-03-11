package br.com.ada.programacaowebi.repository;

import br.com.ada.programacaowebi.model.Aluguel;
import br.com.ada.programacaowebi.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    Optional<Cliente> findByDocumentoContaining(String documento);

    List<Cliente> findByAtivo(Boolean ativo);
}

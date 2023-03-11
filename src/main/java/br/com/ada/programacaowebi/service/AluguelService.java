package br.com.ada.programacaowebi.service;

import br.com.ada.programacaowebi.model.Aluguel;
import br.com.ada.programacaowebi.repository.AluguelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AluguelService {

    @Autowired
    private AluguelRepository aluguelRepository;

    public void createAluguel(Aluguel aluguel){
        this.aluguelRepository.save(aluguel);
    }

    public List<Aluguel> listarTodos() {
        return this.aluguelRepository.findAll();
    }

    public Page<Aluguel> listarPaginado(Integer numeroPagina, Integer tamanhoPagina) {
        return this.aluguelRepository.
                findAll(PageRequest.of(numeroPagina, tamanhoPagina, Sort.by(Sort.Order.asc("id"))));
    }

    public Optional<Aluguel> buscarAluguelPorId(Long id) {
        return this.aluguelRepository.findById(id);
    }


}

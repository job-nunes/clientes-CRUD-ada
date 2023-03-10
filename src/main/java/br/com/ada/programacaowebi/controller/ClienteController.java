package br.com.ada.programacaowebi.controller;

import br.com.ada.programacaowebi.DTO.ClienteDTO;
import br.com.ada.programacaowebi.model.Cliente;
import br.com.ada.programacaowebi.service.ClienteService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @PostMapping("/create")
    public ResponseEntity<String> createCliente(@Valid @RequestBody ClienteDTO cliente){
        try{
            Cliente clienteDB = Cliente.builder()
                    .nome(cliente.getNome())
                    .documento(cliente.getDocumento())
                    .endereco(cliente.getEndereco())
                    .tipoPessoa(cliente.getTipoPessoa())
                    .ativo(cliente.getAtivo())
                    .build();
            this.clienteService.createCliente(clienteDB);
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body("Criado!");
        } catch (Exception e ){
            return ResponseEntity.badRequest().build();
        }

    }

    @GetMapping("/clientes")
    public List<Cliente> listarTodos(){
        return this.clienteService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> buscarCliente(@PathVariable("id") Long id){
        Optional<Cliente> optionalCliente = this.clienteService.buscarClientePorId(id);
        if(optionalCliente.isPresent()){
            return ResponseEntity.ok(optionalCliente.get());
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/")
    public ResponseEntity<String> atualizarCliente(@RequestBody ClienteDTO cliente){
        try{
            Optional<Cliente> optionalCliente = this.clienteService.buscarClientePelaDocumento(cliente.getDocumento());
            if(optionalCliente.isPresent()) {
                Cliente clientePorDocumento = optionalCliente.get();
                Cliente clienteAtualizar = Cliente.builder()
                        .id(clientePorDocumento.getId())
                        .nome(cliente.getNome())
                        .documento(cliente.getDocumento())
                        .tipoPessoa(cliente.getTipoPessoa())
                        .endereco(cliente.getEndereco())
                        .ativo(cliente.getAtivo())
                        .build();
                this.clienteService.createCliente(clienteAtualizar);
                return ResponseEntity
                        .ok("Cliente Atualizado!");
            }
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e ){
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{id}")
    public void removerCliente(@PathVariable("id") Long id){
        this.clienteService.removerClientePorId(id);
    }
}

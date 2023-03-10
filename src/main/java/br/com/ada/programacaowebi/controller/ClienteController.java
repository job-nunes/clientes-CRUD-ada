package br.com.ada.programacaowebi.controller;

import br.com.ada.programacaowebi.model.Cliente;
import br.com.ada.programacaowebi.model.TipoPessoa;
import br.com.ada.programacaowebi.service.ClienteService;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;


@Controller
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @GetMapping("/clientes")
    public ModelAndView clientes(
            @RequestParam(defaultValue = "1", value = "page") Integer numeroPagina,
            @RequestParam(defaultValue = "3", value = "size") Integer tamanhoPagina
        ){
        ModelAndView modelAndView = new ModelAndView("clientes");
        Page<Cliente> clientePage = this.clienteService.listarPaginado(numeroPagina-1,tamanhoPagina);
        modelAndView.addObject("clientes",clientePage.getContent());
        modelAndView.addObject("totalPages",clientePage.getTotalPages());
        modelAndView.addObject("currentPage",numeroPagina);
        modelAndView.addObject("pageSize",clientePage.getSize());
        return modelAndView;
    }

    @GetMapping("/cliente/add")
    public String addCliente(Model model, Cliente veiculo) {
        model.addAttribute("add", Boolean.TRUE);
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("clientes", this.clienteService.listarTodos());
        model.addAttribute("tipoPessoa", TipoPessoa.values());
        return "cliente-add";
    }

    @PostMapping("/cliente/add")
    public String criarCliente(@Valid @ModelAttribute("cliente") Cliente cliente,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            model.addAttribute("add", Boolean.TRUE);
            return "cliente-add";
        }
        this.clienteService.createCliente(cliente);
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/{clienteId}/delete")
    public String deletarCliente(@PathVariable("clienteId") Long clienteId){
        this.clienteService.removerClientePorId(clienteId);
        return "redirect:/clientes";
    }

    @GetMapping("/cliente/{clienteId}/edit")
    public String editCliente(Model model, @PathVariable("clienteId") Long clienteId){
        model.addAttribute("add", Boolean.FALSE);
        Optional<Cliente> optionalCliente = this.clienteService.buscarClientePorId(clienteId);
        optionalCliente.ifPresent(cliente -> model.addAttribute("cliente", cliente));
        model.addAttribute("tipoPessoa", TipoPessoa.values());
        return "cliente-add";
    }

    @PutMapping("/cliente/{clienteId}/edit")
    public String editarCliente(@ModelAttribute("cliente") Cliente cliente,
                                @PathVariable("clienteId") Long clienteId){
        cliente.setId(clienteId);
        this.clienteService.createCliente(cliente);
        return "redirect:/clientes";
    }
}

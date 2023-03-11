package br.com.ada.programacaowebi.controller;

import br.com.ada.programacaowebi.model.Aluguel;
import br.com.ada.programacaowebi.model.Cliente;
import br.com.ada.programacaowebi.model.TipoVeiculo;
import br.com.ada.programacaowebi.model.Veiculo;
import br.com.ada.programacaowebi.service.AluguelService;
import br.com.ada.programacaowebi.service.ClienteService;
import br.com.ada.programacaowebi.service.VeiculoService;
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
public class AluguelController {
    @Autowired
    private AluguelService aluguelService;
    @Autowired
    private ClienteService clienteService;
    @Autowired
    private VeiculoService veiculoService;

    @GetMapping("/alugueis")
    public ModelAndView alugueis(
            @RequestParam(defaultValue = "1", value = "page") Integer numeroPagina,
            @RequestParam(defaultValue = "3", value = "size") Integer tamanhoPagina
    ){
        ModelAndView modelAndView = new ModelAndView("alugueis");
        Page<Aluguel> aluguelPage = this.aluguelService.listarPaginado(numeroPagina-1,tamanhoPagina);
        modelAndView.addObject("alugueis",aluguelPage.getContent());
        modelAndView.addObject("totalPages",aluguelPage.getTotalPages());
        modelAndView.addObject("currentPage",numeroPagina);
        modelAndView.addObject("pageSize",aluguelPage.getSize());
        return modelAndView;
    }

    @GetMapping("/aluguel/add")
    public String addAluguel(Model model, Aluguel aluguel) {
        model.addAttribute("add", Boolean.TRUE);
        model.addAttribute("aluguel", new Aluguel());
        model.addAttribute("clientes", this.clienteService.listarTodosAtivos());
        model.addAttribute("veiculos", this.veiculoService.listarTodosAtivos());
        return "aluguel-add";
    }

    @PostMapping("/aluguel/add")
    public String criarAluguel(@Valid @ModelAttribute("aluguel") Aluguel aluguel,
                               BindingResult result,
                               Model model){
        if(result.hasErrors()){
            model.addAttribute("add", Boolean.TRUE);
            return "aluguel-add";
        }
        this.aluguelService.createAluguel(aluguel);
        aluguel.getVeiculo().setDisponivel(false);
        this.veiculoService.createVeiculo(aluguel.getVeiculo());
        return "redirect:/alugueis";
    }


    @GetMapping("/aluguel/{aluguelId}/desativa")
    public String deletarAluguel(@PathVariable("aluguelId") Long aluguelId){
        Optional<Aluguel> optionalAluguel = this.aluguelService.buscarAluguelPorId(aluguelId);
        optionalAluguel.ifPresent(aluguel -> {
            aluguel.setAtivo(false);
            this.aluguelService.createAluguel(aluguel);
        });
        return "redirect:/alugueis";
    }
}

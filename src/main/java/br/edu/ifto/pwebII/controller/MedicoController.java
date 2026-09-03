package br.edu.ifto.pwebII.controller;

import br.edu.ifto.pwebII.model.entity.Medico;
import br.edu.ifto.pwebII.model.jdbc.repository.MedicoRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller MVC para a entidade Medico.
 * Diferença de implementação em relação ao material de referência:
 * usamos ***injeção por construtor*** ao invés de @Autowired em atributos.
 */
@Controller
@RequestMapping("medico")
public class MedicoController {

    private final MedicoRepository repository;

    public MedicoController(MedicoRepository repository) {
        this.repository = repository;
    }

    //carrega a página form.html do médico (cadastro novo)
    @GetMapping("form")
    public ModelAndView form(Medico medico) {
        return new ModelAndView("medico/form");
    }

    //listagem dos médicos
    @GetMapping("/list")
    public ModelAndView list(ModelMap model) {
        model.addAttribute("medicos", repository.medicos());
        return new ModelAndView("/medico/list", model);
    }

    //cadastra um novo médico
    @Transactional
    @PostMapping("save")
    public ModelAndView save(Medico medico) {
        repository.save(medico);
        return new ModelAndView("redirect:/medico/list");
    }

    //carrega o formulário preenchido para edição
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("medico", repository.medico(id));
        return new ModelAndView("/medico/form", model);
    }

    //atualiza um médico existente
    @Transactional
    @PostMapping("update")
    public ModelAndView update(Medico medico) {
        repository.update(medico);
        return new ModelAndView("redirect:/medico/list");
    }

    //exclui um médico
    @Transactional
    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id) {
        repository.remove(id);
        return new ModelAndView("redirect:/medico/list");
    }

    /**
     * Exibe todas as consultas realizadas por um médico.
     * Uso do método consultas() do domínio (que retorna uma String) conforme
     * o diagrama de classes, além de uma lista de consultas para a tabela.
     */
    @GetMapping("/consultas/{id}")
    public ModelAndView consultas(@PathVariable("id") Long id, ModelMap model) {
        Medico medico = repository.medico(id);
        model.addAttribute("medico", medico);
        model.addAttribute("consultas", repository.consultasDoMedico(id));
        return new ModelAndView("/medico/consultas", model);
    }
}

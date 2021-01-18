package br.com.symbiosys.regescweb.controllers;

import br.com.symbiosys.regescweb.dto.ProfessorDto;
import br.com.symbiosys.regescweb.models.Professor;
import br.com.symbiosys.regescweb.models.StatusProfessor;
import br.com.symbiosys.regescweb.repositories.ProfessorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;


import java.util.List;

@Controller
public class ProfessorController {

    @Autowired
    private ProfessorRepository professorRepository;



    @GetMapping("/professores")
    public ModelAndView index(){

        List<Professor> professores = this.professorRepository.findAll();

        ModelAndView mv = new ModelAndView("professores/index");
        mv.addObject("professores", professores);
        return mv;
    }

    @GetMapping("/professor/new")
    public ModelAndView newProfessor() {

        ModelAndView mv = new ModelAndView("professores/new");

        mv.addObject("statusProfessor", StatusProfessor.values());


        return mv;
    }

    @PostMapping("/professores")
    public String create(ProfessorDto professorDto){

        Professor professor = professorDto.toProfessor();
        System.out.println(professorDto);
        System.out.println(professor);

        this.professorRepository.save(professor);
        return "redirect:/professores";
    }
}
package com.algaworks.cobranca.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.algaworks.cobranca.model.StatusTitulo;
import com.algaworks.cobranca.model.Titulo;
import com.algaworks.cobranca.repository.Titulos;
import com.algaworks.cobranca.service.CadastroTituloService;



@Controller
@RequestMapping("/titulos")
public class TituloController {
	
	private static final String CAD_VIEW="CadastroTitulo";
	private static final String PESQ_VIEW = "PesquisaTitulo";
	private static final String RED_NOVO = "redirect:/titulos/novo";
	private static final String RED_TIT = "redirect:/titulos";
	
	@Autowired
	private CadastroTituloService cadastroTituloService;
	
	@Autowired
	private Titulos titulos;
	
	@RequestMapping("/novo")
	public ModelAndView novo() {
		ModelAndView mv = new ModelAndView(CAD_VIEW);	
		mv.addObject(new Titulo());
		return mv;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	private String salvar(@Validated Titulo titulo, Errors errors, RedirectAttributes attributes) {		
		
		if(errors.hasErrors()) {
			return CAD_VIEW;
		}
		
		try {
			cadastroTituloService.salvar(titulo);		
			attributes.addFlashAttribute("mensagem", "Título cadastrado!");		
			return "redirect:/titulos/novo";
		} catch (IllegalArgumentException e) {
			errors.rejectValue("dataVencimento", null, e.getMessage());			
			return CAD_VIEW;
		}
	}
	
	@ModelAttribute("todosStatusTitulo")
	public List<StatusTitulo> todosStatusTitulo(){
		return Arrays.asList(StatusTitulo.values());
	}
	
	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView pesquisar() {
		List<Titulo> todosTitulos = titulos.findAll();
		ModelAndView mv = new ModelAndView("PesquisaTitulo");
		mv.addObject("titulosObj", todosTitulos);
		return mv;
	}
	
	@RequestMapping("{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Titulo titulo) {
		// ModelAndView editar(@PathVariable Long codigo) {
		//Titulo titulo = titulos.findOne(codigo);		
		ModelAndView mv = new ModelAndView(CAD_VIEW);
		mv.addObject(titulo);
		return mv;
	}
	
	
	@RequestMapping(value="{codigo}", method = RequestMethod.DELETE)
	public String excluir(@PathVariable Long codigo, RedirectAttributes attributes) {
		
		cadastroTituloService.excluir(codigo);
		//ModelAndView mv = new ModelAndView(RED_TIT);
		attributes.addFlashAttribute("mensagem", "Título excluído!");	
		return RED_TIT;
		
	}
	
	
	
	
}

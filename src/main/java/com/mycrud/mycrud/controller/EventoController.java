package com.mycrud.mycrud.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.mycrud.mycrud.models.Evento;
import com.mycrud.mycrud.models.Invitado;
import com.mycrud.mycrud.repository.EventoRepository;
import com.mycrud.mycrud.repository.InvitadoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;

	@Autowired
	private InvitadoRepository ir;
	
	@RequestMapping(value="/evento", method=RequestMethod.GET)
	public String Form() {
		return "evento/formEvento";
	}
	
	@RequestMapping(value="/evento", method=RequestMethod.POST)
	public String Form(Evento evento) {
		
		er.save(evento);
		return "redirect:/evento";
	}
	
	@RequestMapping("/listaeventos")
	public ModelAndView listaEventos() {
		
		ModelAndView model = new ModelAndView("index");
		Iterable<Evento> listaEventos = er.findAll();
		model.addObject("listaEventos", listaEventos);
		
		return model;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ModelAndView detalleEvento(@PathVariable("id") long id) {
		
		ModelAndView model = new ModelAndView("evento/detalleEvento");
		
		Evento evento = er.findById(id);
		model.addObject("evento", evento);
		
		Iterable<Invitado> invitados = ir.findByEvento(evento);
		model.addObject("invitados", invitados);
		
		return model;
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.POST)
	public String detalleEventoPost(@PathVariable("id") long id, 
			@Valid Invitado invitado, 
			BindingResult result,
			RedirectAttributes attributes) {
		
		if(result.hasErrors()) {
			attributes.addFlashAttribute("mensaje", "Verificar los campos!!!");
			return "redirect:/{id}";
		}
		
		Evento evento = er.findById(id);
		invitado.setEvento(evento);
		ir.save(invitado);
		attributes.addFlashAttribute("mensaje", "Invitado registrado con exito!!!");
		return "redirect:/{id}";
	}
	
	@RequestMapping(value="/deleteEvento/{id}", method=RequestMethod.GET)
	public String deleteEvento(@PathVariable("id") long id) {
		Evento evento = er.findById(id);
		er.delete(evento);
		
		return "redirect:/listaeventos";
	}
	
	

}

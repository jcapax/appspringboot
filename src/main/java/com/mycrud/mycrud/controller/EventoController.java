package com.mycrud.mycrud.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.mycrud.mycrud.models.Evento;
import com.mycrud.mycrud.repository.EventoRepository;

@Controller
public class EventoController {
	
	@Autowired
	private EventoRepository er;
	
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
	
	@RequestMapping("/detalleEvento/{id}")
	public ModelAndView detalleEvento(@PathVariable("id") long id) {
		
		ModelAndView model = new ModelAndView("evento/detalleEvento");
		
		Evento evento = er.findById(id);
		model.addObject("evento", evento);
		
		return model;
	}
	
	@RequestMapping(value="/deleteEvento/{id}", method=RequestMethod.GET)
	public ModelAndView deleteEvento(@PathVariable("id") long id) {
		Evento evento = er.findById(id);
		er.delete(evento);
		ModelAndView model = new ModelAndView("index");
		Iterable<Evento> listaEventos = er.findAll();
		model.addObject("listaEventos", listaEventos);
		
		return model;
	}
	
	

}

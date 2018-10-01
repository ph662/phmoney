package com.ph.event;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationEvent;

//essa classe cria um evento da aplicação
public class RecursoCriadoEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	private HttpServletResponse response;
	private Long codigo;

	// para adicionar o header location(criar um evento) crie um objeto dessa classe
	public RecursoCriadoEvent(Object source, HttpServletResponse response, Long codigo) {
		super(source);
		// TODO Auto-generated constructor stub
		this.response = response;
		this.codigo = codigo;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public Long getCodigo() {
		return codigo;
	}

}

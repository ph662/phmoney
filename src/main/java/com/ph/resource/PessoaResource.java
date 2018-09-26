package com.ph.resource;

import java.net.URI;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ph.model.Categoria;
import com.ph.model.Pessoa;
import com.ph.repository.PessoaRepository;

//@RestController retorno já convertido para JSON
@RestController
@RequestMapping("/pessoas")
public class PessoaResource {

	/*
	 * O REST trabalhamos baseado em recursos, então essa é a classe que vai expor
	 * tudo que esteja relacionado ao recurso categoria.
	 * 
	 * Essa classe é uma controladora, é usado o postman para chegar nessa classe. O
	 * spring sabe pela annotaion @RestController e @RequestMapping
	 * 
	 */

	// injeta o repositorio que ja contem varias operações
	// Spring procura a implementação de categoria repository e injeta pra mim
	@Autowired
	private PessoaRepository pessoaRepository;

	// Usa Get pois para buscar via HTTP o verbo GET é o correto
	@GetMapping
	public List<Pessoa> listar() {
		return pessoaRepository.findAll();// Select * from categoria;

		// Caso a List categorias esteja vazia
		// return categorias.isEmpty() ? ResponseEntity.ok(categorias) :
		// ResponseEntity.notFound().build();
		// ResponseEntity.notFound().build() retorna 404 - nao encontrado
		// ResponseEntity.noContent() retorna 204 - sem conteudo
	}

	// 201 created eh o codigo ideal para retornar ao gravar informacao no banco
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa pessoa, HttpServletResponse response) {

		Pessoa pessoaSalva = pessoaRepository.save(pessoa);

		// Cria URL com o id do que foi criado no servidor
		URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{codigo}")
				.buildAndExpand(pessoaSalva.getCodigo()).toUri();
		response.setHeader("Location", uri.toASCIIString());

		// retorna o que foi criado neste momento
		return ResponseEntity.created(uri).body(pessoaSalva);
	}

	// o codigo numero que vem por parametro entra em {codigo} e depois entra em
	// codigo do parametro
	@GetMapping("/{codigo}")
	public ResponseEntity<Pessoa> buscarPeloCodigo(@PathVariable Long codigo) {
		Pessoa retorno = pessoaRepository.findOne(codigo);
		return retorno == null ? ResponseEntity.notFound().build() : ResponseEntity.ok(retorno);
	}

	@GetMapping("/outro")
	// Funciona, para casos que é preciso usar GetMapping mais de uma vez
	// pra chegar aqui /categorias/outro
	public String outro() {
		return "outro";

	}

}

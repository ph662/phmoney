package com.ph.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ph.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

	/*
	 * CategoriaRepository extends JpaRepository Essa interface já nos entrega
	 * varios metodos como findAll, delete, entre outros. A implementaçaõ da
	 * interface que da é o Spring Data JPA. Operações de CRUD já ganha de graça por
	 * usar o spring.data.JPA. este está no pom.xml
	 * 
	 * Os generics Categoria - é para trabalhar em cima desse tipo e o tipo da chave
	 * primária é Long,
	 * 
	 * Recebe Long como chave e vai retornar ou receber o tipo Categoria.
	 */
}

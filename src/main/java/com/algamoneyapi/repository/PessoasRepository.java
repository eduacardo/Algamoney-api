package com.algamoneyapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.algamoneyapi.model.Pessoa;

public interface PessoasRepository extends JpaRepository<Pessoa, Long> {

}

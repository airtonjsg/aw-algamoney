package com.exemplo.algamoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exemplo.algamoney.api.model.Lancamento;

public interface LancamentoRepository extends JpaRepository<Lancamento, Long>  {

}
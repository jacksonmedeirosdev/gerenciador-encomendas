package br.com.jjnervosia.gerenciador_encomendas.apartamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {

    boolean existsByNumeroAndBlocoId(String numero, Long blocoId);
    Optional<Apartamento> findByNumeroAndBlocoId(String numero, Long blocoId);
}

package br.com.jjnervosia.gerenciador_encomendas.apartamento;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartamentoRepository extends JpaRepository<Apartamento, Long> {

    boolean existsByNumeroAndBlocoId(String numero, Long blocoId);
}

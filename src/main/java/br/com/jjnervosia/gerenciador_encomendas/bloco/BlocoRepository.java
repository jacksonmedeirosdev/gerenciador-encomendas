package br.com.jjnervosia.gerenciador_encomendas.bloco;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BlocoRepository extends JpaRepository<Bloco, Long> {
    boolean existsByIdentificacao(String identificacao);

    Optional<Bloco> findByIdentificacao(String identificacao);
}

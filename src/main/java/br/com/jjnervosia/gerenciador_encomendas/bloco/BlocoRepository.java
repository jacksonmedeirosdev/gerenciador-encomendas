package br.com.jjnervosia.gerenciador_encomendas.bloco;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BlocoRepository extends JpaRepository<Bloco, Long> {
    boolean existsByIdentificacao(String identificacao);
}

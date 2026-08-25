package br.com.jjnervosia.gerenciador_encomendas.morador;

import br.com.jjnervosia.gerenciador_encomendas.apartamento.Apartamento;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table
@Getter
public class Morador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,  length = 100)
    private String nome;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "apartamento_id", nullable = false)
    private Apartamento apartamento;

    protected Morador() {

    }

    public Morador( String nome, Apartamento apartamento) {

        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException(("O nome do morador é obrigatório"));
        }
        if (apartamento == null) {
            throw new IllegalArgumentException(("O apartamento é obrigatório para cadastrar um morador"));
        }

        this.nome = nome;
        this.apartamento = apartamento;
    }
}

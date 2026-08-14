package br.com.jjnervosia.gerenciador_encomendas.apartamento;

import br.com.jjnervosia.gerenciador_encomendas.bloco.Bloco;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;

@Entity
@Table
@Getter
public class Apartamento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name= "numero",  nullable = false,  length = 10)
    private String numero;

    //LAZY: o bloco é carregado sob demanda pelo JPA.
    @ManyToOne(fetch = FetchType.LAZY)
    private Bloco bloco;

    protected  Apartamento(){
    }
    public Apartamento(String numero, Bloco bloco){

        if (numero == null || numero.isBlank()){
            throw new IllegalArgumentException("O número do apartamento é obrigatório.");
        }
        if (bloco == null){
            throw new IllegalArgumentException("O bloco é obrigatório para cadastrar o apartamento.");
        }

        this.numero = numero;
        this.bloco = bloco;
    }

    public void alterarNumeroEBloco(String numero, Bloco bloco) {
        this.numero = numero;
        this.bloco = bloco;
    }
}

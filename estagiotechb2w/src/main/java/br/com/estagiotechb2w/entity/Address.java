package br.com.estagiotechb2w.entity;

import br.com.estagiotechb2w.dto.AddressDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor // Construtor Padrão
@AllArgsConstructor
@Builder
@Entity
@Table(name="address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "logradouro")
    private String logradouro;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "bairro")
    private String bairro;

    @Column(name = "localidade")
    private String localidade;

    @Column(name = "uf")
    private String uf;

    @Column(name = "cep")
    private String cep;

    public Address(AddressDTO addressDTO){
        this.logradouro = addressDTO.getLogradouro();
        this.complemento = addressDTO.getComplemento();
        this.bairro = addressDTO.getBairro();
        this.localidade = addressDTO.getLocalidade();
        this.uf = addressDTO.getUf();
        this.cep = addressDTO.getCep();

    }
}

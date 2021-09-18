/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsp;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import br.edu.ifpb.domain.IntegranteInterface;
import br.edu.ifpb.domain.persistencyJDBC.IntegranteJDBC;
import java.sql.SQLException;

/**
 *
 * @author mandy
 */
public class IntegranteController {
    
    private String resultIntegrante = "";
    private Integrante integrante = new Integrante();
    private IntegranteInterface integrantes;
    
    /**
     * Construtor
     * @throws SQLException 
     */
    public IntegranteController() throws SQLException {
        this.integrantes = (IntegranteInterface) new IntegranteJDBC();
    }

    /**
     * Getter e setter
     * @return 
     */
    public String getResultIntegrante() {
        return resultIntegrante;
    }
    public void setResultIntegrante(String resultIntegrante) {
        this.resultIntegrante = resultIntegrante;
    }
    public Integrante getIntegrante() {
        return integrante;
    }
    public void setIntegrante(Integrante integrante) {
        this.integrante = integrante;
    }
    public IntegranteInterface getIntegrantes() {
        return integrantes;
    }
    public void setIntegrantes(IntegranteInterface integrantes) {
        this.integrantes = integrantes;
    }
    
    /**
     * Inserir integrante
     * @return 
     */
    public String add(){
        this.integrantes.addIntegrante(this.integrante);
        this.integrante = new Integrante();
        return "/integrantes/list?faces-redirect=true";
    }
    
    /**
     * Deletar integrante
     * @param integrante
     * @return 
     */
    public String delete(Integrante integrante) {
        this.integrantes.deleteIntegrante(integrante);
        return "/integrantes/list";
    }
    
    /**
     * Editar integrante
     * @param integrante
     * @return 
     */
    public String update(Integrante integrante){
        this.integrante = integrante;
        return "/integrantes/edit?faces-redirect=true";
    }
    
    public String search (){
        Integrante resultInt = this.integrantes.searchIntegrante(this.integrante.getCpf().getNumero());
        if(resultInt.getNome().equals("Integrante Não encontrado, tente novamente")){
            this.resultIntegrante = resultInt.getNome() +  "não esta cadastrado";
        } else{
            this.resultIntegrante = "Integrante: " + resultInt.getNome() + "id: " + resultInt.getId() +"CPF:" + resultInt.getCpf() + "Data de Nascimento:" + resultInt.getDataDeNascimento();
        }
        this.integrante = new Integrante();
        return "/integrantes/search";
    }
    
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain;

import java.io.Serializable;
import java.util.List;

/**
 *
 * @author mandy
 */
public interface IntegranteInterface extends Serializable{
    
    public List<Integrante> listaIntegrantes();
    
    public void addIntegrante (Integrante integrante);
    
    public void updateIntegrante (Integrante integrante);
    
    public void deleteIntegrante (Integrante integrante);
    
    public Integrante searchIntegrante (String cpf);
    
}

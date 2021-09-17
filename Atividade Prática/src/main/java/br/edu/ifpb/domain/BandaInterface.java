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
public interface BandaInterface extends Serializable{
    
    public List<Banda> listaBanda();
    
    public void addBanda (Banda banda);
    
    public void updateBanda (Banda banda);
    
    public void deleteBanda (Banda banda);
    
    public List<Banda> searchBanda(String localDeOrigem);
    
}

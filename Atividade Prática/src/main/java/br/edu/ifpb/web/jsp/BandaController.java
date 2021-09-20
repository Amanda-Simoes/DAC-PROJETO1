/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.web.jsp;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.BandaInterface;
import br.edu.ifpb.domain.persistencyJDBC.BandaJDBC;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author mandy
 */

@Named
@SessionScoped
public class BandaController implements Serializable{
    
    
    private Banda banda = new Banda();
    private List<Banda> resultBandas = new ArrayList<>();
    private BandaInterface bandas;
    
    /**
     * Construtor
     * @throws SQLException 
     */
    public BandaController() throws SQLException {
        this.bandas = (BandaInterface) new BandaJDBC();
    }

    /**
     * Getter e setter
     * @return 
     */
    public Banda getBanda() {
        return banda;
    }
    public void setBanda(Banda banda) {
        this.banda = banda;
    }
    public List<Banda> getResultBandas() {
        return resultBandas;
    }
    public void setResultBandas(List<Banda> resultBandas) {
        this.resultBandas = resultBandas;
    }
    public BandaInterface getBandas() {
        return bandas;
    }
    public void setBandas(BandaInterface bandas) {
        this.bandas = bandas;
    }
    
    /**
     * Adicionar banda
     * @return 
     */
    public String add() { 
        if(this.banda.getId() > 0){
            this.bandas.updateBanda(this.banda);
        } else{
            this.bandas.addBanda(banda);
        }
        this.banda = new Banda();
        return "/bandas/list?faces-redirect=true";
    }
    
    /**
     * Excluir banda
     * @param banda
     * @return 
     */
    public String delete(Banda banda){
        this.bandas.deleteBanda(banda);
        return "/bandas/list";
    }
    
    /**
     * Editar banda
     * @param banda
     * @return 
     */
    public String update(Banda banda){
        this.banda = banda;
        return "/bandas/edit?faces-redirect=true";
    }
    
    /**
     * Pesquisar banda por local de origem
     * @return 
     */
    public String search(){
        
        this.resultBandas = this.bandas.searchBanda(this.banda.getLocalDeOrigem());
        this.banda = new Banda("", "", new ArrayList<>());
        return "/bandas/search";
    }
    
    public List<Banda> listar(){
        return this.bandas.listaBanda();
    }
    
}

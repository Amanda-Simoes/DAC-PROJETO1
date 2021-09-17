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
     * Adicionar banda
     * @return 
     */
    public String add() { 
        this.bandas.addBanda(banda);
        this.banda = new Banda();
        return "/Banda/list?faces-redirect=true";
    }
    
    /**
     * Excluir banda
     * @param banda
     * @return 
     */
    public String delete(Banda banda){
        this.bandas.deleteBanda(banda);
        return "/Banda/list";
    }
    
    /**
     * Editar banda
     * @param banda
     * @return 
     */
    public String update(Banda banda){
        this.banda = banda;
        return "/Banda/edit?faces-redirect=true";
    }
    
    public String search(){
        this.resultBandas = this.bandas.searchBanda(this.banda.getLocalDeOrigem());
        this.banda = new Banda();
        return "/Banda/search";
    }
    
}

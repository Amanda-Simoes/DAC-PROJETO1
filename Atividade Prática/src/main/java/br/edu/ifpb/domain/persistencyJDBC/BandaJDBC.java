/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.persistencyJDBC;

import br.edu.ifpb.domain.Banda;
import br.edu.ifpb.domain.BandaInterface;
import br.edu.ifpb.domain.Integrante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mandy
 */
public class BandaJDBC{
    
    private Connection connection;
    
    /**
     * Conexão com o banco
     * @throws SQLException 
     */
    public BandaJDBC() throws SQLException{
        
        try{
            // Conexão com o banco
            Class.forName("org.postgresql.Driver");
            this.connection = DriverManager.getConnection(
                    "jdbc:postgresql://host-banco:5432/projeto1",
                    "postgres",
                    "123"
            );
        } catch (ClassNotFoundException | SQLException e){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE,null,e);
            
        }
        
    }
    
    public List<Banda> lista() throws SQLException{
        
        try{
            
            List<Banda> banda = new ArrayList<>();
            ResultSet bandaResult = connection.prepareStatement("SELECT * FROM banda").executeQuery();
            
            while ( bandaResult.next() ){
                banda.add(bandaGuia(bandaResult));
            }
            
            return banda;
            
        } catch(SQLException ex){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, ex);
            return Collections.EMPTY_LIST;
        }
        
    }
    
    /**
     * Adicionar uma banda
     * @param banda 
     */
    public void addBanda (Banda banda) {
        
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO Banda (localDeOrigem, nomeFantasia) VALUES (?, ?)");
            
            statement.setString(1, banda.getLocalDeOrigem());
            statement.setString(2, banda.getNomeFantasia());
            statement.executeQuery();
            
            // Inserir na tabela integrante_banda
            
        } catch (SQLException e){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    /**
     * Atualizar dados de uma banda
     * @param banda 
     */
    public void updateBanda (Banda banda){
        
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE banda SET nomeFantasia=?,localDeOrigem=? WHERE id=?");
            statement.setString(1, banda.getNomeFantasia());
            statement.setString(2, banda.getLocalDeOrigem());
            statement.setInt(3, banda.getId());
            statement.executeQuery();
        } catch (SQLException e){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    /**
     * Deletar uma banda de acordo com seu ID
     * @param banda 
     */
    public void deleteBanda (Banda banda){
        
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM banda WHERE id=?");
            statement.setInt(1, banda.getId());
            statement.executeQuery();
        } catch (SQLException e){
            Logger.getLogger(BandaJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    public List<Banda> searchBanda (String localDeOrigem){
        
        try{
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM banda WHERE localDeOrigem=?");
            statement.setString(1, localDeOrigem);
            statement.executeQuery();
            
            List<Banda> banda = new ArrayList<>();
            
            ResultSet resultBanda = statement.getResultSet();
            while (resultBanda.next()){
                banda.add(bandaGuia(resultBanda));
            }
            
            return banda;
        } catch (SQLException e){
            return Collections.EMPTY_LIST;
        }
        
    }
    
    public Banda bandaGuia (ResultSet result) throws SQLException{
         int id = result.getInt("id");
         String nomeFantasia = result.getString("NomeFantasia");
         String localDeOrigem = result.getString("LocalDeOrigem");
         List<Integrante> integrantes = new ArrayList<>();
         
         return new Banda(id, localDeOrigem, nomeFantasia, integrantes);
    }
    
}

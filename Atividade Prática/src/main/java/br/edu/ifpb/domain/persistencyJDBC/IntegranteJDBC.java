/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.ifpb.domain.persistencyJDBC;

import br.edu.ifpb.domain.CPF;
import br.edu.ifpb.domain.Integrante;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author mandy
 */
public class IntegranteJDBC {
    
    private Connection connection;
    
    
    /**
     * Conexão com o banco
     * @throws SQLException 
     */
    public IntegranteJDBC() throws SQLException{
        
        try{
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
    
    public List<Integrante> listaIntegrante() throws SQLException{
        
        try{
            ResultSet resultIntegrante = connection.prepareStatement("SELECT * FROM Integrante").executeQuery();
            
            List<Integrante> integrante = new ArrayList<>();
            
            while(resultIntegrante.next()){
                integrante.add(integranteGuia(resultIntegrante));
            }
            
            return integrante;
        } catch (SQLException e) {
            return Collections.EMPTY_LIST;
        }
        
    }
    
    /**
     * Adicionar integrante
     * @param integrante
     * @throws SQLException 
     */
    public void addIntegrante(Integrante integrante) throws SQLException{
        
        try{
            PreparedStatement statement = connection.prepareStatement("INSERT INTO integrante (Nome, CPF, dataDeNascimento) VALUES(?,?,?)");
            statement.setString(1, integrante.getNome());
            statement.setString(2,integrante.getCpf().getNumero());
            statement.setDate(3, java.sql.Date.valueOf(integrante.getDataDeNascimento()));
            statement.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE,null,e);
        }
        
    }
    
    /**
     * Atualizar informações de um Integrante
     * @param integrante
     * @throws SQLException 
     */
    public void updateIntegrante(Integrante integrante) throws SQLException{
        
        try{
            PreparedStatement statement = connection.prepareStatement("UPDATE integrante SET nome=?, cpf=?, dataDeNascimento=? WHERE id=?");
            statement.setString(1, integrante.getNome());
            statement.setString(2, integrante.getCpf().getNumero());
            statement.setDate(3, java.sql.Date.valueOf(integrante.getDataDeNascimento()));
            statement.setInt(4, integrante.getId());
            statement.executeQuery();
        } catch (SQLException e){
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    /**
     * Deletar integrante
     * @param integrante
     * @throws SQLException 
     */
    public void deleteIntegrante (Integrante integrante) throws SQLException{
        
        try{
            PreparedStatement statement = connection.prepareStatement("DELETE FROM integrante WHERE id=?");
            statement.setInt(1, integrante.getId());
            statement.executeQuery();
        } catch (SQLException e) {
            Logger.getLogger(IntegranteJDBC.class.getName()).log(Level.SEVERE, null, e);
        }
        
    }
    
    /**
     * Pesquisa de Integrante por CPF
     * @param cpf
     * @return 
     */
    public Integrante searchIntegrante (String cpf){
         
          try {
           
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Integrante WHERE Cpf = ?");
            
            statement.setString(1, cpf);
            statement.executeQuery();
            
            ResultSet resultCPF = statement.getResultSet();
            
            while(resultCPF.next()){
                return integranteGuia(resultCPF);
            }
            
            return new Integrante("Integrante Não encontrado, tente novamente!");
            
                         
        } catch (SQLException e) {
            return new Integrante("", new CPF(""), LocalDate.of(1, 1, 1));
        }
         
     }
    
     private Integrante integranteGuia(ResultSet result) throws SQLException {
         
        int id = result.getInt("id");
        String nome = result.getString("nome");
        String date = result.getString("dataDeNascimento");
        LocalDate dataDeNascimento = LocalDate.of(
                Integer.parseInt(date.substring(0, 4)), 
                Integer.parseInt(date.substring(5, 7)), 
                Integer.parseInt(date.substring(8, 10))
        );
        CPF cpf = new CPF(result.getString("cpf"));
        return new Integrante(id, nome,dataDeNascimento, cpf);
    }
    
}

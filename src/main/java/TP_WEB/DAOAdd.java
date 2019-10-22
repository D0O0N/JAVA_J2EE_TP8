/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TP_WEB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.DataSource;
import simplejdbc.CustomerEntity;
import simplejdbc.DAO;
import simplejdbc.DAOException;

/**
 *
 * @author pedago
 */
public class DAOAdd extends DAO{
    
    public DAOAdd(DataSource dataSource) {
        super(dataSource);
    }
    
    public ArrayList<String> getAllStates(){
        ArrayList<String> allStates = new ArrayList<String>();
        String sql = "SELECT DISTINCT STATE FROM CUSTOMER ";
        try (Connection connection = myDataSource.getConnection();
                PreparedStatement stmt = connection.prepareStatement(sql)) {


                try (ResultSet rs = stmt.executeQuery()) {
                        while (rs.next()) { // Tant qu'il y a des enregistrements
                                allStates.add(rs.getString("STATE"));
                        }
                }
        }  catch (SQLException ex) {
                Logger.getLogger("DAO").log(Level.SEVERE, null, ex);
        }
        return allStates;
    }
    
}

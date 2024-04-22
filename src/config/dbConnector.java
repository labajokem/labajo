
package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;


public class dbConnector {
    private Connection connect;
     public dbConnector(){
            try{
                connect = DriverManager.getConnection("jdbc:mysql://localhost:3306/sampledbs", "root", "");
            }catch(SQLException ex){
                    System.out.println("Can't connect to database: "+ex.getMessage());
            }
        }
     
     //Function to retrieve data
        public ResultSet getData(String sql) throws SQLException{
            Statement stmt = connect.createStatement();
            ResultSet rst = stmt.executeQuery(sql);
            return rst;
        }
    
        //Function to save data
        public boolean insertData(String sql){
            try{
                PreparedStatement pst = connect.prepareStatement(sql);
                pst.executeUpdate();
                System.out.println("Inserted Successfully!");
                pst.close();
               return true;
            }catch(SQLException ex){
                System.out.println("Connection Error: "+ex);
               return false;
            }
        }
        
        public void updateData(String sql){
        
        try{
            PreparedStatement pst = connect.prepareStatement(sql);
            int rowsUpdated = pst.executeUpdate();
            if(rowsUpdated > 0){
                JOptionPane.showMessageDialog(null,"Data Updated Succesfully!");
            }else{
                System.out.println("Data Update Failed!");
            }
            pst.close();
        }catch(SQLException ex){
            System.out.println("Connection Error:"+ex);
        }
    }
    
    public void deleteData(int id, String table){
        try{
            PreparedStatement pst = connect.prepareStatement("DELETE FROM tbl_user WHERE id = ?");
            pst.setInt(1,id);
            int rowsDeleted = pst.executeUpdate();
                if(rowsDeleted > 0){
                    JOptionPane.showMessageDialog(null,"Data Deleted Succesfully!");
                }else{
                    System.out.println("Data Delete Failed!");
                }
                pst.close();
        }catch(SQLException ex){
            System.out.println("Connection Error:"+ex);
        }
    }
        
        
        
        
}

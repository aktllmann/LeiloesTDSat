/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Adm
 */

import java.sql.PreparedStatement;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.util.ArrayList;


public class ProdutosDAO {
    Connection conn;
    PreparedStatement prep;
    ResultSet resultset;
    ArrayList<ProdutosDTO> listagem = new ArrayList<>();
    
    public ProdutosDAO() {
        conectaDAO conecta = new conectaDAO();
        conn = conecta.connectDB();    
    }
    
    public void cadastrarProduto (ProdutosDTO produto){
        conectaDAO conecta = new conectaDAO();
        conn = conecta.connectDB();
       String sql = "INSERT INTO produtos (nome, valor, status) VALUES "
                            + "(?, ?, ?)";
        try {   prep = this.conn.prepareStatement(sql);
                prep.setString(1, produto.getNome());
                prep.setInt(2, produto.getValor());
                prep.setString(3, produto.getStatus());
                prep.execute();
                
        } catch (Exception e) {
                System.out.println("Erro ao inserir produto: " + e.getMessage());
  }
 }
    
    public ArrayList<ProdutosDTO> listarProdutos(){
               
        String sql = "SELECT * FROM produtos";

     try {
         
        conn = new conectaDAO().connectDB();
        prep = conn.prepareStatement(sql);
        ResultSet rs = prep.executeQuery();

        while (rs.next()) {
            ProdutosDTO p = new ProdutosDTO();

            p.setId(rs.getInt("id"));
            p.setNome(rs.getString("nome"));
            p.setValor(rs.getInt("valor"));
            p.setStatus(rs.getString("status"));

            listagem.add(p); 
       }
        } catch (Exception e) {
               System.out.println("Erro listar produtos: " + e.getMessage());
    }            
           return listagem;
    }
 }


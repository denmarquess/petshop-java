
package modelDAO;

import controller.Conexao;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.JOptionPane;
import model.Animal;


public class AnimalDAO {

    private Connection conn = null;
    DateTimeFormatter fdata = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public void AnimalDAO() {

        Conexao a = Conexao.obterInstancia();
        conn = a.obterConexao();

    }
    
    
    public int proximoCodigo() {
        String sql;
        PreparedStatement ps = null;
        int proximoCodigo = -1;

        sql = "select max(id) from animal";

        try {
            ps = conn.prepareStatement(sql);
            ResultSet rs = null;
            rs = ps.executeQuery();

            if (rs.next()) {
                proximoCodigo = rs.getInt(1);
                proximoCodigo++;
            }
            ps.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na operação buscar código máximo: " + e.getMessage());
        }

        return proximoCodigo;
    }

    public void incluir(Animal ani, String caminho) throws FileNotFoundException {
        String sql;
        PreparedStatement ps = null;
        File file = new File(caminho);
        InputStream fis = new FileInputStream(file);
        
        sql = "INSERT INTO animal(id,nome,data_nascimento,raca,altura,foto) VALUES (?,?,?,?,?,?)";
        try {
            ps = conn.prepareStatement(sql);
            //ps.setInt(1, ani.getId());
            ps.setString(2, ani.getNome());
            ps.setString(3, ani.getDataNascimento().toString());
            ps.setString(4, ani.getRaca());
            ps.setDouble(5, ani.getAltura());
            //ps.setBinaryStream(6, fis, ani.getFoto());
            ps.setBinaryStream(6, fis, (int)file.length());
            ps.execute();
            ps.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
        }
    }

    public void alterar(Animal a) {

        String sql;
        PreparedStatement ps = null;
        sql = "update animal set nome = ?, dataNascimento = ?, raca = ?, peso = ?, altura = ?, foto = ? where id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, a.getNome());
            ps.setString(2, a.getDataNascimento().format(fdata));
            ps.setString(3, a.getRaca());
            ps.setFloat(4, a.getPeso());
            ps.setFloat(5, a.getAltura());
            ps.setBytes(6, a.getFoto());
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de alterar produto: " + e.getMessage());
        }
    }

    public void excluir(int id) {
        String sql;
        PreparedStatement ps = null;
        sql = "delete from animal where id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            ps.execute();
            ps.close();
        } catch (Exception e) {
            System.out.println("Erro na operação de apagar registro: " + e.getMessage());
        }
    }
    
    public Animal retornaAnimal(int id) {
         String sql;
         PreparedStatement ps = null;
         ResultSet rs = null;
         Animal a = new Animal();
         sql = "SELECT * FROM animal where id = ?";
         
         try{
             ps = conn.prepareStatement(sql);
             ps.setInt(1, id);
             rs = ps.executeQuery();
             
             if(rs.next()){
                 a.setId(rs.getInt("id"));
                 a.setNome(rs.getString("nome"));
                 a.setDataNascimento( LocalDate.parse(rs.getString("dataNascimento"), formatter));
                 a.setRaca(rs.getString("raca"));
                 a.setPeso(rs.getFloat("peso"));
                 a.setAltura(rs.getByte("foto"));
                
                           
             }
             
             ps.close();
             
         }catch (Exception e){
             System.out.println("Erro na operação de listar" +e.getMessage());
         }
   
        return a;      
    }
    
     public ArrayList<Animal> listar(){
	ArrayList<Animal> animais = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from login";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Animal a = new Animal();
                a.setId(rs.getInt("id"));
                 a.setNome(rs.getString("nome"));
                 a.setDataNascimento( LocalDate.parse(rs.getString("dataNascimento"), formatter));
                 a.setRaca(rs.getString("raca"));
                 a.setPeso(rs.getFloat("peso"));
                 a.setAltura(rs.getByte("foto"));
                 animais.add(a);
            }
            rs.close();
            ps.close();
        } 
        catch (Exception e) {
            System.err.println("Erro na operação de listar registros: " + e.getMessage());
        }

        return animais;
    }
    
    
    

}

    



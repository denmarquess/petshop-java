/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelDAO;

import controller.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import model.Item;
import model.Produto;
import model.Profissional;
import model.Servico;

public class ItensDAO {

    private Connection conn = null;
    DateTimeFormatter fdata = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public ItensDAO() {
        Conexao a = Conexao.obterInstancia();
        conn = a.obterConexao();
    }

    public int proximoCodigo() {
        String sql;
        PreparedStatement ps = null;
        int proximoCodigo = -1;

        sql = "select max(id) from item";

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

    public boolean incluirItem(Item item) {
        String sql;
        PreparedStatement ps = null;
        boolean retorno = true;
        int tipo;
        if (item instanceof Produto) {
            tipo = 1;
        } else {
            tipo = 2;
        }
        sql = "INSERT INTO item(descricao, preco, tipo, status) VALUES (?, ?, ?, ?)";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, item.getDescricao());
            ps.setFloat(2, item.getPreco());
            ps.setInt(3, tipo);
            ps.setInt(4, 0);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir Item: " + e.getMessage());

        }
        if (item instanceof Produto) {
            Produto p = (Produto) item;
            p.setId(proximoCodigo() - 1);
            retorno = incluirProduto(p);
        } else {
            Servico s = (Servico) item;
            s.setId(proximoCodigo() - 1);
            retorno = incluirServico(s);
        }
        return retorno;

    }

    private boolean incluirProduto(Produto pdt) {
        String sql;
        PreparedStatement ps = null;
        sql = "INSERT INTO produto(validade,marca, modelo,codigo_produto, produto_item) VALUES (?, ?, ?, ?,?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, pdt.getValidade().format(fdata));
            ps.setString(2, pdt.getMarca());
            ps.setString(3, pdt.getModelo());
            ps.setString(4, pdt.getCodProduto());
            ps.setInt(5, pdt.getId());
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
            return false;

        }
    }

    private boolean incluirProfissionalServico(Profissional p, int idServico) {
        String sql;
        PreparedStatement ps = null;
        ps = null;
        sql = "INSERT INTO servico_profissional(id_profissional, id_servico) VALUES (?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, p.getId());
            ps.setInt(2, idServico);
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
            return false;

        }
    }

    private boolean incluirServico(Servico serv) {
        String sql;
        PreparedStatement ps = null;
        boolean retorno = true;
        sql = "INSERT INTO servico(equipamentos, servico_item) VALUES (?, ?)";
        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, serv.getEquipamentos());
            ps.setInt(2, serv.getId());
            ps.execute();
            ps.close();
            for (Profissional p : serv.getProficionais()) {
                retorno = incluirProfissionalServico(p, serv.getId());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
            return false;

        }
        return retorno;

    }

    private Produto buscarProduto(Produto pr) {
        String sql;
        PreparedStatement ps = null;

        sql = "SELECT * FROM produto WHERE produto_item = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pr.getId());
            ResultSet rs = null;
            rs = ps.executeQuery();
            if (rs.next()) {
                pr.setCodProduto(rs.getString("codigo_produto"));
                pr.setMarca(rs.getString("marca"));
                pr.setModelo(rs.getString("modelo"));
                pr.setValidade(LocalDate.parse(rs.getString("validade"), formatter));
                rs.close();
                ps.close();
                return pr;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
        }
        return pr;
    }

    public ArrayList<Item> listarItensExcluídos(int t) {
        ArrayList<Item> prods = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from item";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int tipo = rs.getInt("tipo");
                int status = rs.getInt("status");
                if ((tipo == t) && (status == 1)) {
                    if (t == 1) {
                        Produto p = new Produto();
                        p.setId(rs.getInt("id"));
                        p.setDescricao(rs.getString("descricao"));
                        p.setPreco(rs.getFloat("preco"));
                        prods.add(buscarProduto(p));
                    } else {
                        Servico p = new Servico();
                        p.setId(rs.getInt("id"));
                        p.setDescricao(rs.getString("descricao"));
                        p.setPreco(rs.getFloat("preco"));
                        prods.add(buscarServico(p));
                    }

                }
            }
            rs.close();
            ps.close();
            return prods;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de listar Itens: " + e.getMessage());
        }

        return null;
    }

    public ArrayList<Item> listarItensAtivos(int t) {
        ArrayList<Item> prods = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from item";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                int tipo = rs.getInt("tipo");
                int status = rs.getInt("status");
                if ((tipo == t) && (status == 0)) {
                    if (t == 1) {
                        Produto p = new Produto();
                        p.setId(rs.getInt("id"));
                        p.setDescricao(rs.getString("descricao"));
                        p.setPreco(rs.getFloat("preco"));
                        prods.add(buscarProduto(p));
                    } else {
                        Servico p = new Servico();
                        p.setId(rs.getInt("id"));
                        p.setDescricao(rs.getString("descricao"));
                        p.setPreco(rs.getFloat("preco"));
                        prods.add(buscarServico(p));
                    }

                }
            }
            rs.close();
            ps.close();
            return prods;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de listar Itens excluídos: " + e.getMessage());
        }

        return null;
    }

    public void alterar(Produto p) {
        String sql;
        PreparedStatement ps = null;

        sql = "update produto set validade = ?,marca = ?, modelo = ? where produto_item = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setString(1, p.getValidade().format(fdata));
            ps.setString(2, p.getMarca());
            ps.setString(3, p.getModelo());
            ps.setInt(4, p.getId());
            ps.execute();
            ps.close();
            try {
                sql = "update item set descricao = ?, preco = ? where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1, p.getDescricao());
                ps.setFloat(2, p.getPreco());
                ps.setInt(3, p.getId());
                ps.execute();
                ps.close();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "Erro na operação de alterar produto: " + e.getMessage());
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de alterar produto: " + e.getMessage());
        }
    }

    public void excluirItem(int id) {
        String sql;
        PreparedStatement ps = null;

        sql = "update item set status = ? where id = ?";

        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, 1);
            ps.setInt(2, id);
            ps.execute();
            ps.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de excluir produto: " + e.getMessage());
        }
    }

    public ArrayList<Profissional> listaProfissionais() {
        ArrayList<Profissional> prs = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from profissional";
        try {
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                Profissional pr = new Profissional();
                pr.setEspecialidade(rs.getString("especialidade"));
                pr.setId(rs.getInt("id"));
                pr.setNome(rs.getString("nome"));
                prs.add(pr);
            }
            rs.close();
            ps.close();
            return prs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
        }

        return null;
    }

    private Profissional buscaProfissional(int id) {
        Profissional pr = new Profissional();
        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from profissional WHERE id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            if (rs.next()) {
                pr.setEspecialidade(rs.getString("especialidade"));
                pr.setId(rs.getInt("id"));
                pr.setNome(rs.getString("nome"));
            }
            rs.close();
            ps.close();
            return pr;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
        }
        return null;
    }

    private ArrayList<Profissional> listaProfissionaisServico(int id) {
        ArrayList<Profissional> prs = new ArrayList<>();

        PreparedStatement ps = null;
        ResultSet rs = null;

        String sql = "select * from servico_profissional WHERE id_servico = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            while (rs.next()) {
                prs.add(buscaProfissional(rs.getInt("id_profissional")));
            }
            rs.close();
            ps.close();
            return prs;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
        }

        return null;
    }

    private Servico buscarServico(Servico pr) {
        String sql;
        PreparedStatement ps = null;

        sql = "SELECT * FROM servico WHERE servico_item = ? ";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1, pr.getId());
            ResultSet rs = null;
            rs = ps.executeQuery();
            if (rs.next()) {
                pr.setEquipamentos(rs.getString("equipamentos"));
                pr.setProficionais(listaProfissionaisServico(pr.getId()));
                rs.close();
                ps.close();
                return pr;
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Erro na operação de incluir registro: " + e.getMessage());
        }
        return pr;
    }

}

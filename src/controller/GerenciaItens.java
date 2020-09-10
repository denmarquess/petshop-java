/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import static com.sun.org.apache.xalan.internal.lib.ExsltDatetime.date;
import java.time.LocalDate;
import java.util.ArrayList;
import model.Item;
import model.Produto;
import model.Profissional;
import model.Servico;
import modelDAO.ItensDAO;

public class GerenciaItens {
    ItensDAO itDao;

    public GerenciaItens() {
        itDao= new ItensDAO();
    }
    
   public void cadastrarProduto(Produto p){
       itDao.incluirItem(p);
    } 
   public void alterarProdutos(Produto p){
      itDao.alterar(p);
   }
   
   public ArrayList<Produto> listarProdutos(){
      ArrayList<Produto> prs = new ArrayList<>();
      for(Item i : itDao.listarItensAtivos(1)){
          Produto p  = (Produto)i;
          prs.add(p);
      }
      return prs;
   }
   public void excluirProduto(int id){
       itDao.excluirItem(id);
   }
   public boolean cadastrarServico(Servico  s){
       return itDao.incluirItem(s);
       
    } 
   public void alterarServico(Servico s){
      //itDao.alterar(s);
   }
   
   public ArrayList<Servico> listarServicos(){
      ArrayList<Servico> servicos = new ArrayList<>();
      
      for(Item i : itDao.listarItensAtivos(2)){
          Servico s = (Servico)i;
          servicos.add(s);
      }
      return servicos;
   }
   public void excluirServico(int id){
       itDao.excluirItem(id);
   }
   public int proximoCodigo(){
       return itDao.proximoCodigo();
   }
   
   
        
}

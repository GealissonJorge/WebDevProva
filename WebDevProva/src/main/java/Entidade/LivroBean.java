/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.inject.Named;
import javax.enterprise.context.Dependent;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;

/**
 *
 * @author Gealisson Jorge da Silva Oliveira
 */
@Named(value = "livroBean")
@SessionScoped
public class LivroBean implements Serializable{
    @Inject
    private LivroBeanServiceLocal livroservice;
    
    private Livro selectedLivro;
    private Long selectedLivroId;
    private List<Livro> allLivro;
    private List<Livro> filteredLivro;
    
    
    public LivroBean() {
    }
    
    @PostConstruct
    public void init(){
        if(selectedLivro==null){
            selectedLivro = new Livro();
        }
    }
    
    public Livro getSelectedLivro() {
        return selectedLivro;
    }

    public void setSelectedLivro(Livro selectedLivro) {
        this.selectedLivro = selectedLivro;
    }

    public Long getSelectedLivroId() {
        if (selectedLivroId== null || selectedLivro == null) {
            selectedLivro = new Livro();
        }
        return selectedLivroId;
    }

    public void setSelectedLivroId(Long selectedLivroId) {
        this.selectedLivroId = selectedLivroId;
    }

    public List<Livro> getAllLivro() {
        if(allLivro==null){
            reloadLivros();
        }
        return allLivro;
    }

    public void setAllLivro(List<Livro> allLivro) {
        this.allLivro = allLivro;
    }

    public List<Livro> getFilteredLivro() {
        return filteredLivro;
    }

    public void setFilteredLivro(List<Livro> filteredLivro) {
        this.filteredLivro = filteredLivro;
    }
    
    public String salvar(Livro livro){
        livroservice.Salvar(livro);
        reloadLivros();
        reset();
        return null;
    }
    public String salvarAtual(){
        salvar(selectedLivro);
        return "index?faces-redirect=true";
    }
    public List<Livro> findAll(){
        return livroservice.findAll();
    }
    public void reset(){
        selectedLivro = new Livro();
    }
    private void reloadLivros(){
        allLivro=findAll();
        System.out.println("Livros: " +allLivro);
    }
     public String MoverLixeira(Livro livro){
        livroservice.MoverLixeira(livro);
        reloadLivros();
        return null;
    }
    public String MoverAtualLixeira(){
        MoverLixeira(selectedLivro);
        return "index?faces-redirect=true";
    }
    public Livro loadLivro(Livro livro){
        if (livro != null) {
            Livro fullLivro = livroservice.LoadLivro(livro.getId());
            selectedLivro = fullLivro;
            return selectedLivro;
        } else {
            return null;
        }
        
    }
}

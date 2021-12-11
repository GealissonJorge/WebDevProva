/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Gealisson Jorge da Silva Oliveira
 */
@Stateless
public class LivroBeanService implements LivroBeanServiceLocal {
    @PersistenceContext
    EntityManager entityManager;

    @Override
    public void Salvar(Livro livro) {
        if (entityManager.contains(livro)) {
            System.out.println("LivroServiceBean::save[U].task => " + livro);
            entityManager.persist(livro);
        } else if (livro.getId() != null) {
            
            System.out.println("LivroServiceBean::save[U'].task => " + livro);
            entityManager.merge(livro);
        } else {
            System.out.println("LivroServiceBean::save[S].task => " + livro);
            entityManager.merge(livro);
    }
        
    }

    @Override
    public List<Livro> findAll() {
        return entityManager
                .createNamedQuery(
                        "livro.findAll",
                        Livro.class)
                .getResultList();
    }

    @Override
    public void MoverLixeira(Livro livro) {
        livro.setLixo(true);
        entityManager.merge(livro);
    }

    @Override
    public Livro LoadLivro(Long id) {
        return entityManager
                .createNamedQuery(
                        "livro.loadLivro",
                        Livro.class)
                .setParameter("id", id)
                .getSingleResult();
    }
    
    

}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entidade;

import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author PICHAU
 */
@Local
public interface LivroBeanServiceLocal {

    void Salvar(Livro livro);

    List<Livro> findAll();

    void MoverLixeira(Livro livro);

    Livro LoadLivro(Long id);
    
}

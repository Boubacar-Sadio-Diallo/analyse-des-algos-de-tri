/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controler;

/**
 *
 * @author mathet
 */
public interface ModeleEcoutable {
    void addModelListener(EcouteurModele l);
    void removeModelListener(EcouteurModele l);
}

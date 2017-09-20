/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arvorern;

/**
 *
 * @author Douglas
 */
public class No {
    public int chave;
    public boolean corRed;
    public No esq;
    public No dir;
    public No pai;

    public No(int chave,No externo,boolean cor,No pai) {
        this.chave = chave;
        this.corRed = cor;
        this.esq = externo;
        this.dir = externo;
        this.pai=pai;
    }
    public No() {
        this.corRed=false;
    }
       
    
}

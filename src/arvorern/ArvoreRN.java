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
public class ArvoreRN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Arvore novo=new Arvore();
        novo.inserir(50);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir(40);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir(60);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir(30);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir(45);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir(20);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir(35);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir(10);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        /*novo.inserir(55);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
       /* novo.inserir(32);
        novo.percorrerPreOrdem(novo.raiz);
        System.out.println("******************");
        novo.inserir(31);
       
        System.out.println("***FIM***");
        novo.percorrerPreOrdem(novo.raiz);
        
        /*int valor=15;
        System.out.println("******************");  
        System.out.println("REMOVER O "+valor);
        novo.remover(valor);
        System.out.println("******************");
        novo.percorrerPreOrdem(novo.raiz);
        
        System.out.println("******************");        
        int valor2=32;
        System.out.println("REMOVER O "+valor2);
        novo.remover(valor2);
        System.out.println("******************");
        novo.percorrerPreOrdem(novo.raiz);
        
        /*System.out.println("******************");        
        int valor3=30;
        System.out.println("REMOVER O "+valor3);
        novo.remover(valor3);
        System.out.println("******************");
        novo.percorrerPreOrdem(novo.raiz);
        
        System.out.println("******************");        
        int valor4=31;
        System.out.println("REMOVER O "+valor4);
        novo.remover(valor4);
        System.out.println("******************");
        novo.percorrerPreOrdem(novo.raiz);
        
        System.out.println("******************");        
        int valor5=32;
        System.out.println("REMOVER O "+valor5);
        novo.remover(valor5);
        System.out.println("******************");
        novo.percorrerPreOrdem(novo.raiz);*/
    }
    
}

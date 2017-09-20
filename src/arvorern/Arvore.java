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
public class Arvore {
    public No externo=new No();
    public No raiz;
    public No ptq;//FILHO DE V, NÓ CORRENTE NA BUSCA
    public No ptt;//FICA NO LADO OPOSTO (IRMÃO) DO PTV, UTILIZADO PARA O CASO 2.1 (TROCA DE CORES)
    public No aux;
    public int a;//CONTROLA O ACESSO A ROTA 
    public No x;//auxliar para o fixup
    public No w;
    
    //COR DO NÓ É TRUE = RUBRO E FALSE = NEGRO
    public Arvore(){
        this.raiz=this.externo;
    }
    
    //CÓDIGO ABAIXO DO INSERIR
    public void inserir(int chave){//MÉTODO PUBLIC QUE VOU CHAMAR NO MAIN, PARA CHAMAR O PRIVADO ABAIXO
        a=1;//NO LIVRO ELE PASSA 1 COMO PARAMÊTRO
        insRN(chave, this.raiz,externo,externo);
    }
    
    private void insRN(int chave, No ptv, No ptw, No ptr){
        if(ptv==externo){//CONDIÇÃO DE PARADA DA RECURSÃO (QUANDO CHEGAR NO NÓ VAZIO)
            ptv = new No(chave,this.externo,true,ptw);//CRIO O NÓ COM A NOVA CHAVE
            if(this.raiz==externo){//QUANDO NÃO EXISTE A RAIZ
                ptv.corRed=false;//SE FOR A RAIZ A COR SERÁ NEGRO, SENÃO SERÁ RED
                ptv.pai=new No();
                ptv.pai=externo;
                this.raiz=ptv;                
            }else{//QUANDO PERCORRO A ARVORE ATÉ A FOLHA OU EXTERNO
                if(chave<ptw.chave)
                    ptw.esq=ptv;//O PAI DO NOVO INSERIDO RECEBE ELE PARA O LADO ESQUERDO
                else{
                    ptw.dir=ptv;
                }
            }
        }else{
            if(chave!=ptv.chave){
                if(chave<ptv.chave){
                    insRN(chave,ptv.esq,ptv,ptw);
                    ptq=ptv.esq;
                }else{
                    insRN(chave,ptv.dir,ptv,ptw);
                    ptq=ptv.dir;
                }
              
                if(a==1){
                    //System.out.println("ROTA COM PTQ = "+ptq.chave+" PTV = "+ptv.chave);
                    rota(ptq,ptv,ptw,ptr);
                }
                else{
                    if(a==0)
                        a=1;
                }
            }else{
                System.out.println("ELEMENTO EXISTENTE");
            }
        }
    }
    
    public void rota(No ptq, No ptv, No ptw, No ptr){//PTV = PAI, PTW=AVÔ *ROTA DO INSERIR*
        a=2;
        System.out.println(ptq.chave);
        if(ptv.corRed){
            if(ptv==ptw.esq)
                ptt = ptw.dir;//ptt É O TIO DE ptq
            else ptt = ptw.esq;
            if((ptt!=externo)&&(ptt.corRed)){//CASO 2.1//ALTEREI AQUI 11/07 10:45 (null->externo)
                System.out.println("ENTROU NO 2.1 TROCA SÓ AS CORES");
                ptv.corRed=false;
                ptw.corRed=true;
                ptt.corRed=false;
                a=0;//UNICO CASO QUE PODE SER NECESSÁRIO ENTRAR NA ROTAÇÃO 
            }else{//CASO 2.2
                ptw.corRed=true;
                if(ptq==ptv.esq){
                    if(ptv==ptw.esq){//CASO 2.2.1
                        System.out.println("ENTROU NO 2.2.1 ROTAÇÃO SIMPLES A DIREITA");
                        //System.out.println("COM ptq = "+ptq.chave+" ptv = "+ptv.chave+" ptw= "+ptw.chave+" ptr = "+ptr.chave);
                        ptv.corRed=false;
                        ptw.esq=ptv.dir;
                        ptv.dir=ptw;
                        aux=ptv;
                        //ATUALIZANDO OS PAIS
                        ptv.pai=ptw.pai;
                        ptw.pai=ptv;
                    }else{//CASO 2.2.2
                        System.out.println("ENTROU NO 2.2.2 ROTAÇÃO DUPLA A ESQUERDA");
                        ptq.corRed=false;
                        ptw.dir=ptq.esq;
                        ptv.esq=ptq.dir;
                        ptq.esq=ptw;
                        ptq.dir=ptv;                         
                        aux=ptq;                        
                        //ATUALIZANDO OS PAIS
                        ptq.pai=ptw.pai;
                        ptv.pai=ptq;
                        ptw.pai=ptq;
                    }
                }else{
                    if(ptv==ptw.dir){//CASO 2.2.3
                        System.out.println("ENTROU NO 2.2.3 ROTAÇÃO SIMPLES A ESQUERDA");
                        ptv.corRed=false;
                        ptw.dir=ptv.esq;
                        ptv.esq=ptw;                        
                        aux=ptv;
                        //ATUALIZANDO OS PAIS
                        ptv.pai=ptw.pai;
                        ptw.pai=ptv;
                    }else{//CASO 2.2.4
                        System.out.println("ENTROU NO 2.2.4 ROTAÇÃO DUPLA A DIREITA");                        
                        //System.out.println("COM ptq = "+ptq.chave+" ptv = "+ptv.chave+" ptw= "+ptw.chave+" ptr = "+ptr.chave);
                        ptq.corRed=false;
                        ptw.esq=ptq.dir;
                        ptv.dir=ptq.esq;
                        ptq.esq=ptv;
                        ptq.dir=ptw;                        
                        aux=ptq;
                        //ATUALIZANDO OS PAIS
                        ptq.pai=ptw.pai;
                        ptv.pai=ptq;
                        ptw.pai=ptq;
                    }
                }
                if(ptr!=null){
                    if(ptq.chave<ptr.chave)
                        ptr.esq=aux;
                    else ptr.dir=aux;
                }else{
                    this.raiz=aux;
                }   
            }
        }
    this.raiz.corRed=false;
    }
    
    public void percorrerPreOrdem(No pt){
        if(pt==null) return;
        if(pt.pai==externo){
            if(pt.corRed)
                System.out.println("Chave: " + pt.chave+"  RUBRO (RAIZ)");
            else System.out.println("Chave: " + pt.chave+"  NEGRA (RAIZ)");
        }else{
            if(pt.corRed)
                System.out.println("Chave: " + pt.chave+"  RUBRO (FILHO DE "+pt.pai.chave+")");
            else System.out.println("Chave: " + pt.chave+"  NEGRA (FILHO DE "+pt.pai.chave+")");
        }
        if(pt.esq!=externo){
            percorrerPreOrdem(pt.esq);//CHAMO ESTE MÉTODO AGORA COM O ESQ COMO RAIZ E JÁ VOU IMPRIMINDO
        }
        if(pt.dir!=externo){
            percorrerPreOrdem(pt.dir);//CHAMO ESTE MÉTODO AGORA COM O DIR COMO RAIZ
        }
    }
    
    //TODO CÓDIGO ABAIXO É DO REMOVER
    private void moverPai(No u,No v){//u É O NO PARA ELIMINAR, v VAI PARA SEU LUGAR
        if(u.pai==externo){//ESTE MÉTODO VAI TROCAR OS LUGARES DE u PARA v
            this.raiz=v;
            //v.pai=externo;
        }else{
            if(u==u.pai.esq)//SE u É UM FILHO A ESQUERDA OU DIREITA AÍ TROCA
                u.pai.esq=v;
            else u.pai.dir=v;
        }
        if(v!=externo){//SÓ ATUALIZO O PAI DO v SE ELE NÃO FOR EXTERNO           
            v.pai=u.pai;
            //v.corRed=z.corRed;//ALTEREI AQUI 10/07 10:43
        }
        
    }
        
    public void remover(int chave){
        No b = busca(this.raiz,chave);
        if(b==null)System.out.println("CHAVE INEXISTENTE");
        else removerRN(b);
    }
    
    private void removerRN(No z){//z É O NO QUE DESEJA REMOVER
        No y=z;
        boolean corAux=y.corRed;
        if(z.esq==externo){
            x=z.dir;
            x.pai=z.pai;//AQUI ALTEREI
            moverPai(z,z.dir);
        }else{
            if(z.dir==externo){
                x=z.esq;
                x.pai=z.pai;//ALTEREI AQUI
                moverPai(z,z.esq);  
            }else{//QUANDO TEM DOIS FILHO
                y=RBminimo(z.dir);//PEGO O MINIMO MAIOR
                corAux=y.corRed;
                x=y.dir;  
                x.pai=y.pai;//ALTEREI AQUI (NÃO PRECISAVA)
                if(y.pai==z)//QUANDO O MINIMO É FILHO DE Z
                    x.pai=y;
                else{//QUANDO NÃO É FILHO DE Z, ELE PRECISA RECEBER O FILHO.DIR DO EXCLUÍDO
                    moverPai(y,y.dir);
                    y.dir=z.dir;//O SUCESSOR RECEBE O FILHO.DIR DO EXCLUÍDO
                  //if(y.dir!=externo)//IRRELEVANTE 
                    y.dir.pai=y;
                }
                //ATUALIZO O LADO ESQUERDO DO EXCLUÍDO PARA O SUCESSOR
                moverPai(z,y);
                y.esq=z.esq;
                y.esq.pai=y;
                y.corRed=z.corRed;
            }
        } 
        if(!corAux){
            rotaCor(x);
        }
    }
    
    private void rotaCor(No x){
        while((x!=this.raiz)&&(!x.corRed)){  
            if(x==x.pai.esq){//X É UM FILHO DO LADO ESQUERDO
                w=x.pai.dir;//W RECEBE O IRMAO DE X
                if(w.corRed){//CASO 1 QUANDO O IRMÃO DELE É VERMELHO, IRMAO SERÁ NEGRO E O SEU PAI RUBRO
                    System.out.println("CASO 1 ESQUERDA (REMOVER)");
                    w.corRed=false;
                    x.pai.corRed=true;
                    RotaEsq(x.pai);
                    w=x.pai.dir;//ATUALIZA w APÓS A ROTAÇÃO 
                }
                if((!w.esq.corRed)&&(!w.dir.corRed)){//CASO 2 QUANDO OS DOIS FILHOS DE w SÃO NEGROS, QUANDO NÃO DÁ PARA ROTACIONAR
                    System.out.println("CASO 2 ESQUERDA (REMOVER)");
                    w.corRed=true;//SETA COMO RUBRO POIS SEUS DOIS FILHOS JÁ SÃO NEGROS
                    x=x.pai;//SETA COMO SEU PAI, POIS PODE TER VIOLADO AS REGRAS COM O PAI
                }else{
                    if(!w.dir.corRed){//CASO 3 QUANDO w É NEGRO E SEU DIREITO NEGRO
                        System.out.println("CASO 3 ESQUERDA (REMOVER)");//ESTE CASO FARÁ DUAS ROTAÇÕES
                        w.esq.corRed=false;
                        w.corRed=true;
                        RotaDir(w);                        
                        w=x.pai.dir;
                    }//QUANDO O TIO TIVER 2 RUBROS
                    System.out.println("CASO 4 ESQUERDA (REMOVER)");
                    w.corRed=x.pai.corRed;//CASO 4
                    x.pai.corRed=false;                    
                    w.dir.corRed=false;//AQUI ESTÁ ESTRANHO
                    RotaEsq(x.pai);
                    x=this.raiz;
                }
            }else{//QUANDO X É DO LADO DIREITA DO TEU PAI
                    w=x.pai.esq;
                    if(w.corRed){//CASO 1 QUANDO O IRMÃO DELE É VERMELHO, IRMAO SERÁ NEGRO E O SEU PAI RUBRO
                        System.out.println("CASO 1 DIREITA (REMOVER)");
                        w.corRed=false;
                        x.pai.corRed=true;
                        RotaDir(x.pai);
                        w=x.pai.esq;
                    }
                    if((!w.dir.corRed)&&(!w.esq.corRed)){//CASO 2 QUANDO OS DOIS FILHOS DE w SÃO NEGROS, QUANDO NÃO DÁ PARA ROTACIONAR
                        System.out.println("CASO 2 DIREITA (REMOVER)");
                        w.corRed=true;
                        x=x.pai;
                    }else{
                        if(!w.esq.corRed){//CASO 3 QUANDO w É NEGRO E SEU DIREITO NEGRO
                            System.out.println("CASO 3 DIREITA (REMOVER)");
                            w.dir.corRed=false;
                            w.corRed=true;
                            RotaEsq(w);
                            w=x.pai.esq;
                        }//QUANDO SOBRINHO DE X FOREM RUBROS 
                        System.out.println("CASO 4 DIREITA (REMOVER)");
                        w.corRed=x.pai.corRed;
                        x.pai.corRed=false;
                        w.esq.corRed=false;//AQUI ESTÁ ESTRANHO
                        RotaDir(x.pai);
                        x=this.raiz;
                    }
                } 
        }
        x.corRed=false;
    }
    
    private void RotaEsq(No x){
        System.out.println("ROTA PARA ESQUERDA");
        No y=x.dir;
        x.dir=y.esq;
        if((y.esq!=externo)&&(y!=externo))
            y.esq.pai=x;
        y.pai=x.pai;
        if(x.pai==externo)
            this.raiz=y;
        else{
            if(x==x.pai.esq)
                x.pai.esq=y;
            else x.pai.dir=y;
        }
        y.esq=x;
        x.pai=y;    
    }
    
    private void RotaDir(No x){
        System.out.println("ROTA PARA DIREITA");
        No y=x.esq;
        x.esq=y.dir;
        if(y.dir!=externo)
            y.dir.pai=x;
        y.pai=x.pai;
        if(x.pai==externo)
            this.raiz=y;
        else{
            if(x==x.pai.dir)
                x.pai.dir=y;
            else x.pai.esq=y;
        }
        y.dir=x;
        x.pai=y;  
    }
    
    private No RBminimo(No pt){
        if(pt.esq==externo)return pt;
        else return RBminimo(pt.esq);
    }
    
    public No busca(No raiz,int chave){
        if(raiz==externo)return null;
        if(chave==raiz.chave){//CASO BASE, QUANDO A RAIZ FOR IGUAL A CHAVE, 
            return raiz;//EM DETERMINADO MOMENTO O DIR OU ESQ PASSA A SER RAIZ QUANDO CHAMO RECURSIVAMENTE
        }else{
            if(chave>raiz.chave){//SE A CHAVE QUE ESTÁ BUSCANDO FOR MAIOR ENTAO DEVO IR PARA A DIREITA
                return busca(raiz.dir,chave);//CHAMO RECURSIVAMENTE O BUSCA TENDO DIR COMO RAIZ, ATÉ Q SEJA O PROCURADO
            }else{
                return busca(raiz.esq,chave);
            }
        }        
    }
}

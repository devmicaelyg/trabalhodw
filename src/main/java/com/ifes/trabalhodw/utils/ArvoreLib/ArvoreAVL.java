package com.ifes.trabalhodw.utils.ArvoreLib;

import java.util.Comparator;

public class ArvoreAVL<T> extends ArvoreBinaria<T> {

    public ArvoreAVL(Comparator<T> comparator) {
        super(comparator);
    }
    
    //Implementar métodos para efetuar o balanceamento e sobrescrever método de adicionar elemento...

    @Override
    protected No<T> addRecursao(No<T> raiz, No<T> novo){
        raiz = super.addRecursao(raiz, novo);

        if(raiz.fatorBalanceamento() > 1){
            if(raiz.getFilhoDireita().fatorBalanceamento() > 0){
                raiz = this.rotacaoEsquerda(raiz);
            } else {
                raiz = this.rotacaoDireitaEsquerda(raiz);
            }
        } else if (raiz.fatorBalanceamento() < -1){
            if(raiz.getFilhoEsquerda().fatorBalanceamento() < 0){
                raiz = this.rotacaoDireita(raiz);
            } else {
                raiz = this.rotacaoEsquerdaDireita(raiz);
            }
        }

        return raiz;
    }

    private No<T> rotacaoEsquerda(No<T> r){
        No<T> f = r.getFilhoDireita();
        r.setFilhoDireita(f.getFilhoEsquerda());
        f.setFilhoEsquerda(r);

        // Atualização das alturas do nó e do nó filho (f)
        r.altura();
        f.altura();
        return f;
    }

    private No<T> rotacaoDireita(No<T> r){
        No<T> f = r.getFilhoEsquerda();
        r.setFilhoEsquerda(f.getFilhoDireita());
        f.setFilhoDireita(r);

        // Atualização das alturas do nó e do nó filho (f)
        r.altura();
        f.altura();
        return f;
    }

    private No<T> rotacaoDireitaEsquerda(No<T> r) {
        r.setFilhoDireita(rotacaoDireita(r.getFilhoDireita()));
        r = rotacaoEsquerda(r);
        // Atualização das alturas do nó e seus filhos
        r.altura();
        r.getFilhoDireita().altura();
        r.getFilhoEsquerda().altura();

        return r ;
    }


    private No<T> rotacaoEsquerdaDireita(No<T> r){
        r.setFilhoEsquerda(rotacaoEsquerda(r.getFilhoEsquerda()));
        r =rotacaoDireita(r);
        // Atualização das alturas do nó e seus filhos
        r.altura();
        r.getFilhoDireita().altura();
        r.getFilhoEsquerda().altura();

        return r ;
    }

}

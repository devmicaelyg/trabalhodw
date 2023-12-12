package com.ifes.trabalhodw.application;

import com.ifes.trabalhodw.application.LibGrafos.Grafo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GrafoDependecia<T> {

    public boolean possuiDependencia(T obj, List<T> dependecias) {
        Grafo<T> grafo = new Grafo<>();
        grafo.adicionarVertice(obj);

        for (T dependencia : dependecias) {
            grafo.adicionarVertice(dependencia);
            grafo.adicionarAresta(obj, dependencia, 1);
            }
        return grafo.temCiclo();
    }

    public List<T> recomendacao(T obj, List<T> dependecias) {
        Grafo<T> grafo = new Grafo<>();
        grafo.adicionarVertice(obj);

        for (T dependencia : dependecias) {
            grafo.adicionarVertice(dependencia);
            grafo.adicionarAresta(obj, dependencia, 1);
            }
        return grafo.ordenacaoTopologica();
    }
}

package com.ifes.trabalhodw.utils.LibGrafos;
import java.util.ArrayList;
import java.util.Stack;

public class Grafo<T> implements IGrafo<T> {
    private ArrayList<Vertice<T>> vertices;

    public Grafo() {
        this.vertices = new ArrayList<>();
    }

    // Adiciona um vértice ao grafo com o valor fornecido.
    public Vertice<T> adicionarVertice(T valor) {
        Vertice<T> novo = new Vertice<>(valor);
        this.vertices.add(novo);
        return novo;
    }

    // Obtém um vértice do grafo pelo valor.
    private Vertice<T> obterVertice(T valor) {
        for (Vertice<T> v : this.vertices) {
            if (v.getValor().equals(valor)) {
                return v;
            }
        }
        return null;
    }

    // Adiciona uma aresta entre os vértices de origem e destino, com o peso fornecido.
    // Cria os vértices se não existirem.
    public void adicionarAresta(T origem, T destino, float peso) {
        Vertice<T> verticeOrigem = obterVertice(origem);

        if (verticeOrigem == null) {
            verticeOrigem = adicionarVertice(origem);
        }

        Vertice<T> verticeDestino = obterVertice(destino);

        if (verticeDestino == null) {
            verticeDestino = adicionarVertice(destino);
        }

        verticeOrigem.adicionarDestino(new Aresta<>(verticeDestino, peso));
    }

    // Realiza uma busca em largura no grafo a partir do primeiro vértice adicionado.
    public void buscaEmLargura() {
        ArrayList<Vertice<T>> marcados = new ArrayList<>();
        ArrayList<Vertice<T>> fila = new ArrayList<>();

        //Pega o primeiro vértice como ponto de partida e o coloca na fila
        //Poderia escolher qualquer outro
        //Mas note que dependendo do grafo pode ser que vc não caminhe por todos os vertices
        Vertice<T> atual = this.vertices.get(0);
        fila.add(atual);
        System.out.println("Busca em largura a partir do vértice: " + atual.getValor());

        //Enquanto houver vertice na fila ...
        while (!fila.isEmpty()) {
            //Pego o proximo da fila, marco como visitado e o imprimo
            atual = fila.get(0);
            fila.remove(0);
            marcados.add(atual);
            System.out.println(atual.getValor());

            //Depois pego a lista de adjacencia do no e se o no adjacente ainda não tiver sido visitado, o coloco na
            // fila
            // Itera sobre os destinos do vértice atual
            for (Aresta<T> destino : atual.getDestinos()) {
                Vertice<T> proximo = destino.getDestino();

                if (!marcados.contains(proximo) && !fila.contains(proximo)) {
                    fila.add(proximo);
                }
            }
        }
    }

    // Verifica se o grafo possui ciclos.
    public boolean temCiclo() {
        ArrayList<Vertice<T>> visitados = new ArrayList<>();
        ArrayList<Vertice<T>> pilhaRecursao = new ArrayList<>();

        for (Vertice<T> vertice : vertices) {
            if (!visitados.contains(vertice) && temCicloUtil(vertice, visitados, pilhaRecursao)) {
                return true;
            }
        }

        return false;
    }

    // Função auxiliar para verificar ciclos durante a busca em profundidade (DFS).
    private boolean temCicloUtil(Vertice<T> vertice, ArrayList<Vertice<T>> visitados, ArrayList<Vertice<T>> pilhaRecursao) {
        visitados.add(vertice);
        pilhaRecursao.add(vertice);

        for (Aresta<T> aresta : vertice.getDestinos()) {
            Vertice<T> destino = aresta.getDestino();

            if (!visitados.contains(destino)) {
                if (temCicloUtil(destino, visitados, pilhaRecursao)) {
                    return true;
                }
            } else if (pilhaRecursao.contains(destino)) {
                return true;
            }
        }

        pilhaRecursao.remove(vertice);
        return false;
    }

    // Executa uma ordenação topológica do grafo.
    public ArrayList<T> ordenacaoTopologica() {
        ArrayList<Vertice<T>> resultado = new ArrayList<>();
        Stack<Vertice<T>> pilha = new Stack<>();
        ArrayList<Vertice<T>> visitados = new ArrayList<>();

        for (Vertice<T> vertice : vertices) {
            if (!visitados.contains(vertice)) {
                ordenacaoTopologicaUtil(vertice, visitados, pilha);
            }
        }

        // A pilha agora contém a ordenação topológica inversa
        while (!pilha.isEmpty()) {
            resultado.add(pilha.pop());
        }
        //Culpa de TPA Não Assumo
        ArrayList<T> resultadoT = new ArrayList<>();
        for (Vertice<T> vertice : resultado) {
            resultadoT.add(vertice.getValor());
        }
        return resultadoT;
    }

    // Função auxiliar para a ordenação topológica durante a busca em profundidade (DFS).
    private void ordenacaoTopologicaUtil(Vertice<T> vertice, ArrayList<Vertice<T>> visitados, Stack<Vertice<T>> pilha) {
        visitados.add(vertice);

        for (Aresta<T> aresta : vertice.getDestinos()) {
            Vertice<T> destino = aresta.getDestino();

            if (!visitados.contains(destino)) {
                ordenacaoTopologicaUtil(destino, visitados, pilha);
            }
        }

        pilha.push(vertice);
    }
}
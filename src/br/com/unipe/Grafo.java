package br.com.unipe;

import java.util.*;

public class Grafo {
    private final Map<String, Vertice> vertices;
    private final boolean direcionado;

    public static class Vertice {
        private final String nome;
        private final List<Aresta> arestas;

        public Vertice(String nome) {
            this.nome = nome;
            this.arestas = new ArrayList<>();
        }

        public String getNome() { return nome; }
        public List<Aresta> getArestas() { return arestas; }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof Vertice)) return false;
            Vertice vertice = (Vertice) o;
            return Objects.equals(nome, vertice.nome);
        }

        @Override
        public int hashCode() { return Objects.hash(nome); }
    }

    public static class Aresta {
        private final String nome;
        private final Vertice origem;
        private final Vertice destino;
        private final int peso;

        public Aresta(String nome, Vertice origem, Vertice destino, int peso) {
            this.nome = nome;
            this.origem = origem;
            this.destino = destino;
            this.peso = peso;
        }

        public Vertice getDestino() { return destino; }
        public int getPeso() { return peso; }
    }

    public Grafo(boolean direcionado) {
        this.vertices = new HashMap<>();
        this.direcionado = direcionado;
    }

    public void adicionaVertice(String nome) {
        vertices.putIfAbsent(nome.toLowerCase(), new Vertice(nome));
    }

    public void adicionaAresta(String nomeAresta, String nomeOrigem, String nomeDestino, int peso) {
        Vertice o = vertices.get(nomeOrigem.toLowerCase());
        Vertice d = vertices.get(nomeDestino.toLowerCase());
        if (o == null || d == null) return;

        o.getArestas().add(new Aresta(nomeAresta, o, d, peso));
        if (!direcionado) {
            d.getArestas().add(new Aresta(nomeAresta, d, o, peso));
        }
    }

    public Collection<Vertice> getVertices() {
        return vertices.values();
    }

    public Optional<Vertice> encontraVertice(String nome) {
        return Optional.ofNullable(vertices.get(nome.toLowerCase()));
    }

    /**
     * Algoritmo clássico de Dijkstra para caminhos mínimos baseados em PESO (Afinidade)
     */
    public ResultadoDijkstra dijkstra(String nomeOrigem, String nomeDestino) {
        Vertice origem = vertices.get(nomeOrigem.toLowerCase());
        Vertice destino = vertices.get(nomeDestino.toLowerCase());

        if (origem == null || destino == null) {
            return new ResultadoDijkstra(-1, Collections.emptyList());
        }

        Map<Vertice, Integer> distancias = new HashMap<>();
        Map<Vertice, Vertice> antecessores = new HashMap<>();
        PriorityQueue<NoDijkstra> fila = new PriorityQueue<>();

        for (Vertice v : vertices.values()) {
            distancias.put(v, Integer.MAX_VALUE);
        }

        distancias.put(origem, 0);
        fila.add(new NoDijkstra(origem, 0));

        while (!fila.isEmpty()) {
            NoDijkstra atual = fila.poll();
            Vertice u = atual.vertice;

            if (u.equals(destino)) break;

            if (atual.distancia > distancias.get(u)) continue;

            for (Aresta aresta : u.getArestas()) {
                Vertice v = aresta.getDestino();
                int novaDistancia = distancias.get(u) + aresta.getPeso();

                if (novaDistancia < distancias.get(v)) {
                    distancias.put(v, novaDistancia);
                    antecessores.put(v, u);
                    fila.add(new NoDijkstra(v, novaDistancia));
                }
            }
        }

        int custoTotal = distancias.get(destino);
        if (custoTotal == Integer.MAX_VALUE) {
            return new ResultadoDijkstra(-1, Collections.emptyList());
        }

        List<String> caminho = new ArrayList<>();
        Vertice atual = destino;
        while (atual != null) {
            caminho.add(0, atual.getNome());
            atual = antecessores.get(atual);
        }

        return new ResultadoDijkstra(custoTotal, caminho);
    }

    private static class NoDijkstra implements Comparable<NoDijkstra> {
        Vertice vertice;
        int distancia;

        NoDijkstra(Vertice vertice, int distancia) {
            this.vertice = vertice;
            this.distancia = distancia;
        }

        @Override
        public int compareTo(NoDijkstra outro) {
            return Integer.compare(this.distancia, outro.distancia);
        }
    }

    public static class ResultadoDijkstra {
        private final int custo;
        private final List<String> caminho;

        public ResultadoDijkstra(int custo, List<String> caminho) {
            this.custo = custo;
            this.caminho = caminho;
        }

        public int getCusto() { return custo; }
        public List<String> getCaminho() { return caminho; }
    }
}
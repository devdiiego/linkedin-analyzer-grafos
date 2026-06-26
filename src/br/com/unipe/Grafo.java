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
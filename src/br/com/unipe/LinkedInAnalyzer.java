package br.com.unipe;

import java.util.*;

public class LinkedInAnalyzer {
    private final Grafo grafo;

    // Missão 1: Construtor
    public LinkedInAnalyzer(Grafo grafo) {
        this.grafo = grafo;
    }

    // Missão 2: Sugestão de Conexões
    public List<Map.Entry<String, Integer>> sugerirConexoes(String nomePessoa) {
        return Collections.emptyList();
    }

    // Missão 3: Grau de Separação
    public int calcularGrauSeparacao(String nomeOrigem, String nomeDestino) {
        return -1;
    }

    // Missão 4: Rota de Maior Afinidade
    public Grafo.ResultadoDijkstra obterRotaMaiorAfinidade(String nomeOrigem, String nomeDestino) {
        return grafo.dijkstra(nomeOrigem, nomeDestino);
    }

    // Missão 5: Mapear Sub-redes
    public List<List<String>> mapearSubRedes() {
        return Collections.emptyList();
    }
}
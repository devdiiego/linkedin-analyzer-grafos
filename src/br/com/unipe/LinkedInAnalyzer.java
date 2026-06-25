package br.com.unipe;

import java.util.*;
import br.com.unipe.Grafo.Vertice;
import br.com.unipe.Grafo.Aresta;

public class LinkedInAnalyzer {
    private final Grafo grafo;

    /// Missão 1: Construtor principal
    public LinkedInAnalyzer(Grafo grafo) {
        this.grafo = grafo;
    }

    // Missão 2: Sugestão de Conexões (Diego.F)
    public List<Map.Entry<String, Integer>> sugerirConexoes(String nomePessoa) {
        Optional<Vertice> alvoOpt = grafo.encontraVertice(nomePessoa);
        if (alvoOpt.isEmpty()) return Collections.emptyList();

        Vertice alvo = alvoOpt.get();
        Set<Vertice> contatosDiretos = new HashSet<>();
        for (Aresta a : alvo.getArestas()) {
            contatosDiretos.add(a.getDestino());
        }

        Map<Vertice, Integer> contagemAmigosEmComum = new HashMap<>();

        for (Vertice amigo : contatosDiretos) {
            for (Aresta a : amigo.getArestas()) {
                Vertice amigoDoAmigo = a.getDestino();

                if (!amigoDoAmigo.equals(alvo) && !contatosDiretos.contains(amigoDoAmigo)) {
                    contagemAmigosEmComum.put(amigoDoAmigo, contagemAmigosEmComum.getOrDefault(amigoDoAmigo, 0) + 1);
                }
            }
        }

        List<Map.Entry<String, Integer>> resultado = new ArrayList<>();
        for (Map.Entry<Vertice, Integer> entry : contagemAmigosEmComum.entrySet()) {
            resultado.add(new AbstractMap.SimpleEntry<>(entry.getKey().getNome(), entry.getValue()));
        }

        resultado.sort((e1, e2) -> e2.getValue().compareTo(e1.getValue()));

        return resultado;
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
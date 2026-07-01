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
        Optional<Vertice> origemOpt = grafo.encontraVertice(nomeOrigem);
        Optional<Vertice> destinoOpt = grafo.encontraVertice(nomeDestino);

        if (origemOpt.isEmpty() || destinoOpt.isEmpty()) return -1;

        Vertice origem = origemOpt.get();
        Vertice destino = destinoOpt.get();

        if (origem.equals(destino)) return 0;

        Queue<Vertice> fila = new LinkedList<>();
        Map<Vertice, Integer> passos = new HashMap<>();

        fila.add(origem);
        passos.put(origem, 0);

        while (!fila.isEmpty()) {
            Vertice atual = fila.poll();
            int passosAtuais = passos.get(atual);

            if (atual.equals(destino)) return passosAtuais;

            for (Aresta a : atual.getArestas()) {
                Vertice vizinho = a.getDestino();
                if (!passos.containsKey(vizinho)) {
                    passos.put(vizinho, passosAtuais + 1);
                    fila.add(vizinho);
                }
            }
        }

        return -1; // Caso sejam perfis totalmente isolados
    }

    // Missão 4: Rota de Maior Afinidade
        public Grafo.ResultadoDijkstra obterRotaMaiorAfinidade(String nomeOrigem, String nomeDestino) {
                return grafo.dijkstra(nomeOrigem, nomeDestino);
            }

    // Missão 5: Mapear Sub-redes
        public List<List<String>> mapearSubRedes() {
                List<List<String>> subRedes = new ArrayList<>();
                Set<Vertice> visitados = new HashSet<>();
        
                for (Vertice v : grafo.getVertices()) {
                    if (!visitados.contains(v)) {
                        List<String> componente = new ArrayList<>();
                        dfsMapeamento(v, visitados, componente);
                        Collections.sort(componente); // Ordenação apenas visual para o console
                        subRedes.add(componente);
                    }
                }
                return subRedes;
            }
        
            private void dfsMapeamento(Vertice atual, Set<Vertice> visitados, List<String> componente) {
                visitados.add(atual);
                componente.add(atual.getNome());
        
                for (Aresta a : atual.getArestas()) {
                    Vertice vizinho = a.getDestino();
                    if (!visitados.contains(vizinho)) {
                        dfsMapeamento(vizinho, visitados, componente);
                    }
                }
            }
        }

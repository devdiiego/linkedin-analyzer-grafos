package br.com.unipe;

import java.util.List;
import java.util.Map;

public class LinkedInApp {
    public static void main(String[] args) {
        // Inicializa rede como Não-Direcionada e Ponderada
        Grafo redeSocial = new Grafo(false);

        // 1. Cadastrando as Pessoas (Vértices)
        redeSocial.adicionaVertice("Ana");
        redeSocial.adicionaVertice("Bruno");
        redeSocial.adicionaVertice("Carlos");
        redeSocial.adicionaVertice("Daniela");
        redeSocial.adicionaVertice("Eduardo");
        redeSocial.adicionaVertice("Fernanda");

        // Grupo Isolado 1
        redeSocial.adicionaVertice("Gabriel");
        redeSocial.adicionaVertice("Hugo");

        // Grupo Isolado 2
        redeSocial.adicionaVertice("Igor");
        redeSocial.adicionaVertice("Juliana");

        // 2. Criando conexões com Afinidades (Pesos) conforme enunciado
        redeSocial.adicionaAresta("C1", "Ana", "Bruno", 1);
        redeSocial.adicionaAresta("C2", "Ana", "Carlos", 2);
        redeSocial.adicionaAresta("C3", "Ana", "Daniela", 8);
        redeSocial.adicionaAresta("C4", "Bruno", "Eduardo", 1);
        redeSocial.adicionaAresta("C5", "Carlos", "Eduardo", 1);
        redeSocial.adicionaAresta("C6", "Daniela", "Fernanda", 5);
        redeSocial.adicionaAresta("C7", "Eduardo", "Fernanda", 1);

        // Conexões dos grupos isolados
        redeSocial.adicionaAresta("C8", "Gabriel", "Hugo", 1);
        redeSocial.adicionaAresta("C9", "Igor", "Juliana", 1);

        // Instancia o analisador injetando o grafo
        LinkedInAnalyzer analyzer = new LinkedInAnalyzer(redeSocial);

        System.out.println("===================================================================");
        System.out.println("            EXECUÇÃO DO MOTOR DE ANÁLISES LINKEDIN                 ");
        System.out.println("===================================================================\n");

        // TESTE DA MISSÃO 2: Sugestão de Conexões para Ana
        System.out.println("--- [MISSÃO 2: Sugestão de Amigos de 2º Grau para Ana] ---");
        List<Map.Entry<String, Integer>> sugestoes = analyzer.sugerirConexoes("Ana");
        if (sugestoes.isEmpty()) {
            System.out.println("Nenhuma sugestão encontrada.");
        } else {
            for (Map.Entry<String, Integer> sug : sugestoes) {
                System.out.println("Perfil sugerido: " + sug.getKey() + " | Amigos em comum: " + sug.getValue());
            }
        }
        System.out.println();

        // TESTE DA MISSÃO 3: Grau de Separação (Contagem de passos/saltos)
        System.out.println("--- [MISSÃO 3: Grau de Separação - Quantos passos?] ---");
        int passosCurto = analyzer.calcularGrauSeparacao("Ana", "Fernanda");
        int passosIsolado = analyzer.calcularGrauSeparacao("Ana", "Gabriel");
        System.out.println("Passos de Ana para Fernanda (Caminho mais curto em saltos): " + passosCurto);
        System.out.println("Passos de Ana para Gabriel (Perfis isolados): " + passosIsolado);
        System.out.println();

        // TESTE DA MISSÃO 4: Rota de Maior Afinidade (Menor Peso Acumulado)
        System.out.println("--- [MISSÃO 4: Rota e Custo de Maior Afinidade] ---");
        Grafo.ResultadoDijkstra rotaAfinidade = analyzer.obterRotaMaiorAfinidade("Ana", "Fernanda");
        System.out.println("Melhor rota por afinidade: " + String.join(" -> ", rotaAfinidade.getCaminho()));
        System.out.println("Custo total da afinidade acumulada (Dijkstra): " + rotaAfinidade.getCusto());
        System.out.println();

        // TESTE DA MISSÃO 5: Mapear Sub-redes Isoladas
        System.out.println("--- [MISSÃO 5: Mapeamento de Componentes/Grupos Isolados] ---");
        List<List<String>> subRedes = analyzer.mapearSubRedes();
        for (int i = 0; i < subRedes.size(); i++) {
            System.out.println("Sub-rede #" + (i + 1) + ": " + subRedes.get(i));
        }
        System.out.println("\n===================================================================");
    }
}
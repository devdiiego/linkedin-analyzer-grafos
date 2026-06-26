# 🚀 LinkedIn Analyzer

Um motor de análises e recomendações para redes profissionais desenvolvido em Java. O projeto utiliza a estrutura de dados em **Grafos (Não-Direcionados e Ponderados)** para mapear usuários e suas afinidades.

## ⚙️ Funcionalidades

A classe `LinkedInAnalyzer` atua como o motor de análises da rede, implementando as seguintes operações:

* **Sugestão de Conexões:** Recomenda contatos de 2º grau (amigos em comum).
* **Grau de Separação:** Calcula a distância em "passos" entre dois perfis.
* **Rota de Maior Afinidade:** Encontra o melhor caminho baseado no peso das conexões utilizando o Algoritmo de Dijkstra.
* **Mapeamento de Sub-redes:** Identifica componentes conexos (grupos isolados) dentro da rede.

## 🛠️ Tecnologias
* **Java** (JDK)

## 🚀 Como Executar

1. Clone este repositório.
2. Navegue até a raiz do projeto e compile os arquivos do pacote `br.com.unipe`:
   ```bash
   javac br/com/unipe/*.java
   ```
3. Execute a classe principal `LinkedInApp` para rodar o cenário de testes integrado:
   ```bash
   java br.com.unipe.LinkedInApp
   ```

## 👥 Equipe
* **[Diego Feitosa]**
* **[Diego Braga]** 
* **[Lucas]**
* **[Kleyton]**

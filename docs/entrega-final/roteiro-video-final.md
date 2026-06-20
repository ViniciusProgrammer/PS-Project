# Roteiro do Vídeo Final — 12 minutos

**Projeto:** Plataforma de Fotografia e Videografia Esportiva  
**Objetivo do vídeo:** demonstrar o MVP e explicar criticamente a evolução do processo da equipe, conectando Kanban, VSM, DORA, decisões tomadas e lições aprendidas.

---

## Estrutura recomendada

| Tempo | Bloco | Conteúdo | Responsável sugerido |
|---:|---|---|---|
| 0:00–1:00 | Introdução | Nome da equipe, disciplina, problema e objetivo do produto | Pessoa 1 |
| 1:00–3:30 | Demonstração do MVP | Mostrar backend rodando, autenticação, fotos/eventos, pedidos ou endpoints demonstráveis | Pessoa 2 |
| 3:30–4:30 | Evolução Sprint 1 → Sprint 3 | Explicar o que foi planejado, o que evoluiu e o que ficou limitado | Pessoa 1 |
| 4:30–6:00 | Kanban final | Mostrar quadro GitHub Projects e explicar fluxo real das tarefas | Pessoa 3 |
| 6:00–7:30 | VSM atual | Apresentar pipeline atual e gargalos | Pessoa 3 |
| 7:30–9:00 | Métricas DORA | Explicar frequência de deploy, lead time, taxa de falha e MTTR | Pessoa 2 |
| 9:00–10:30 | Decisões e impactos | CI/CD, Docker, Flyway, Definition of Done e ajustes no processo | Pessoa 1 |
| 10:30–11:30 | Lições aprendidas | O que funcionou, o que falhou e o que seria feito diferente | Todos |
| 11:30–12:00 | Conclusão | Reforçar MVP, proposta de melhoria e encerramento | Pessoa 1 |

---

## Fala sugerida por bloco

### 0:00–1:00 — Introdução

“Olá, somos a equipe formada por Thereza Angélica, Vinicius Davi e Lucas de Oliveira. Nosso projeto da disciplina de Processos de Software é uma plataforma de fotografia e videografia esportiva. O problema identificado é que empresas desse segmento precisam de um canal próprio para organizar, divulgar e vender fotos de eventos esportivos. A solução proposta é um sistema web com autenticação, organização de eventos, publicação de fotos, carrinho/pedidos e controle administrativo.”

**Mostrar na tela:** README do projeto, nome do repositório e link do Kanban.

---

### 1:00–3:30 — Demonstração do MVP

“Agora vamos demonstrar o incremento funcional do MVP. O backend foi desenvolvido com Spring Boot, Java, MySQL, Flyway, Spring Security com JWT e Docker. O projeto possui autenticação de usuários, estrutura de fotos vinculadas a eventos, pedidos e versionamento do banco com migrations.”

**Mostrar na tela:**
- Estrutura do projeto no VS Code.
- `backend/src/main/java/com/ps/backend/controller/AuthController.java`.
- `backend/src/main/java/com/ps/backend/controller/FotoController.java`.
- `backend/src/main/java/com/ps/backend/controller/PedidoController.java`.
- `backend/src/main/resources/db/migration`.
- Execução local ou prints de endpoints no Postman/Insomnia.

**Fala honesta recomendada:**
“Algumas funcionalidades planejadas originalmente, como carrinho completo, painel admin e reconhecimento facial, ficaram como limitações do MVP. Por isso, na análise de processo, tratamos essas pendências como evidências de gargalos e pontos de melhoria.”

---

### 3:30–4:30 — Evolução Sprint 1 → Sprint 3

“Na Sprint 1, o foco foi configurar o ambiente, criar a base Spring Boot, banco MySQL, Flyway e autenticação. A retrospectiva registrou que a configuração inicial funcionou bem, mas também apontou problemas como permissões no GitHub Projects, ausência de daily formalizada e conflito de branches. Na Sprint 2, o foco avançou para eventos, fotos e modelagem. Na Sprint 3, a equipe concentrou esforços em pedidos, carrinho, CI/CD e análise do processo.”

**Mostrar na tela:**
- `backend/etapas.md`.
- Commits ou histórico do GitHub.
- Quadro Kanban com tarefas concluídas e pendentes.

---

### 4:30–6:00 — Kanban final

“O Kanban foi usado para visualizar o fluxo do trabalho. No estado final, ele deve mostrar o que foi concluído, o que ficou em andamento e o que não entrou no MVP. A principal lição é que o quadro precisa refletir o fluxo real da equipe, não apenas o planejamento ideal.”

**Explicar:**
- Tarefas concluídas: base do backend, autenticação, migrations, CI/CD, Docker, parte de fotos e pedidos.
- Tarefas em andamento/pendentes: carrinho completo, admin, reconhecimento facial, frontend completo.
- Problema observado: tarefas grandes demais aumentaram lead time.

---

### 6:00–7:30 — VSM atual

“O VSM atual mostra o caminho de uma ideia até uma entrega demonstrável: requisito, backlog, seleção para sprint, desenvolvimento local, push, CI/CD, build Docker, teste manual e entrega. Os principais gargalos identificados foram validação tardia, tarefas grandes, documentação desalinhada e baixa cobertura de testes.”

**Mostrar na tela:**
- Seção de VSM em `docs/entrega-final/proposta-melhoria-processo.md`.
- Fluxo atual e gargalos.

---

### 7:30–9:00 — Métricas DORA

“As métricas DORA foram usadas para avaliar velocidade e estabilidade do processo. A frequência de deploy começou baixa porque as entregas eram concentradas. O lead time ficou alto em funcionalidades maiores. A taxa de falha foi impactada por problemas de integração e conflitos. O MTTR ainda não foi medido de forma ideal, mas o uso do GitHub Actions ajuda a detectar falhas mais rapidamente.”

**Mostrar na tela:**
- `.github/workflows/ci-cd.yml`.
- `.github/workflows/dora-metrics.yml`.
- Aba Actions do GitHub com execuções.

**Fala importante:**
“Mesmo sem deploy em produção completo, usamos as execuções de CI/CD como proxy de entrega integrada, pois elas representam a validação automatizada da main.”

---

### 9:00–10:30 — Decisões de processo e impactos

“Algumas decisões melhoraram o processo: usar Flyway para banco versionado, Docker para padronizar execução, GitHub Actions para build e testes, e organizar o backend em camadas. Porém, a equipe percebeu que precisava de uma Definition of Done mais objetiva e de histórias menores.”

**Conectar com proposta:**
- Criar DoD formal.
- Limitar WIP.
- Quebrar histórias grandes.
- Atualizar documentação conforme implementação real.
- Criar testes para fluxos críticos.

---

### 10:30–11:30 — Lições aprendidas

“Como lições aprendidas, percebemos que processo não é apenas ter quadro Kanban ou sprints. É necessário medir, revisar e ajustar continuamente. Também aprendemos que prometer funcionalidades grandes sem quebrar em partes menores aumenta o risco de terminar a sprint com trabalho parcialmente concluído.”

**Cada integrante pode falar uma frase:**
- “Aprendi que CI/CD ajuda a encontrar erros cedo.”
- “Aprendi que documentação precisa acompanhar o estado real do produto.”
- “Aprendi que retrospectivas precisam gerar ações concretas.”

---

### 11:30–12:00 — Conclusão

“Concluímos o projeto com um backend funcional parcial, documentação consolidada e uma análise crítica do processo. A principal proposta de melhoria é tornar o fluxo mais incremental, com WIP limit, Definition of Done, histórias menores, mais testes e acompanhamento das métricas DORA. Isso reduz desperdícios, melhora previsibilidade e aumenta a qualidade das próximas entregas.”

---

## Checklist de gravação

- [ ] Mostrar repositório no GitHub.
- [ ] Mostrar quadro Kanban final.
- [ ] Mostrar backend/projeto no VS Code.
- [ ] Demonstrar endpoints ou execução local.
- [ ] Mostrar CI/CD no GitHub Actions.
- [ ] Mostrar VSM atual.
- [ ] Mostrar métricas DORA ou workflow de coleta.
- [ ] Explicar gargalos e desperdícios Lean.
- [ ] Apresentar proposta de melhoria.
- [ ] Encerrar em até 12 minutos.

---

## Observação para apresentação

Evitem afirmar que funcionalidades incompletas estão prontas. O mais seguro para avaliação é demonstrar o que funciona e usar o que ficou incompleto como base para a análise crítica do processo, mostrando maturidade, transparência e conexão com os conceitos da disciplina.

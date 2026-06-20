# Proposta de Melhoria de Processo — Entrega Final

**Projeto:** Plataforma de Fotografia e Videografia Esportiva  
**Disciplina:** Processos de Software (DIM0510)  
**Equipe:** Thereza Angélica, Vinicius Davi, Lucas de Oliveira  
**Período analisado:** Sprint 1 até Sprint 3  
**Repositório:** https://github.com/itstherall/PS-Project.git  
**Kanban:** https://github.com/users/itstherall/projects/3

---

## 1. Contexto do Projeto

O projeto consiste em uma plataforma web para venda e distribuição de fotografias de eventos esportivos. A proposta do produto é permitir que uma empresa de fotografia organize eventos, publique fotos, permita autenticação de usuários, disponibilize fotos com marca d'água antes da compra e gere pedidos para aquisição das imagens.

Ao longo do semestre, a equipe trabalhou com práticas inspiradas em Scrum e Kanban, utilizando backlog, quadro de tarefas, controle de versão com GitHub, versionamento de banco com Flyway, backend em Spring Boot, banco MySQL, Docker e workflows de CI/CD com GitHub Actions.

Nesta entrega final, o foco principal não é apenas mostrar o produto, mas analisar criticamente o processo vivido pela equipe, identificando gargalos, desperdícios, métricas, aprendizados e melhorias possíveis para um próximo ciclo de desenvolvimento.

---

## 2. VSM — Estado Atual do Pipeline de Entrega

O Value Stream Map abaixo representa o fluxo real aproximado usado pela equipe para transformar uma necessidade do projeto em incremento demonstrável.

```text
Ideia / requisito
   ↓
Backlog no GitHub Projects
   ↓
Seleção para Sprint
   ↓
Desenvolvimento local
   ↓
Commit e push para GitHub
   ↓
Pull Request / merge na main
   ↓
GitHub Actions executa build e testes
   ↓
Build Docker do backend
   ↓
Teste manual / demonstração
   ↓
Entrega registrada no SIGAA
```

### 2.1 Tempos estimados do fluxo atual

| Etapa | Tempo ativo estimado | Tempo de espera estimado | Observação |
|---|---:|---:|---|
| Entendimento do requisito | 1h–2h | 1 dia | Algumas tarefas ficaram amplas demais |
| Planejamento no Kanban | 30min | 1 dia | Uso do quadro ajudou, mas nem sempre refletiu o fluxo real |
| Desenvolvimento local | 4h–12h | 1–3 dias | Dependia da disponibilidade dos membros |
| Integração com GitHub | 30min–1h | 0–1 dia | Houve conflito de branches na Sprint 1 |
| CI/CD | 5–10min | 0h | Build e testes automatizados existem |
| Teste manual | 1h–2h | 1–2 dias | Validação manual ficou concentrada no fim |
| Registro/documentação | 1h–3h | 1–2 dias | Documentação ficou parcialmente desalinhada do código |

### 2.2 Gargalos identificados

1. **Tarefas grandes demais no backlog:** funcionalidades como carrinho, pedidos, painel admin e reconhecimento facial foram planejadas em alto nível, dificultando a conclusão incremental.
2. **Baixa formalização das cerimônias:** a retrospectiva da Sprint 1 existe, mas faltam registros equivalentes para Sprint 2 e Sprint 3.
3. **Documentação maior que a implementação real:** o README descreve funcionalidades que não estão completamente implementadas no código atual.
4. **Validação tardia:** testes automatizados existem, mas ainda são poucos e não cobrem os principais fluxos do MVP.
5. **Dependência de testes manuais:** parte da verificação do MVP depende de execução local e demonstração manual.

---

## 3. Métricas DORA

As métricas DORA foram usadas para refletir sobre a capacidade da equipe de entregar software com velocidade e estabilidade. O projeto já possui workflow específico para coleta de métricas, além de CI/CD para build, testes e Docker.

### 3.1 Momento 1 — Início/meio do projeto

| Métrica | Situação observada | Interpretação |
|---|---|---|
| Frequência de deploy | Baixa | O projeto ainda estava em estruturação e as entregas eram concentradas |
| Lead time for changes | Médio/alto | Tarefas demoravam para sair do desenvolvimento e virar incremento integrado |
| Change failure rate | Médio | Conflitos de branch e ajustes de integração aumentaram risco de falha |
| MTTR | Não medido formalmente | Correções eram feitas manualmente, sem processo explícito de recuperação |

### 3.2 Momento 2 — Final da Sprint 3

| Métrica | Situação observada | Interpretação |
|---|---|---|
| Frequência de deploy | Melhorou parcialmente | O CI/CD permite validar a main com mais frequência |
| Lead time for changes | Ainda alto em funcionalidades grandes | Fluxos como carrinho/admin continuaram com partes incompletas |
| Change failure rate | Tendência de redução se CI passar consistentemente | Build e testes reduzem risco, mas baixa cobertura limita confiança |
| MTTR | Parcialmente melhorado | GitHub Actions facilita encontrar falhas rapidamente, mas ainda falta política clara de correção |

### 3.3 Reflexão sobre as métricas

A equipe evoluiu ao adicionar automação de build, testes e Docker, o que melhora a confiabilidade do pipeline. Porém, as métricas mostram que a velocidade de entrega ainda é limitada por tarefas grandes, pouca quebra de histórias, baixa cobertura de testes e documentação de processo incompleta.

Para melhorar o desempenho DORA, o próximo ciclo deve priorizar histórias menores, integração contínua mais frequente, testes automatizados para fluxos críticos e definição clara de quando uma tarefa está realmente pronta.

---

## 4. Análise de Problemas

### 4.1 Desperdícios Lean identificados

| Desperdício Lean | Como apareceu no projeto | Impacto |
|---|---|---|
| Espera | Tarefas aguardavam disponibilidade dos membros | Aumentou lead time |
| Trabalho parcialmente concluído | Carrinho, admin e eventos ficaram incompletos | Reduziu demonstrabilidade do MVP |
| Defeitos/retrabalho | Conflitos de branch e divergência documentação/código | Consumiu tempo de correção |
| Estoque de tarefas | Backlog com funcionalidades grandes demais | Dificultou priorização |
| Movimento/troca de contexto | Equipe alternou entre produto, documentação e integração | Reduziu foco por sprint |
| Superprocessamento | README documentou recursos além do implementado | Criou risco de avaliação negativa por inconsistência |

### 4.2 Problema escolhido para causa raiz

**Problema:** algumas funcionalidades planejadas para o MVP ficaram incompletas ou não demonstráveis até a Sprint 3.

### 4.3 5 Porquês

1. **Por que funcionalidades ficaram incompletas?**  
   Porque tarefas como carrinho, admin e eventos foram planejadas de forma ampla e não terminaram dentro da sprint.

2. **Por que as tarefas ficaram amplas demais?**  
   Porque o backlog foi escrito por módulo, não por fatias pequenas de valor demonstrável.

3. **Por que o backlog não foi quebrado em fatias menores?**  
   Porque a equipe priorizou implementar a base técnica e não formalizou critérios de aceite detalhados para cada história.

4. **Por que os critérios de aceite não foram formalizados?**  
   Porque a Definition of Done não estava suficientemente objetiva para exigir testes, endpoint funcional, evidência e documentação atualizada.

5. **Por que a Definition of Done não estava objetiva?**  
   Porque o processo ainda estava amadurecendo e a equipe registrou poucas retrospectivas para corrigir esse problema mais cedo.

**Causa raiz:** ausência de uma Definition of Done objetiva combinada com histórias grandes demais, gerando trabalho parcialmente concluído e dificuldade de demonstrar valor ao final da sprint.

---

## 5. VSM — Estado Futuro Proposto

```text
Ideia / necessidade
   ↓
História pequena com critério de aceite
   ↓
Priorização no Kanban com WIP limit
   ↓
Desenvolvimento em branch curta
   ↓
Teste unitário ou teste de API
   ↓
Pull Request com checklist de DoD
   ↓
CI executa build, testes e Docker
   ↓
Merge na main
   ↓
Demonstração registrada com evidência
   ↓
Retrospectiva com ação mensurável
```

### 5.1 Mudanças principais no estado futuro

- Quebrar funcionalidades por fatias verticais pequenas, por exemplo: “criar evento”, “listar eventos”, “adicionar foto ao carrinho”, “remover foto do carrinho”.
- Adotar WIP limit no Kanban para evitar muitas tarefas simultâneas.
- Exigir que cada tarefa tenha critério de aceite antes de entrar em desenvolvimento.
- Atualizar documentação somente com funcionalidades realmente implementadas.
- Criar checklist de Pull Request baseado na Definition of Done.
- Registrar retrospectiva ao final de cada sprint, com pelo menos uma ação concreta e responsável definido.

---

## 6. Ações de Melhoria Priorizadas

| Prioridade | Ação | Métrica-alvo | Prazo sugerido | Resultado esperado |
|---:|---|---|---|---|
| 1 | Definir DoD objetiva para todas as tarefas | 100% das tarefas com checklist de pronto | Próxima sprint | Menos trabalho incompleto |
| 2 | Quebrar histórias grandes em tarefas de até 1 dia | Lead time menor que 2 dias por tarefa | Próxima sprint | Entregas menores e frequentes |
| 3 | Aplicar WIP limit no Kanban | Máximo 2 tarefas em progresso por pessoa | Imediato | Menos troca de contexto |
| 4 | Criar testes para autenticação, fotos e pedidos | Cobrir 3 fluxos críticos do MVP | Próxima sprint | Mais confiança no CI |
| 5 | Registrar retrospectiva Sprint 2 e Sprint 3 | 1 documento por sprint | Entrega final | Evidência de melhoria contínua |
| 6 | Atualizar README conforme estado real do MVP | 0 funcionalidades não implementadas descritas como prontas | Entrega final | Coerência entre produto e documentação |

---

## 7. Definition of Done Proposta

Uma tarefa só deve ser considerada concluída quando atender aos seguintes critérios:

- Código implementado e versionado no GitHub.
- Build local ou CI executado sem falha.
- Teste mínimo criado ou justificativa registrada quando não houver teste.
- Endpoint ou tela demonstrável funcionando.
- Documentação atualizada conforme o que foi realmente entregue.
- Tarefa movida corretamente no Kanban.
- Evidência registrada: print, link do PR, execução da API ou descrição da demonstração.

---

## 8. Retrospectivas Complementares

### Sprint 2 — Eventos e Galeria

**O que funcionou bem:**
- O modelo de domínio avançou com entidades como Evento, Foto e Categoria.
- O uso de migrations ajudou a manter o banco versionado.
- A estrutura em camadas facilitou separar controller, service, repository e model.

**O que pode melhorar:**
- Algumas funcionalidades planejadas ficaram apenas parcialmente implementadas.
- A documentação avançou mais rápido que o código em alguns pontos.
- Faltou registrar melhor evidências de teste e demonstração.

**Ações de melhoria:**
- Quebrar cada módulo em endpoints menores e demonstráveis.
- Criar checklist de aceite para cada funcionalidade.
- Atualizar documentação apenas após validação do incremento.

### Sprint 3 — Busca, Carrinho e Processo

**O que funcionou bem:**
- O projeto passou a ter maior preocupação com CI/CD e métricas.
- A equipe identificou gargalos do processo e pontos de melhoria.
- A entrega final consolidou aprendizados técnicos e de organização.

**O que pode melhorar:**
- Carrinho e admin precisam ser finalizados ou removidos da promessa do MVP.
- Faltam testes automatizados para fluxos principais.
- O Kanban precisa refletir melhor o estado real das tarefas.

**Ações de melhoria:**
- Priorizar o menor MVP demonstrável.
- Criar testes para autenticação, fotos e pedidos.
- Medir DORA em dois momentos e registrar os resultados na proposta.

---

## 9. Conexão com Conceitos da Disciplina

A análise do projeto mostra a importância da melhoria contínua, um princípio central de processos ágeis. O Kanban ajudou a visualizar o trabalho, mas sua efetividade depende de limites de WIP e atualização constante. A retrospectiva mostrou ser essencial para transformar problemas em ações concretas.

O VSM permitiu enxergar que o gargalo não estava apenas na codificação, mas também na espera, na validação tardia e no desalinhamento entre documentação e implementação. As métricas DORA trouxeram uma visão quantitativa da capacidade de entrega, estabilidade e recuperação do processo.

Os desperdícios Lean mostram que trabalho parcialmente concluído e tarefas grandes demais reduzem o fluxo de valor. A principal melhoria proposta é tornar o processo mais incremental, mensurável e verificável, com histórias pequenas, Definition of Done objetiva, CI/CD confiável e retrospectivas documentadas.

---

## 10. Conclusão

O projeto evoluiu tecnicamente com backend Spring Boot, autenticação, entidades de domínio, migrations, Docker e CI/CD. Porém, o processo mostrou gargalos importantes: tarefas grandes, documentação parcialmente desalinhada do código, pouca cobertura de testes e retrospectivas incompletas.

A proposta de melhoria concentra-se em tornar o fluxo mais enxuto e previsível. Com WIP limit, histórias menores, Definition of Done objetiva, testes automatizados e análise contínua das métricas DORA, a equipe tende a reduzir lead time, aumentar a frequência de entrega, diminuir falhas e melhorar a qualidade do MVP.

Para a entrega final, recomenda-se apresentar de forma transparente o que está implementado, o que ficou como limitação e quais mudanças de processo foram aprendidas para ciclos futuros.

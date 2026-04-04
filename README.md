# 📸 Documentação do Projeto — Fotografia e Videografia Esportiva

> Plataforma web para venda e distribuição de fotografias de eventos esportivos.

**Disciplina:** Processos de Software  
**Equipe:** Thereza Angélica · Vinicius Davi · Lucas de Oliveira · Leandro Felix  
**Repositório:** https://github.com/itstherall/PS-Project.git  
**Kanban:** https://github.com/users/itstherall/projects/3  
**Versão:** 1.0 — Abril de 2026

---

## Índice

1. [Visão Geral do Projeto](#1-visão-geral-do-projeto)
2. [Requisitos do Sistema](#2-requisitos-do-sistema)
3. [Arquitetura do Sistema](#3-arquitetura-do-sistema)
4. [Modelagem do Banco de Dados](#4-modelagem-do-banco-de-dados)
5. [Casos de Uso](#5-casos-de-uso)
6. [Plano de Projeto](#6-plano-de-projeto)
7. [Manual do Usuário](#7-manual-do-usuário)
8. [Documentação da API REST](#8-documentação-da-api-rest)
9. [Como Rodar o Projeto](#9-como-rodar-o-projeto)

---

## 1. Visão Geral do Projeto

### 1.1 Sobre

Este sistema é uma plataforma web que permite a uma empresa de fotografia e videografia esportiva disponibilizar, organizar e comercializar fotografias de eventos esportivos. Atletas e clientes podem buscar suas próprias fotos — inclusive por **reconhecimento facial** — e adquiri-las com download em alta resolução.

O projeto é inspirado na plataforma [Banlek](https://banlek.com), adaptado à realidade e necessidades da empresa.

### 1.2 Problema e Solução

| Item | Descrição |
|------|-----------|
| **Problema** | A empresa não possui canal próprio para venda e divulgação de suas fotografias esportivas |
| **Impacto** | Perda de receita, dificuldade de comunicação com clientes e desorganização do portfólio |
| **Solução** | Site web próprio para catalogar, divulgar e vender fotografias de eventos |

### 1.3 Funcionalidades Principais

- Galeria de eventos e fotos com busca por esporte, cidade e data
- Busca por reconhecimento facial (envio de selfie)
- Visualização de fotos com marca d'água antes da compra
- Carrinho de compras e checkout
- Download em alta resolução após a compra
- Painel administrativo para gestão de eventos, fotos e avisos

### 1.4 Partes Interessadas

| Ator | Descrição |
|------|-----------|
| Empresa de Fotografia | Proprietária do negócio; cadastra eventos e publica fotos |
| Atletas / Clientes | Buscam, visualizam e compram fotografias |
| Equipe de Desenvolvimento | Responsável por desenvolver e entregar o sistema |

### 1.5 Tecnologias

| Camada | Tecnologia |
|--------|-----------|
| Backend | Java 17 + Spring Boot 3 |
| Banco de Dados | MySQL 8 |
| ORM | Spring Data JPA / Hibernate |
| Segurança | Spring Security + JWT |
| Build | Maven |
| Frontend | HTML5, CSS3, JavaScript |

---

## 2. Requisitos do Sistema

### 2.1 Convenção de Prioridades

| Código | Prioridade |
|--------|-----------|
| P1 | Alta — essencial para o MVP |
| P2 | Média — importante mas não bloqueante |
| P3 | Baixa — desejável para versões futuras |

### 2.2 Requisitos Funcionais

**Módulo de Autenticação**

| ID | Requisito | Prioridade |
|----|-----------|-----------|
| RF01 | O sistema deve permitir cadastro com nome, e-mail e senha | P1 |
| RF02 | O sistema deve permitir login com e-mail e senha | P1 |
| RF03 | O sistema deve permitir recuperação de senha via e-mail | P2 |
| RF04 | O sistema deve permitir logout | P1 |

**Módulo de Eventos e Fotografias**

| ID | Requisito | Prioridade |
|----|-----------|-----------|
| RF05 | O sistema deve listar os eventos com nome, data e local | P1 |
| RF06 | O sistema deve exibir as fotos de cada evento em grade | P1 |
| RF07 | O sistema deve exibir fotos com marca d'água antes da compra | P1 |
| RF08 | O sistema deve permitir visualizar uma foto em tela cheia (lightbox) | P1 |
| RF09 | O sistema deve exibir a data e hora de cada fotografia | P2 |
| RF10 | O sistema deve permitir navegar entre fotos no visualizador | P2 |
| RF11 | O sistema deve permitir compartilhar uma foto via link | P3 |

**Módulo de Busca**

| ID | Requisito | Prioridade |
|----|-----------|-----------|
| RF12 | O sistema deve permitir busca por nome do evento ou esporte | P1 |
| RF13 | O sistema deve permitir filtrar eventos por cidade, data e país | P2 |
| RF14 | O sistema deve permitir busca por reconhecimento facial | P2 |
| RF15 | O sistema deve exibir categorias de esportes na página inicial | P1 |

**Módulo de Carrinho e Compra**

| ID | Requisito | Prioridade |
|----|-----------|-----------|
| RF16 | O sistema deve permitir adicionar fotografias ao carrinho | P1 |
| RF17 | O sistema deve exibir o total do carrinho com valores individuais | P1 |
| RF18 | O sistema deve permitir remover itens do carrinho | P1 |
| RF19 | O sistema deve exibir o preço de cada fotografia | P1 |
| RF20 | O sistema deve permitir finalizar a compra | P1 |
| RF21 | Após a compra, o sistema deve disponibilizar o download sem marca d'água | P1 |

**Módulo Administrativo**

| ID | Requisito | Prioridade |
|----|-----------|-----------|
| RF22 | O administrador deve poder cadastrar novos eventos | P1 |
| RF23 | O administrador deve poder fazer upload de fotos em um evento | P1 |
| RF24 | O administrador deve poder editar e excluir eventos e fotos | P2 |
| RF25 | O administrador deve poder enviar avisos para os clientes | P2 |
| RF26 | O administrador deve poder visualizar relatório de vendas | P3 |

### 2.3 Requisitos Não-Funcionais

| ID | Requisito |
|----|-----------|
| RNF01 | Páginas de listagem devem carregar em no máximo 3 segundos |
| RNF02 | O sistema deve ser responsivo para mobile e desktop |
| RNF03 | As senhas devem ser armazenadas com hash BCrypt |
| RNF04 | Fotos não compradas devem ser protegidas com marca d'água |
| RNF05 | O painel admin deve ser restrito ao perfil `ROLE_ADMIN` |
| RNF06 | O código deve seguir arquitetura em camadas (Controller/Service/Repository) |
| RNF07 | O banco deve ser versionado com scripts de migração |

### 2.4 Regras de Negócio

| ID | Regra |
|----|-------|
| RN01 | O download sem marca d'água só é liberado após pagamento confirmado |
| RN02 | O carrinho persiste entre sessões para usuários autenticados |
| RN03 | Cada foto pertence a exatamente um evento |
| RN04 | Somente perfil `ADMIN` pode cadastrar eventos e fazer upload de fotos |
| RN05 | A busca por reconhecimento facial exige login |

---

## 3. Arquitetura do Sistema

### 3.1 Visão Geral

O sistema segue o padrão **MVC (Model-View-Controller)** em arquitetura de camadas, amplamente suportado pelo Spring Framework.

```
┌─────────────────────────────────────────────┐
│                  CLIENTE                    │
│           (Navegador Web / Mobile)          │
└──────────────────┬──────────────────────────┘
                   │ HTTP / HTTPS
┌──────────────────▼──────────────────────────┐
│              FRONTEND                       │
│         (HTML, CSS, JavaScript)             │
└──────────────────┬──────────────────────────┘
                   │ REST API (JSON)
┌──────────────────▼──────────────────────────┐
│           BACKEND (Java + Spring)           │
│  ┌─────────────┐  ┌──────────────────────┐  │
│  │  Controller │  │  Spring Security/JWT │  │
│  └──────┬──────┘  └──────────────────────┘  │
│  ┌──────▼──────┐                            │
│  │   Service   │  (Regras de Negócio)       │
│  └──────┬──────┘                            │
│  ┌──────▼──────┐                            │
│  │ Repository  │  (Spring Data JPA)         │
│  └──────┬──────┘                            │
└─────────┼───────────────────────────────────┘
          │ JDBC / JPA
┌─────────▼───────────────────────────────────┐
│              BANCO DE DADOS                 │
│                  MySQL                      │
└─────────────────────────────────────────────┘
```

### 3.2 Estrutura de Pacotes

```
com.fotografia.esportiva
│
├── controller/       # Endpoints REST
│   ├── AuthController.java
│   ├── EventoController.java
│   ├── FotoController.java
│   ├── CarrinhoController.java
│   └── AdminController.java
│
├── service/          # Regras de negócio
│   ├── AuthService.java
│   ├── EventoService.java
│   ├── FotoService.java
│   ├── CarrinhoService.java
│   └── PedidoService.java
│
├── repository/       # Acesso ao banco (Spring Data JPA)
│   ├── UsuarioRepository.java
│   ├── EventoRepository.java
│   ├── FotoRepository.java
│   └── PedidoRepository.java
│
├── model/            # Entidades JPA
│   ├── Usuario.java
│   ├── Evento.java
│   ├── Foto.java
│   ├── Pedido.java
│   └── ItemPedido.java
│
├── dto/              # Objetos de transferência (Request/Response)
├── security/         # JWT e Spring Security
└── config/           # Configurações gerais
```

### 3.3 Segurança

- **Autenticação:** Login por e-mail e senha com retorno de JWT
- **Senhas:** Armazenadas com hash BCrypt
- **Rotas públicas:** Página inicial, listagem de eventos, visualização de fotos com marca d'água
- **Rotas autenticadas:** Carrinho, compra, perfil, reconhecimento facial
- **Rotas admin:** Cadastro de eventos/fotos, envio de avisos — restrito ao perfil `ROLE_ADMIN`
- **Download:** URLs geradas com token temporário após confirmação de compra

### 3.4 Fluxo de uma Requisição

Exemplo: cliente adiciona uma foto ao carrinho.

```
1. Usuário clica em "Adicionar ao Carrinho"
2. Frontend envia POST /api/carrinho/{fotoId} com JWT no header
3. Spring Security valida o token e identifica o usuário
4. CarrinhoController chama CarrinhoService.adicionarItem()
5. CarrinhoService busca a Foto via FotoRepository
6. CarrinhoService persiste o item via CarrinhoRepository
7. Controller retorna HTTP 200 com o carrinho atualizado em JSON
8. Frontend atualiza o ícone do carrinho
```

---

## 4. Modelagem do Banco de Dados

### 4.1 Relacionamentos

```
USUARIO      ||--o{ PEDIDO        : "realiza"
USUARIO      ||--o{ CARRINHO_ITEM : "possui"
PEDIDO       ||--|{ ITEM_PEDIDO   : "contém"
FOTO         }|--|| EVENTO        : "pertence a"
FOTO         ||--o{ ITEM_PEDIDO   : "está em"
FOTO         ||--o{ CARRINHO_ITEM : "está em"
EVENTO       }|--|| CATEGORIA     : "tem"
```

### 4.2 Scripts de Criação

```sql
CREATE TABLE usuarios (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome        VARCHAR(100) NOT NULL,
    email       VARCHAR(150) NOT NULL UNIQUE,
    senha_hash  VARCHAR(255) NOT NULL,
    perfil      ENUM('CLIENTE', 'ADMIN') NOT NULL DEFAULT 'CLIENTE',
    criado_em   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ativo       BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE categorias (
    id    BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome  VARCHAR(80) NOT NULL UNIQUE,
    slug  VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE eventos (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    titulo       VARCHAR(200) NOT NULL,
    descricao    TEXT,
    data_evento  DATETIME NOT NULL,
    local        VARCHAR(200),
    cidade       VARCHAR(100),
    pais         VARCHAR(80) DEFAULT 'Brasil',
    categoria_id BIGINT,
    fotografo    VARCHAR(100),
    criado_em    DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (categoria_id) REFERENCES categorias(id)
);

CREATE TABLE fotos (
    id               BIGINT AUTO_INCREMENT PRIMARY KEY,
    evento_id        BIGINT NOT NULL,
    numero           BIGINT,
    url_marca_dagua  VARCHAR(500) NOT NULL,
    url_original     VARCHAR(500) NOT NULL,
    preco            DECIMAL(10, 2) NOT NULL,
    tirada_em        DATETIME,
    criado_em        DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (evento_id) REFERENCES eventos(id)
);

CREATE TABLE pedidos (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id  BIGINT NOT NULL,
    total       DECIMAL(10, 2) NOT NULL,
    status      ENUM('PENDENTE', 'PAGO', 'CANCELADO') NOT NULL DEFAULT 'PENDENTE',
    criado_em   DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id)
);

CREATE TABLE itens_pedido (
    id              BIGINT AUTO_INCREMENT PRIMARY KEY,
    pedido_id       BIGINT NOT NULL,
    foto_id         BIGINT NOT NULL,
    preco_unitario  DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (pedido_id) REFERENCES pedidos(id),
    FOREIGN KEY (foto_id) REFERENCES fotos(id)
);

CREATE TABLE carrinho_itens (
    id             BIGINT AUTO_INCREMENT PRIMARY KEY,
    usuario_id     BIGINT NOT NULL,
    foto_id        BIGINT NOT NULL,
    adicionado_em  DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
    UNIQUE KEY uq_usuario_foto (usuario_id, foto_id),
    FOREIGN KEY (usuario_id) REFERENCES usuarios(id),
    FOREIGN KEY (foto_id) REFERENCES fotos(id)
);
```

### 4.3 Índices Recomendados

```sql
CREATE INDEX idx_fotos_evento    ON fotos(evento_id);
CREATE INDEX idx_eventos_data    ON eventos(data_evento);
CREATE INDEX idx_pedidos_usuario ON pedidos(usuario_id);
CREATE INDEX idx_itens_pedido    ON itens_pedido(pedido_id);
```

### 4.4 Dados Iniciais (Seed)

```sql
INSERT INTO categorias (nome, slug) VALUES
('Futebol', 'futebol'), ('Ciclismo', 'ciclismo'),
('Crossfit', 'crossfit'), ('Beach Tennis', 'beach-tennis'),
('Corrida', 'corrida'), ('Futsal', 'futsal');

INSERT INTO usuarios (nome, email, senha_hash, perfil) VALUES
('Administrador', 'admin@fotoesportiva.com.br', '$2a$10$HASH_BCRYPT_AQUI', 'ADMIN');
```

---

## 5. Casos de Uso

### 5.1 Atores

| Ator | Descrição |
|------|-----------|
| Visitante | Usuário não autenticado |
| Cliente | Usuário cadastrado e autenticado |
| Administrador | Funcionário com acesso ao painel admin |

### 5.2 Lista de Casos de Uso

| ID | Nome | Atores |
|----|------|--------|
| UC01 | Visualizar página inicial | Visitante, Cliente |
| UC02 | Buscar fotografias | Visitante, Cliente |
| UC03 | Buscar por reconhecimento facial | Cliente |
| UC04 | Visualizar evento | Visitante, Cliente |
| UC05 | Visualizar foto em detalhe | Visitante, Cliente |
| UC06 | Cadastrar-se | Visitante |
| UC07 | Fazer login | Cliente, Admin |
| UC08 | Adicionar foto ao carrinho | Cliente |
| UC09 | Gerenciar carrinho | Cliente |
| UC10 | Finalizar compra | Cliente |
| UC11 | Baixar foto após compra | Cliente |
| UC12 | Cadastrar evento | Administrador |
| UC13 | Fazer upload de fotos | Administrador |
| UC14 | Enviar aviso para clientes | Administrador |

### 5.3 Descrições Detalhadas

---

**UC03 — Buscar por Reconhecimento Facial**

| Campo | Descrição |
|-------|-----------|
| Pré-condição | Usuário autenticado |
| Pós-condição | Sistema exibe fotos onde o cliente aparece |

Fluxo principal:
1. O cliente clica em "Encontre suas fotos com reconhecimento facial"
2. O cliente faz upload de uma selfie
3. O sistema processa a imagem e identifica correspondências
4. O sistema exibe as fotos encontradas
5. O cliente pode adicioná-las ao carrinho

Fluxo alternativo: nenhuma foto encontrada → sistema informa o cliente

---

**UC10 — Finalizar Compra**

| Campo | Descrição |
|-------|-----------|
| Pré-condição | Carrinho com ao menos um item |
| Pós-condição | Pedido criado e fotos liberadas para download |

Fluxo principal:
1. O cliente clica em "Finalizar Compra"
2. O sistema exibe resumo do pedido e opções de pagamento (Pix, Cartão, PayPal)
3. O cliente confirma o pagamento
4. O sistema registra o pedido com status `PAGO`
5. O sistema libera os links de download sem marca d'água
6. O sistema envia confirmação por e-mail

Fluxo alternativo: pagamento recusado → sistema informa o erro e mantém o carrinho

---

**UC13 — Fazer Upload de Fotos (Admin)**

| Campo | Descrição |
|-------|-----------|
| Pré-condição | Administrador autenticado; evento cadastrado |
| Pós-condição | Fotos publicadas com marca d'água e preço definido |

Fluxo principal:
1. O admin seleciona um evento no painel
2. O admin faz upload de múltiplas fotos
3. O sistema processa e aplica marca d'água automaticamente
4. O admin define o preço padrão por foto
5. O sistema publica as fotos no evento

---

## 6. Plano de Projeto

### 6.1 Equipe

| Nome | Responsabilidades Principais |
|------|------------------------------|
| Thereza Angélica | Backend — autenticação e usuários |
| Vinicius Davi | Backend — eventos e fotos |
| Lucas de Oliveira | Frontend — galeria e busca |
| Leandro Felix | Backend — carrinho, pedidos e integração |

### 6.2 Sprints

**Sprint 1 — Configuração e Base** (2 semanas)

| Tarefa | Responsável |
|--------|-------------|
| Criar repositório e estrutura Spring Boot | Todos |
| Criar banco de dados e migrations | Leandro |
| Implementar cadastro e login com JWT | Thereza |
| Criar layout base do frontend | Lucas |

**Sprint 2 — Eventos e Galeria** (2 semanas)

| Tarefa | Responsável |
|--------|-------------|
| CRUD de categorias e eventos | Vinicius |
| Listagem de eventos na página inicial | Lucas |
| Grid de fotos com marca d'água | Lucas |
| Upload de fotos pelo admin | Vinicius |
| Lightbox de visualização | Lucas |

**Sprint 3 — Busca e Carrinho** (2 semanas)

| Tarefa | Responsável |
|--------|-------------|
| Busca por texto e categoria | Vinicius |
| Filtros de evento | Vinicius |
| Adicionar/remover item do carrinho | Leandro |
| Exibição do carrinho com totais | Lucas |

**Sprint 4 — Compras e Admin** (2 semanas)

| Tarefa | Responsável |
|--------|-------------|
| Finalizar compra e gerar pedido | Leandro |
| Liberar download após compra | Leandro |
| Gestão de eventos/fotos no admin | Vinicius |
| Envio de avisos para clientes | Thereza |

**Sprint 5 — Testes e Entrega** (1–2 semanas)

| Tarefa | Responsável |
|--------|-------------|
| Testes de aceitação | Todos |
| Revisão de segurança | Thereza |
| Revisão de responsividade | Lucas |
| Deploy e apresentação | Todos |

### 6.3 Riscos

| Risco | Probabilidade | Impacto | Mitigação |
|-------|--------------|---------|-----------|
| Dificuldade com reconhecimento facial | Média | Alto | Usar API externa; deixar como feature opcional |
| Integração de pagamento complexa | Alta | Alto | Usar mockup de pagamento no MVP |
| Sobrecarga da equipe na entrega | Média | Médio | Priorizar P1; adiar P2/P3 |
| Conflitos de merge | Baixa | Baixo | Branches por feature + revisão de PR |

### 6.4 Critérios de Aceitação do MVP

- [ ] Cadastro e autenticação de usuários funcionando
- [ ] Eventos e fotos cadastrados pelo admin
- [ ] Fotos exibidas com marca d'água antes da compra
- [ ] Carrinho funcional com adição e remoção de itens
- [ ] Compra gera pedido e libera download sem marca d'água
- [ ] Site acessível e funcional em dispositivos móveis

---

## 7. Manual do Usuário

### 7.1 Criando uma Conta

1. Clique em **"Criar conta"** no canto superior direito
2. Preencha nome, e-mail e senha
3. Clique em **"Cadastrar"**

### 7.2 Fazendo Login

1. Clique em **"Entrar"**
2. Digite e-mail e senha
3. Clique em **"Entrar"**

Esqueceu a senha? Clique em **"Esqueci minha senha"** e siga as instruções enviadas ao seu e-mail.

### 7.3 Encontrando Suas Fotos

**Por nome ou esporte:** use a barra de busca na página inicial.

**Por categoria:** clique no esporte desejado na seção Categorias (Futebol, Ciclismo, Crossfit, etc.).

**Por reconhecimento facial** *(requer login)*:
1. Clique em **"Encontre suas fotos com reconhecimento facial"**
2. Envie uma selfie nítida
3. O sistema exibirá automaticamente as fotos em que você aparece

> Dica: use foto com boa iluminação e sem óculos escuros para melhores resultados.

### 7.4 Visualizando Fotos

- Todas as fotos são exibidas com **marca d'água** antes da compra
- Clique em qualquer foto para abrir o **lightbox** com visualização ampliada
- Use **◀ ▶** para navegar entre fotos do evento
- O número da foto, data/hora e preço são exibidos no lightbox

### 7.5 Comprando

1. No lightbox ou na grade, clique no ícone 🛒 na foto desejada
2. Acesse o **Carrinho** no menu superior para revisar os itens
3. Clique em **"Finalizar Compra"**
4. Escolha a forma de pagamento (Pix, Cartão de Crédito ou PayPal) e confirme

### 7.6 Baixando Suas Fotos

Após a compra confirmada:

1. Acesse **"Meus Pedidos"** no menu da conta
2. Clique em **"Baixar"** ao lado da foto desejada
3. O arquivo em **alta resolução sem marca d'água** será baixado

> Recomendamos fazer backup das fotos após o download.

### 7.7 Perguntas Frequentes

**As fotos têm marca d'água?** Sim, antes da compra. Após adquirir, você recebe a versão original em alta qualidade.

**Posso comprar só algumas fotos de um evento?** Sim, você escolhe exatamente quais adicionar ao carrinho.

**Posso acessar minhas fotos compradas depois?** Sim, pelo menu "Meus Pedidos" na sua conta.

**Não encontrei minha foto. O que faço?** Tente a busca por reconhecimento facial ou contate a empresa informando o nome do evento e a data.

---

## 8. Documentação da API REST

**Base URL:** `http://localhost:8080/api`  
**Autenticação:** `Authorization: Bearer <token_jwt>`

### 8.1 Autenticação

**POST `/auth/cadastro`** — Cadastra um novo usuário

```json
// Request
{ "nome": "João Silva", "email": "joao@email.com", "senha": "senha123" }

// Response 201
{ "id": 1, "nome": "João Silva", "email": "joao@email.com", "perfil": "CLIENTE" }
```

**POST `/auth/login`** — Autentica e retorna JWT

```json
// Request
{ "email": "joao@email.com", "senha": "senha123" }

// Response 200
{ "token": "eyJhbGci...", "tipo": "Bearer", "usuario": { "id": 1, "nome": "João Silva" } }
```

### 8.2 Eventos

**GET `/eventos`** — Lista eventos (público)

Parâmetros: `busca`, `categoriaId`, `cidade`, `page`, `size`

```json
// Response 200
{
  "conteudo": [
    {
      "id": 1, "titulo": "Pedalada MTB 3 ABR 26",
      "dataEvento": "2026-04-03T07:00:00",
      "cidade": "Recife", "categoria": { "id": 2, "nome": "Ciclismo" },
      "totalFotos": 248
    }
  ],
  "totalElementos": 1, "totalPaginas": 1
}
```

**GET `/eventos/{id}`** — Detalhes de um evento (público)

**POST `/eventos`** *(Admin)* — Cadastra novo evento

### 8.3 Fotos

**GET `/fotos?eventoId={id}`** — Lista fotos de um evento (público)

```json
// Response 200 — urlOriginal não é retornada antes da compra
{
  "conteudo": [
    {
      "id": 101, "numero": 1423758752,
      "urlMarcaDagua": "https://.../101_watermark.jpg",
      "preco": 12.99, "tiradaEm": "2026-04-03T08:32:00"
    }
  ]
}
```

**POST `/fotos/upload`** *(Admin)* — Upload de fotos (`multipart/form-data`: `eventoId`, `arquivos[]`, `preco`)

### 8.4 Carrinho

Todos os endpoints exigem autenticação.

| Método | Rota | Descrição |
|--------|------|-----------|
| GET | `/carrinho` | Retorna itens do carrinho |
| POST | `/carrinho/{fotoId}` | Adiciona foto ao carrinho |
| DELETE | `/carrinho/{fotoId}` | Remove foto do carrinho |

### 8.5 Pedidos

**POST `/pedidos`** — Finaliza a compra

```json
// Request
{ "formaPagamento": "PIX" }

// Response 201
{ "id": 10, "status": "PAGO", "total": 25.98, "criadoEm": "2026-04-04T10:15:00" }
```

**GET `/pedidos`** — Lista pedidos do usuário autenticado

**GET `/pedidos/{pedidoId}/download/{fotoId}`** — Gera link temporário de download

```json
// Response 200
{ "url": "https://.../download/token/foto_original.jpg", "expiresAt": "2026-04-04T11:15:00" }
```

### 8.6 Reconhecimento Facial

**POST `/facial/buscar`** *(autenticado)* — Busca fotos por selfie (`multipart/form-data`: `imagem`)

```json
// Response 200
{ "fotosEncontradas": [ { "id": 101, "urlMarcaDagua": "...", "preco": 12.99 } ], "total": 3 }
```

### 8.7 Códigos HTTP

| Código | Significado |
|--------|-------------|
| 200 | Sucesso |
| 201 | Recurso criado |
| 400 | Dados inválidos |
| 401 | Não autenticado |
| 403 | Sem permissão |
| 404 | Não encontrado |
| 409 | Conflito (ex: item duplicado) |
| 500 | Erro interno |

---

## 9. Como Rodar o Projeto

### 9.1 Pré-requisitos

- [Java 17+](https://adoptium.net/)
- [Maven 3.8+](https://maven.apache.org/)
- [MySQL 8.x](https://dev.mysql.com/downloads/)
- [Git](https://git-scm.com/)

### 9.2 Passo a Passo

**1. Clone o repositório**

```bash
git clone https://github.com/itstherall/PS-Project.git
cd PS-Project
```

**2. Crie o banco de dados**

```sql
CREATE DATABASE fotografia_esportiva CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

**3. Configure as credenciais**

Crie o arquivo `src/main/resources/application-local.properties`:

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/fotografia_esportiva
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
spring.jpa.hibernate.ddl-auto=update

jwt.secret=seu_segredo_jwt_aqui
jwt.expiration=86400000
```

> ⚠️ Nunca suba credenciais reais para o repositório!

**4. Compile e execute**

```bash
mvn clean install
mvn spring-boot:run
```

Acesse em: **http://localhost:8080**

### 9.3 Estrutura de Diretórios

```
PS-Project/
├── src/
│   ├── main/
│   │   ├── java/com/fotografia/esportiva/
│   │   └── resources/application.properties
│   └── test/
├── docs/
│   └── DOCUMENTACAO.md   ← este arquivo
└── pom.xml
```

---

*Projeto de Processos de Software*

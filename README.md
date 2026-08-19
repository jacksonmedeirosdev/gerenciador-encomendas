# 📦 Gerenciador de Encomendas

Sistema para gerenciamento de encomendas em condomínios residenciais, desenvolvido com Java e Spring Boot.

O objetivo deste projeto é aplicar boas práticas de desenvolvimento Back-end, arquitetura em camadas, modelagem de domínio e versionamento de banco de dados, evoluindo o sistema incrementalmente por meio de Sprints.

---

# 🎯 Objetivos

- Desenvolver uma API REST profissional utilizando Spring Boot.
- Aplicar boas práticas de arquitetura e organização por domínio.
- Versionar o banco de dados utilizando Flyway.
- Construir um projeto escalável e de fácil manutenção.
- Documentar as decisões técnicas tomadas durante o desenvolvimento.

---

# 🛠️ Tecnologias

- Java 21
- Spring Boot 4
- Spring Data JPA
- Hibernate
- PostgreSQL
- Flyway
- Maven
- Git
- GitHub

---

# 🏗️ Arquitetura

O projeto utiliza arquitetura em camadas, organizada por domínio.

```text
src/main/java/br/com/jjnervosia/gerenciador_encomendas

bloco/
├── dto/
│   ├── AtualizarBlocoDTO.java
│   ├── CadastrarBlocoDTO.java
│   └── BlocoResponseDTO.java
├── Bloco.java
├── BlocoController.java
├── BlocoRepository.java
└── BlocoService.java

apartamento/
├── dto/
│   ├── CadastrarApartamentoDTO.java
│   ├── AtualizarApartamentoDTO.java
│   └── ApartamentoResponseDTO.java
├── Apartamento.java
├── ApartamentoController.java
├── ApartamentoRepository.java
└── ApartamentoService.java

exception/
├── ApiError.java
├── ApartamentoJaExisteNoBlocoException.java
├── BlocoJaExisteException.java
├── BlocoNaoEncontradoException.java
├── CampoErro.java
├── GlobalExceptionHandler.java
├── BlocoPossuiApartamentosException.java
└── ApartamentoNaoEncontradoException.java

config/
├── OpenApiConfig.java

morador/
encomenda/
historico/
```

Essa organização aproxima todos os arquivos relacionados ao mesmo domínio, facilitando a manutenção e evolução do sistema.
---

# 🗂️ Modelagem do Domínio (MVP)

Antes da implementação da API, foi realizada a modelagem inicial do domínio para definir as principais entidades e seus relacionamentos.

Essa modelagem representa a primeira versão (MVP) do sistema e servirá como base para a evolução das próximas funcionalidades.

<p>
  <img src="docs/modelagem/modelagem-mvp.png" width="788" alt="modelagem do mvp">
</p>
---

# 📐 Princípios adotados

Durante o desenvolvimento, algumas decisões arquiteturais foram definidas desde o início:

- Organização por domínio.
- Responsabilidade única (Single Responsibility Principle).
- Regras de negócio centralizadas na camada Service.
- Persistência isolada na camada Repository.
- Entidades protegidas contra estados inválidos.
- Banco de dados tratado como código através do Flyway.
- Restrições críticas garantidas tanto na aplicação quanto no banco de dados.

---

# 🤝 Processo de Desenvolvimento

Este projeto é desenvolvido utilizando Inteligência Artificial como apoio técnico, simulando a dinâmica entre um Desenvolvedor Back-end e um Tech Lead.

O foco da utilização da IA não é a geração automática de código, mas sim o desenvolvimento do raciocínio técnico, por meio de discussões sobre arquitetura, modelagem, revisão de código e tomada de decisões.

Cada funcionalidade segue um fluxo semelhante ao encontrado em equipes de desenvolvimento:

1. Definição do problema.
2. Discussão da solução.
3. Implementação realizada por mim.
4. Code Review técnico.
5. Refatorações.
6. Registro das decisões arquiteturais.

---
# 📝 Convenção de Commits

Este projeto segue uma convenção baseada em Conventional Commits para manter o histórico organizado e facilitar a leitura da evolução do sistema.

| Tipo | Utilização |
|------|------------|
| feat | Nova funcionalidade |
| fix | Correção de bugs |
| docs | Alterações na documentação |
| refactor | Refatoração sem alterar comportamento |
| test | Inclusão ou alteração de testes |
| style | Apenas formatação |
| chore | Configurações e tarefas de manutenção |

### Princípios adotados

- Cada commit representa uma única intenção.
- O histórico deve contar a evolução do projeto.
- Commits pequenos e objetivos facilitam revisões e manutenção.

### Exemplos

feat(bloco): implementa consulta por id

fix(api): corrige tratamento de exceção

docs(readme): atualiza roadmap do projeto

refactor(exception): reduz duplicação na criação do ApiError

---
# 📁 Banco de Dados

Tecnologia utilizada:

- PostgreSQL

Versionamento:

- Flyway

## Estrutura atual

### bloco

| Campo | Tipo | Restrições |
|--------|------|------------|
| id | BIGSERIAL | PRIMARY KEY |
| identificacao | VARCHAR(5) | NOT NULL, UNIQUE |


### apartamento

| Campo | Tipo | Restrições |
|--------|------|------------|
| id | BIGINT | PRIMARY KEY, IDENTITY |
| numero | VARCHAR(10) | NOT NULL |
| bloco_id | BIGINT | NOT NULL, FOREIGN KEY |

Restrições adicionais:

- `bloco_id` referencia `bloco(id)`.
- A combinação `(bloco_id, numero)` é única.
- O mesmo número de apartamento pode existir em blocos diferentes.
- O mesmo número não pode se repetir dentro do mesmo bloco.
---

# ▶️ Como executar o projeto

## Pré-requisitos

Antes de iniciar a aplicação, tenha instalado:

- Java 21
- PostgreSQL
- Git

O projeto utiliza Maven Wrapper, portanto não é necessário instalar o Maven separadamente.

---

## 1. Clone o repositório

```bash
git clone https://github.com/jacksonmedeirosdev/gerenciador-encomendas.git
```

Entre na pasta do projeto:

```bash
cd gerenciador-encomendas
```

---

## 2. Crie o banco de dados

No PostgreSQL, crie o banco:

```sql
CREATE DATABASE gerenciador_encomendas;
```

As tabelas e alterações de estrutura são controladas automaticamente pelo Flyway ao iniciar a aplicação.

---

## 3. Configure as variáveis de ambiente

A aplicação utiliza as seguintes variáveis:

```text
DB_URL
DB_USERNAME
DB_PASSWORD
```

Exemplo:

```text
DB_URL=jdbc:postgresql://localhost:5432/gerenciador_encomendas
DB_USERNAME=postgres
DB_PASSWORD=sua_senha
```

As credenciais não são armazenadas diretamente no repositório.

### Windows PowerShell

No terminal, execute:

```powershell
$env:DB_URL="jdbc:postgresql://localhost:5432/gerenciador_encomendas"
$env:DB_USERNAME="postgres"
$env:DB_PASSWORD="sua_senha"
```

Essas variáveis ficam disponíveis durante a sessão atual do terminal.

---

## 4. Execute a aplicação

Certifique-se de estar na raiz do projeto, onde está localizado o arquivo `pom.xml`.

### Windows PowerShell

```powershell
.\mvnw.cmd spring-boot:run
```

### Linux / macOS

```bash
./mvnw spring-boot:run
```

Também é possível iniciar a aplicação diretamente pela IDE.

Quando a aplicação iniciar corretamente, o servidor estará disponível em:

```text
http://localhost:8080
```

---

## 5. Acesse a documentação da API

Com a aplicação em execução, acesse:

```text
http://localhost:8080/swagger-ui.html
```

A especificação OpenAPI está disponível em:

```text
http://localhost:8080/v3/api-docs
```

---

## 6. Pare a aplicação

Quando a aplicação estiver rodando no terminal, pressione:

```text
Ctrl + C
```

Isso encerra o processo do Spring Boot e libera a porta `8080`.

Se o terminal solicitar confirmação para finalizar o processo, confirme a operação.

# 📌 Roadmap

## Sprint 1 — Fundação do Projeto ✅

- [x] Criação do projeto Spring Boot
- [x] Configuração do PostgreSQL
- [x] Configuração do Flyway
- [x] Primeira Migration
- [x] Modelagem inicial do banco
- [x] Entidade Bloco

---

## Sprint 2 — Camada de Negócio ✅

- [x] BlocoRepository
- [x] BlocoService
- [x] Primeira regra de negócio
- [x] Injeção de Dependência (Constructor Injection)
- [x] README inicial

---

## Sprint 3 — API REST ✅

- [x] BlocoController
- [x] DTO de cadastro
- [x] Bean Validation
- [x] Endpoint POST /blocos
- [x] Testes utilizando Postman
- [x] ResponseEntity (201 Created)

---
## Sprint 4 — Tratamento de Erros ✅

- [x] BlocoJaExisteException
- [x] GlobalExceptionHandler
- [x] ApiError
- [x] CampoErro
- [x] Tratamento HTTP 400
- [x] Tratamento HTTP 404
- [x] Tratamento HTTP 405
- [x] Tratamento HTTP 409
- [x] Testes no Postman

---
## Sprint 5 — CRUD de Blocos ✅

- [x] BlocoResponseDTO
- [x] Endpoint GET /blocos
- [x] Endpoint GET /blocos/{id}
- [x] Endpoint PUT /blocos/{id}
- [x] Endpoint DELETE /blocos/{id}
- [x] Conversão Entity → DTO
- [x] AtualizarBlocoDTO
- [x] Método de domínio para alteração
- [x] Consulta utilizando Optional
- [x] Tratamento de BlocoNaoEncontradoException
- [x] Tratamento de MethodArgumentTypeMismatchException
- [x] Tratamento de NoResourceFoundException
- [x] Testes utilizando Postman
---

---
## Sprint 6 — Apartamentos ✅

- [x] Modelagem da entidade Apartamento
- [x] Relacionamento `Apartamento -> Bloco`
- [x] Migration V2 para criação da tabela apartamento
- [x] Foreign Key entre apartamento e bloco
- [x] Restrição de unicidade composta `(bloco_id, numero)`
- [x] ApartamentoRepository
- [x] Validação de duplicidade por bloco
- [x] ApartamentoService
- [x] CadastrarApartamentoDTO
- [x] ApartamentoResponseDTO
- [x] Endpoint POST /apartamentos
- [x] Tratamento de `ApartamentoJaExisteNoBlocoException`
- [x] Bean Validation para cadastro
- [x] Tratamento de `HttpMessageNotReadableException`
- [x] Refatoração da criação do `ApiError`
- [x] Testes utilizando Postman
- [x] Listagem de apartamentos
- [x] Consulta de apartamento por ID
- [x] Tratamento de `ApartamentoNaoEncontradoException`
- [x] Atualização de apartamento
- [x] Exclusão de apartamento
---
## Sprint 7 — Qualidade e Documentação da API ✅

- [x] Impedir exclusão de blocos com apartamentos vinculados
- [x] Configurar Springdoc OpenAPI
- [x] Configurar informações gerais da API
- [x] Documentar endpoints de Blocos
- [x] Documentar endpoints de Apartamentos
- [x] Documentar respostas HTTP
- [x] Documentar schema padronizado de erro com `ApiError`
- [x] Remover credenciais do banco do versionamento
- [x] Configurar variáveis de ambiente
- [x] Introdução aos testes unitários
- [x] Testes unitários iniciais da BlocoService
- [x] Testes unitários da ApartamentoService
---
## Próximas Sprints

- [ ] Moradores
- [ ] Encomendas
- [ ] Histórico de Encomendas
- [ ] Docker
- [ ] Autenticação
- [ ] Deploy


---

# 🚀 Roadmap Funcional

## Blocos

- [x] Cadastro
- [x] Listagem
- [x] Consulta por ID
- [x] Atualização
- [x] Exclusão

## Apartamentos

- [x] Cadastro
- [x] Listagem
- [x] Consulta por ID
- [x] Atualização
- [x] Exclusão

## Moradores

- [ ] Cadastro
- [ ] Consulta
- [ ] Atualização
- [ ] Exclusão

## Encomendas

- [ ] Cadastro
- [ ] Recebimento
- [ ] Retirada
- [ ] Histórico

## Funcionalidades Futuras

- [ ] QR Code para retirada
- [ ] Dashboard administrativo
- [ ] Múltiplos condomínios
- [ ] Docker
- [ ] Deploy
---

# 📚 Journal de Desenvolvimento

O histórico detalhado de evolução do projeto, decisões técnicas e principais aprendizados está disponível em:

➡️ [Journal de Desenvolvimento](docs/JOURNAL.md)

## Backlog Técnico

- [x] Centralizar a criação do `ApiError` para reduzir repetição no `GlobalExceptionHandler`.
- [x] Impedir exclusão de blocos que possuam apartamentos vinculados.
- [ ] Centralizar normalização de textos (`trim + uppercase`) para evitar duplicação entre Services.
- [ ] Melhorar o tratamento de `HttpMessageNotReadableException` para identificar o campo inválido quando o Jackson disponibilizar essa informação.
- [ ] Gerar etiqueta de encomenda com QR Code para facilitar identificação e busca pela portaria.
- [ ] Adicionar exemplos personalizados de respostas no Swagger/OpenAPI.

---

# 👨‍💻 Autor

**Jackson Medeiros**

Projeto desenvolvido com foco na construção de software utilizando boas práticas de arquitetura, versionamento de banco de dados e organização por domínio.

A Inteligência Artificial foi utilizada como apoio técnico durante o desenvolvimento, atuando como um Tech Lead em discussões de arquitetura, revisão de código e evolução das soluções, enquanto toda a implementação foi construída a partir do entendimento dos conceitos estudados.
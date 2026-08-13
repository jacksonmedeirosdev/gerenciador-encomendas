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

- Java 25
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
└── GlobalExceptionHandler.java

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
## Sprint 6 — Apartamentos 🚧

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
- [ ] Listagem de apartamentos
- [ ] Consulta de apartamento por ID
- [ ] Atualização de apartamento
- [ ] Exclusão de apartamento

---
## Próximas Sprints

- [ ] Moradores
- [ ] Encomendas
- [ ] Histórico de Encomendas
- [ ] Swagger / OpenAPI
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
- [ ] Consulta
- [ ] Atualização
- [ ] Exclusão

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
- [ ] Swagger
- [ ] Deploy
---

# 📚 Journal de Desenvolvimento

## Sprint 1

### Objetivos

Construção da fundação do projeto.

### Entregas

- Estrutura inicial.
- Configuração do PostgreSQL.
- Configuração do Flyway.
- Primeira entidade (Bloco).
- Primeira Migration.

### Principais aprendizados

- Papel do JPA.
- Papel do Hibernate.
- Papel do Spring Data JPA.
- Organização por domínio.
- Banco versionado utilizando Flyway.

---

## Sprint 2

### Objetivos

Implementar a primeira camada de regras de negócio.

### Entregas

- Criação do BlocoRepository.
- Criação da BlocoService.
- Primeira validação de domínio.
- Validação de duplicidade utilizando Spring Data JPA.
- Introdução à Injeção de Dependência.

### Principais aprendizados

- Constructor Injection.
- Responsabilidade única.
- Repository Pattern.
- Diferença entre domínio e persistência.
- Quando validar na aplicação e quando validar no banco.

---

## Sprint 3

### Objetivos

Disponibilizar o primeiro endpoint REST da aplicação.

### Entregas

- Criação da BlocoController.
- Implementação do DTO de cadastro.
- Bean Validation.
- Endpoint POST /blocos.
- ResponseEntity com HTTP 201.
- Testes utilizando Postman.

### Principais aprendizados

- Fluxo de uma requisição HTTP no Spring Boot.
- DispatcherServlet. (Responsável por entender o que vem do cliente e passar para o controller)
- DTOs utilizando record.
- Bean Validation.
- ResponseEntity.
- Diferença entre 200 OK e 201 Created.

---
## Sprint 4

### Objetivos

Padronizar o tratamento de erros da API e fornecer respostas consistentes para regras de negócio e validações.

### Entregas

- Criação da BlocoJaExisteException.
- Criação da GlobalExceptionHandler.
- Padronização das respostas de erro utilizando ApiError.
- Implementação do CampoErro para detalhamento das validações.
- Tratamento das exceções de domínio (HTTP 409 Conflict).
- Tratamento do Bean Validation (HTTP 400 Bad Request).
- Tratamento de Method Not Allowed (HTTP 404 e 405)
- Testes realizados via Postman.

### Principais aprendizados

- Diferença entre exceções de domínio e exceções de infraestrutura.
- Criação de exceções customizadas.
- Funcionamento do @RestControllerAdvice.
- Uso do @ExceptionHandler.
- Construção de respostas HTTP padronizadas.
- Bean Validation e MethodArgumentNotValidException.
- Collections (List e ArrayList).
- Enhanced for (for-each).
- Programar para interfaces, não para implementações.
---
## Sprint 5

### Objetivos

Disponibilizar consultas e atualização de blocos, consolidando a padronização das respostas da API e aplicando regras de negócio durante a alteração de dados.

### Entregas

- Implementação do endpoint `GET /bloco`.
- Implementação do endpoint `GET /bloco/{id}`.
- Implementação do endpoint `PUT /bloco/{id}`.
- Implementação do endpoint `DELETE /bloco/{id}`.
- Criação do `BlocoResponseDTO`.
- Criação do `AtualizarBlocoDTO`.
- Conversão de Entity para DTO.
- Utilização de `Optional` para busca por identificador.
- Criação do método de domínio `alterarIdentificacao()`.
- Implementação da regra de atualização com validação de duplicidade.
- Reutilização da `BlocoNaoEncontradoException`.
- Tratamento das exceções:
  - `MethodArgumentTypeMismatchException`.
  - `NoResourceFoundException`.
- Testes realizados via Postman.

### Principais aprendizados

- `Optional`.
- `orElseThrow()`.
- `Supplier`.
- Expressões Lambda.
- `@PathVariable`.
- Conversão automática de parâmetros pelo Spring.
- Diferença entre `existsBy` e `findBy`.
- Atualização de recursos utilizando `PUT`.
- Exclusão de recursos utilizando `DELETE`.
- Métodos de domínio para proteger o estado da entidade.
- Comparação entre objetos utilizando `equals()`.
- Conversão de Entity para DTO.
- Diferença entre recurso inexistente e endpoint inexistente.
- Padronização das respostas de erro da API.

### Principais decisões arquiteturais

- Manter a camada **Service** responsável pelas regras de negócio da atualização e exclusão.
- Reutilizar exceções de domínio existentes, evitando duplicação de código.
- Utilizar métodos de domínio na entidade (`alterarIdentificacao`) em vez de expor setters públicos.
- Retornar sempre `BlocoResponseDTO`, evitando expor diretamente a entidade.
- Considerar a atualização do próprio registro como um caso válido, impedindo conflito apenas quando a identificação já pertence a outro bloco.
- Validar previamente a existência do recurso antes da exclusão, mantendo respostas HTTP padronizadas.

---
## Sprint 6

### Objetivos

Iniciar o domínio de apartamentos, implementando o relacionamento com blocos e consolidando o tratamento padronizado de erros da API.

### Entregas

- Criação da entidade `Apartamento`.
- Implementação do relacionamento `@ManyToOne` entre Apartamento e Bloco.
- Utilização de `FetchType.LAZY` no relacionamento.
- Criação da migration `V2__create_table_apartamento.sql`.
- Criação da Foreign Key `apartamento.bloco_id -> bloco.id`.
- Criação da restrição de unicidade composta `(bloco_id, numero)`.
- Criação do `ApartamentoRepository`.
- Implementação da consulta derivada `existsByNumeroAndBlocoId`.
- Criação do `ApartamentoService`.
- Validação da existência do bloco antes do cadastro.
- Validação de duplicidade do número do apartamento dentro do mesmo bloco.
- Criação da `ApartamentoJaExisteNoBlocoException`.
- Criação do `CadastrarApartamentoDTO`.
- Criação do `ApartamentoResponseDTO`.
- Implementação do endpoint `POST /apartamentos`.
- Retorno HTTP `201 Created`.
- Validação dos dados de entrada com Bean Validation.
- Tratamento de `HttpMessageNotReadableException` para corpo de requisição inválido ou incompatível.
- Refatoração do `GlobalExceptionHandler`.
- Centralização da criação de respostas `ApiError` no método auxiliar `criarRespostaErro()`.
- Testes dos principais cenários utilizando Postman.

### Principais aprendizados

- Relacionamentos entre entidades utilizando `@ManyToOne`.
- Diferença entre relacionamento no banco de dados e navegação entre objetos Java.
- Utilização de `FetchType.LAZY`.
- Foreign Keys.
- Restrições de unicidade compostas.
- Derived Queries utilizando propriedades de entidades relacionadas.
- Diferença entre validação de DTO e proteção do estado da entidade.
- `@NotNull`, `@NotBlank`, `@Size` e `@Pattern`.
- Conversão automática realizada pelo Jackson.
- Diferença entre Bean Validation e erros de desserialização do JSON.
- Funcionamento do `HttpMessageNotReadableException`.
- Refatoração sem alteração de comportamento.
- Identificação e remoção de duplicação de código.

### Principais decisões arquiteturais

- Manter o relacionamento inicialmente unidirecional de `Apartamento` para `Bloco`, evitando adicionar `@OneToMany` sem um caso de uso que justifique.
- Manter `blocoId` no contrato de entrada da API e converter esse identificador para uma entidade `Bloco` válida na camada Service.
- Não expor diretamente entidades JPA nas respostas da API.
- Retornar no `ApartamentoResponseDTO` tanto o identificador quanto a identificação do bloco.
- Garantir a unicidade do apartamento dentro do bloco tanto na aplicação quanto no banco de dados.
- Utilizar Bean Validation no DTO para validar o contrato HTTP.
- Manter a entidade responsável por impedir estados inválidos básicos.
- Centralizar a construção do `ApiError` dentro do `GlobalExceptionHandler`, evitando duplicação sem criar uma nova abstração prematuramente.
- Manter inicialmente uma resposta geral para `HttpMessageNotReadableException`, deixando a identificação específica do campo como possível evolução futura.
---
## Backlog Técnico

- [x] Centralizar a criação do `ApiError` para reduzir repetição no `GlobalExceptionHandler`.
- [ ] Centralizar normalização de textos (`trim + uppercase`) para evitar duplicação entre Services.
- [ ] Impedir exclusão de blocos que possuam apartamentos vinculados.
- [ ] Melhorar o tratamento de `HttpMessageNotReadableException` para identificar o campo inválido quando o Jackson disponibilizar essa informação.
---

# 👨‍💻 Autor

**Jackson Medeiros**

Projeto desenvolvido com foco na construção de software utilizando boas práticas de arquitetura, versionamento de banco de dados e organização por domínio.

A Inteligência Artificial foi utilizada como apoio técnico durante o desenvolvimento, atuando como um Tech Lead em discussões de arquitetura, revisão de código e evolução das soluções, enquanto toda a implementação foi construída a partir do entendimento dos conceitos estudados.
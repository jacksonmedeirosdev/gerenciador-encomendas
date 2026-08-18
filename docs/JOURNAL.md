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
- Implementação do endpoint `GET /apartamentos`.
- Implementação do endpoint `GET /apartamentos/{id}`.
- Conversão da lista de entidades `Apartamento` para `List<ApartamentoResponseDTO>`.
- Retorno de lista vazia com HTTP `200 OK` quando não existem apartamentos cadastrados.
- Criação da `ApartamentoNaoEncontradoException`.
- Tratamento padronizado de apartamento inexistente com HTTP `404 Not Found`.
- Reutilização do tratamento de `MethodArgumentTypeMismatchException` para identificadores inválidos na URL.
- Testes dos endpoints utilizando Postman.
- Criação do `AtualizarApartamentoDTO`.
- Implementação do endpoint `PUT /apartamentos/{id}`.
- Validação da existência do apartamento antes da atualização.
- Validação da existência do bloco informado no corpo da requisição.
- Validação de conflito da combinação `(numero, blocoId)` durante a atualização.
- Reutilização da `ApartamentoJaExisteNoBlocoException` para conflitos de duplicidade.
- Criação do método de domínio `alterarNumeroEBloco()`.
- Implementação do endpoint `DELETE /apartamentos/{id}`.
- Validação da existência do apartamento antes da exclusão.
- Retorno HTTP `204 No Content` após exclusão bem-sucedida.
- Testes dos fluxos de atualização e exclusão utilizando Postman.

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
- Reutilização do padrão Controller → Service → Repository em um novo domínio.
- Conversão de coleções de entidades para coleções de DTOs.
- Comportamento do `findAll()` quando não existem registros.
- Reutilização de `findById()`, `Optional` e `orElseThrow()`.
- Diferença entre o número de negócio do apartamento e seu identificador (`id`).
- Importância da nomenclatura dos métodos para representar corretamente sua intenção.
- Acesso a entidades relacionadas durante a montagem de DTOs.
- Introdução conceitual ao possível problema de N+1 consultas em relacionamentos `LAZY`, sem otimização prematura.
- Atualização de recursos relacionados utilizando `PUT`.
- Diferença entre identificar o recurso pela URL e definir seu novo estado pelo body.
- Validação de conflito utilizando uma chave de negócio composta.
- Uso de `Optional` para identificar se outra entidade ocupa a combinação desejada.
- Diferença entre "recurso não encontrado" e "combinação disponível".
- Reutilização de exceções de domínio quando a mesma regra de negócio é violada em fluxos diferentes.
- Alteração de relacionamentos entre entidades.
- Uso de métodos de domínio para modificar o estado da entidade sem expor setters públicos.
- Exclusão com validação prévia de existência para manter respostas HTTP padronizadas.

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
- Retornar `200 OK` com lista vazia nas consultas de coleção sem resultados, em vez de `404 Not Found`.
- Manter a conversão de `Apartamento` para `ApartamentoResponseDTO` na camada Service.
- Não otimizar antecipadamente o carregamento do relacionamento `LAZY`, deixando melhorias de performance para quando houver necessidade real.
- Permitir a alteração conjunta de número e bloco do apartamento no mesmo `PUT`.
- Tratar `numero + bloco` como combinação de negócio para validação de duplicidade.
- Utilizar `findByNumeroAndBlocoId` na atualização para identificar se o conflito pertence ao próprio apartamento ou a outro registro.
- Manter a alteração do estado da entidade através do método de domínio `alterarNumeroEBloco()`.
- Validar a existência do recurso antes da exclusão e utilizar `delete(apartamento)` para manter o fluxo explícito.
---
## Sprint 7

### Objetivos

Aumentar a qualidade técnica e a capacidade de apresentação da API, reforçando regras de integridade e iniciando a documentação interativa com OpenAPI/Swagger.

### Entregas

- Implementação da consulta `existsByBlocoId`.
- Criação da `BlocoPossuiApartamentosException`.
- Bloqueio da exclusão de blocos com apartamentos vinculados.
- Retorno HTTP `409 Conflict` para tentativa de exclusão inválida.
- Configuração do Springdoc OpenAPI.
- Criação da configuração global da documentação da API.
- Documentação dos endpoints de Blocos.
- Documentação dos endpoints de Apartamentos.
- Documentação dos principais códigos HTTP de sucesso e erro.
- Associação das respostas de erro ao schema `ApiError`.
- Validação da documentação através do Swagger UI.

### Principais aprendizados

- Consultas de existência com Spring Data JPA.
- Navegação por propriedades relacionadas em derived queries.
- Diferença entre `fix` e `refactor` em Conventional Commits.
- Conceitos de OpenAPI e Swagger UI.
- Uso de `@Tag`, `@Operation`, `@ApiResponses`, `@ApiResponse`, `@Content` e `@Schema`.
- Diferença entre documentar apenas o status HTTP e documentar também o contrato do corpo da resposta.
- Uso de documentação como parte do contrato público de uma API.

### Principais decisões arquiteturais

- Impedir a exclusão de blocos com apartamentos vinculados.
- Permitir a atualização da identificação do bloco mesmo quando houver apartamentos vinculados.
- Documentar os erros da API utilizando o mesmo contrato `ApiError` utilizado em tempo de execução.
- Adiar exemplos personalizados no Swagger para evitar poluição excessiva das Controllers.
---

# Tech Store API

API REST desenvolvida com **Java 17 + Spring Boot 3.2** para gestão.

---

## Estrutura do Projeto

```
src/main/java/com/techstore/api/
├── TechStoreApiApplication.java
├── entity/
│   ├── Empresa.java
│   ├── Funcionario.java
│   ├── Fornecedor.java
│   ├── Categoria.java
│   ├── Produto.java
│   ├── Cliente.java
│   ├── Venda.java
│   └── ItemVenda.java
├── dto/
│   ├── request/
│   │   ├── EmpresaRequest.java
│   │   ├── FuncionarioRequest.java
│   │   ├── FornecedorRequest.java
│   │   ├── CategoriaRequest.java
│   │   ├── ProdutoRequest.java
│   │   ├── ClienteRequest.java
│   │   ├── VendaRequest.java
│   │   └── ItemVendaRequest.java
│   └── response/
│       ├── EmpresaResponse.java
│       ├── FuncionarioResponse.java
│       ├── FornecedorResponse.java
│       ├── CategoriaResponse.java
│       ├── ProdutoResponse.java
│       ├── ClienteResponse.java
│       ├── VendaResponse.java
│       └── ItemVendaResponse.java
├── repository/
│   ├── EmpresaRepository.java
│   ├── FuncionarioRepository.java
│   ├── FornecedorRepository.java
│   ├── CategoriaRepository.java
│   ├── ProdutoRepository.java
│   ├── ClienteRepository.java
│   ├── VendaRepository.java
│   └── ItemVendaRepository.java
├── service/
│   ├── EmpresaService.java
│   ├── FuncionarioService.java
│   ├── FornecedorService.java
│   ├── CategoriaService.java
│   ├── ProdutoService.java
│   ├── ClienteService.java
│   └── VendaService.java
├── controller/
│   ├── EmpresaController.java
│   ├── FuncionarioController.java
│   ├── FornecedorController.java
│   ├── CategoriaController.java
│   ├── ProdutoController.java
│   ├── ClienteController.java
│   └── VendaController.java
└── exception/
    ├── ResourceNotFoundException.java
    ├── BusinessException.java
    └── GlobalExceptionHandler.java
```

---

## Tecnologias

| Tecnologia | Versão |
|---|---|
| Java | 17 |
| Spring Boot | 3.2.5 |
| Spring Data JPA | 3.2.5 |
| Spring Validation | 3.2.5 |
| MySQL Connector | (managed) |
| Lombok | (managed) |
| Maven | 3.x |

---

## Configuração

### 1. Banco de dados
Execute o arquivo `banco_de_dados_corrigido.sql` no MySQL:
```bash
mysql -u root -p < banco_de_dados_corrigido.sql
```

### 2. application.properties
Edite `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:mysql://localhost:3306/tech_store_api?useSSL=false&serverTimezone=America/Sao_Paulo
spring.datasource.username=root
spring.datasource.password=SUA_SENHA
```

### 3. Executar
```bash
mvn spring-boot:run
```

---

## Endpoints

### Empresa `/api/empresas`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/empresas` | Criar empresa |
| GET | `/api/empresas` | Listar todas |
| GET | `/api/empresas/{id}` | Buscar por ID |
| PUT | `/api/empresas/{id}` | Atualizar |
| DELETE | `/api/empresas/{id}` | Deletar |

### Funcionário `/api/funcionarios`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/funcionarios` | Criar funcionário |
| GET | `/api/funcionarios` | Listar todos |
| GET | `/api/funcionarios/{id}` | Buscar por ID |
| GET | `/api/funcionarios/empresa/{idEmpresa}` | Listar por empresa |
| PUT | `/api/funcionarios/{id}` | Atualizar |
| DELETE | `/api/funcionarios/{id}` | Deletar |

### Fornecedor `/api/fornecedores`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/fornecedores` | Criar fornecedor |
| GET | `/api/fornecedores` | Listar todos |
| GET | `/api/fornecedores/{id}` | Buscar por ID |
| PUT | `/api/fornecedores/{id}` | Atualizar |
| DELETE | `/api/fornecedores/{id}` | Deletar |

### Categoria `/api/categorias`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/categorias` | Criar categoria |
| GET | `/api/categorias` | Listar todas |
| GET | `/api/categorias/{id}` | Buscar por ID |
| PUT | `/api/categorias/{id}` | Atualizar |
| DELETE | `/api/categorias/{id}` | Deletar |

### Produto `/api/produtos`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/produtos` | Criar produto |
| GET | `/api/produtos` | Listar todos |
| GET | `/api/produtos/{id}` | Buscar por ID |
| GET | `/api/produtos/buscar?nome=X` | Buscar por nome |
| GET | `/api/produtos/estoque-baixo?quantidade=5` | Estoque abaixo de X |
| PUT | `/api/produtos/{id}` | Atualizar |
| DELETE | `/api/produtos/{id}` | Deletar |

### Cliente `/api/clientes`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/clientes` | Criar cliente |
| GET | `/api/clientes` | Listar todos |
| GET | `/api/clientes/{id}` | Buscar por ID |
| PUT | `/api/clientes/{id}` | Atualizar |
| DELETE | `/api/clientes/{id}` | Deletar |

### Venda `/api/vendas`
| Método | Rota | Descrição |
|---|---|---|
| POST | `/api/vendas` | Registrar venda (baixa estoque automaticamente) |
| GET | `/api/vendas` | Listar todas |
| GET | `/api/vendas/{id}` | Buscar por ID |
| GET | `/api/vendas/cliente/{idCliente}` | Vendas por cliente |
| GET | `/api/vendas/periodo?inicio=...&fim=...` | Vendas por período |
| DELETE | `/api/vendas/{id}` | Deletar |

---

## Exemplo de Requisição — Criar Venda

```json
POST /api/vendas
{
  "idCliente": 1,
  "idFuncionario": 1,
  "metodoPagamento": "CARTAO_CREDITO",
  "itens": [
    { "idProduto": 1, "quantidade": 2 },
    { "idProduto": 3, "quantidade": 1 }
  ]
}
```

A API calcula o total automaticamente e desconta o estoque de cada produto.

---

## Respostas de Erro

| HTTP | Situação |
|---|---|
| 400 | Validação de campos falhou |
| 404 | Recurso não encontrado |
| 409 | Conflito (CPF/CNPJ/e-mail duplicado, estoque insuficiente) |
| 500 | Erro interno |

---

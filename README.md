# 🚗 AutoManager - Gestão Inteligente de Clientes

[![Java](https://img.shields.io/badge/Java-17-orange?logo=java)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-2.6.3-green?logo=spring)](https://spring.io/projects/spring-boot)
[![MySQL](https://img.shields.io/badge/MySQL-8.0-blue?logo=mysql)](https://www.mysql.com/)
[![CI/CD](https://img.shields.io/badge/GitHub%20Actions-Ativo-brightgreen?logo=github-actions)](https://github.com/features/actions)

O **AutoManager** é um microserviço REST robusto projetado para o ecossistema automotivo. O foco desta versão (v2.0) foi elevar o projeto de um simples CRUD para uma aplicação de **nível produção**, aplicando padrões de design (SOLID) e alta escalabilidade.

---

## 🚀 O que mudou na v2.0? (Evolução)

Saímos de uma estrutura básica para uma arquitetura profissional e segura:

* **Segurança com DTOs:** Implementação de camadas de transferência de dados (**Data Transfer Objects**). A API nunca expõe as entidades do banco de dados diretamente, protegendo informações sensíveis.
* **Responsabilidade Única (SRP):** O antigo controlador centralizado foi decomposto em **5 controladores especializados**, tornando a manutenção e a expansão do código muito mais simples.
* **Tratamento Global de Erros:** Implementação de um `@ControllerAdvice`. Agora, a API responde com status HTTP semânticos (404, 400, 201) e mensagens amigáveis em vez de erros brutos do servidor.
* **Padrão Strategy de Atualização:** Lógica de atualização inteligente que preserva dados existentes e evita sobrescritas nulas indesejadas.

---

## 🏗️ Arquitetura do Sistema

O projeto utiliza o modelo de **Arquitetura em Camadas**, garantindo isolamento total entre a interface e os dados:

1.  **Apresentação (REST):** Controladores desacoplados que gerenciam as rotas de entrada.
2.  **Serviço (Business):** Camada que centraliza as regras de negócio e orquestra as operações.
3.  **Modelo (Domain):** Entidades JPA (Cliente, Endereço, Documento, Telefone) com relacionamentos em cascata.
4.  **Persistência (Data):** Repositórios Spring Data JPA com suporte a MySQL e H2.

---
### 📡 Visão Geral da API (Endpoints)

| Método | Rota | Descrição |
| :--- | :--- | :--- |
| **GET** | `/cliente/clientes` | Lista todos os clientes (formato DTO) |
| **GET** | `/cliente/{id}` | Busca detalhes completos por ID | 
| **POST** | `/cliente/cadastro` | Cadastra um novo cliente e dependentes |
| **PUT** | `/cliente/atualizar` | Atualização parcial inteligente |
| **DELETE** | `/cliente/excluir/{id}` | Exclusão em cascata (limpa dependências) |
---
## 🛠️ Tecnologias e Ecossistema

* **Linguagem:** Java 17 (LTS)
* **Framework:** Spring Boot 2.6.3 (Web, Data JPA, DevTools)
* **Persistência:** Hibernate (ORM) e Banco MySQL 8.0
* **Produtividade:** Lombok (Redução de boilerplate)
* **DevOps:** GitHub Actions (Integração Contínua testada em ambiente Linux)

## 🚦 Como Executar o Projeto

### Pré-requisitos
* **JDK 17** instalado e configurado nas variáveis de ambiente.
* **Maven 3.6+** para gerenciamento de dependências.
* **MySQL** rodando localmente (ou use o perfil H2 configurado no `application.properties`).
* **VS Code** com o [Extension Pack for Java](https://marketplace.visualstudio.com/items?itemName=vscjava.vscode-java-pack) instalado.

---

### 🛠️ Execução Recomendada (VS Code)

Esta é a forma mais estável e visual de rodar o projeto:

1.  **Abrir o Projeto:** No VS Code, vá em `File > Open Folder` e selecione a pasta raiz (onde está o arquivo `pom.xml`).
2.  **Importação:** Aguarde o VS Code baixar as dependências do Maven (uma barra de progresso aparecerá no canto inferior direito).
3.  **Execução via Interface:**
    * Abra o arquivo `src/main/java/com/autobots/automanager/AutomanagerApplication.java`.
    * Clique no botão **Run** que aparece logo acima do método `main`.
    * *Alternativa:* Clique com o botão direito sobre o arquivo `pom.xml` para instalar as dependências, e depois execute novamente.

---

### 💻 Execução via Terminal (Opção B)

Caso prefira o terminal ou esteja em um ambiente sem interface gráfica:

```bash
# Clone o repositório
git clone [https://github.com/Templasan/AV1-DevWeb-III.git](https://github.com/Templasan/AV1-DevWeb-III.git)

# Entre na pasta do projeto
cd automanager

# Compile e execute a aplicação
./mvnw spring-boot:run

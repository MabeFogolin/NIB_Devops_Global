
<h1 align="left"> Global Solution: Java Advanced</h1>
<p align="left">O projeto N.I.B. visa desenvolver um aplicativo que permite aos usuários cadastrarem informações de tomadas inteligentes, indicando o valor de consumo apresentaod para ela para que seja possível identificar padrões e o tanto de gastos.</p>

<h2 align="left">Benefícios do Aplicativo</h2>
<ul>
  <li>🩺 <strong>Monitoramento de Gastos:</strong> Acompanhe os seus gastos de energia com a tomada.</li>
  <li>📱 <strong>Acesso Facilitado:</strong> Acesso prático e personalizado.</li>
  <li>🤖 <strong>Dados para Inteligência Artificial:</strong> Geração de base de dados para modelos de IA .</li>
</ul>

<h2 align="left">Objetivos do Projeto</h2>
<ul>
  <li>📊 <strong>Monitoramento de Rotinas:</strong> Registro dos gastos diárias dos usuários. </li>
  <li>🔍 <strong>Identificação de Tendências:</strong> Análise de dados para reconhecer perfis propensos a terem um alto gasto. </li>
</ul>

<h2 align="left">Tecnologias Utilizadas</h2>
<ul>
  <li>⚙️ <strong>Backend:</strong> Java com Spring. </li>
  <li>🗄️ <strong>Banco de Dados:</strong> Oracle. </li>
  <li>🧠 <strong>Inteligência Artificial:</strong> Modelos de machine learning com Python para análise de dados. </li>
  <li> 📱 <strong>Aplicativo:</strong> Construção de aplicativo de tela com a linguagem Kotlin. </li>
  
</ul>

<h2 align="left"> 📆Prazos</h2>
<ul>
  <li> 🗓️ <strong>11/11</strong> LIVE LANÇAMENTO </li> 
  <li> 🗓️ <strong>11/11</strong> CONTEÚDO PARA OS ALUNOS </em> </li>
  <li> 🗓️ <strong>22/11</strong> ENTREGA PORTAL </li> 
</ul>

<h2 align="left"> 📹 Link do vídeo</h2>

```bash
https://youtu.be/MsGUwGbkH_E
```

<h2 align="left"> 📝 Testes dos endpoints</h2>

<h3 align="left"> ☁️ Endpoint Nuvem</h3>

 **Usuário**

```bash
https://global-java-advanced.onrender.com/usuario/todos
```

 **Endereço**

```bash
https://global-java-advanced.onrender.com/enderecos/todos
```

 **Tomadas**

```bash
https://global-java-advanced.onrender.com/tomada/todas
```

 **Funcionário**

```bash
https://global-java-advanced.onrender.com/funcionario/todos
```

<h3 align="left"> 👷‍♀️ Funcionário</h3>

1. **POST**

```bash
  http://localhost:8080/funcionario/criar
{
  "nomeFuncionario": "Joao",
  "sobrenomeFuncionario": "Silva",
  "telefoneFuncionario": "11987654321",
  "emailFuncionario": "joao.silva@example.com"
}
```

2. **GET ALL**

```bash
  http://localhost:8080/funcionario/todos
```

3. **GET Find by ID**

```bash
  http://localhost:8080/funcionario/registroFuncionario/1
```

4. **PUT Update all itens**

```bash
  http://localhost:8080/funcionario/atualizar/1
{
  "nomeFuncionario": "Teste",
  "sobrenomeFuncionario": "Silva",
  "telefoneFuncionario": "11987654321",
  "emailFuncionario": "joao.silva@example.com"
}
```

5. **PATCH Update Email or Telephone**

```bash
   http://localhost:8080/funcionario/atualizar-email-telefone/1
{
   "emailFuncionario": null,
   "telefoneFuncionario": "11912345678"
}
```
6. **DELETE by ID**

```bash
 http://localhost:8080/funcionario/deletar/1
```

<h3 align="left"> 🧝‍♀️ Usuário</h3>

1. **POST**

```bash
  http://localhost:8080/usuario/criar
{
  "cpfUser": "92712116003",
  "nomeUser": "teste cpf",
  "sobrenomeUser": "Fogolin",
  "telefoneUser": 1234567890,
  "dataNascimentoUser": "1990-01-01",
  "emailUser": "maria.fogolin@example.com"
}
```

2. **GET ALL**

```bash
  http://localhost:8080/usuario/todos
```

3. **GET Find by CPF**

```bash
  http://localhost:8080/usuario/cpf/92712116003
```

4. **GET Find by Data nascimento & Page**

```bash
  http://localhost:8080/usuario/nascimento?dataNascimentoUser=1990-01-01&page=0&size=10
```

5. **GET Find by Data nascimento**

```bash
   http://localhost:8080/usuario/nascimento/1990-01-01
```

6. **PUT Update all**

```bash
  http://localhost:8080/usuario/cpf/92712116003
{
  "cpfUser": "92712116003",
  "nomeUser": "teste atualização",
  "sobrenomeUser": "Fogolin",
  "telefoneUser": 1234567890,
  "dataNascimentoUser": "2002-11-01",
  "planoUser": "Premium",
  "emailUser": "maria.fogolin@example.com"
}
```
7. **DELETE by CPF**

```bash
 http://localhost:8080/usuario/cpf/92712116003
```
<h3 align="left"> 🔦 Tomada</h3>

1. **POST**

```bash
  http://localhost:8080/tomada/criar?cpfUser=92712116003
{
  "nomeTomada": "Tomada 01",
  "diaContagem": "2024-11-01",
  "qtdGasta": 150.75,
  "tarifaEletricidade": 0.85,
  "voltagem" : "220"
}
```
2. **GET Find by ID**

```bash
  http://localhost:8080/tomada/1
```
3. **GET Find ALL**

```bash
  http://localhost:8080/tomada/todas
```

4. **GET Find ALL by Usuario**

```bash
  http://localhost:8080/tomada/todas/usuario?cpfUser=92712116003
```

5. **PUT Update all parts**

```bash
 http://localhost:8080/tomada/atualizar/1
{
  "nomeTomada": "Tomada Atualizada",
  "diaContagem": "2024-11-05",
  "qtdGasta": 180.25,
  "tarifaEletricidade": 1.05
}
```
6. **PATCH Update name**

```bash
  http://localhost:8080/tomada/atualizar/1
{
    "nomeTomada" : null,
    "voltagem" : 220
}
```

7. **DELETE by ID**

```bash
  http://localhost:8080/tomada/deletar/1
```

<h3 align="left"> 🗺️ Endereço</h3>

1. **POST**

```bash
 http://localhost:8080/enderecos/criar/92712116003
{
  "ruaEndereco": "endereço",
  "numeroEndereco": 123,
  "complementoEndereco": "Apartamento 45",
  "bairroEndereco": "Jardim das Rosas",
  "cidadeEndereco": "São Paulo",
  "cepEndereco": "12345-678",
  "estadoEndereco": "SP"
}

```
***Atenção, o 1 do path representa o ID da pessoa, podendo ser o ID do funcinário ou o CPF do usuário***

2. **GET Find by ID (Funcionário)**

```bash
  http://localhost:8080/enderecos/funcionario/1
```

3. **GET Find by ID (Usuário)**

```bash
  http://localhost:8080/enderecos/usuario/92712116003
```

4. **GET ALL**

```bash
  http://localhost:8080/enderecos/todos
```

5. **PUT Update all parts**

```bash
  http://localhost:8080/enderecos/atualizar/92712116003
{
  "ruaEndereco": "Rua das Acácias",
  "numeroEndereco": 789,
  "complementoEndereco": "Apartamento 12",
  "bairroEndereco": "Jardim Primavera",
  "cidadeEndereco": "São Paulo",
  "cepEndereco": "98765-432",
  "estadoEndereco": "SP"
}
```

6. **PUT Update some parts**

```bash
  http://localhost:8080/enderecos/92712116003
 {
  "numeroEndereco": null,
  "complementoEndereco": "teste"
}
```
7. **DELETE by ID person**

```bash
  http://localhost:8080/enderecos/deletar/92712116003
```
<h2 align="left"> 🧑‍🤝‍🧑 Integrantes</h2>
<ul>
  <li> Igor Gabriel Pereira Marcondes <strong>RM 553544 </strong></li>
  <dl> 
    <dt> <strong> Matérias responsáveis </strong></dt>
    <dd>  COMPLIANCE & QUALITY ASSURANCE </dd>
    <dd>  MOBILE APP DEVELOPMENT </dd>
  </dl>
  <li> Maria Beatriz Reis Fogolin de Godoy <strong>RM 552669 </strong></li>
   <dl> 
    <dt> <strong> Matérias responsáveis </strong></dt>
         <dd>  DEVOPS TOOLS E CLOUD COMPUTING </dd>
          <dd> MASTERING RELATIONAL AND NON RELATIONAL DATABASE </dd>
          <dd> JAVA ADVANCED  </dd>
  </dl>
  <li> Nicholas Pereira Paulo Lima Barbosa <strong>RM 552744 </strong></li>
  <dl> 
    <dt> <strong> Matérias responsáveis </strong></dt>
    <dd>  ADVANCED BUSINESS DEVELOPMENT WITH .NET </dd>
    <dd>  DISRUPTIVE ARCHITECTURES: IOT, IOB & GENERATIVE IA </dd>
 </dd>
  </dl>
</ul>

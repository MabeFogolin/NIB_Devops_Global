
<h1 align="left"> Global Solution: DEVOPS TOOLS E CLOUD COMPUTING</h1>
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


<h3 align="left"> 🧝‍♀️ Usuário</h3>

1. **POST**

```bash
Invoke-RestMethod -Uri http://40.90.198.227:8080/usuario/criar -Method Post -Headers @{"Content-Type"="application/json"} -Body '{"cpfUser": "92712116003", "nomeUser": "teste cpf", "sobrenomeUser": "Fogolin", "telefoneUser": 1234567890, "dataNascimentoUser": "1990-01-01", "emailUser": "maria.fogolin@example.com"}'
```

2. **GET ALL**

```bash
 Invoke-RestMethod -Uri http://40.90.198.227:8080/usuario/todos -Method Get
```

3. **GET Find by CPF**

```bash
Invoke-RestMethod -Uri http://40.90.198.227:8080/usuario/cpf/92712116003 -Method Get
```

4. **GET Find by Data nascimento & Page**

```bash
Invoke-RestMethod -Uri "http://40.90.198.227:8080/usuario/nascimento?dataNascimentoUser=1990-01-01&page=0&size=10" -Method Get
```

5. **GET Find by Data nascimento**

```bash
Invoke-RestMethod -Uri http://40.90.198.227:8080/usuario/nascimento/1990-01-01 -Method Get
```

6. **PUT Update all**

```bash
Invoke-RestMethod -Uri http://40.90.198.227:8080/usuario/cpf/92712116003 -Method Put -Headers @{"Content-Type"="application/json"} -Body '{"cpfUser": "92712116003", "nomeUser": "teste atualização", "sobrenomeUser": "Fogolin", "telefoneUser": 1234567890, "dataNascimentoUser": "2002-11-01", "planoUser": "Premium", "emailUser": "maria.fogolin@example.com"}'
```
7. **DELETE by CPF**

```bash
Invoke-RestMethod -Uri http://40.90.198.227:8080/usuario/cpf/92712116003 -Method Delete
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

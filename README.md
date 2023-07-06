# Energy API

Projeto fase 1 - Pós TECH.

## 🚀 Começando

Essas instruções permitirão que você obtenha uma cópia do projeto em operação na sua máquina local para fins de desenvolvimento e teste.

### 📋 Pré-requisitos
Será necessário as seguintes tecnologias:
```
Java 17
Maven
Docker ou instalar postgreSQL (de preferência 12)
```

## 📦 Implantação

Após realizar o donwload do repositório para sua máquina. 
Configure o projeto apontando para JAVA 17.

De um update nas depências do maven.

Caso prefira utilizar o banco instalando o mesmo na máquina.
É só seguir a documentação, Após alterar no properties.yml os campos: user, password e database para os criados no postgreSQL.

Caso prefira rodar o postgreSQL com docker, execute o comando a seguir.

```
docker run --name db-energy -e POSTGRES_PASSWORD=minha-senha -p 5432:5432 -d postgres:<versão>
```

 --name db-energy: é o nome da imagem docker.

 Após a criação via docker alterar campos no properties.yml se necessário.

 ## 🛠️ Dados de entrada e saída

 Após rodar a aplicação é possível ver e testar os endpoints pela documentação gerada pelo Swagger.
 http://localhost:8080/api/v1/swagger-ui/index.html

** /api/v1/person/register **

```json
  {
  "firstName": "uNTDwnxzpTxIMlsZuuyecKqPX",
  "lastName": "YjlkNmV",
  "gender": "Feminino",
  "email": "teste@teste.com",
  "cpf": "21627745939",
  "dateOfBirth": "2000-07-06",
  "kinship": [
    {
      "relationshipType": "string",
      "prFirstName": "bkLbxaeKXkaeGpocbtINsXDsK",
      "prLastName": "tezDgpNokdQaGxwijwIdqoxQU",
      "prGender": "Feminino",
      "prDateOfBirth": "2003-07-06",
      "prEmail": "parente@teste.com",
      "prCpf": "10375905030"
    }
  ]
}
```
** SUCCESS - HTTP STATUS - 201**

```json
{
  "[Person]": "Usuário registrado com sucesso"
}
```

** BAD REQUEST - HTTP STATUS - 400 **

```json
{
  "httpStatus": [
    "400 BAD_REQUEST"
  ],
  "errors": [
    "[Person] O cpf 21627745939 já está associado a um usuário"
  ]
}
```

** VALIDATION INPUTS - BAD REQUESTS - 400 **

```json
{
  "Errors": [
    "kinship[0].prEmail: The email is invalid.",
    "email: The email is invalid.",
    "dateOfBirth: The date of birth in date of birth field must be before the current date.",
    "cpf: The CPF is invalid.",
    "kinship[0].prCpf: The CPF is invalid.",
    "kinship[0].prDateOfBirth: The date of birth in date of birth field must be before the current date."
  ],
  "Http Status": [
    "400 BAD_REQUEST"
  ]
}
```

** /api/v1/home-appliance/register **

```json
{
  "name": "HxHfgNddGPSCrnEPMdFpWDUBW",
  "model": "string",
  "brand": "dedgOxKBYHaJEIGbUibsaxkuB",
  "volts": "110v",
  "watts": "IAQCkNusFc"
}
```

** SUCCESS - HTTP STATUS - 201**

```json
{
  "[Home Appliance]": "Eletrodoméstico registrado com sucesso"
}
```

** VALIDATION INPUTS - BAD REQUESTS - 400 **

```json
{
  "Errors": [
    "name: The name field must contain only letters."
  ],
  "Http Status": [
    "400 BAD_REQUEST"
  ]
}
```

** /api/v1/address/register **

```json
{
  "road": "JpElcLIsiKWMLghiGPYSlEWGFFXySAYlfRCeaxDpgvKoAsOSuBDI",
  "number": 0,
  "district": "string",
  "city": "string"
}
```

** SUCCESS - HTTP STATUS - 201**

```json
{
  "[Address]": "Endereço registrado com sucesso"
}
```

** VALIDATION INPUTS - BAD REQUESTS - 400 **

```json
{
  "Errors": [
    "number: The { Number } field cannot be negative"
  ],
  "Http Status": [
    "400 BAD_REQUEST"
  ]
}
```

## 🛠️ Construído com

Mencione as ferramentas que você usou para criar seu projeto

* [SpringBoot]([http://www.dropwizard.io/1.0.2/docs/](https://docs.spring.io/spring-boot/docs/current/reference/htmlsingle/)) - O framework Java utilizado.
* [Maven](https://maven.apache.org/) - Gerente de Dependência.
* [PostgreSQL](https://www.postgresql.org/docs/) - Banco de dado relacional utilizado.
* [Docker](https://hub.docker.com/_/docker-docs) - Caso prefira executar o postgres via docker, assim não precisa instalar o postgreSQL na máquina.

## 📌 Versão

Para versionamento foi utilizado o GIT. 

## ✒️ Autor

* **Aluno:** - [Hernandes Andrade](https://github.com/HernandesHD)


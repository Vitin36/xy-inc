## Ponto de Interesse
Repositório do projeto: -> https://github.com/Vitin36/xy-inc.git

### Instruções para execução do projeto
- Clonar repositório.
- Importar o repositório no SpringBoot.
- Instalar depêndencias maven.
- Run As > Spring Boot App
- Testar Endpoints pelo browser de preferência.

#### Servicos

Serviço para cadastrar um pontos com 3 atributos.
```
POST - http://localhost:8080/ponto-de-interesse
```

Serviço para listar todos os pontos cadastrados.
```
GET - http://localhost:8080/ponto-de-interesse
```

Serviço para listar os pontos por proximidade.
```
GET - http://localhost:8080/ponto-de-interesse?x=20&y=10&dMax=20
```

### Instruções para as execuções de testes

Os testes foram implementados para o serviço e o controller.

Para executar os testes do controller.
- src/main/test/java/com.xyinc.controlador
- Run As JUnit test na classe PonteInteresseControladorTest

Para executar os testes do servico
- src/main/test/java/com.xyinc.servico
- Run As JUnit test na classe PonteInteresseServicoTest



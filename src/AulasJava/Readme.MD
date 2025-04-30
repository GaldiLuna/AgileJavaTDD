Pasta criada para melhorar o meu entendimento do Java, suas classes, objetos, construtores, métodos e tudo mais.

Atributos são as características que a Classe tem.
Métodos são as ações/operações que posso fazer na Classe.
Atributos são representados por substantivos.
Métodos são representados por verbos.

Classe é uma estrutura/modelo/fôrma de algo.
Objeto é o resultado da algo feito sobre uma Classe.
Classes são usadas para instanciar Objetos.
Objetos são instâncias de Classes.
Por exemplo: a Bíblia seria a Classe e os livros seus Objetos.

Cada arquivo .java só pode ter uma Classe definida como pública.
UML - Unified Modeling Language:
é utilizada para documentar sistemas orientados a objetos;
é composta por diversos diagramas, sendo um deles o Diagrama de Classes, onde são mostradas as classes do sistema, juntamente com seus respectivos métodos e atributos.

Sobrecarregar um método significa criar outros métodos com o mesmo nome, porém com assinatura diferente.
Para fazer a instância de um objeto é utilizado o new.

Métodos podem ser sobrecarregados quando tiverem o mesmo nome porém com parâmetros diferentes.
Metódos getter e setter tem os benefícios de proteger os atributos e evitar a mudança de código em vários lugares.

Um Construtor sempre terá o mesmo nome da classe, diferentemente dos métodos que são nomeados como verbos (palavras de ação/ordem).

O modificador static pertence à classe e não aos objetos da classe, não sendo mais necessário fazer a criação de um objeto para que possa chamar pelo método, podendo fazer isso fazendo a chamada da classe e seu método estático desejado.

Para que uma classe possa herdar atributos e métodos de outra classe basta incluir o extends.
Podemos modificar um comportamento herdado de outra classe utilizando o overriding.
Na herança uma instância pode chamar apenas os métodos globais ou pode chamar os métodos locais(específicos) + os globais de acordo com a forma que foi criada essa instância.

Quando um atributo de classe é definido como public, isso quer dizer que esse atributo pode ser lido em qualquer parte do código, quando esse atributo é definido como private, isso quer dizer que apenas a classe que foi definido pode ler livremente esse atributo, sendo necessário chamar métodos getters para que outras classes possam ter acesso e quando os atributos saõ definidos como protected, isso quer dizer que todas as classes e subclasses tem acesso a leitura desse atributo.

Não existe herança de construtores, existe a chamada da superclasse super(); dentro do construtor.

* TDD::

Princípio F.I.R.S.T.
Fast - os testes unitários devem executar rapidamente;
Independent - os testes unitários devem ser independentes uns dos outros;
Repeatable - os testes unitários devem ser repetitíveis;
Self-validating - os testes unitários devem se auto validarem;
Thorought $ Timely - os testes unitários devem cobrir casos extremos.

Pirâmide de Testes:
Os testes unitários estão na base da pirâmide pois são escritos antes de qualquer outro tipo de testes, são muito rápidos e são testes de pequenos trechos de código isolados que utilizam objetos fakes ou mockadas.
Os testes de integração estão no meio da pirâmide e neles não são usados objetos fakes e nem mokados, neles tem acessos reais aos banco de dados ou enviar requisições HTTP reais e por isso são mais lentos que os testes unitários.
Testes ponta-a-ponta (end-to-end) estão no topo da pirâmide e são testes automatizados da funcionalidade de software/aplicação, são ainda mais lentos que os testes de integração.

JUnit:
É um framework de testes que permite fazer e implementar testes unitários.

ESTUDAR AINDA MAIS!!!

Lembrar:
Avançar na leitura do livro e fazer o curso na Udemy e DIO para ajudar.
Fé em Deus que vai dar certo!
Me ajuda Pai!
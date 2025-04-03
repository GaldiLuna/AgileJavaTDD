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


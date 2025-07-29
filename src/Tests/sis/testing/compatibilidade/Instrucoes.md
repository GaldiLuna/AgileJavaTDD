Como Executar este Teste:

    1. Crie os arquivos: Crie MinhaAnotacao.java (Versão 1) e ClasseComAnotacao.java 
        em um diretório apropriado (ex: src/compatibilidade/teste).

    2. Compile a Versão 1: Use javac MinhaAnotacao.java ClasseComAnotacao.java.

    3. Mantenha os .class originais: Certifique-se de que MinhaAnotacao.class e 
        ClasseComAnotacao.class (gerados pela versão 1 da anotação) estejam em algum 
        lugar no seu classpath.

    4. Edite MinhaAnotacao.java para a Versão 2: Modifique o arquivo Java da anotação 
        conforme a "Versão 2" acima (removendo default de valor e removendo infoV1).

    5. Compile APENAS a Versão 2 de MinhaAnotacao.java e CompatibilidadeTeste.java: javac 
        MinhaAnotacao.java CompatibilidadeTeste.java. Não recompile ClasseComAnotacao.java!

    6. Execute CompatibilidadeTeste: java -ea compatibilidade.teste.CompatibilidadeTeste.

Você deverá ver uma AnnotationFormatError ou IncompatibleClassChangeError ao tentar executar 
CompatibilidadeTeste, demonstrando que a alteração na anotação (MinhaAnotacao) quebrou a 
compatibilidade com classes que foram compiladas com a versão anterior da anotação.

Este tipo de teste é essencial para entender como as alterações em tipos de anotação podem 
impactar o código que já foi compilado, especialmente em ambientes onde você pode ter 
diferentes versões de bibliotecas ou classes em jogo. Ele ilustra a importância de ser 
cuidadoso ao modificar anotações que já estão em uso.
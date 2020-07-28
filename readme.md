# Primeiro carregamento do projeto

Caso haja problemas quanto ao reconhecimento dos submodulos como dependências

mvn clean install

Para gerar o uber/fat jar com dependências para cada submodulo

mvn assembly:single


# Para executar os arterfatos

Acessar o prompt de comando

Executar o comando: start java.exe "CAMINHO_PRO_JAR\FATJAR.jar"
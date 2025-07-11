package Tests.sis.search;

import util.StringUtil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class Search {
    private URL url;
    private String searchString;
    private int matches = 0;
    private Exception exception = null;

    public Search(String urlString, String searchString) throws IOException {
        this.url = new URL(urlString);
        this.searchString = searchString;
    }

    public String getText() {
        return searchString;
    }

    public String getUrl() {
        return url.toString();
    }

    public int matches() {
        return matches;
    }

    public boolean errored() {
        return exception != null;
    }

    public Exception getError() {
        return exception;
    }

    public void execute() {
        try {
            searchUrl();
        }
        catch (IOException e) {
            exception = e;
        }
    }

    private void searchUrl() throws IOException {
        URLConnection connection = url.openConnection();
        InputStream input = connection.getInputStream();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(input));
            String line;
            while ((line = reader.readLine()) != null)
                matches += StringUtil.occurrences(line, searchString);
        }
        finally {
            if (reader != null)
                reader.close();
        }
    }
}

/**
 * Os parênteses devem ajudá-lo a entender este código. Primeiro, o resultado de reader.readLine() é atribuído à variável de referência line. O valor da referência line é comparado com null para determinar se o loop while deve ou não terminar.
 *
 * A parte ruim é que a classe Search deve mudar. Para obter um InputStream de uma URL com um protocolo de arquivo, você deve extrair as informações do caminho da URL e usá-las para abrir o fluxo como um FileInputStream. Não é uma mudança significativa. A pequena desvantagem é que agora você tem um pouco de código em seu sistema de produção que provavelmente só será usado pelo teste.
 * private void searchUrl() throws IOException {
 *     InputStream input = getInputStream(url);
 *     BufferedReader reader = null;
 *     try {
 *         reader = new BufferedReader(new InputStreamReader(input));
 *         String line;
 *         while ((line = reader.readLine()) != null)
 *             matches += StringUtil.occurrences(line, searchString);
 *     }
 *     finally {
 *         if (reader != null)
 *             reader.close();
 *     }
 * }
 *
 * private InputStream getInputStream(URL url) throws IOException {
 *     if (url.getProtocol().startsWith("http")) {
 *         URLConnection connection = url.openConnection();
 *         return connection.getInputStream();
 *     }
 *     else if (url.getProtocol().equals("file")) {
 *         return new FileInputStream(url.getPath());
 *     }
 *     return null;
 * }
 *
 * Outra coisa que você deve considerar é que você não está mais exercitando a porção do código que lida com uma URL HTTP. A melhor abordagem é garantir que você tenha cobertura adequada em um nível de teste de aceitação.[3] Testes de aceitação fornecem um nível de teste acima do teste de unidade – eles testam o sistema do ponto de vista do usuário final. Eles são executados contra um sistema o mais real possível e não devem usar mocks. Para a aplicação de busca, você certamente executaria testes de aceitação que usassem URLs de web reais.
 *
 * [3] Existem muitas nomenclaturas diferentes para os diversos tipos de teste. Testes de aceitação são os testes que significam que o sistema atende aos critérios de aceitação do cliente para o qual é entregue. Você pode ouvir esses testes serem chamados de testes de cliente.
 */

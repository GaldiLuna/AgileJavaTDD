package util;

import java.io.*;

public class Command {
    private String command;
    private Process process;
    private StringBuilder output = new StringBuilder();
    private StringBuilder errorOutput = new StringBuilder();

    public Command(String command) {
        this.command = command;
    }

    public void execute() throws Exception {
        process = new ProcessBuilder(command).start(); // 1
        collectOutput();
        collectErrorOutput();
        process.waitFor(); // 2
    }

    private void collectErrorOutput() { // 3
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    collectOutput(process.getErrorStream(), errorOutput); // 4
                } catch (IOException e) {
                    errorOutput.append(e.getMessage());
                }
            }
        };
        new Thread(runnable).start();
    }

    private void collectOutput() { // 5
        Runnable runnable = new Runnable() {
            public void run() {
                try {
                    collectOutput(process.getInputStream(), output); // 6
                } catch (IOException e) {
                    output.append(e.getMessage());
                }
            }
        };
        new Thread(runnable).start();
    }

    private void collectOutput( // 7
                                InputStream inputStream, StringBuilder collector)
            throws IOException {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null)
                collector.append(line);
        }
        finally {
            reader.close();
        }
    }

    public String getOutput() throws IOException {
        return output.toString();
    }

    public String getErrorOutput() throws IOException {
        return output.toString();
    }

    public String toString() {
        return command;
    }
}

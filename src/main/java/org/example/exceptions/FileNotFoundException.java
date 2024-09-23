package org.example.exceptions;

public class FileNotFoundException extends Exception {
    private String filePath;

    public FileNotFoundException(String message, String filePath) {
        super(message);
        this.filePath = filePath;
    }

    public String getFilePath() {
        return filePath;
    }
}

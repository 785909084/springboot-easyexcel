package com.al;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;

public class FileBase64Utils {

    public static String encodeFileToBase64(File file) throws IOException {
        if (!file.exists()) {
            throw new FileNotFoundException("File not found: " + file.getAbsolutePath());
        }
        if (file.length() == 0) {
            throw new IllegalArgumentException("File is empty: " + file.getAbsolutePath());
        }

        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }

    public static void decodeBase64ToFile(String base64String, String fileName) throws IOException {
        if (base64String.isEmpty()) {
            throw new IllegalArgumentException("Base64 string is empty");
        }

        byte[] decodedBytes = Base64.getDecoder().decode(base64String);
        Path outputPath = Paths.get (fileName);
        Files.write(outputPath, decodedBytes);
    }

    public static void main(String[] args) {
        try {
            // 文件转换为Base64编码字符串
            File file = new File("path/to/file.txt");
            String base64String = encodeFileToBase64(file);
            System.out.println("Base64 encoded string: " + base64String);

            // Base64编码字符串解码为文件
            String decodedFileName = "restored_file.txt";
            decodeBase64ToFile(base64String, decodedFileName);
            System.out.println("File restored successfully as: " + decodedFileName);
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Invalid input: " + e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

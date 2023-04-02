import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.stream.Stream;


import org.json.JSONObject;

public class NaverPapagoTranslate {

    private static final String CLIENT_ID = System.getenv("MY_CLIENT_ID");
    private static final String CLIENT_SECRET = System.getenv("MY_CLIENT_SECRET");

    private static final String OUTPUT_FILE_PATH = "output.csv";

    public static void main(String[] args) {
        try {
            String inputFilePath = "/Users/ichunghui/CapstoneDesign/PapgoTranslate/PapagoTranslate/src/main/java/caps_train.csv"; // CSV 파일 경로를 변경하십시오.
            processCsvFile(inputFilePath);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void processCsvFile(String inputFilePath) throws Exception {
        Path outputPath = Paths.get(OUTPUT_FILE_PATH);
        int lastTranslatedLine = 0;

        // output.csv 파일이 이미 존재하는 경우 마지막 번역된 행의 번호를 추출합니다.
        if (Files.exists(outputPath)) {
            try (Stream<String> lines = Files.lines(outputPath)) {
                lastTranslatedLine = (int) lines.count();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try (Stream<String> lines = Files.lines(Paths.get(inputFilePath))) {
            lines.skip(lastTranslatedLine).forEach(line -> {
                try {
                    String[] parts = line.split(",", 2);
                    String number = parts[0];
                    String textToTranslate = parts[1];

                    String translatedText = translateEnglishToKorean(textToTranslate);

                    String outputLine = number + "," + translatedText;
                    System.out.println(outputLine);

                    try {
                        Files.write(outputPath, (outputLine + System.lineSeparator()).getBytes(StandardCharsets.UTF_8), StandardOpenOption.CREATE, StandardOpenOption.APPEND);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
        }
    }

    private static String translateEnglishToKorean(String text) throws Exception {
        String apiURL = "https://openapi.naver.com/v1/papago/n2mt";
        String sourceLang = "en";
        String targetLang = "ko";
        String postParams = "source=" + sourceLang + "&target=" + targetLang + "&text=" + URLEncoder.encode(text, "UTF-8");

        URL url = new URL(apiURL);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8");
        connection.setRequestProperty("X-Naver-Client-Id", CLIENT_ID);
        connection.setRequestProperty("X-Naver-Client-Secret", CLIENT_SECRET);

        connection.setDoOutput(true);
        DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream());
        outputStream.write(postParams.getBytes(StandardCharsets.UTF_8));
        outputStream.flush();
        outputStream.close();

        int responseCode = connection.getResponseCode();
        BufferedReader bufferedReader;
        if (responseCode == HttpURLConnection.HTTP_OK) {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        } else {
            bufferedReader = new BufferedReader(new InputStreamReader(connection.getErrorStream()));
        }

        StringBuilder response = new StringBuilder();
        String inputLine;
        while ((inputLine = bufferedReader.readLine()) != null) {
            response.append(inputLine);
        }

        bufferedReader.close();

        JSONObject jsonResponse = new JSONObject(response.toString());
        String translatedText = jsonResponse.getJSONObject("message").getJSONObject("result").getString("translatedText");

        return translatedText;
    }
}



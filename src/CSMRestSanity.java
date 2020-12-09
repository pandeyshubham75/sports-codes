//import javax.xml.crypto.Data;
//import java.io.IOException;
//import java.net.*;
//import java.net.http.HttpClient;
//import java.net.http.HttpRequest;
//import java.net.http.HttpResponse;
//import java.util.Date;
//import java.util.HashMap;
//import java.util.Map;
//
//public class CSMRestSanity {
//    public static void main (String ar[]) {
//        String baseUrl = "http://slc11wjy.us.oracle.com:7101";
//        boolean demo = true;
//
//        String basePath = demo ? "/USBSandboxDemo/applcoreApi/v1/csm/" : "/fscmUI/applcoreApi/v1/csm/";
//
//        if (baseUrl == null || baseUrl.isEmpty()) {
//            System.out.println("Not a valid base URL");
//            return;
//        }
//        String formedUrl = baseUrl + basePath;
//        System.out.println("Starting CSM Rest Sanity.....");
//        System.out.println("Formed URL is: "+ formedUrl);
//        String csId = startExport(formedUrl);
//    }
//
//    public static String startExport(String formedUrl) {
//        System.out.println("Starting EXPORT........");
//        Map<String,String> values = new HashMap<String, String>() {{
//            put("name", "Sanity_Test_CS");
//            put ("description", "Test CS for Sanity");
//        }};
//        ObjectMapper objectMapper = new ObjectMapper();
//        String requestBody = objectMapper
//                .writeValueAsString(values);
//        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder()
//                .uri(URI.create(formedUrl + "createcustomizationset"))
//                .POST(HttpRequest.BodyPublishers.ofString(requestBody))
//                .build();
//        HttpResponse<String> response = null;
//        try {
//            response = client.send(request,
//                    HttpResponse.BodyHandlers.ofString());
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            System.out.println("Interrupted Exception in sending request.");
//            e.printStackTrace();
//        }
//
//        System.out.println(response.body());
//        return null;
//    }
//}

package br.com.aps.fittracker.model.subsistemagoogle;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class ComunicacaoGoogle implements ISubsistemaComunicacaoGoogle{
    
    @Value("${app.googleApi.tokeninfo.url}")
    private String googleApiUrl;

    @Override
    public String getEmailFromTokenGoogle(String idToken) {
        String email = null;
        JSONObject jsonResponse = null;
        URL url;
            try {
                // Open the connection
                url = new URL(googleApiUrl);
                HttpURLConnection connection;
                connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("POST");
                connection.setRequestProperty("Content-Type", "application/json");
                connection.setDoOutput(true);

                // Create the JSON body containing the ID token
                JSONObject requestBody = new JSONObject();
                requestBody.put("id_token", idToken);
                //System.out.println(requestBody);

                // Write the JSON body to the connection's output stream
                connection.getOutputStream().write(requestBody.toString().getBytes());

                // Get the response code
                int responseCode = connection.getResponseCode();

                // Check if the response is successful (HTTP status code 200)
                if (responseCode == HttpURLConnection.HTTP_OK) {
                    // Get the response
                    InputStream inputStream = connection.getInputStream();
                    String responseString = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

                    // Parse the response JSON string into a JSONObject
                    jsonResponse = new JSONObject(responseString);
                    email = jsonResponse.getString("email");
                } 

                // Close the connection
                connection.disconnect();

            } catch (Exception e) {
                e.printStackTrace();
            }
        //System.out.println(jsonResponse);
        return email;
    }

    public static void main(String[] args) {
        ComunicacaoGoogle communicator = new ComunicacaoGoogle();
        communicator.googleApiUrl = "https://www.googleapis.com/oauth2/v2/tokeninfo";
        String idToken = "eyJhbGciOiJSUzI1NiIsImtpZCI6ImEzYmRiZmRlZGUzYmFiYjI2NTFhZmNhMjY3OGRkZThjMGIzNWRmNzYiLCJ0eXAiOiJKV1QifQ.eyJpc3MiOiJodHRwczovL2FjY291bnRzLmdvb2dsZS5jb20iLCJuYmYiOjE2OTAwNzE3NTksImF1ZCI6Ijg4OTMwMDM5MDcwNy00OGFsMXZubW9hdW8wdDNuOWJhY2xuMDR0MTFta2c0bi5hcHBzLmdvb2dsZXVzZXJjb250ZW50LmNvbSIsInN1YiI6IjExNjQxNDU5MDM1MzA0NzQ4NzQwOSIsImhkIjoiY2luLnVmcGUuYnIiLCJlbWFpbCI6InBhYTNAY2luLnVmcGUuYnIiLCJlbWFpbF92ZXJpZmllZCI6dHJ1ZSwiYXpwIjoiODg5MzAwMzkwNzA3LTQ4YWwxdm5tb2F1bzB0M245YmFjbG4wNHQxMW1rZzRuLmFwcHMuZ29vZ2xldXNlcmNvbnRlbnQuY29tIiwibmFtZSI6IlBhdHJpY2lhIEFyY2VsbyBkZSBBcnJ1ZGEiLCJwaWN0dXJlIjoiaHR0cHM6Ly9saDMuZ29vZ2xldXNlcmNvbnRlbnQuY29tL2EvQUFjSFR0ZWU4ZGZUM0pYbDFzWTZkY2dCYmJvc1JJbFFuRnVDem0teXdjUnJzRTFGPXM5Ni1jIiwiZ2l2ZW5fbmFtZSI6IlBhdHJpY2lhIiwiZmFtaWx5X25hbWUiOiJBcmNlbG8gZGUgQXJydWRhIiwiaWF0IjoxNjkwMDcyMDU5LCJleHAiOjE2OTAwNzU2NTksImp0aSI6IjFkMTc2YTk0YjZjZjM2M2VhYjFlMGIwODY1M2FhMTFmM2MzNjUwMWMifQ.KGvvslTk1VYjCwLpGuiWrUFk1lh4ycxMVdYw8kC9isf10hTkuGpJ94eW3JWOOm_KzxCJeMd9oq4Nz0hIbNsucHYU_S0LSlYefZrZ8MOf57DDVSAE5wvSI-HyfFaa36gLBFokCzhz41v5_e3PPzYb42tCL6Ok8HHQ3vDPANSZW0lJ-1oY2VYqzIBkD2ursJ-oYhhhtqYNVlxg98Q1--mtMKNa1M1sxLRxBzAzZIZfPJVmfIjrheeiMe8ruHjhdn-cSZelPooFIwPkPS6CP9aYQ1peNiA3FJaspvwDRUcUQEOuvnNI4GIawZHFIOHkXKGNbB5dT7jdBvQWfNINrn0xfg"; // Replace with your actual ID token
        System.out.println(communicator.getEmailFromTokenGoogle(idToken));
        
    }

}

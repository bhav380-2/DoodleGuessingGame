// package org.app.doodlegame.service;

// import org.springframework.stereotype.Service;
// import org.springframework.util.Base64Utils;

// import java.io.File;
// import java.io.FileOutputStream;
// import java.io.IOException;

// @Service
// public class DoodleService {

//     public String predict(String image) {
//         String base64Image = image.split(",")[1];
//         System.out.println(base64Image);
//         System.out.println("*****************************************************************************");
//         byte[] decodedBytes = Base64Utils.decodeFromString(base64Image);
//         File file = new File("uploads/drawing.png");

//         // return "cat";

//         try (FileOutputStream fos = new FileOutputStream(file)) {
//             fos.write(decodedBytes);
            
//             // Call the Python script and get the result
//             // ProcessBuilder pb = new ProcessBuilder("python3", "predict.py", file.getAbsolutePath());
//             // Process process = pb.start();
//             // process.waitFor();
//             // String result = new String(process.getInputStream().readAllBytes());
//             return "cat";
//         }  catch (IOException  e) {
//             e.printStackTrace();
//             return "Error: " + e.getMessage();
//         }
//     }
// }

// // | InterruptedException



package org.app.doodlegame.service;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.logging.Logger;

@Service
public class DoodleService {

    private static final Logger logger = Logger.getLogger(DoodleService.class.getName());

    public String predict(String image) {
        String base64Image = image.split(",")[1];
        logger.info("Received Base64 Image Length: " + base64Image.length());

        // Ensure the base64 string length is a multiple of 4
        System.out.println(base64Image.length()+" "+base64Image.charAt(6448));
        while (base64Image.length() % 4 != 0) {
            base64Image += "=";
        }

        byte[] decodedBytes = Base64.getDecoder().decode(base64Image);
        File uploadsDir = new File("uploads");

        // Ensure the uploads directory exists
        if (!uploadsDir.exists()) {
            uploadsDir.mkdirs();
        }

        File file = new File(uploadsDir, "drawing.png");

        try (FileOutputStream fos = new FileOutputStream(file)) {
            fos.write(decodedBytes);
            // Call the Python script and get the result
            ProcessBuilder pb = new ProcessBuilder("python3", "predict.py", file.getAbsolutePath());
            Process process = pb.start();
            process.waitFor();
            String result = new String(process.getInputStream().readAllBytes()).trim();
            return result;
        } catch (IOException | InterruptedException e) {
            logger.severe("Error processing image or executing Python script: " + e.getMessage());
            return "error";
        }
    }
}

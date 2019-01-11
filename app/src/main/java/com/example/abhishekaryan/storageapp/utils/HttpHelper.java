package com.example.abhishekaryan.storageapp.utils;
import android.content.Context;
import android.widget.Toast;

import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpHelper {


    public static String downloadUrl(Context context, String urlAddress) throws IOException{

        InputStream inputStream=null;

        try {
            URL url=new URL(urlAddress);

            HttpURLConnection httpURLConnection= (HttpURLConnection) url.openConnection();

            httpURLConnection.setReadTimeout(10000);
            httpURLConnection.setConnectTimeout(15000);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.setDoInput(true);
            httpURLConnection.connect();
            inputStream=httpURLConnection.getInputStream();
            int responseCode=httpURLConnection.getResponseCode();

            if(responseCode!=200){

                Toast.makeText(context,"Got response code" + responseCode,Toast.LENGTH_SHORT).show();
                throw new IOException("Got response code" + responseCode);

            }


            return readStream(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        finally {
            if(inputStream!=null){

                inputStream.close();
            }
        }


        return null;



    }

    private static String readStream(InputStream inputStream)throws IOException {

        byte[] buffer=new byte[1024];

        ByteArrayOutputStream byteArrayOut=new ByteArrayOutputStream();

        BufferedOutputStream outputFile=null;


        try {
            int length=0;

            outputFile=new BufferedOutputStream(byteArrayOut);

            while ((length=inputStream.read(buffer)) > 0){

                outputFile.write(buffer,0,length);
            }

            outputFile.flush();
            return byteArrayOut.toString();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        finally {
            if(outputFile!=null){

                outputFile.close();
            }
        }



    }
}

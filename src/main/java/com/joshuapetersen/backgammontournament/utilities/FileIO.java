package com.joshuapetersen.backgammontournament.utilities;

import java.io.*;

public class FileIO
{
    public static String readFile(String path)
    {
        String contents = "";
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path)))
        {
            while (bufferedReader.ready())
            {
                contents += bufferedReader.readLine();
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return contents;
    }


    public static void writeFile(String path, String contents)
    {
        File file = new File(path);
        System.out.println("Can Write: " + file.canWrite());
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file)))
        {

            bufferedWriter.write(contents);
            bufferedWriter.flush();
            bufferedWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }


}
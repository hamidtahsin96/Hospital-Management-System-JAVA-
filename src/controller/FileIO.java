package controller;
import java.io.*;

public class FileIO
{
    public String[] readFile(String fileName)
    {
        try
        {
            File f = new File(fileName);
            FileReader rf = new FileReader(f);
            BufferedReader bfr = new BufferedReader(rf);

            String information[] = new String[100];
            String temp = "";
            int i = 0;

            while((temp = bfr.readLine()) != null)
            {
                information[i] = temp;
                i++;
            }

            bfr.close();

            return information;
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }

        return null;
    }

    public void writeFile(String fileName, String information[])
    {
        try
        {
            File f = new File(fileName);
            FileWriter fw = new FileWriter(f, false);

            for(int i = 0; i < information.length; i++)
            {
                if(information[i] != null)
                {
                    fw.write(information[i]);
                    fw.write("\n");
                }
            }

            fw.close();
        }

        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
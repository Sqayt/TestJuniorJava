package com.example.demo.ivanovds;

import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import au.com.bytecode.opencsv.CSVReader;
import com.example.demo.ivanovds.DB;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class DemoApplication
{

    @SuppressWarnings("resource")
    public static void main(String[] args) throws Exception
    {
        DB.setConnection();

        while (true)
        {

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println ("Введите путь к файлу (например('C:\\DB\\check01.csv'))");
            String URLFiles = bufferedReader.readLine();
            System.out.println ("Введите путь куда сохранить новый файл (например('C:\\source01.csv'))");
            String URLtoFiles = bufferedReader.readLine();
            DB.sql(URLFiles,URLtoFiles);
            try
            {
                CSVReader reader = new CSVReader(new FileReader(URLFiles), ',', '"', 1);
                List<String[]> allRows = reader.readAll();
                for (String[] row : allRows)
                {
                    System.out.println(Arrays.toString(row));
                }

            }
            catch (IOException e)
            {
                System.out.println("Error DA!");
                bufferedReader.close();
                break;
            }
        }
    }
    }



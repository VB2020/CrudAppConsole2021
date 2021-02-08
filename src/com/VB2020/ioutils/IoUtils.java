package com.VB2020.ioutils;

import java.io.*;
import java.util.List;

public class IoUtils {
    public static <T> void WriteToFile(List<T> data, File filename)  {
        try (ObjectOutputStream oStream = new ObjectOutputStream(new FileOutputStream(filename)))
        {
            oStream.writeObject(data);
        } catch (IOException ex)
        {
            System.out.println(ex.getMessage());
        }
    }

    public static <T> List<T> ReadFromFile(File filename) throws FileNotFoundException{
        try (ObjectInputStream oStream = new ObjectInputStream(new FileInputStream(filename)))
        {
            return (List<T>)oStream.readObject();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}

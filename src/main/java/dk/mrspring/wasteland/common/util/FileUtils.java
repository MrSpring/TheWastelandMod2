package dk.mrspring.wasteland.common.util;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

/**
 * Created on 16-01-2016 for TheWastelandMod2.
 */
public class FileUtils
{
    public static <T> T readJsonFile(File file, Class<T> clazz) throws
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            IOException
    {
        return readJsonFile(file, clazz, true);
    }

    public static <T> T readJsonFile(File file, Class<T> clazz, boolean save) throws
            InvocationTargetException,
            NoSuchMethodException,
            InstantiationException,
            IllegalAccessException,
            IOException
    {
        T result = null;
        Gson gson = makeGson();
        if (file.exists())
        {
            FileReader reader = new FileReader(file);
            result = gson.fromJson(reader, clazz);
            reader.close();
        }
        if (result == null) result = clazz.getConstructor().newInstance();
        if (save) saveToFileAsJson(file, result, gson);
        return result;
    }

    public static void saveToFileAsJson(File file, Object o) throws IOException
    {
        saveToFileAsJson(file, o, makeGson());
    }

    private static void saveToFileAsJson(File file, Object o, Gson gson) throws IOException
    {
        String json = gson.toJson(o);
        /*if (file.exists() || ((file.getParentFile().exists() || file.getParentFile().mkdirs()) && file.createNewFile()))
            IOUtils.write(json, new FileWriter(file));*/
        org.apache.commons.io.FileUtils.write(file, json);
    }

    private static Gson makeGson()
    {
        GsonBuilder builder = new GsonBuilder().setPrettyPrinting();
        return builder.create();
    }
}

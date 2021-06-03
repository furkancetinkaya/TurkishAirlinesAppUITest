package Utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Objects;

public class JSONOperations {
    public static List<String> jsonDB;


    public String readJSONFromResourcesFolder(String fileName){
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(Objects.requireNonNull(classLoader.getResource(fileName)).getFile());
        try {
            return new String(Files.readAllBytes(file.toPath()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    public List<Element> convertJSON2ElementList(String jsonString) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(jsonString, new TypeReference<List<Element>>(){});
    }


    public String findElementByKey(String key){
        for (String s : jsonDB) {
            String jsonString = readJSONFromResourcesFolder(s);
            List<Element> listElems = null;
            try {
                listElems = convertJSON2ElementList(jsonString);
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            }
            for (Element listElem : Objects.requireNonNull(listElems)) {
                if (listElem.key.equals(key))
                    return listElem.androidValue;
            }
        }
        return "";
    }

}

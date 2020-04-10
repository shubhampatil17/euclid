package io.github.fosstree.exceptions.json;

import com.fasterxml.jackson.databind.JsonNode;

import java.io.IOException;

public class Json {

    private static final String JSON_PATH_SEPARATOR = "\\.";

    /**
     * @param json        : JsonNode to parse
     * @param pathToFetch : dotted notation based path to fetch from json
     * @return : json node corresponding to final key in the path
     * @throws IOException
     */
    public static JsonNode fetchPath(JsonNode json, String pathToFetch) throws IOException {
        JsonNode current = json;
        if (pathToFetch != null && !pathToFetch.isEmpty()) {
            for (String key : pathToFetch.split(JSON_PATH_SEPARATOR)) {
                if (current.hasNonNull(key)) {
                    current = current.get(key);
                } else {
                    throw new IOException("Path : " + pathToFetch + " not found in Json :" + json.toString());
                }
            }
        }
        return current;
    }
}

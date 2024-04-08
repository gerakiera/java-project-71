package hexlet.code;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.List;
import java.util.Map;

import static hexlet.code.formatters.Json.jsonFormat;
import static hexlet.code.formatters.Plain.plainFormat;
import static hexlet.code.formatters.Stylish.stylishFormat;

public class Formatter {
    public static String chooseFormat(List<Map<String, Object>> diff, String format) throws JsonProcessingException {
        String result = null;
        if (format.equals("stylish")) {
            result = stylishFormat(diff);
        } else if (format.equals("plain")) {
            result = plainFormat(diff);
        } else if (format.equals("json")) {
            result = jsonFormat(diff);
        } else {
            throw new RuntimeException("Неверный формат: " + format);
        }
        return result;
    }
}

package restapi;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class AquariumRestController {


    @RequestMapping("/calculate")
    public String greeting(@RequestParam(value = "sequence", defaultValue = "47") String sequence) throws JsonProcessingException {


        HashMap<String, String> result = new HashMap<>();

        ArrayList<Integer> entryList = new ArrayList<>();

        StringTokenizer tokenizer = new StringTokenizer(sequence, " ");

        while (tokenizer.hasMoreTokens()) {
            entryList.add(Integer.valueOf(tokenizer.nextToken()));
        }


        for (int i = 0; i < entryList.size() - 1; i++) {


            if (entryList.get(i) == 0) {
                continue;
            }

            int j = i + 1;
            int localMaximum;

            while (j < entryList.size() - 1 && entryList.get(i) > entryList.get(j) && entryList.get(j) != 0) {
                j++;
            }


            localMaximum = Math.min(entryList.get(i), entryList.get(j));
            if (localMaximum != 0) {
                for (int count = i + 1; count < j; count++) {
                    result.put(String.valueOf(count), String.valueOf(localMaximum));

                }
            }
            i = j - 1;
        }

        return new ObjectMapper().writeValueAsString(result);
    }
}

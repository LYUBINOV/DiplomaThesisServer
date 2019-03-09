package Application.Controllers;

import Application.CommandHandlers.VerificationCommandHandler;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VerificationController {
    private VerificationCommandHandler verificationCommandHandler;

    /** SHOW OBJ TO STRING
     * ---------------------------------------------------
     * ObjectMapper mapper = new ObjectMapper();
     * String jsonString = mapper.writeValueAsString(tmp);
     * ---------------------------------------------------
     */

    @GetMapping("/getEXAMPLE")
    public String getEXAMPLE() {
        return null;
    }

    @PutMapping(value = "/putEXAMPLE/{tmp}", consumes={MediaType.ALL_VALUE})
    public void putEXAMPLE(@PathVariable String tmp) {
//        tmp.add(str);
    }
}

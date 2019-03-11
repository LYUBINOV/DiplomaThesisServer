package Application.Controllers;

import Application.CommandHandlers.VerificationCommandHandler;
import Application.DataTransferObjects.VerificationDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/verification")
public class VerificationController {
//     private VerificationCommandHandler verificationCommandHandler = new VerificationCommandHandler();
    @Component private VerificationCommandHandler verificationCommandHandler;
    /** SHOW OBJ TO STRING
     * ---------------------------------------------------
     * ObjectMapper mapper = new ObjectMapper();
     * String jsonString = mapper.writeValueAsString(tmp);
     * ---------------------------------------------------
     */

    @PostMapping(value = "/check", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String checkSign(@RequestBody VerificationDto verificationDto) {
        return verificationCommandHandler.checkSign(verificationDto);
    }
}

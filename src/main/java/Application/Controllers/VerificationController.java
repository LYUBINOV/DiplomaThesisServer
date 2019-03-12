package Application.Controllers;

import Application.CommandHandlers.VerificationCommandHandler;
import Application.DataTransferObjects.VerificationDto;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping(path = "/verification")
public class VerificationController {
//     private VerificationCommandHandler verificationCommandHandler = new VerificationCommandHandler();
    @Resource
    private VerificationCommandHandler verificationCommandHandler;
    /** SHOW OBJ TO STRING
     * ---------------------------------------------------
     * ObjectMapper mapper = new ObjectMapper();
     * String jsonString = mapper.writeValueAsString(tmp);
     * ---------------------------------------------------
     */

    @GetMapping(value = "/test")
    public String testGet() {
        return "HelloWorld";
    }

    @PostMapping(value = "/check", consumes = {MediaType.APPLICATION_JSON_VALUE})
    public String checkSign(@RequestBody VerificationDto verificationDto) {
        return verificationCommandHandler.checkSign(verificationDto);
    }
}

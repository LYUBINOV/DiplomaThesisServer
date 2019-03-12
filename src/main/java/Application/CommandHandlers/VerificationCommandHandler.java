package Application.CommandHandlers;

import Application.DataTransferObjects.VerificationDto;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256Signer;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.bouncycastle.util.encoders.Base64;

public class VerificationCommandHandler {
    public String checkSign(VerificationDto verificationDto) {
        SPHINCSPublicKeyParameters publicKey = new SPHINCSPublicKeyParameters(Base64.decode(verificationDto.getPublicKey()));
        MessageSigner sphincsSigner = new SPHINCS256Signer(new SHA3Digest(256), new SHA3Digest(512));

        sphincsSigner.init(false, publicKey);

        boolean isValidSignature = sphincsSigner.verifySignature(Base64.decode(verificationDto.getDocument()),
                                                                 Base64.decode(verificationDto.getSignature()));

        if (!isValidSignature) {
            try {
                throw new Exception("SignInvalid");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "SignValid!";
    }
}

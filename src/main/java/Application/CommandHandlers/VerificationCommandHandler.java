package Application.CommandHandlers;

import Application.DataTransferObjects.VerificationDto;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256Signer;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;

public class VerificationCommandHandler {
    public String checkSign(VerificationDto verificationDto) {
        SPHINCSPublicKeyParameters publicKey = new SPHINCSPublicKeyParameters(verificationDto.getPublicKey().getBytes());
        MessageSigner sphincsSigner = new SPHINCS256Signer(new SHA3Digest(256), new SHA3Digest(512));

        sphincsSigner.init(false, publicKey);

        boolean isValidSignature = sphincsSigner.verifySignature(verificationDto.getDocument().getBytes(),
                                                                 verificationDto.getSignature().getBytes());

        if (!isValidSignature) {
            try {
                throw new Exception("Podpis nieje validny!");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return "Podpis je validny!";
    }
}

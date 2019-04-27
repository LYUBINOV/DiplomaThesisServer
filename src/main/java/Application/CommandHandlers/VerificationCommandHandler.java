package Application.CommandHandlers;

import Application.DataTransferObjects.VerificationDto;
import org.bouncycastle.crypto.digests.SHA3Digest;
import org.bouncycastle.pqc.crypto.MessageSigner;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCS256Signer;
import org.bouncycastle.pqc.crypto.sphincs.SPHINCSPublicKeyParameters;
import org.bouncycastle.util.encoders.Base64;
import org.springframework.stereotype.Component;

import java.sql.*;

@Component
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

        insertToDB(verificationDto.getPublicKey(), verificationDto.getSignature(), verificationDto.getDocument());

        return "SignValid!";
    }


    public void insertToDB(String publicKey_PK, String signature, String document)
    {
        try
        {
            Class.forName("com.mysql.jdbc.Driver");

//            https://remotemysql.com/phpmyadmin/sql.php?server=1&db=h9neXSyEba&table=SignedDocs&pos=0
//            String url = "jdbc:mysql://remotemysql.com:3306/h9neXSyEba?autoReconnect=true&useSSL=false";
            String url = "jdbc:mysql://remotemysql.com:3306/h9neXSyEba?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=GMT&useSSL=false";
            Connection c = DriverManager.getConnection(url, "h9neXSyEba", "knsiS0duEe");

            PreparedStatement st = c.prepareStatement("insert into " + "SignedDocs" + " values (?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

            ResultSet rs = st.getGeneratedKeys();

            Long id = null;
            if(rs.next()) {
                id = rs.getLong(1);
            }
            else { //inac je to prvz yaynam
                id = 0L;
            }

            st.setLong(1, 0L);
            st.setString(2, publicKey_PK);
            st.setString(3, signature);
            st.setString(4, document);

            st.execute();
            st.close();
            c.close();
        }
        catch (ClassNotFoundException | SQLException e)
        {
            e.printStackTrace();
        }
    }
}

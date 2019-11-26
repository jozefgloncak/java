package gloncak.jozef.java8.features;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Base64;

public class App {
    private static final Logger LOG = LoggerFactory.getLogger(App.class);
    public static void main(String[] args) {
        byte[] refString =
                ("This is string which should be encoded to base-64 /example/. This line can be also very long to " +
                        "demonstrate wrapping in mime encoding/decoding. In MIME there is default wrapping which " +
                        "cause that output base 64 encoded string isn't too long").getBytes();

        LOG.info("::::Simple encoding/decoding");
        //encoding simple
        Base64.Encoder encoder = Base64.getEncoder();
        String encodedString = encoder.encodeToString(refString);
        LOG.info("Base 64 Encoding: {}", encodedString);

        //decoding simple
        Base64.Decoder decoder = Base64.getDecoder();
        byte[] decodedString = decoder.decode(encodedString);
        LOG.info("Base 64 Decoded: {}", new String(decodedString));

        LOG.info("::::MIME encoding/decoding");
        //mime encoding
        String mimeBase64EncodedString = Base64.getMimeEncoder().encodeToString(refString);
        LOG.info("Mime base 64 encoded string: {}", mimeBase64EncodedString);

        //meme decoding
        byte[] mimeBase64DecodedString = Base64.getMimeDecoder().decode(mimeBase64EncodedString);
        LOG.info("Mime base 64 decoded: {}", new String(mimeBase64DecodedString));

        LOG.info("::::URL encoding/decoding");
        //url encoding
        String urlBase64EncodedString = Base64.getUrlEncoder().encodeToString(refString);
        LOG.info("URL base 64 encoded sring: {}", urlBase64EncodedString);

        //url decoding
        byte[] urlBase64DecodedString = Base64.getUrlDecoder().decode(urlBase64EncodedString);
        LOG.info("URL base 64 decoded: {}", new String(urlBase64DecodedString));
    }
}

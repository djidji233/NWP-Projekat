package raf.edu.rs.projekat.model;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.tomcat.util.codec.binary.Base64;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class DecodedToken {

    public String sub;
    public boolean can_destroy_machines;
    public boolean can_restart_machines;
    public boolean can_search_machines;
    public boolean can_delete_users;
    public boolean can_update_users;
    public boolean can_start_machines;
    public boolean can_stop_machines;
    public boolean can_read_users;
    public boolean can_create_users;
    public boolean can_create_machines;

    public static DecodedToken getDecoded(String encodedToken) throws UnsupportedEncodingException {
        String[] pieces = encodedToken.split("\\.");
        String b64payload = pieces[1];
        String jsonString = new String(Base64.decodeBase64(b64payload), StandardCharsets.UTF_8);

        return new Gson().fromJson(jsonString, DecodedToken.class);
    }

    public String toString() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        return gson.toJson(this);
    }

}

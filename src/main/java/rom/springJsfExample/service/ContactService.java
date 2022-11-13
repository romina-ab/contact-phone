package rom.springJsfExample.service;
import rom.springJsfExample.entity.Contact;
import java.util.List;

public interface ContactService {

    List<Contact> displayContact();
    void insertContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(Contact contact);
    List<Contact> findByChar(String chr);
}

package rom.springJsfExample.dao;

import rom.springJsfExample.entity.Contact;
import java.util.List;

public interface ContactDao {
    List<Contact> displayContact();
    void insertContact(Contact contact);
    void updateContact(Contact contact);
    void deleteContact(Contact contact);
    List<Contact> findByChar(String chr);
}

package rom.springJsfExample.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rom.springJsfExample.dao.ContactDao;
import rom.springJsfExample.entity.Contact;

import java.util.List;

@Service
public class ContactServiceImpl implements ContactService {

    private final ContactDao contactDao;

    @Autowired
    public ContactServiceImpl(ContactDao contactDao) {
        this.contactDao = contactDao;
    }

    @Override
    public List<Contact> displayContact() {
        return contactDao.displayContact();
    }

    @Override
    public void insertContact(Contact contact) {
        contactDao.insertContact(contact);
    }

    @Override
    public void updateContact(Contact contact) {
        contactDao.updateContact(contact);
    }

    @Override
    public void deleteContact(Contact contact) {
        contactDao.deleteContact(contact);
    }


    public List<Contact> findByChar(String chr){
        return  contactDao.findByChar(chr);
    }

}

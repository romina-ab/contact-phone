package rom.springJsfExample.backBean;

import org.primefaces.PrimeFaces;
import rom.springJsfExample.SpringViewScoped;
import rom.springJsfExample.entity.Contact;
import rom.springJsfExample.service.ContactService;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SpringViewScoped
public class DisplayContactManageBean implements Serializable {

    private final ContactService contactService;
    private Contact selectedContact = new Contact();
    private String searchChar;

    public DisplayContactManageBean(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostConstruct
    private void init() {
        selectedContact = new Contact();
    }

    public List<Contact> getContacts() {
        if (searchChar != null) {
            return contactService.findByChar(searchChar);
        } else {
            return contactService.displayContact();
        }
    }

    public Contact getSelectedContact() {
        return selectedContact;
    }

    public void setSelectedContact(Contact selectedContact) {
        this.selectedContact = selectedContact;
    }

    public void addMessage(FacesMessage.Severity severity, String summary, String detail) {
        FacesContext.getCurrentInstance().
                addMessage(null, new FacesMessage(severity, summary, detail));
    }

    public void saveAction() {

        if (selectedContact.getId() != null) {
            contactService.updateContact(selectedContact);
            addMessage(FacesMessage.SEVERITY_INFO, "Message", "Contact has been updated successfully!");
        } else {
            contactService.insertContact(selectedContact);
            addMessage(FacesMessage.SEVERITY_INFO, "Message", "Contact has been created successfully!");
        }
        init();

        PrimeFaces.current().executeScript("PF('dlgAddContactWv').hide();");
    }

    public void deleteAction() {
        addMessage(FacesMessage.SEVERITY_INFO, "Delete record", "Record has been deleted.");
        contactService.deleteContact(selectedContact);
        init();
    }

    public String getSearchChar() {
        return searchChar;
    }

    public void setSearchChar(String searchChar) {
        this.searchChar = searchChar;
    }
}


package ch.makery.address.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of persons. This is used for saving the
 * list of persons to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "clients")
public class ClientListWrapper {

    private List<Client> clients;

    @XmlElement(name = "clients")
    public List<Client> getClient() {
        return clients;
    }

    public void setClient(List<Client> clients) {
        this.clients = clients;
    }
}
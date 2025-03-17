package com.example.core_bu.demo.service;

import com.example.core_bu.demo.model.Client;
import com.example.core_bu.demo.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
public class ClientService {
    private final ClientRepository clientRepository;

    public Client createClient(Client client) {
        return clientRepository.save(client);
    }

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) throws ChangeSetPersister.NotFoundException {
        return clientRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }

    public void deleteClientById(Long id) {
        clientRepository.deleteById(id);
    }

    public Client updateClient(Long id, Client client) {
        Client clientToUpdate = clientRepository.findById(id).orElseThrow();
        clientToUpdate.setName(client.getName());
        clientToUpdate.setLastName(client.getLastName());
        clientToUpdate.setDocumentType(client.getDocumentType());
        clientToUpdate.setDocumentNumber(client.getDocumentNumber());
        clientToUpdate.setBirthDate(client.getBirthDate());
        clientToUpdate.setGender(client.getGender());
        return clientRepository.save(clientToUpdate);
    }

}

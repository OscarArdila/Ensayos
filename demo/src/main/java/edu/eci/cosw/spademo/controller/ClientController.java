/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.cosw.spademo.controller;

import edu2.eci.cosw.stubs.fakeclientslibrary.CliendLoadException;
import edu2.eci.cosw.stubs.fakeclientslibrary.Client;
import edu2.eci.cosw.stubs.fakeclientslibrary.ClientServices;
import edu2.eci.cosw.stubs.fakeclientslibrary.ClientServicesStub;
import java.util.Set;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/clients")

/**
 *
 * @author Felipe - Oscar
 */
public class ClientController {
    ClientServices stub = new ClientServicesStub();
    
    @RequestMapping(method = RequestMethod.GET)
    public Set<Client> getClients() throws CliendLoadException{
        return stub.getAllClients();
    }
    
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Client getClient(@PathVariable int id) throws CliendLoadException{
        return stub.getClientById(id);
        
    }
    
    @RequestMapping(value="/{id}/picture", method = RequestMethod.GET)
    public ResponseEntity<InputStreamResource> getClientPicture(@PathVariable int id) throws CliendLoadException{
        try{
           return ResponseEntity.ok().contentType(MediaType.parseMediaType("image/jpg")).body(new InputStreamResource(stub.getClientPicture(id)));
        }catch(CliendLoadException e){
             return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}



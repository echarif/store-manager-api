package org.store.manager.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.store.manager.model.Customer;
import org.store.manager.service.CustomerService;

import javax.websocket.server.PathParam;

import static javax.swing.text.html.FormSubmitEvent.MethodType.GET;

@RestController("/api/")
public class CustomerController {

    CustomerService customerService = new CustomerService();

    @RequestMapping(value = "/customers", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<Customer> getCustomers() {

        return customerService.getAllCustomers();
    }

    /*
        @POST
        public Response addCustomer(Customer cust, @Context UriInfo uriInfo)
                throws URISyntaxException {
            Customer newCustomer = customerService.addCustomer(cust);
            String newId = String.valueOf(newCustomer.getId());
            URI uri = uriInfo.getAbsolutePathBuilder().path(newId).build();
            return Response.created(uri).entity(newCustomer).build();
        }

        @DELETE
        @Path("/{customerId}")
        public void deleteCustomer(@PathParam("customerId") long id) {
            customerService.removeCustomer(id);
        }

        @PUT
        @Path("/{messageId}")
        public Customer updateMessage(@PathParam("messageId") long id,
                Customer customer) {

            return null;
        }
    */
    @RequestMapping(value = "/customers", params = {"customerId"}, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public @ResponseBody
    Customer getCustomer(@RequestParam(value = "customerId") Long id) {
        return customerService.getCustomer(id);
    }

    @RequestMapping(value = "/customers/create", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<String> addCustomer(@RequestBody(required = true) Customer customer) {
        if (customerService.isValid(customer)) {
            customerService.addCustomer(customer);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            //return ResponseEntity.status(HttpStatus.I_AM_A_TEAPOT).build();
            return null;
        }
    }

}

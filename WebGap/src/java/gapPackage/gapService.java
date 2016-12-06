/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gapPackage;


import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;


/**
 *
 * @author fa
 */
@WebService(serviceName = "gapService")
public class gapService {
    boolean valid_email;
    /**
     * Web service operation
     */
    @WebMethod(operationName = "getLogin")
    public String getLogin(@WebParam(name = "email") String email, @WebParam(name = "password") String password) {
        //TODO write your implementation code here:
       
        valid_email=true;
        if (valid_email) return "ok";
        else return "Hello";
    }
}

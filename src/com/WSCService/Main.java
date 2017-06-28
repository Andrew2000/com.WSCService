package com.WSCService;

/**
 * Created by andrewlee on 6/26/17.
 */
import com.sforce.soap.enterprise.*;
import com.sforce.soap.enterprise.sobject.*;
import com.sforce.ws.*;

public class Main {
    public static void main(String[] args) {
        EnterpriseConnection connection = null;
        Config config = new Config();

        try {
            connection = Connector.newConnection(config.getUsername(), config.getPassword());

            // create a new account
            System.out.println("Creating a new Account...");
            Account account = new Account();
            account.setName("ACME Account 1");
            SaveResult[] results = connection.create(new Account[] { account });
            System.out.println("Created Account: " + results[0].getId());

            // query for the 5 newest accounts
            System.out.println("Querying for the 5 newest Accounts...");
            QueryResult queryResults = connection.query("SELECT Id, Name from Account " +
                    "ORDER BY CreatedDate DESC LIMIT 5");
            //if (queryResults.getSize() > 0) {
            //    for (SObject a: queryResults.getRecords()) {
            //        System.out.println("Id: " + a.getId() + " - Name: "+a.getName());
            //    }
            //}

        } catch (ConnectionException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

}

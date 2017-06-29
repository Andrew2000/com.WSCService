package com.WSCService;

/**
 * Created by alee on 6/29/17.
 */
import com.sforce.soap.partner.*;
import com.sforce.soap.partner.sobject.*;
import com.sforce.ws.*;

import java.io.FileNotFoundException;

public class Main {

    public static void main(String[] args) throws FileNotFoundException {

        ConnectorConfig config = new ConnectorConfig();
        config.setUsername(Config.getUsername());
        config.setPassword(Config.getPassword());

        PartnerConnection connection = null;
        try {
            connection = Connector.newConnection(config);

            System.out.println("Querying for the Accounts...");
            QueryResult queryResults = connection.query("SELECT Id, Name from Account " +
                    "ORDER BY CreatedDate DESC");
            boolean done = false;
            if (queryResults.getSize() > 0) {
                while (!done) {
                    for (SObject s : queryResults.getRecords()) {
                        System.out.println("Id: " + s.getField("Id") + " - Name: " + s.getField("Name"));
                    }

                    if (queryResults.isDone()) {
                        done = true;
                    } else {
                        queryResults = connection.queryMore(queryResults.getQueryLocator());
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
// **********************************************************************
// Copyright (c) 2017 Telefonaktiebolaget LM Ericsson, Sweden.
// All rights reserved.
// The Copyright to the computer program(s) herein is the property of
// Telefonaktiebolaget LM Ericsson, Sweden.
// The program(s) may be used and/or copied with the written permission
// from Telefonaktiebolaget LM Ericsson or in accordance with the terms
// and conditions stipulated in the agreement/contract under which the
// program(s) have been supplied.
// **********************************************************************
package com.crud.mongodb;

import org.bson.Document;

import com.crud.mongodb.dto.User;

public final class Utils
{

    public static Document toMongoDocument(User user, boolean includeId)
    {
        Document document = new Document().append("name", user.getName()).
                append("role", user.getRole()).append("isEmployee", user.isEmployee());
        if (includeId)
            document.append("_id", user.getId());
        return document;
    }

    public static User fromMongoDocument(final Document doc)
    {
        return new User()
        {
            {
                setId(doc.getInteger("_id"));
                setName(doc.getString("name"));
                setRole(doc.getString("role"));
                setIsEmployee(doc.getBoolean("isEmployee"));
            }
        };
    }

}

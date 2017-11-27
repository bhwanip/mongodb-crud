package com.crud.mongodb;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.hamcrest.MatcherAssert.assertThat;

import org.bson.Document;
import org.junit.Test;

import com.crud.mongodb.dto.User;
import com.sun.istack.internal.logging.Logger;

public class MongoDbCrudTest
{

    /**
     *
     */
    private static final int USER_ID = 999;

    private final static Logger logger = Logger.getLogger(MongoDbCrudTest.class);

    private final MongoDbCrud mongoCrud = new MongoDbCrud();

    private final User user = new User()
    {
        {
            setId(USER_ID);
            setName("Test User");
            setRole("On Bench");
            setIsEmployee(true);
        }
    };

    @Test
    public void testCreate()
    {
        mongoCrud.create(user);
        User user = mongoCrud.read(USER_ID);
        Document doc = Utils.toMongoDocument(user, true);
        logger.info("Record is:" + doc);
        assertThat(doc.getInteger("_id"), is(999));
        assertThat(doc.getString("name"), is("Test User"));
        assertThat(doc.getString("role"), is("On Bench"));
        assertThat(doc.getBoolean("isEmployee"), is(true));
        user.setIsEmployee(false);
        mongoCrud.update(user);
        user = mongoCrud.read(USER_ID);
        doc = Utils.toMongoDocument(user, true);
        logger.info("Record is:" + doc);
        assertThat(doc.getInteger("_id"), is(999));
        assertThat(doc.getString("name"), is("Test User"));
        assertThat(doc.getString("role"), is("On Bench"));
        assertThat(doc.getBoolean("isEmployee"), is(false));
        mongoCrud.delete(user);
        user = mongoCrud.read(USER_ID);
        assertThat(user, is(nullValue()));
    }
}

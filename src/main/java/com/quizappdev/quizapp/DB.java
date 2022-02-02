package com.quizappdev.quizapp;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

import java.util.ArrayList;
import java.util.Iterator;

public class DB {
    static MongoClient client = MongoClients.create("mongodb+srv://root:root@quizapp.eqapk.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
    static MongoDatabase db = client.getDatabase("students_data");
    static MongoCollection<Document> collection = db.getCollection("studnets");


    public static void insertToDB(Student std){
        Document sample = new Document("id", std.getStudentID()).append("name", std.getStudentName()).append("userName", std.getStudentUserName()).append("passWord", std.getPassword());

        collection.insertOne(sample);
    }

    public static boolean checkDB(Student std){
        // if db stdID is present then it will not insert data in DB
        // else it will

        boolean flag = true;

        FindIterable<Document> iterDoc = collection.find();

        //specific document retrieving in a collection
        BasicDBObject searchQuery = new BasicDBObject();
        searchQuery.put("id", std.getStudentID());

        MongoCursor<Document> cursor = collection.find(searchQuery).iterator();
        while (cursor.hasNext()) {
            if(std.getStudentID().equals(cursor.next().toString())){
                flag = false;
            }
            //flag = false;
        }
        return flag;
    }

    public static String showDetails(String id){
        try {
            FindIterable<Document> iterDoc = collection.find();

            //specific document retrieving in a collection
            BasicDBObject searchQuery = new BasicDBObject();
            searchQuery.put("id", id);

            MongoCursor<Document> cursor = collection.find(searchQuery).iterator();
            while (cursor.hasNext()) {
                //if(id.equals(cursor.next().toString())){
                    return cursor.next().toString();
                //}
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static ArrayList<String> showDB(){
        try {
            // Get the list of documents from the DB
            FindIterable<Document> iterobj
                    = collection.find();

            // Print the documents using iterators
            Iterator itr = iterobj.iterator();
            ArrayList<String> list = new ArrayList<>();
            while (itr.hasNext()) {
                list.add(itr.next().toString());
            }
            return list;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }


    /*
    public static void main(String[] args) {
        MongoClient client = MongoClients.create("mongodb+srv://root:root@quizapp.eqapk.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
        MongoDatabase db = client.getDatabase("students_data");
        MongoCollection<Document> collection = db.getCollection("studnets");

        Document sample = new Document("id", "1").append("name", "Sagar");

        collection.insertOne(sample);
    }*/

}

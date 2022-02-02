package com.quizappdev.quizapp;

import com.mongodb.BasicDBObject;
import com.mongodb.client.*;
import org.bson.Document;

public class QuizDB {
    static MongoClient client = MongoClients.create("mongodb+srv://root:root@quizapp.eqapk.mongodb.net/myFirstDatabase?retryWrites=true&w=majority");
    static MongoDatabase db = client.getDatabase("QuizDB");
    static MongoCollection<Document> collection = db.getCollection("quizes");


    public static void insertToDB(Quiz quiz){
        Document data = new Document("id", quiz.getId()).append("title", quiz.getQuizTitle()).append("question", quiz.getQuizQuestion())
                .append("option1", quiz.getOptions_1()).append("option2", quiz.getOptions_2())
                .append("option3", quiz.getOptions_3()).append("option4", quiz.getOptions_4()).append("answer", quiz.getAnswer());

        collection.insertOne(data);
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
}

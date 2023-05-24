package org.example;

import org.apache.jena.rdf.model.*;
import org.apache.jena.util.FileManager;
import org.apache.jena.query.*;

import java.io.*;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создание модели данных RDF
        Model model = ModelFactory.createDefaultModel();

        System.out.println("Reading files...");
        long startTime = System.currentTimeMillis();

        File folder = new File("C:\\Blazegraph\\1");
        for (File file : folder.listFiles()) {
            if (file.isFile() && file.getName().endsWith(".nq")) {
                System.out.println("Reading file " + file.getName() + "...");
                InputStream in = FileManager.getInternal().open( file.getAbsolutePath());
                model.read(in, null);
            }
        }

        long endTime = System.currentTimeMillis();
        long dur = endTime - startTime;
        System.out.println("Files are readed, elapsed time: " + dur + "ms");
        System.out.println("Total model size: " + model.size());

        // Выполнение запроса
        try (QueryExecution qe = QueryExecutionFactory.create(
                QueryFactory.create(
                        "SELECT ?s ?p ?o WHERE {?s ?p ?o .}"
                ), model)) {
            System.out.println("Executing SPARQL Query #1");
            startTime = System.currentTimeMillis();
            ResultSet results = qe.execSelect();
            int resultCount = ResultSetFormatter.consume(results);
            endTime = System.currentTimeMillis();
            dur = endTime - startTime;

            System.out.println("Elapsed time: " + dur + "ms, Query elements: " + resultCount);


            // Вывод данных в читаемом виде
            // ResultSetFormatter.out(System.out, results, query);
        }

        // Выполнение запроса
        try (QueryExecution qe = QueryExecutionFactory.create(
                QueryFactory.create(
                        "SELECT ?s ?p ?o WHERE {?s ?p ?o .}"
                ), model)) {
            System.out.println("Executing SPARQL Query #1 again");
            startTime = System.currentTimeMillis();
            ResultSet results = qe.execSelect();
            int resultCount = ResultSetFormatter.consume(results);
            endTime = System.currentTimeMillis();
            dur = endTime - startTime;

            System.out.println("Elapsed time: " + dur + "ms, Query elements: " + resultCount + "\n");


            // Вывод данных в читаемом виде
            // ResultSetFormatter.out(System.out, results, query);
        }

        // Выполнение запроса
        try (QueryExecution qe = QueryExecutionFactory.create(
                QueryFactory.create(
                                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>\n" +
                                "PREFIX my: <http://127.0.0.1/bg/ont/test1#>\n" +
                                "SELECT ?Object\n" +
                                "WHERE {\n" +
                                "?Object my:has_id \"Object_10000\"\n" +
                                "}"
                ), model)) {
            System.out.println("Executing SPARQL Query #2");
            startTime = System.currentTimeMillis();
            ResultSet results = qe.execSelect();
            int resultCount = ResultSetFormatter.consume(results);
            endTime = System.currentTimeMillis();
            dur = endTime - startTime;

            System.out.println("Elapsed time: " + dur + "ms, Query elements: " + resultCount + "\n");


            // Вывод данных в читаемом виде
            // ResultSetFormatter.out(System.out, results, query);
        }

        // Выполнение запроса
        try (QueryExecution qe = QueryExecutionFactory.create(
                QueryFactory.create(
                                "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                                "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
                                "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>\n" +
                                "PREFIX my: <http://127.0.0.1/bg/ont/test1#>\n" +
                                "SELECT ?Subject ?Object\n" +
                                "WHERE\n" +
                                "{\n" +
                                "?Subject my:linked_to ?Object\n" +
                                "}"
                ), model)) {
            System.out.println("Executing SPARQL Query #3");
            startTime = System.currentTimeMillis();
            ResultSet results = qe.execSelect();
            int resultCount = ResultSetFormatter.consume(results);
            endTime = System.currentTimeMillis();
            dur = endTime - startTime;

            System.out.println("Elapsed time: " + dur + "ms, Query elements: " + resultCount + "\n");


            // Вывод данных в читаемом виде
            // ResultSetFormatter.out(System.out, results, query);
        }

        // Выполнение запроса
        try (QueryExecution qe = QueryExecutionFactory.create(
                QueryFactory.create(
                                            "PREFIX rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                                            "PREFIX rdfs: <http://www.w3.org/2000/01/rdf-schemak>\n " +
                                            "PREFIX xsd: <http://www.w3.org/2001/XMLSchema>\n" +
                                            "PREFIX my: <http://127.0.0.1/bg/ont/test1#>\n" +
                                            "SELECT ?Object WHERE {\n" +
                                            "?Object my:has_parent_id/my:has_parent_id/my:linked_to ?Core_2_Level_5_18548 .\n" +
                                            "?Option my:has_parent_id/my:has_parent_id ?Core_2_Level_5_18548 .\n" +
                                            "?Option my:has_id ?Option_id .\n" +
                                            "FILTER (?Option_id = \"Option_10\")\n" +
                                            "}\n" +
                                            "LIMIT 100"
                ), model)) {
            System.out.println("Executing SPARQL Query #4");
            startTime = System.currentTimeMillis();
            ResultSet results = qe.execSelect();
            int resultCount = ResultSetFormatter.consume(results);
            endTime = System.currentTimeMillis();
            dur = endTime - startTime;

            System.out.println("Elapsed time: " + dur + "ms, Query elements: " + resultCount + "\n");


            // Вывод данных в читаемом виде
            // ResultSetFormatter.out(System.out, results, query);
        }
    }
}


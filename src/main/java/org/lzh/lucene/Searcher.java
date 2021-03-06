package org.lzh.lucene;


import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.Term;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.apache.lucene.search.*;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import javax.management.*;
import java.io.File;
import java.io.IOException;

/**
 * Created by lizhaohui on 17-2-23.
 */
public class Searcher {
    public static void main(String[] args) throws IOException, ParseException {
        if(args.length != 3){
            System.err.println("Your command is: " + args.toString());
            throw new IllegalArgumentException("Usage: java" + Searcher.class.getName() + " <indexDir> <field> <query>");
        }
        String indexDir = args[0];
        String field = args[1];
        String query = args[2];
        search(indexDir, field, query);
    }

    public static void search(String indexDir, String field, String q) throws IOException, ParseException {
        DirectoryReader directoryReader = DirectoryReader.open(FSDirectory.open(new File(indexDir)));
        IndexSearcher is = new IndexSearcher(directoryReader);
//        QueryParser queryParser = new QueryParser(Version.LUCENE_48, field, new StandardAnalyzer(Version.LUCENE_48));
//        Query query = queryParser.parse(q);
        Query query = new TermQuery(new Term(field, q));
//        Query query = new PrefixQuery(new Term(field, q));
//        Query query = new WildcardQuery(new Term(field, q));
        long start = System.currentTimeMillis();
        TopDocs hits = is.search(query, 10);
        long end = System.currentTimeMillis();
        System.out.println("Found " + hits.totalHits + " documents (in " + (end - start) + "milliseconds) that" +
                " matched query '" + q + "'");
        for(ScoreDoc scoreDoc : hits.scoreDocs){
            Document doc = is.doc(scoreDoc.doc);
            System.out.println(doc.get("filename") + " " + doc.get("contents"));
        }

    }
}

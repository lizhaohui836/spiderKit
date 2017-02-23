package org.lzh.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.util.Version;

import java.io.*;
import java.nio.charset.StandardCharsets;

/**
 * Created by lizhaohui on 17-2-23.
 */
public class Indexer {
    public static void main(String[] args) throws Exception {
        if(args.length != 2){
            throw new IllegalArgumentException("Usage: java " + Indexer.class.getName() + " <index dir> <data dir>");
        }
        String indexDir = args[0];
        String dataDIr = args[1];
        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(indexDir);
        int numIndex;
        try {
            numIndex = indexer.index(dataDIr, new TextFilesFilter());
        } finally {
            indexer.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Indexing " + numIndex + " files took " + (end - start) + " milliseconds");
    }

    private IndexWriter writer;
    public Indexer(String indexDir) throws IOException{
        Directory directory = FSDirectory.open(new File(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, new StandardAnalyzer(Version.LUCENE_48));
        writer = new IndexWriter(directory, config);
    }

    public void close() throws IOException{
        writer.close();
    }

    public int index(String dataDir, FileFilter filter) throws Exception{
        File[] files = new File(dataDir).listFiles();
        for(File file : files){
            if(!file.isDirectory() &&
                    !file.isHidden() &&
                    file.exists() &&
                    file.canRead() &&
                    (filter == null || filter.accept(file))){
                indexFile(file);
            }
        }
        return writer.numDocs();
    }

    private static class TextFilesFilter implements FileFilter{
        public boolean accept(File path){
            return path.getName().toLowerCase().endsWith(".txt");
        }
    }

    protected Document getDocument(File file) throws Exception{
        Document doc = new Document();
        String content = "";
        StringBuffer sb = new StringBuffer();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        while(content != null){
            content = reader.readLine();
            if(content == null){
                break;
            }
            sb.append(content);
        }
        content = sb.toString();
        doc.add(new StringField("contents",
                content, Field.Store.YES));
        doc.add(new TextField("text", reader));
        doc.add(new StringField("filename", file.getName(), Field.Store.YES));
        doc.add(new StringField("path", file.getPath(), Field.Store.YES));
        return doc;
    }

    private void indexFile(File file) throws Exception{
        System.out.println("Indexing " + file.getCanonicalPath());
        Document document = getDocument(file);
        writer.addDocument(document);
    }
}

package org.lzh.lucene;

import org.apache.commons.io.FileUtils;
import org.apache.lucene.analysis.standard.StandardAnalyzer;
import org.apache.lucene.document.*;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.index.Term;
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
        String usage = "Usage: java " + Indexer.class.getName() + " <-index INDEX_PATH><-data DATA_PATH> [-update]";
        String indexPath = "index";
        String dataPath = null;
        boolean create = true;
        //参数检验与拼接
        for(int i = 0; i < args.length; i++){
            if("-index".equals(args[i])){
                indexPath = args[i+1];
            } else if("-data".equals(args[i])){
                dataPath = args[i+1];
            } else if("-update".equals(args[i])){
                create = false;
            }
        }
        //dataPath为空
        if(dataPath == null){
            throw new IllegalArgumentException(usage);
        }
        long start = System.currentTimeMillis();
        Indexer indexer = new Indexer(indexPath, create);
        int numIndex;
        try {
            numIndex = indexer.index(dataPath, new TextFilesFilter());
        } finally {
            indexer.close();
        }
        long end = System.currentTimeMillis();
        System.out.println("Indexing " + numIndex + " files took " + (end - start) + " milliseconds");
    }

    private IndexWriter writer;
    public Indexer(String indexDir, boolean create) throws IOException{
        Directory directory = FSDirectory.open(new File(indexDir));
        IndexWriterConfig config = new IndexWriterConfig(Version.LUCENE_48, new StandardAnalyzer(Version.LUCENE_48));
        //是否为新建
        if(create){
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE);
        }
        else {
            config.setOpenMode(IndexWriterConfig.OpenMode.CREATE_OR_APPEND);
        }
        writer = new IndexWriter(directory, config);
    }

    public void close() throws IOException{
        writer.close();
    }

    /**
     * 针对目录递归进行创建索引
     * @param dataDir 待创建索引的文件目录
     * @param filter 文件系统
     * @return 创建索引的数量
     * @throws Exception
     */
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

    /**
     * 创建文档
     * @param file
     * @return document对象
     * @throws Exception
     */
    protected Document getDocument(File file) throws Exception{
        Document doc = new Document();
        String content = "";
        StringBuffer sb = new StringBuffer();
        FileInputStream fis;
        try {
            fis = new FileInputStream(file);
        } catch (FileNotFoundException e){
            return null;
        }
        BufferedReader reader = new BufferedReader(new InputStreamReader(fis, StandardCharsets.UTF_8));
        while(content != null){
            content = reader.readLine();
            if(content == null){
                break;
            }
            sb.append(content);
        }
        content = sb.toString();
        System.out.println("file content: " + content);
        doc.add(new TextField("contents",
                content, Field.Store.YES));
        doc.add(new LongField("modified", file.lastModified(), Field.Store.NO));
        doc.add(new TextField("text", reader));
        doc.add(new StringField("filename", file.getName(), Field.Store.YES));
        doc.add(new StringField("path", file.getPath(), Field.Store.YES));
        return doc;
    }

    /**
     * 对文件创建索引
     * @param file
     * @throws Exception
     */
    private void indexFile(File file) throws Exception{
        System.out.println("Indexing " + file.getCanonicalPath());
        Document document = getDocument(file);
        if(document != null){
            if(writer.getConfig().getOpenMode() == IndexWriterConfig.OpenMode.CREATE){
                System.out.println("index adding " + file.getPath());
                writer.addDocument(document);
            }
            else {
                System.out.println("index updating " + file.getPath());
                writer.updateDocument(new Term("path", file.getPath()), document);
            }
        }
        else {
            System.err.println("file get document error: " + file.getPath());
        }
    }
}

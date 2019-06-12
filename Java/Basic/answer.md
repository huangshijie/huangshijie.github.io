# Answer

## 1

## 2

```java
public class DownLoadApp{

    // Create logger
    private static final Logger logger = LogManager.getLogger(DownLoadApp.class);

    // Use to define target file is changed or not during downloading
    private String eTag;

    // Total size of target file
    private long totalSize;

    // Default number of download thread is 10
    private int downloadThreadSize = 10;

    // Download path
    private String targetPath;

    // Store path
    private String storePath;

    public DownLoadApp(String targetPath, String storePath) {

        if("".equals(targetPath) || targetPath == null) throw new RuntimeException("Wrong Path Input");
        if("".equals(storePath) || storePath == null) throw new RuntimeException("Wrong Path Input");
        this.targetPath = targetPath;
        this.storePath = storePath;
        CloseableHttpClient client = null;

        // init Download app, get total size of file
        try{
            URL url = new URL(this.targetPath);
            client = HttpClient.getDefault();
            HttpGet getReq = new HttpGet(url);

            getReq.addHeader(new Header("", ""));
                    ... // add relevent header info

            HttpResponse rep = client.excute(getReq);
            // set total size of file
            this.totalSize = rep.getEntity().getContentLength();
            this.eTag = rep.getHeaders("etag");
        }catch(Exception e){
            logger.error(e.getMessage());
        }finally(){
            if(client != null){
                client.close();
            }
        }
    }

    public getFileDownload() {
        long startPoint = 0, fileSize = this.totalSize / this.downloadThreadSize;

        // Split download job
        for(int i = 0; i < this.downloadThreadSize; i++) {
            startPoint = fileSize * i;
            fileSize = this.totalSize - startPoint < fileSize ? this.totalSize - startPoint < fileSize : fileSize;
            DownloadThread faster = new DownloadThread(startPoint, this.eTag, fileSize, this.storePath, this.targetPath);
            Thread tmpThread = new tmpThread(xunlei, "Faster - " + i);

            tmpThread.start();
        }
    }

}

public class DownloadThread implements Runable{

    private long startPoint;

    private String eTag;

    private long fileSize;

    private String storePath;

    private String targetPath;

    public DownloadThread(long startPoint, String eTag, long fileSize, String storePath, String targetPath) {
        this.startPoint = startPoint;
        this.eTag = eTag;
        this.fileSize = fileSize;
        this.storePath = storePath;
        this.targetPath = targetPath;
    }

    public void run() {

        CloseableHttpClient client = null;
        InputStream in = null;
        // init Download app, get total size of file
        try{
            URL url = new URL(this.targetPath);
            CloseableHttpClient client = HttpClient.getDefault();
            HttpGet getReq = new HttpGet(url);

            getReq.addHeader(new Header("range", "bytes=" + this.startPoint + "-" + this.fileSize + ""));
                    ... // add relevent header info

            HttpResponse rep = client.excute(getReq);
            // set total size of file
            this.totalSize = rep.getEntity().getContentLength();
            String tmpETag = rep.getHeaders("etag");

            if(tmpETag == null || !tmpETag.equals("")) throw new RuntimeException("File has been changed while download");

            File file = new File(this.storePath);
            // write into file skip bytes by start point
            file.write(this.startPoint, this.fileSize, in);
        }catch(Exception e){
            logger.error(e.getMessage());
        }finally(){
            if(client != null){
                client.close();
            }

            if(in != null){
                in.close();
            }
        }
    }
}
```

## 3

## 4

1. ```sql
   CREATE TABLE USR_INFO (
       nickname varchar(255) NOT NULL,
       follow_num number(10),
       follower_num number(10),
       weibo_num number(10),
       PRIMARY KEY(`nickname`)
   );

   CREATE TABLE USR_CONTENT (
       nickname varchar(255) NOT NULL,
       content_id number(20) NOT NULL,
       post_time date,
       repost_num number(10),
       comment_num number(20),
       like_num number(10),
       PRIMARY KEY('nickname', 'content_id')
   );

   CREATE TABLE USR_CONTENT_DETAIL (
       content_id number(10) NOT NULL,
       content varchar(500),
       PRIMARY KEY('content_id')
   )
    ```

2. ```sql
   SELECT sum(like_num) FROM USR_CONTENT WHERE nickname = 'PPlabs2019'
   ```

3. ```sql
   SELECT USR_INFO.nickname,
          USR_INFO.follow_num,
          USR_INFO.follower_num
   FROM USR_INFO
   INNER JOIN (
       SELECT TOP 50 nickname
       FROM (
           SELECT nickname,
                  sum(like_num) as total_like_num
           FROM USR_CONTENT
           GOUP BY nickname
       )
       ORDER BY total_like_num
   ) TOP_TABLE
   ON USR_INT.nickname = TOP_TABLE.nickname
   ```

4. ```sql
   ALTER TABLE USR_CONTENT ADD FULLTEXT(nickname, like_num);
   ```

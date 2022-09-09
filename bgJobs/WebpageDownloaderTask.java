package bgJobs;

import Dao.BookmarkDao;
import Entities.WebLink;
import Util.HttpConnect;
import Util.IOUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class WebpageDownloaderTask implements Runnable{

    private static BookmarkDao dao  =new BookmarkDao();
    private static final long TIME_FRAME = 3000000000L;

    private boolean downloadAll = false;

    ExecutorService downloaderExecutor = Executors.newFixedThreadPool(5);

    public WebpageDownloaderTask(boolean downloadAll){
        this.downloadAll = downloadAll;
    }

    private static class Downloader<T extends WebLink> implements Callable<T>{

        private T weblink;
        public Downloader(T weblink){
            this.weblink = weblink;
        }
        @Override
        public T call() throws Exception {
            if(!weblink.getUrl().endsWith(".pdf")){
                weblink.setDownloadStatus(WebLink.DownloadStatus.FAILED);
                String page = HttpConnect.download(weblink.getUrl());
                weblink.setHtmlPage(page);
            }
            else{
                weblink.setDownloadStatus(WebLink.DownloadStatus.NOT_ELIGIBLE);
            }

            return weblink;
        }
    }
    @Override
    public void run() {

        while(!Thread.currentThread().isInterrupted()){

            List<WebLink> weblinks = getWeblinks();
            if(weblinks.size() > 0){
                download(weblinks);
            }
            else{
                System.out.println("no new weblinks to download");
            }
            try {
                TimeUnit.SECONDS.sleep(15);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        downloaderExecutor.shutdown();
    }

    public List<WebLink> getWeblinks(){
        List<WebLink> weblinks = new ArrayList<>();
        if(downloadAll){
            weblinks = dao.getAllWebLinks();
            downloadAll = false;
        }
        else{
            weblinks = dao.getWebLinks(WebLink.DownloadStatus.NOT_ATTEMPTED);
        }
        return weblinks;
    }

    public void download(List<WebLink> weblinks){
        List<Downloader<WebLink>> tasks = tasks(weblinks);
        List<Future<WebLink>> futures = new ArrayList<>();



        try {
            futures = downloaderExecutor.invokeAll(tasks,TIME_FRAME,TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        for(Future<WebLink> future:futures){
            if(!future.isCancelled()){
                try {
                    WebLink webLink = future.get();
                    String page = webLink.getHtmlPage();
                    if(page != null){
                        IOUtil.write(page,webLink.getId());
                        webLink.setDownloadStatus(WebLink.DownloadStatus.SUCCESS);
                        System.out.println("Download success "+webLink.getUrl());
                    }
                    else{
                        System.out.println("Webpage not downloaded : "+ webLink.getUrl());
                    }
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                } catch (ExecutionException e) {
                    System.out.println("\n\n task cancelled "+Thread.currentThread());
                }
            }
        }
    }

    public List<Downloader<WebLink>> tasks(List<WebLink> webLinks){
        List<Downloader<WebLink>> downloaders = new ArrayList<>();
        for(WebLink webLink:webLinks){
            downloaders.add(new Downloader<>(webLink));
        }
        return downloaders;
    }
}

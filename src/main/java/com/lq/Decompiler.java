package com.lq;

import com.lq.util.FilePathUtil;

import java.io.File;
import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class Decompiler {

  private String source;

  private String topPackageName;

  private String dest;

  private String fernFlower;

  private ExecutorService pool = Executors.newFixedThreadPool(5);

  private AtomicInteger taskToProcess = new AtomicInteger(0);

  public Decompiler(
    String source,
    String topPackageName,
    String dest,
    String fernFlower)
  {
    this.source = source;
    this.topPackageName = topPackageName;
    this.dest = dest;
    this.fernFlower = "java -jar " + fernFlower;
  }

  public void start() throws Exception
  {
    Stack<String> folderToSearch = new Stack<>();
    folderToSearch.push(topPackageName);
    while (!folderToSearch.isEmpty()) {
      String pop = folderToSearch.pop();
      File[] files = new File(FilePathUtil.add(source, pop)).listFiles();
      for (File file : files) {
        if (file.isFile()) {
          String path = FilePathUtil.add(dest, pop);
          FilePathUtil.createDir(path);
          taskToProcess.incrementAndGet();
          pool.submit(() -> {
            try {
              Process process = Runtime.getRuntime().exec("cmd.exe /c " + fernFlower + " " + file.getAbsolutePath() + " " + path);
              int status = process.waitFor();
              if (status != 0) {
                throw new RuntimeException("");
              }
            } catch (Throwable t) {
              t.printStackTrace();
              System.exit(-1);
            } finally {
              taskToProcess.decrementAndGet();
            }
          });
        } else {
          folderToSearch.push(FilePathUtil.add(pop, file.getName()));
        }
      }
    }
    new Thread(() -> {
      while (true) {
        try {
          Thread.sleep(1000);
          if (taskToProcess.get() == 0) {
            return;
          }
          System.out.println("taskToProcess : " + taskToProcess.get());
        } catch (Exception e) {
        }
      }
    }).start();
    pool.awaitTermination(1, TimeUnit.DAYS);
    System.out.println("done.");
  }
}

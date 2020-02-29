package com.lq;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@SpringBootApplication
public class Main {

  @Value("${source}")
  private String source;

  @Value("${topPackageName}")
  private String topPackageName;

  @Value("${dest}")
  private String dest;

  @Value("${fernFlowerPath}")
  private String fernFlowerPath;

  @PostConstruct
  void init() throws Exception
  {
    new Decompiler(source, topPackageName, dest, fernFlowerPath).start();
  }

  public static void main(String[] args)
  {
    SpringApplication.run(Main.class, args);
  }

}

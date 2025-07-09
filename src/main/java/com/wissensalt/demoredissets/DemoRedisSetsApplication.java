package com.wissensalt.demoredissets;

import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class DemoRedisSetsApplication {

  @Autowired
  private AccountSetRepository accountSetRepository;

  public static void main(String[] args) {
    SpringApplication.run(DemoRedisSetsApplication.class, args);
  }


  @PostMapping("/upsert")
  public boolean upsert(@RequestBody Set<String> values) {
    accountSetRepository.addAccountIds(values);

    return true;
  }

  @GetMapping("/list")
  public Set<String> index() {
    return accountSetRepository.getAllAccountIds();
  }

  @DeleteMapping("/delete")
  public boolean delete(@RequestBody Set<String> values) {
    accountSetRepository.removeAccountIds(values);

    return true;
  }

  @GetMapping("/exists")
  public boolean exists(@RequestParam("accountId") String accountId) {
    return accountSetRepository.isAccountIdExists(accountId);
  }

  @GetMapping("/size")
  public long size() {
    return accountSetRepository.size();
  }

  @GetMapping("/isEmpty")
  public boolean isEmpty() {
    return accountSetRepository.isEmpty();
  }

  @DeleteMapping("/clear")
  public boolean clear() {
    accountSetRepository.clearAccountIds();
    return true;
  }
}

package com.wissensalt.demoredissets;

import java.util.Set;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.support.collections.DefaultRedisSet;
import org.springframework.data.redis.support.collections.RedisSet;
import org.springframework.stereotype.Repository;

@Repository
public class AccountSetRepository {

  private final RedisSet<String> accountIdSet;

  public AccountSetRepository(RedisTemplate<String, String> redisTemplate) {
    RedisOperations<String, String> ops = redisTemplate.opsForValue().getOperations();
    this.accountIdSet = new DefaultRedisSet<>("accountIds:set", ops);
  }

  public void addAccountIds(Set<String> accountIds) {
    accountIdSet.addAll(accountIds);
  }

  public void removeAccountIds(Set<String> accountIds) {
    accountIdSet.removeAll(accountIds);
  }

  public boolean isAccountIdExists(String accountId) {
    return accountIdSet.contains(accountId);
  }

  public Set<String> getAllAccountIds() {

    return accountIdSet;
  }

  public void clearAccountIds() {
    accountIdSet.clear();
  }

  public long size() {
    return accountIdSet.size();
  }

  public boolean isEmpty() {
    return accountIdSet.isEmpty();
  }
}

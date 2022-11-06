package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        boolean result = false;
        if (getById(account.id()).isEmpty()) {
            accounts.put(account.id(), account);
            result = true;
        } else {
            throw new IllegalStateException("Not found account by id = " + account.id());
        }
        return result;
    }

    public synchronized boolean update(Account account) {
        boolean result = false;
        if (!getById(account.id()).isEmpty()) {
            accounts.put(account.id(), account);
            result = true;
        }
        return result;
    }

    public synchronized boolean delete(int id) {
        boolean result = false;
        if (!getById(id).isEmpty()) {
            accounts.remove(id);
            result = true;
        }
        return result;
    }

    public synchronized Optional<Account> getById(int id) {
        Account account = accounts.get(id);
        return account != null ? Optional.of(account) : Optional.empty();
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        Account accountFrom = getById(fromId).orElseThrow();
        Account accountTo = getById(toId).orElseThrow();
        if (accountFrom.amount() >= amount) {
            accountFrom = new Account(fromId, accountFrom.amount() - amount);
            accountTo = new Account(toId, accountTo.amount() + amount);
            accounts.put(fromId, accountFrom);
            accounts.put(toId, accountTo);
            result = true;
        }
        return result;
    }
}


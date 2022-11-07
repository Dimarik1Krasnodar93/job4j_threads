package ru.job4j.cash;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;
import java.util.NoSuchElementException;
import java.util.Optional;

@ThreadSafe
public class AccountStorage {
    @GuardedBy("this")
    private final HashMap<Integer, Account> accounts = new HashMap<>();

    public synchronized boolean add(Account account) {
        return accounts.putIfAbsent(account.id(), account) == null;
    }

    public synchronized boolean update(Account account) {
        return accounts.replace(account.id(), account) != null;
    }

    public synchronized boolean delete(int id) {
        return accounts.remove(id) != null;
    }

    public synchronized Optional<Account> getById(int id) {
        return Optional.ofNullable(accounts.get(id));
    }

    public synchronized boolean transfer(int fromId, int toId, int amount) {
        boolean result = false;
        Account accountFrom = getById(fromId).orElseThrow(() -> new NoSuchElementException("no such account from"));
        Account accountTo = getById(toId).orElseThrow(() -> new NoSuchElementException("no such account to"));
        if (accountFrom.amount() >= amount) {
            accounts.put(fromId, new Account(fromId, accountFrom.amount() - amount));
            accounts.put(toId, new Account(toId, accountTo.amount() + amount));
            result = true;
        }
        return result;
    }
}
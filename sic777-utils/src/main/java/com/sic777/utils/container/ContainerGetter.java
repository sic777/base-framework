package com.sic777.utils.container;


import com.sic777.utils.container.tuple.FiveTupleList;
import com.sic777.utils.container.tuple.SixTupleList;
import com.sic777.utils.container.tuple.ThreeTupleList;
import com.sic777.utils.container.tuple.TwoTupleList;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * <p>容器获取工具类</p>
 *
 * @author Zhengzhenxie
 * @version v1.0
 * @since 2018-02-11 10:58
 */
public class ContainerGetter {
    public static <K, V> HashMap<K, V> hashMap() {
        return new HashMap<K, V>();
    }

    public static <K, V> HashMap<K, V> hashMap(int capacity) {
        return new HashMap<K, V>(capacity);
    }

    public static <K extends Enum<K>, V> EnumMap<K, V> enumMap(Class<K> type) {
        return new EnumMap<K, V>(type);
    }

    public static <K, V> ConcurrentHashMap<K, V> concurHashMap() {
        return new ConcurrentHashMap<K, V>();
    }

    public static <K, V> TreeMap<K, V> treeMap() {
        return new TreeMap<>();
    }

    public static <K, V> TreeMap<K, V> treeMap(Comparator<K> comparator) {
        return new TreeMap<>(comparator);
    }

    public static <K, V> ConcurrentHashMap<K, V> concurHashMap(int capacity) {
        return new ConcurrentHashMap<>(capacity);
    }

    public static <T> ArrayList<T> arrayList(Collection<T> list) {
        return new ArrayList<T>(list);
    }

    public static <T> LinkedHashSet<T> linkedHashSet() {
        return new LinkedHashSet<>();
    }

    public static <K, V> LinkedHashMap<K, V> linkedHashMap() {
        return new LinkedHashMap<>();
    }

    public static <T> LinkedHashSet<T> linkedHashSet(Collection<T> set) {
        return new LinkedHashSet<>(set);
    }

    public static <T> HashSet<T> hashSet(Collection<T> set) {
        return new HashSet<T>(set);
    }

    public static <T> CopyOnWriteArraySet<T> copyOnWriteArraySet() {
        return new CopyOnWriteArraySet<T>();
    }

    public static <T> ArrayList<T> arrayList() {
        return new ArrayList<T>();
    }

    public static <T> ArrayList<T> arrayList(int capacity) {
        return new ArrayList<T>(capacity);
    }

    public static <T> LinkedList<T> linkedList() {
        return new LinkedList<T>();
    }

    public static <T> LinkedList<T> linkedList(Collection<T> collection) {
        return new LinkedList<T>(collection);
    }

    public static <T> HashSet<T> hashSet(int capacity) {
        return new HashSet<T>(capacity);
    }

    public static <T> HashSet<T> hashSet() {
        return new HashSet<T>();
    }

    public static <T> TreeSet<T> treeSet() {
        return new TreeSet<T>();
    }

    public static <T> TreeSet<T> treeSet(Comparator<T> comparator) {
        return new TreeSet<>(comparator);
    }

    public static <T> ConcurrentLinkedQueue<T> concurLinkedQueue() {
        return new ConcurrentLinkedQueue<T>();
    }

    public static <T> PriorityQueue<T> priorityQueue(Comparator<T> comparator) {
        return new PriorityQueue<T>(11, comparator);
    }

    public static <T> PriorityQueue<T> priorityQueue() {
        return new PriorityQueue<>();
    }

    public static <T> CopyOnWriteArrayList<T> copyOnWriteArrayList() {
        return new CopyOnWriteArrayList<T>();
    }

    public static <T> CopyOnWriteArrayList<T> copyOnWriteArrayList(Collection<T> coll) {
        return new CopyOnWriteArrayList<T>(coll);
    }

    public static <A, B, C> ThreeTupleList<A, B, C> threeTupleList() {
        return new ThreeTupleList<A, B, C>();
    }

    public static <A, B, C, D, E> FiveTupleList<A, B, C, D, E> fiveTupleList() {
        return new FiveTupleList<A, B, C, D, E>();
    }

    public static <A, B, C, D, E, F> SixTupleList<A, B, C, D, E, F> sixTupleList() {
        return new SixTupleList<A, B, C, D, E, F>();
    }

    public static <A, B> TwoTupleList<A, B> twoTupleList() {
        return new TwoTupleList<A, B>();
    }

    public static <T> Set<T> treeSet(Collection<T> coll) {
        TreeSet<T> treeSet = new TreeSet<>(coll);
        return treeSet;
    }

}
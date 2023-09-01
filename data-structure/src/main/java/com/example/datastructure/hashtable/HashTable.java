package com.example.datastructure.hashtable;

import java.util.Collection;

public interface HashTable<K, V> {

    /**
     * 해시 테이블에 엔트리를 key에 매칭되도록 삽입
     * @param key
     * @param value
     */
    void put(K key, V value);

    /**
     * 해시 테이블에서 KEY 에 매칭되는 엔트리 삭제
     * @param key
     */
    void remove(K key);

    /**
     * 해시 테이블에서 key에 매칭되는 엔트리 조회/검색
     * @param key
     * @return entry
     */
    V get(K key);

    /**
     * 해시 테이블에 요소 존재 유무 반환
     * @return 요소가 없는 경우 true
     */
    boolean isEmpty();

    /**
     * 해시 테이블에 있는 모든 키의 목록을 반환
     * @return key collection
     */
    Collection<K> keys();

    /**
     * 해시 테이블에 있는 모든 값을 반환
     * @return value collection
     */
    Collection<V> values();
}

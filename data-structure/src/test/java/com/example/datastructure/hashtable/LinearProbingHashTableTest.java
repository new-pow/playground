package com.example.datastructure.hashtable;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class LinearProbingHashTableTest {

    @BeforeEach
    void init () {

    }

    @Test
    @DisplayName("생성자 테스트")
    public void constructor_get() throws Exception{
        HashTable<Integer, String> hashTable = new LinearProbingHashTable<>(10);
        hashTable.put(1, "1번");
        assertThat(hashTable.get(1)).isEqualTo("1번");
    }

    @Test
    @DisplayName("삭제 테스트")
    public void remove() throws Exception{
        HashTable<Integer, String> hashTable = new LinearProbingHashTable<>(10);
        hashTable.put(1, "1번");
        hashTable.remove(1);
        assertThat(hashTable.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("resize 테스트")
    public void resize() throws Exception{
        HashTable<Integer, String> hashTable = new LinearProbingHashTable<>(1);
        hashTable.put(1, "1번");
        hashTable.put(2, "2번");
        hashTable.put(3, "3번");
        hashTable.put(4, "4번");
        hashTable.put(5, "5번");
        hashTable.put(6, "6번");
        hashTable.put(7, "7번");
        assertThat(hashTable.values()).hasSizeGreaterThan(3);
        assertThat(hashTable.get(5)).isEqualTo("5번");
    }
}

package com.example.datastructure.linkedlist;

public interface LinkedList<T> {

    /**
     * 리스트에 아무 데이터가 없는지 확인합니다.
     * @return 아무 데이터가 없으면 true
     */
    boolean isEmpty();

    /**
     * 리스트에 저장된 데이터 크기를 반환합니다.
     * @return 데이터 수
     */
    int size();

    /**
     * 해당 데이터의 저장 index를 반환합니다.
     * @param data
     * @return 해당 데이터의 저장된 위치 Index
     */
    int indexOf(T data);

    /**
     * 특정 index에 있는 data를 반환합니다.
     * @param index
     * @return index에 있는 data
     */
    T get(int index);

    /**
     * 리스트 마지막 위치에 data를 추가합니다.
     * @param data
     */
    void add(T data);

    /**
     * 리스트의 특정 index 위치에 data를 추가합니다.
     * @param index
     * @param data
     */
    void add(int index, T data);

    /**
     * 리스트의 마지막에 있는 data를 삭제합니다.
     */
    void remove();

    /**
     * 리스트의 특정 index위치에 있는 data를 삭제합니다.
     * @param index
     */
    void remove(int index);
}

package com.example.javalib;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

import sun.net.util.IPAddressUtil;

public class MyClass {
    public static void main(String[] args){
        System.out.println("Hello");
/*        Queue<Student> sq = new PriorityQueue<>(Comparator.comparing(o -> o.name));
        Queue<Student> sq = new PriorityQueue<>((o1, o2) -> o1.name.compareTo(o2.name));
        Queue<Student> sq = new PriorityQueue<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return 0;
            }
        });*/
        //Pair<Integer, Integer> pair = new Pair<>(1,1);

        Queue<Student> sq = new PriorityQueue<>(
                ((Comparator<Student>) (o1, o2) -> Integer.compare(o2.roll, o1.roll))
                        .thenComparing(o -> o.name)
        );
        sq.add(new Student(1,"a","f"));
        sq.add(new Student(4,"ab","g"));
        sq.add(new Student(13,"cde","a"));
        sq.add(new Student(15,"gdeea","b"));
        sq.add(new Student(4,"adfbb","a"));
        sq.add(new Student(2,"cdgtda","c"));
        sq.add(new Student(6,"dfa","d"));
        for (Student s : sq) {
            System.out.println("name="+s.name +" Grade="+s.grade+" Roll="+s.roll);
        }
        while (!sq.isEmpty()){
            Student s = sq.poll();
            System.out.println("Roll="+s.roll +" name="+s.name +" grade="+ s.grade);
        }
        PriorityQueue<String> q = new PriorityQueue<>(Collections.reverseOrder());
        q.add("d");
        q.add("a");
        q.add("f");
        System.out.println(q.add("a"));
        System.out.println(q.add("b"));
        System.out.println(q.offer("c"));
        System.out.println(q.contains("a"));
        System.out.println(q.poll());
        System.out.println(q.peek());
       // q.clear();
        Object[] a = q.toArray();
        for (Object string :a) {
            System.out.println(string);
        }
        int[] count = new int[100];
        String s = "abcdefghijklmnopqrstuvwxyzAZ";
        System.out.println(s.indexOf("fgi"));

        String ips = "222.111.111.111";
        try {
           int j= Integer.parseInt(ips);
        }catch (NumberFormatException e){
            System.out.println(e);
        }


        int ii = 123;
        String.valueOf(ii);
        char[] charArray = ips.toCharArray();

        String[] ip = ips.split("\\.");
        ip[0].startsWith("0");
        System.out.println();
        int i = s.charAt(2);
        for (char c : s.toCharArray()){
            int j = c-'A';
            System.out.println(j);
        }

       // int i1=0,m=1;
        //int min1 = i1==m ? Integer.MAX_VALUE : a[i1];
        LinkedHashMap<Integer,Integer> map = new LinkedHashMap<>();
        map.put(1,2);
        map.put(2,2);
        map.put(3,2);
        map.put(4,2);
        map.put(2,2);
        map.remove(1);
        map.put(1,2);
        System.out.println(map.keySet().iterator().next());
        map.forEach((key1, value) -> System.out.println(" " + key1));

        boolean[] list = new boolean[5];
        List<Boolean> boolList = new ArrayList(5);
        for (boolean b :
                boolList) {
            System.out.println(b);
        }
    }
}

class Student{
    int roll;
    String name;
    String grade;
    Student(int roll,String name, String grade){
        this.name = name;
        this.roll = roll;
        this.grade = grade;
    }
}

class Test {

    public static class MinHeap{
        int[] arr;
        int size;
        int capacity;

        MinHeap(int c){
            size = 0;
            capacity = c;
            arr = new int[c];
        }

        int left(int i) { return (2*i + 1); }
        int right(int i) { return (2*i + 2); }
        int parent(int i) { return (i-1)/2; }


        public void insert(int x)
        {
            if (size == capacity)return;
            size++;
            arr[size-1]=x;

            for (int i=size-1;i!=0 && arr[parent(i)]>arr[i];)
            {
                int temp = arr[i];
                arr[i] = arr[parent(i)];
                arr[parent(i)] = temp;
                i = parent(i);
            }
        }

        public void minHeapify(int i)
        {
            int lt = left(i);
            int rt = right(i);
            int smallest = i;
            if (lt < size && arr[lt] < arr[i])
                smallest = lt;
            if (rt < size && arr[rt] < arr[smallest])
                smallest = rt;
            if (smallest != i)
            {
                int temp = arr[i];
                arr[i] = arr[smallest];
                arr[smallest] = temp;
                minHeapify(smallest);
            }
        }

        public int extractMin()
        {
            if (size <= 0)
                return Integer.MAX_VALUE;
            if (size == 1)
            {
                size--;
                return arr[0];
            }
            int temp = arr[0];
            arr[0] = arr[size-1];
            arr[size-1] = temp;
            size--;
            minHeapify(0);

            return arr[size];
        }

        void decreaseKey(int i, int x)
        {
            arr[i] = x;
            while (i != 0 && arr[parent(i)] > arr[i])
            {
                int temp = arr[i];
                arr[i] = arr[parent(i)];
                arr[parent(i)] = temp;
                i = parent(i);
            }
        }

        void deleteKey(int i)
        {
            decreaseKey(i, Integer.MIN_VALUE);
            extractMin();
        }

    }
    public static void main(String args[])
    {
        MinHeap h=new MinHeap(11);
        h.insert(3);
        h.insert(2);
        h.deleteKey(0);
        h.insert(15);
        h.insert(20);
        System.out.println(h.extractMin());
        h.decreaseKey(2, 1);
        System.out.println(h.extractMin());
    }

}
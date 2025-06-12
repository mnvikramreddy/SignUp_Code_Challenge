package com.example.codechallenge

import android.os.Build
import androidx.annotation.RequiresApi
import java.util.*
import kotlin.collections.ArrayList
import kotlin.random.Random

@RequiresApi(Build.VERSION_CODES.N)
fun main() {
    println("Hello, World!")
    val store = Store<Int,String>()
    store.setData(1,"one")
    store.setData(3,"two")
    store.setData(7,"three")
    store.setData(2,"four")
    store.setData(5,"five")
    store.setData(50,"fifty")
    println(store.map.toSortedMap { o1, o2 -> o1.compareTo(o2) })
    println("sortMap = ${store.map.toSortedMap()}")
    println("map = ${store.map}")
    println("List = ${store.map.toList()}")
    println("deleted = ${store.remove(3)}")
    println("data = ${store.getData(3) }")
    println("random = ${store.random()}")
    println("random = ${store.random()}")
    println("random = ${store.random()}")

    val queue = LinkedList<Int>()
    queue.add(1)
    queue.poll()
    val priority = PriorityCache<PriorityObject>(3)

  /*  priority.addItemToCache(1)
    priority.addItemToCache(20)
    priority.addItemToCache(5)
    priority.addItemToCache(8)
    priority.addItemToCache(7)
    priority.addItemToCache(6)
    priority.addItemToCache(30)*/

    priority.addItemToCache(PriorityObject("first",1))
    priority.addItemToCache(PriorityObject("Twenty",20))
    priority.addItemToCache(PriorityObject("Five",5))
    priority.addItemToCache(PriorityObject("Eight",8))
    priority.addItemToCache(PriorityObject("Seven",7))
    priority.addItemToCache(PriorityObject("Six",6))
    priority.addItemToCache(PriorityObject("Thirty",30))

    println(priority.priorityQueue.toList())
    val priority1 = PriorityCache<PriorityObject1>(3)
    priority1.addItemToCache(PriorityObject1("ten",10))
    priority1.addItemToCache(PriorityObject1("Twenty",20))
    priority1.addItemToCache(PriorityObject1("Fifty",50))
    priority1.addItemToCache(PriorityObject1("Eight",8))
    priority1.addItemToCache(PriorityObject1("Seven",7))
    priority1.addItemToCache(PriorityObject1("Sixty",60))
    priority1.addItemToCache(PriorityObject1("Thirty",30))

//    with(priority1){
//        addItemToCache(PriorityObject1("Thirty",30))
//        addItemToCache()
//    }
    println(priority1.priorityQueue.toList())

    /*        Queue<Student> sq = new PriorityQueue<>(Comparator.comparing(o -> o.name));
        Queue<Student> sq = new PriorityQueue<>((o1, o2) -> o1.name.compareTo(o2.name));
        Queue<Student> sq = new PriorityQueue<>(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return 0;
            }
        });*/
    //Pair<Integer, Integer> pair = new Pair<>(1,1);
    val sq: Queue<Student> = PriorityQueue(
        Comparator { o1: Student, o2: Student ->
                    o2.roll.compareTo(o1.roll)
                }
            .thenComparing { o: Student -> o.name }
    )
    val a = intArrayOf(1,3,3,3)
    val b = IntArray(10){it*2}
    for (item in b.indices)
        println(item)
    //[[1,3],[2,4]]
val entry = intArrayOf(1,2)
    val exit = intArrayOf(3,4)
    val result = IntArray(5)

    for(i in entry.indices) {
        result[entry[i]]++
        //result[exit[i]]++
        if(exit[i]+1<result.size)
        result[exit[i]+1]--
    }
    for (i in 1 until  result.size){
        result[i] = result[i]+result[i-1]
        print(" index ${result[i]}")
    }
    for (i in result)
        print(i)
    println(" ")
    val s = "addiccc"
    for(i in s.indices){
        println(s[i].code)
    }
    s.toCharArray()
    println(" ")
    for(i in createLps("aaabaaacc")){
        print(i)
    }
    val list = arrayListOf<Int>()

    sq.add(Student(1, "a", "f"))
    sq.add(Student(4, "ab", "g"))
    sq.add(Student(13, "cde", "a"))
    sq.add(Student(15, "gdeea", "b"))
    sq.add(Student(4, "adfbb", "a"))
    sq.add(Student(2, "cdgtda", "c"))
    sq.add(Student(6, "dfa", "d"))
    for (s in sq) {
        println("name=" + s.name + " Grade=" + s.grade + " Roll=" + s.roll +" " +b[4])
    }
    while (!sq.isEmpty()) {
        val s: Student = sq.poll()
        println("Roll=" + s.roll + " name=" + s.name + " grade=" + s.grade)
    }
}

class Store<K,V> {

    internal val map:HashMap<K,V> = HashMap()
    private val list:ArrayList<K> by lazy { ArrayList() }

    fun setData(key:K,value:V){
        map[key] = value
        list.add(key)
    }

    fun getData(key: K):V?{
       return map[key]
    }

    fun remove(key: K):Pair<K,V?>{
        if (list.contains(key))
            list.remove(key)
        return Pair(key,map.remove(key))
    }

    fun random(): Pair<K,V?>{
       val key = list[Random.nextInt(list.size)]
        return Pair(key,map[key])
    }
}

open class PriorityBase(
    open val priority:Int
)


data class PriorityObject(
    val name:String, override val priority: Int,
): PriorityBase(priority)

data class PriorityObject1(
    val name:String, override val priority: Int,
): PriorityBase(priority)

class PriorityCache<E: PriorityBase>(private val capacity: Int){

     //@RequiresApi(Build.VERSION_CODES.N)
     val priorityQueue = PriorityQueue(capacity,Comparator<PriorityBase>{
         p1,p2 ->
         when {
             p1.priority>p2.priority -> -1
             p1.priority<p2.priority -> 1
             else -> 0
         }
     })

    fun addItemToCache(item:E){
        if(priorityQueue.size>capacity)
            priorityQueue.poll()
        priorityQueue.add(item)
    }

    fun getNextItemFromCache(): PriorityBase? {
        return priorityQueue.peek()
    }

/*class Comparator: java.util.Comparator<com.example.codechallenge.PriorityObject>{
    override fun compare(o1: com.example.codechallenge.PriorityObject, o2: com.example.codechallenge.PriorityObject): Int {
        return o1.priority.compareTo(o2.priority)
    }
}*/
}

data class Student(val roll: Int, val name: String, val grade: String)

fun createLps(string: String):IntArray{
    val n = string.length
    val lps = IntArray(n)
    var len = 0
    var i=1
    while (i<n){
        if(string[i]==string[len]){
            len++
            lps[i]=len
            i++
        }else{
            if (len ==0) {
                lps[i] = 0
                i++
            }else{
                len = lps[len-1]
            }
        }
    }
return lps
}
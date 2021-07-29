package algorithmLean;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @Description: 比较器、优先级队列、二叉树
 * @Author: haole
 * @Date: 2021/7/28 9:11
 */
public class Code06_ShowComparator {
    public static class Student {
        public String name;
        public int id;
        public int age;

        public Student(String name, int id, int age) {
            this.name = name;
            this.id = id;
            this.age = age;
        }
    }

    public static class IdComparator implements Comparator<Student> {

        //如果返回负数，认为第一个参数应该排在前面
        //如果返回正数，认为第二个参数应该排在前面
        //如果返回0，认为谁排在前面无所谓
        @Override
        public int compare(Student o1, Student o2) {
            if (o1.age < o2.age) {
                return -1;
            } else if (o1.age > o2.age) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void printArray(int[] arr) {
        for (int i = 0; i < arr.length; i++) {
            System.out.print(arr[i] + " ");
        }
        System.out.println();
    }

    public static void printStudents(Student[] students) {
        for (int i = 0; i < students.length; i++) {
            System.out.println(students[i].name + "," + students[i].age + "," + students[i].id + ",");
        }
        System.out.println();
    }

    public static void main(String[] args) {

        Student s1 = new Student("张三", 5, 27);
        Student s2 = new Student("李四", 1, 17);
        Student s3 = new Student("王五", 4, 29);
        Student s4 = new Student("赵六", 3, 14);
        Student s5 = new Student("钱八", 2, 35);
        Student[] students = {s1, s2, s3, s4, s5};
        printStudents(students);
        Arrays.sort(students, new IdComparator());
        printStudents(students);

        System.out.println("--------------------");

        ArrayList<Student> arrayList = new ArrayList<>();
        arrayList.add(s1);
        arrayList.add(s2);
        arrayList.add(s3);
        arrayList.add(s4);
        arrayList.add(s5);

        arrayList.sort(new IdComparator());

        for (Student s : students) {
            System.out.println(s.name + "," + s.age + "," + s.id + ",");
        }

        System.out.println("--------------------");
        PriorityQueue<Student> heap = new PriorityQueue<>(new IdComparator());
        heap.add(s1);
        heap.add(s2);
        heap.add(s3);
        heap.add(s4);
        heap.add(s5);
        while (!heap.isEmpty()) {
            Student s = heap.poll();
            System.out.println(s.name + "," + s.age + "," + s.id + ",");
        }
    }
}

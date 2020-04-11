import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static Connection connection;
    private static Statement stmt;
    private static PreparedStatement pstmt;

    private int size;
    int[] array;

    public Main(int size) {
        this.size = 0;
        this.array = new int[size];
    }

    public int Rnd(int bound) {
        Random rnd = new Random();
        return rnd.nextInt(bound);
    }

    public void create(int number) {
        array[this.size] = number;
        this.size++;
    }

    public void delete(int number){
        if(find(number) != -1) {
            for (int i = find(number); i < this.size - 1; i++) {
                this.array[i] = this.array[i + 1];
            }
            this.size--;
            for (int i = 0; i < this.size; i++) {
                if (this.array[i] == number) delete(number);
            }
        }
    }
    public int find(int findNumber) {
        int i;
        for (i = 0; i < this.size; i++) {
            if (this.array[i] == findNumber) return i;
        }
        return -1;
    }

    public void info() {
        for (int i = 0; i < this.size; i++) {
            System.out.print(this.array[i] + " ");
        }
        System.out.println();
        System.out.println("Количество элементов: " + this.size);
        System.out.println("Удалено: " + (array.length - this.size));
    }


    public static void main(String[] args) throws SQLException {

        new Thread(null, new Runnable() {
            public void run() {
                long time = System.currentTimeMillis();
                Main obj = new Main(50);
                for (int i = 0; i < obj.array.length; i++) {
                    obj.create(obj.Rnd(10));
                }
                obj.info();
                obj.delete(9);
                obj.info();
                System.out.println("Время выполнения = " + (System.currentTimeMillis() - time) + " мс.");
            }
        }, "1", 1 << 23).start();
    }
    public static void update(String id, String newValue) throws SQLException {
        String sql = String.format("UPDATE students SET score = %s where id = %s", newValue, id);
        stmt.executeUpdate(sql);
    }
}

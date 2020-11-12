package com.huang.Stream;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Stream;

/**
 * @Author : I325805
 * @Description:
 */
public class ByteArrayDemo {
    private String s;
    public void ByteArrayDemovvv() {
    }
    class innerClass{
        public String aa;
        public void m(){
            s="s";
        }
    }

    @Override
    public String toString() {
        return "demoe";
    }
    void display(){
        System.out.println("data="+data);
    }

    int data;

    public static void main(String[] args) throws ParseException {
        byte[] c1 = {10, 20, 30, 40, 50};
        byte[] c2 = {60, 70, 80, 90};
        ByteArrayOutputStream b1 = new ByteArrayOutputStream();
        ByteArrayOutputStream b2 = new ByteArrayOutputStream(10);

        b2.write(100);
        System.out.println("Out 1 : " + b2.size());
        b2.write(c1, 0, c2.length);
        System.out.println(b2.size());

        System.out.println(Stream.of("green", "yellow", "blue")
                .max((s1, s2)-> s1.compareTo(s2)).filter(s->s.endsWith("n")).orElse("yellow"));

        int a = 9, b =2;
        float f;
        f=a/b;
        System.out.println(f);
        f=f/2;
        System.out.println(f);
        f=a+b/f;
        System.out.println(f);
        List<String> st = new ArrayList<>();
        st.add("1");
        st.add("2");
        st.add(0, "3");
        st.add(1, "4");
        st.sort(String::compareTo);
        st.forEach(System.out::println);

        Optional<ByteArrayDemo> p = Optional.of(new ByteArrayDemo());
        System.out.println(p.get().toString());

        Long l = new Long(1234);
        Long l1 = l;
        System.out.println(l1==l);
        l++;
        System.out.println(l==l1);

        Date adate = new SimpleDateFormat("yyyy-mm-dd").parse("2012-01-15");
        Calendar ca = Calendar.getInstance();
        ca.setTime(adate);
        System.out.println(ca.get(ca.MONTH));
        DateTimeFormatter fo = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate d = LocalDate.parse("2012-01-15", fo);
        System.out.println(d.getDayOfMonth()+","+d.getMonthValue());

        String strA = "A";
        String strB = "B";
        String strNull = null;
        StringBuilder bufferc = new StringBuilder("c");
        Formatter fmt = new Formatter(bufferc);
        fmt.format("%s%s",strA, strB);
        System.out.println(fmt);
        fmt.format("%-2s", strB);
        System.out.println(fmt);
        fmt.format("%b", strNull);
        System.out.println(fmt);

        Set<String> ste = new LinkedHashSet<>();
        ste.add("3");
        ste.add("1");
        ste.add("3");
        ste.add("2");
        ste.forEach(s-> System.out.print(s+"-"));

        Integer x = 3;
        Integer y = null;
        System.out.println(Integer.compareUnsigned(x,3)==0||Integer.compareUnsigned(y, 0)==0);
        System.out.println(y.compareTo(null)==0||true);
        Object demo = new ByteArrayDemo();
    }

    public void meth(String[] args) {
        System.out.println(args);
        System.out.println(args[1]);
        Runtime.getRuntime().maxMemory();
    }
}

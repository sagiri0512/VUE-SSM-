package com.sagiri.pojo;

public class PFailed {
    private String name;
    private long sum;

    public PFailed(String name, long sum) {
        this.name = name;
        this.sum = sum;
    }

    public PFailed() {
    }
    public String getName() {
        return name;
    }
    public long getSum() {
        return sum;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setSum(long sum) {
        this.sum = sum;
    }
}

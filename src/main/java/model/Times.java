package model;

/**
 * 下面将使用此类实现一个多考生考试的场景：

 1.考试总时间为10秒，至少2秒后才可进行交卷。
 2.考生可在2-10秒这段时间内的任意时间交卷。
 3.考试时间一到，所有未交卷的学生必须交卷。
 */
public enum Times {

    SUBMIT_TIME(10), SUMBMIT_LIMIT(2), MAX_RAND_TIME(15);
    private final int value;

    private Times(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}

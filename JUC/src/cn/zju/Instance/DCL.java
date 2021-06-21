package cn.zju.Instance;


import java.util.*;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

public class DCL {
    private DCL() {
    }
    private volatile DCL singleton;
    public DCL getInstance() {
        if (singleton == null) {
            synchronized(DCL.class) {
                if (singleton == null) {
                    singleton = new DCL();
                }
            }
        }
        return singleton;
    }
}

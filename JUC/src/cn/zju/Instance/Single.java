package cn.zju.Instance;



public class Single {
    private Single() {

    }
    private enum SingletonEnum {
        INSTANCE;
        private Single single;
        SingletonEnum() {
            single = new Single();
        }
        public Single getSingle() {
            return single;
        }
    }
    public Single getInstance() {
        return SingletonEnum.INSTANCE.getSingle();

    }
}
